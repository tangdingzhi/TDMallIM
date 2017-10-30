package com.td.indexInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName: WebMvcConfigurer
 * @Description: 配置拦截器
 * @author 米雪铭
 * @date: 2017年10月19日 下午3:15:00
 */

@SuppressWarnings("deprecation")
@Component
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Autowired
	AuthInterceptor authInterceptor;

	@Value("${file.localPath}")
	private String localPath;

	private String imgFolder = "/img/**";

	/**
	 * vue需要将页面全部跳转到index
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor).addPathPatterns("/manage/**").excludePathPatterns(imgFolder,
				"/manage/upimg/**", "/manage/login/**");
		super.addInterceptors(registry);
	}

	/**
	 * 映射静态资源
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(imgFolder).addResourceLocations("file:" + localPath);
		super.addResourceHandlers(registry);
	}
}