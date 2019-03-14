package com.hjkj.cloud.attend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.hjkj.cloud.attend.domain.model.UserAuditorAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

import javax.servlet.MultipartConfigElement;

//(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@ComponentScan(basePackages = "com.hjkj.cloud.attend")
@EnableJpaAuditing
public class AttendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendApplication.class, args);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 置文件大小限制 ,超出此大小页面会抛出异常信息
		factory.setMaxFileSize("500MB"); //KB,MB
		// 设置总上传数据总大小
		factory.setMaxRequestSize("500MB");
		// 设置文件临时文件夹路径
		// factory.setLocation("E://test//");
		// 如果文件大于这个值，将以文件的形式存储，如果小于这个值文件将存储在内存中，默认为0
		// factory.setMaxRequestSize(0);
		return factory.createMultipartConfig();
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = jsonConverter.getObjectMapper();
		objectMapper.registerModule(new Hibernate5Module());

		return jsonConverter;
	}


}

