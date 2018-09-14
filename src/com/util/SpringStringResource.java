package com.util;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;


public class SpringStringResource {

    private static ResourceBundleMessageSource messageSource;

    public static String getString(String key) {
        Locale locale = LocaleContextHolder.getLocale();                        
        return messageSource.getMessage(key, new Object[0], locale);
    }

	public static ResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	public static void setMessageSource(ResourceBundleMessageSource messageSource) {
		SpringStringResource.messageSource = messageSource;
	}
}
