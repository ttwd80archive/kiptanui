package com.twistlet.kiptanui.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.twistlet.kiptanui.security.service.SecurityContextService;

public class MainController extends AbstractController {
	private SecurityContextService securitycontextService;
	private Map<String, String> map;

	@Autowired
	public MainController(SecurityContextService securitycontextService, Map<String, String> map) {
		super();
		this.securitycontextService = securitycontextService;
		this.map = Collections.unmodifiableMap(Collections.synchronizedMap(map));
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<GrantedAuthority> roles = securitycontextService.getAuthorities();
		List<String> roleNames = new ArrayList<>();
		for (GrantedAuthority role : roles) {
			roleNames.add(role.getAuthority());
		}
		synchronized (map) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				if (roleNames.contains(key)) {
					return new ModelAndView(map.get(key));
				}
			}
		}
		return null;
	}
}
