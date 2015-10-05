package org.smelser.web.oozie.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController { 

    private static final Log logger = LogFactory.getLog(HomeController.class);

	@RequestMapping(value = "/")
	public String angular(Locale locale, ModelMap model, HttpSession httpSession){
		logger.info("oozie index called");
		
		return "index";
	}
}
