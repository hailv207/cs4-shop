package com.red.app.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Component
public class Router {
    public Router(){
    }

    public String map(String route) {
        return MvcUriComponentsBuilder.fromMappingName(route).build();
    }

    public String map(String route, Object... uriVars) {
        return MvcUriComponentsBuilder.fromMappingName(route).buildAndExpand(uriVars);
    }
}
