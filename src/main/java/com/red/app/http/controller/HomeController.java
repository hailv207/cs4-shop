package com.red.app.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	@GetMapping("/")
	public String index(HttpServletRequest request){
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		System.out.println("===========================================================================================");

		System.out.println(localeResolver);
		System.out.println(localeResolver.resolveLocale(request).toString());
		return "layout/home";
	}
}
