package com.td.configuration;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.td.util.LogUtil;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.td.mapper") // 指定mapper类位置
public class MyBatisConfig {
	@Value("${spring.datasource.url}")
	String dbUrl;
	@Value("${spring.datasource.username}")
	String userName;
	@Value("${spring.datasource.password}")
	String password;

	@Value("${spring.datasource.driverClassName}")
	String driverClassName;
	private static String MYBATIS_CONFIG = "mybatis_config.xml";
	private static String MAPPER_PATH = "/mapper/*.xml";

	@Bean
	public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		// 设置mybatis configuration 扫描路径
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
		// 添加mapper 扫描路径
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
		sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
		// 设置datasource
		sqlSessionFactoryBean.setDataSource(dataSource());
		LogUtil.info("配置MyBatis和druid完成！");
		return sqlSessionFactoryBean;
	}

	private DataSource dataSource() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(DruidDataSourceFactory.PROP_DRIVERCLASSNAME, driverClassName);
		properties.put(DruidDataSourceFactory.PROP_URL, dbUrl);
		properties.put(DruidDataSourceFactory.PROP_USERNAME, userName);
		properties.put(DruidDataSourceFactory.PROP_PASSWORD, password);
		// 初始化大小，最小，最大
		properties.put(DruidDataSourceFactory.PROP_INITIALSIZE, "5");
		properties.put(DruidDataSourceFactory.PROP_MINIDLE, "5");
		properties.put(DruidDataSourceFactory.PROP_MAXACTIVE, "20");
		// 配置获取连接等待超时的时间
		properties.put(DruidDataSourceFactory.PROP_MAXWAIT, "60000");
		// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		properties.put(DruidDataSourceFactory.PROP_TIMEBETWEENEVICTIONRUNSMILLIS, "60000");
		// 配置一个连接在池中最小生存的时间，单位是毫秒
		properties.put(DruidDataSourceFactory.PROP_MINEVICTABLEIDLETIMEMILLIS, "30000");
		// 打开PSCache，并且指定每个连接上PSCache的大小
		properties.put(DruidDataSourceFactory.PROP_POOLPREPAREDSTATEMENTS, "true");
		properties.put(DruidDataSourceFactory.PROP_MAXOPENPREPAREDSTATEMENTS, "20");
		// 检查连接可用性，仅在空闲时检测
		properties.put(DruidDataSourceFactory.PROP_VALIDATIONQUERY, "select 1");
		properties.put(DruidDataSourceFactory.PROP_TESTWHILEIDLE, "true");
		properties.put(DruidDataSourceFactory.PROP_TESTONBORROW, "false");
		properties.put(DruidDataSourceFactory.PROP_TESTONRETURN, "false");
		// 添加统计、SQL注入
		properties.put(DruidDataSourceFactory.PROP_FILTERS, "stat,wall");
		// sql合并，慢查询定义为2s
		properties.put(DruidDataSourceFactory.PROP_CONNECTIONPROPERTIES,
				"druid.stat.mergeSql=true;druid.stat.slowSqlMillis=2000");
		try {
			return DruidDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}