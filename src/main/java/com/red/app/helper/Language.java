package com.red.app.helper;

import com.red.system.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Component
public class Language {
    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    protected Auth auth;

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected Environment environment;

    public Language(){}

    public String getCurrentName(HttpServletRequest request){
        Locale locale = localeResolver.resolveLocale(request);
        String lng = locale.toString();
        return messageSource.getMessage("site.lang_" + lng, new Object[]{}, locale);
    }

    public String getCurrentIcon(HttpServletRequest request){
        String locale = localeResolver.resolveLocale(request).toString();
        return environment.getProperty("lang_img." + locale);
    }
}
