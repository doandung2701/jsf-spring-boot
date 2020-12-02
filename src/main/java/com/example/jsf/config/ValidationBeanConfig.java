package com.example.jsf.config;

import java.util.Locale;

import javax.validation.MessageInterpolator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ValidationBeanConfig extends WebMvcConfigurerAdapter {
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

//	@Bean
//	@Primary
//	public MessageSourceResourceBundleLocator messageSourceResourceBundleLocator() {
//		return new MessageSourceResourceBundleLocator(messageSource());
//	}

//	@Bean
//	@Primary
//	public MessageInterpolator messageInterpolator() {
//		return new ResourceBundleMessageInterpolator(messageSourceResourceBundleLocator(), true);
//	}

	@Bean
	@Primary
	public LocalValidatorFactoryBean getMessageValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

//	@Bean
//	@Primary
//	public MethodValidationPostProcessor methodValidationPostProcessor() {
//		MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
//		methodValidationPostProcessor.setValidator(getMessageValidator());
//		return methodValidationPostProcessor;
//	}

	@Override
	public org.springframework.validation.Validator getValidator() {
		// TODO Auto-generated method stub
		return getMessageValidator();
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
