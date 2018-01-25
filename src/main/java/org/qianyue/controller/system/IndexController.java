package org.qianyue.controller.system;

import org.qianyue.dto.UserDto;
import org.qianyue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	UserService userService;

	@RequestMapping()
	public String init(@RequestParam(value = "name", required = false) String name, Model model) {
		UserDto userDto = userService.findUserByName(name);
		model.addAttribute("userDto", userDto);
		return "/system/index";
	}

}
