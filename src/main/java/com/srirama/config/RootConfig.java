package com.srirama.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
 
@Configuration
public class RootConfig {
	/* @Bean
	    public static PropertyPlaceholderConfigurer properties(){
	      PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
	      ClassPathResource[] resources = new ClassPathResource[ ]
	        { new ClassPathResource( "emailproject.properties" ) };
	      ppc.setLocations( resources );
	      ppc.setIgnoreUnresolvablePlaceholders( true );
	      return ppc;
	    }*/
}