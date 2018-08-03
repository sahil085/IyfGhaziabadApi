package com.IyfGZB.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.ui.velocity.VelocityEngineFactory;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@ComponentScan(basePackages={"com.IyfGZB"},
		excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = ".*Test.*"))
@SpringBootApplication
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true,jsr250Enabled = true)
public class IyfGzbApplication {
	@Bean
	public VelocityEngine getVelocityEngine() throws VelocityException, IOException {
		VelocityEngineFactory factory = new VelocityEngineFactory();
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		factory.setVelocityProperties(props);
		return factory.createVelocityEngine();
	}

	public static void main(String[] args) {
		SpringApplication.run(IyfGzbApplication.class, args);
	}
}
