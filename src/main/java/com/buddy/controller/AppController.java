package com.buddy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class AppController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AppController.class);

	@RequestMapping({"/"})
	public String loadUI() {
		log.info("loading UIssdd");
		return "forward:/index.html";
	}

}
