package com.imooc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.controller.interceptor.OneInterceptor;
import com.imooc.controller.interceptor.TwoInterceptor;

//WebMvcConfigurerAdapter spring5.0已废弃
//1.解决 extends WebMvcConfigurationSupport
//2. implements WebMvcConfigurer
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

	@Override
    //重写WebMvcConfigurationSupport里的方法
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 拦截器按照顺序执行
		 */
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");
		registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**");//.addPathPatterns("/one/**");

		super.addInterceptors(registry);
	}

}
