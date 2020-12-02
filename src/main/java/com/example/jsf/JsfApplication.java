package com.example.jsf;

import java.util.Arrays;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.ocpsoft.pretty.PrettyFilter;

@SpringBootApplication
public class JsfApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsfApplication.class, args);
	}

	@Bean
	ServletRegistrationBean jsfServletRegistration(ServletContext servletContext) {
		// spring boot only works if this is set
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

		servletContext.setInitParameter("javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL",Boolean.TRUE.toString());
		servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
		servletContext.setInitParameter("primefaces.INTERPOLATE_CLIENT_SIDE_VALIDATION_MESSAGES", Boolean.TRUE.toString());
		servletContext.setInitParameter("primefaces.TRANSFORM_METADATA", Boolean.TRUE.toString() );
		// registration
		ServletRegistrationBean srb = new ServletRegistrationBean();
		srb.setServlet(new FacesServlet());
		srb.setUrlMappings(Arrays.asList("*.jsf"));
		srb.setLoadOnStartup(1);
		return srb;
	}

	@Bean
	public FilterRegistrationBean prettyFilter() {
		FilterRegistrationBean prettyFilter = new FilterRegistrationBean(new PrettyFilter());
		prettyFilter.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC,
				DispatcherType.ERROR);
		prettyFilter.addUrlPatterns("/*");
		return prettyFilter;
	}
}
