package com.twistlet.kiptanui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.twistlet.kiptanui.model.entity.User;
import com.twistlet.kiptanui.security.service.AdminUserService;

@Controller
public class AdminUserController {

	private AdminUserService adminUserService;

	@Autowired
	public AdminUserController(AdminUserService adminUserService) {
		super();
		this.adminUserService = adminUserService;
	}

	@RequestMapping(value = "/admin/user/list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(adminUserService.listUser());
		return mav;
	}

	@RequestMapping(value = "/admin/user/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView();
		mav.addObject(new User());
		return mav;
	}
}
