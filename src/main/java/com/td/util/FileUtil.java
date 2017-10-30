package com.td.util;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 文件工具类
 * 
 * @author 米雪铭
 *
 */
@Service
public class FileUtil {

	/**
	 * 从webapp到存储文件夹的路径
	 */
	private final static String PROJECTPATH = "img";
	/**
	 * 文件本地路径
	 */
	@Value("${file.localPath}")
	private String localPath;
	/**
	 * 获取ip
	 */
	@Value("${file.ip}")
	private String ip;
	/**
	 * 获取端口号
	 */
	@Value("${server.port}")
	private String port;

	/**
	 * @Title: upFile
	 * @Description: 上传多个文件
	 * @author 米雪铭
	 * @param request
	 * @param folder
	 * @return
	 */
	public List<String> upFile(HttpServletRequest request, String folder) {
		List<String> urlList = new ArrayList<>();
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
				String key = it.next();
				MultipartFile file = multipartRequest.getFile(key);
				urlList.add(upFile(file, folder));
			}
		}
		LogUtil.debug("上传文件：" + urlList.toString());
		return urlList;
	}

	/**
	 * @Title: upFile
	 * @Description: 上传单个文件
	 * @author 米雪铭
	 * @param file
	 * @param folder
	 * @return
	 */
	public String upFile(MultipartFile file, String folder) {
		// 拿到上传的文件名
		StringBuilder fileName = new StringBuilder(UUIDUtil.getUUID());
		// 拿到后缀名
		String suffix = file.getContentType();
		int suffixNum = suffix.indexOf("/");
		if (suffixNum > 0) {
			suffix = suffix.substring(suffixNum + 1);
		}
		fileName.append(".");
		fileName.append(suffix);
		// 按路径生成文件夹
		File fileFolder = new File(localPath + folder);
		if (!fileFolder.exists()) {
			fileFolder.mkdirs();
		}
		fileFolder = new File(fileFolder.getPath(), fileName.toString());
		try {
			// 文件上传
			file.transferTo(fileFolder);
		} catch (Exception e) {
			LogUtil.warn(e.getMessage());
		}
		// 服务器路径
		StringBuilder path = new StringBuilder();
		path.append("http://");
		path.append(StringUtil.isNull(ip) ? getHostIp() : ip);
		path.append(":");
		path.append(port);
		path.append("/");
		path.append(PROJECTPATH);
		// 文件夹名称
		path.append(folder);
		path.append("/");
		// 文件名称
		path.append(fileName);
		LogUtil.debug("上传单个文件：" + path.toString());
		return path.toString();
	}

	/**
	 * @Title: getLocalPath
	 * @Description: 根据系统类型返回文件夹路径
	 * @author 米雪铭
	 * @return
	 */
	// public String getLocalPath() {
	// String reStr = null;
	// String osname = getSystemOSName();
	// try {
	// // 针对window系统
	// if (osname.indexOf("Windows") >= 0)
	// reStr = "D:/TDMallIM/picture/";
	// // reStr = "C:/TDMallIM/picture/";
	// // 针对linux系统
	// else if (osname.indexOf("Linux") >= 0)
	// reStr = "/home/TDMallIM/picture/";
	// } catch (Exception e) {
	// LogUtil.error(e.getMessage());
	// }
	// return reStr;
	// }

	/**
	 * 获取操作系统
	 * 
	 * @return
	 */
	public static String getSystemOSName() {
		// 获得系统属性集
		Properties props = System.getProperties();
		// 操作系统名称
		String osname = props.getProperty("os.name");
		return osname;
	}

	/**
	 * @Title: getHostIp
	 * @Description: 获取网络IP地址，排除虚拟接口和未启用接口
	 * @author 米雪铭
	 * @return
	 */
	public static String getHostIp() {
		try {
			for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces
					.hasMoreElements();) {
				NetworkInterface networkInterface = interfaces.nextElement();
				if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
					continue;
				}
				Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
				if (addresses.hasMoreElements()) {
					return addresses.nextElement().getHostAddress();
				}
			}
		} catch (Exception e) {
		}
		return null;
	}

}
