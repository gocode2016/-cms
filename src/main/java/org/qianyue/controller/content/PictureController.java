package org.qianyue.controller.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.qianyue.bean.Page;
import org.qianyue.constant.PageCodeEnum;
import org.qianyue.dto.CodeDto;
import org.qianyue.dto.PictureDto;
import org.qianyue.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/picture")
public class PictureController {

	@Autowired
	PictureService pictureService;

	@RequestMapping("/addPicture/{id}")
	@ResponseBody
	public Map<String, String> addPicture(@PathVariable("id") Long id, PictureDto pictureDto) {
		Map<String, String> map = new HashMap<String, String>();
		System.out.println(id);
		System.out.println(pictureDto.getImgFile().getOriginalFilename());
		pictureDto.setFolderId(id);
		if (pictureService.addPicture(pictureDto)) {
			map.put(PageCodeEnum.KEY, "pictureSuccessUpload");
			return map;
		} else {
			map.putIfAbsent(PageCodeEnum.KEY, "pictureSuccessFail");
			return map;
		}

	}

	@RequestMapping(value = "/selectPicture", method = RequestMethod.GET)
	public String select(Model model) {

		Page page = new Page();
		PictureDto pictureDto = new PictureDto();
		pictureDto.setPage(page);
		List<PictureDto> list = pictureService.selectAllOrFolder(pictureDto);
		model.addAttribute("list", list);
		model.addAttribute("kind", "Image");
		model.addAttribute("pictureDto", pictureDto);
		model.addAttribute("CodeDto", new CodeDto<PictureDto>(list, "Image", "YES", pictureDto.getPage()));
		return "/content/folder";
	}

	@RequestMapping("/deleteOnePicture/{id}")
	public String deleteOnePicture(@PathVariable("id") Long id) {
		pictureService.deleteOnePicture(id);
		return "/content/folder";
	}

	// 点击文件夹展示图片列表
	@RequestMapping(value = "/{folderId}", method = RequestMethod.GET)
	public String selectPicById(@PathVariable("folderId") Long folderId, Model model) {
		Page page = new Page();
		PictureDto pictureDto = new PictureDto();
		pictureDto.setPage(page);
		pictureDto.setFolderId(folderId);
		List<PictureDto> list = pictureService.selectAllOrFolder(pictureDto);
		model.addAttribute("list", list);
		model.addAttribute("kind", "Image");
		model.addAttribute("isAll", "NO");
		model.addAttribute("CodeDto", new CodeDto<PictureDto>(list, "Image", "NO", pictureDto.getPage()));
		model.addAttribute("folderId", folderId);
		return "/content/folder";
	}

	@RequestMapping(value = "/selectPicture/{currentPage}", method = RequestMethod.GET)
	public String select(@PathVariable("currentPage") int currentPage, Model model) {
		Page page = new Page();
		page.setCurrentPage(currentPage);
		PictureDto pictureDto = new PictureDto();
		pictureDto.setPage(page);
		List<PictureDto> list = pictureService.selectAllOrFolder(pictureDto);
		model.addAttribute("list", list);
		model.addAttribute("kind", "Image");
		model.addAttribute("pictureDto", pictureDto);
		model.addAttribute("CodeDto", new CodeDto<PictureDto>(list, "Image", "YES", pictureDto.getPage()));
		return "/content/folder";
	}

	// 点击文件夹展示图片列表 分页
	@RequestMapping(value = "/{folderId}/{currentPage}", method = RequestMethod.GET)
	public String selectPicById(@PathVariable("folderId") Long folderId, @PathVariable("currentPage") int currentPage,
			Model model) {
		Page page = new Page();
		page.setCurrentPage(currentPage);
		PictureDto pictureDto = new PictureDto();
		pictureDto.setPage(page);
		pictureDto.setFolderId(folderId);
		List<PictureDto> list = pictureService.selectAllOrFolder(pictureDto);
		model.addAttribute("list", list);
		model.addAttribute("kind", "Image");
		model.addAttribute("isAll", "NO");
		model.addAttribute("folderId", folderId);
		model.addAttribute("CodeDto", new CodeDto<PictureDto>(list, "Image", "NO", pictureDto.getPage()));
		return "/content/folder";
	}

}
