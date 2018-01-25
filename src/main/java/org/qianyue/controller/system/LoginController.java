package org.qianyue.controller.system;

import javax.servlet.http.HttpSession;

import org.qianyue.constant.PageCodeEnum;
import org.qianyue.constant.SessionKeyConst;
import org.qianyue.dto.ModifyPwDto;
import org.qianyue.dto.UserDto;
import org.qianyue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	HttpSession session;

	// 跳转到登录页面
	@RequestMapping
	public String index() {
		return "/system/login";
	}

	@RequestMapping("/validate")
	public String validate(UserDto userDto, RedirectAttributes attr) {
		if (userService.validatie(userDto)) {
			session.setAttribute(SessionKeyConst.USER_INFO, userDto);
			attr.addAttribute("name", userDto.getName());
			return "redirect:/index";
		} else {
			return "redirect:/login";
		}
	}

	/**
	 * session超时
	 */
	@RequestMapping("/sessionTimeout")
	public String sessionTimeout(RedirectAttributes attr) {
		attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.SESSION_TIMEOUT);
		return "redirect:/login";
	}

	@RequestMapping("/modifyPw")
	public String modifyPw(ModifyPwDto modifyPwDto, Model model) {
		if (userService.JudgePwExist(modifyPwDto)) {
			if (userService.modyfyPw(modifyPwDto)) {
				model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFYPW_SUCCESS);
				return "/system/index";
			} else {
				model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFYPW_FAIL);
				return "/system/index";
			}
		} else {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFYPW_EMPTY);
			return "/system/index";
		}

	}

	@RequestMapping("/quitSystem")
	@ResponseBody
	public void quitSystem() {
		session.setAttribute(PageCodeEnum.KEY, null);

	}

}
