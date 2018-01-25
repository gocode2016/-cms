package org.qianyue.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.qianyue.ForApiDto.InformationForApiDto;
import org.qianyue.bean.Page;
import org.qianyue.dto.InformationDto;
import org.qianyue.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/informationForApi")
public class InformationForApiController {

	@Autowired
	InformationService informationService;

	// 初始化页面
	@RequestMapping
	@ResponseBody
	public InformationForApiDto init(Model model) {
		InformationDto informationDto = new InformationDto();
		Page page = new Page();
		informationDto.setPage(page);
		List<InformationDto> list = informationService.select(informationDto);
		return new InformationForApiDto(list, informationDto.getPage());
	}

	// 下滑加载
	@RequestMapping("/{currentPage}")
	@ResponseBody
	public InformationForApiDto init(@PathVariable("currentPage") int currentPage) {
		InformationDto informationDto = new InformationDto();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		informationDto.setPage(page);
		List<InformationDto> list = informationService.select(informationDto);
		if (currentPage > informationDto.getPage().getTotalPage() || currentPage < 1) {
			return new InformationForApiDto(null, informationDto.getPage());
		} else {
			return new InformationForApiDto(list, informationDto.getPage());
		}

	}

	// 进入详情页
	@RequestMapping("/intoInfor/{informationId}")
	@ResponseBody
	public InformationDto init(@PathVariable("informationId") Long informationId) {
		InformationDto informationDto = informationService.selectById(informationId);
		List<InformationDto> list = new ArrayList<InformationDto>();
		list.add(informationDto);
		return list.get(0);
	}

}
