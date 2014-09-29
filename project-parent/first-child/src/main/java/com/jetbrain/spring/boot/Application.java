package com.jetbrain.spring.boot;

import org.apache.catalina.connector.Connector;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;





@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application extends SpringBootServletInitializer{ 

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/testservice/rest/*");
		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS,JerseyConfig.class.getName());
		return registration;
	}

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new ContainerCustomizer();
    }
    
	  private static class ContainerCustomizer implements
	    EmbeddedServletContainerCustomizer {

	        public void customize(ConfigurableEmbeddedServletContainer factory) {
	            if (factory instanceof TomcatEmbeddedServletContainerFactory) {
	                customizeTomcat((TomcatEmbeddedServletContainerFactory) factory);
	            }
	        }

	        public void customizeTomcat(
	                TomcatEmbeddedServletContainerFactory factory) {
	            factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
	                public void customize(Connector connector) {
	                    connector.setURIEncoding("UTF-8");
	                }
	            });
	        }

	    }
}