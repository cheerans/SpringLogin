package com.spring.form;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AppConfig
{    
    @Bean
    public ReloadableResourceBundleMessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename( "resource/properties/SpringSecurity" );
        messageSource.setCacheSeconds( 0 );
        return messageSource;
    }

//    @Bean
//    public AjaxViewResolver viewResolver1()
//    {
//    	AjaxViewResolver viewResolver = new AjaxViewResolver();
//        viewResolver.setAjaxView(new AjaxView());
//        viewResolver.setAjaxPrefix("ajax_");
//        return viewResolver;
//    }    
//    
//    @Bean
//    public InternalResourceViewResolver viewResolver2()
//    {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//       viewResolver.setViewClass( JstlView.class );
//        viewResolver.setPrefix( "/WEB-INF/pages/" );
//        viewResolver.setSuffix( ".jsp" );
//        return viewResolver;
//    }   
}
