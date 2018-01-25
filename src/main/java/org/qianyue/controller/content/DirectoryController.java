package org.qianyue.controller.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.qianyue.bean.Page;
import org.qianyue.constant.PageCodeEnum;
import org.qianyue.constant.Suffix;
import org.qianyue.dto.CodeDto;
import org.qianyue.dto.DirectoryDto;
import org.qianyue.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/directory")
public class DirectoryController {

	@Autowired
	DirectoryService directoryService;

	@RequestMapping
	public String init() {
		return "/content/folder";
	}

	// 添加目录图片后跳转到首页并传model过去
	@RequestMapping("/JumpToHome")
	public String initJumpToHome(@ModelAttribute("pageCode") PageCodeEnum pageCode, Model model) {
		System.out.println("拿到重定向得到的参数msg:" + pageCode.getMsg() + pageCode.getCode());
		model.addAttribute(PageCodeEnum.KEY, pageCode);
		return "/content/folder";
	}

	// 获得初始化的树
	@RequestMapping("/getInitTree")
	@ResponseBody
	public List<DirectoryDto> InitTree(Model model) {
		List<DirectoryDto> list = directoryService.selectAll();
		model.addAttribute("list", directoryService.selectAll());
		model.addAttribute("mo", "youmeiyouyong");
		System.out.println(list);
		return list;
	}

	@RequestMapping("/updateTree")
	@ResponseBody
	public Map<String, String> updateTree(@RequestParam("id") Long id, @RequestParam("folderName") String folderName) {
		DirectoryDto directoryDto = new DirectoryDto();
		directoryDto.setId(id);
		directoryDto.setFolderName(folderName);
		Map<String, String> map = new HashMap<String, String>();
		if (directoryService.update(directoryDto)) {
			map.put(PageCodeEnum.KEY, "UpdateSuccess");

			return map;
		} else {
			map.put(PageCodeEnum.KEY, "UpdateFail");
			return map;
		}

	}

	@RequestMapping("/updateTreeIsParent")
	@ResponseBody
	public Map<String, String> updateTreeIsParent(@RequestParam("isParent") Long isParent) {
		Map<String, String> map = new HashMap<String, String>();
		if (directoryService.updateIsParent(isParent)) {
			map.put(PageCodeEnum.KEY, "UpdateIsParentSuccess");
			return map;
		} else {
			map.put(PageCodeEnum.KEY, "UpdateIsParentFail");
			return map;
		}
	}

	@RequestMapping("/addChild")
	@ResponseBody
	public Map<String, Long> addChild(@RequestParam("name") String name, @RequestParam("parentId") Long parentId) {
		Map<String, Long> map = new HashMap<String, Long>();
		DirectoryDto directoryDto = new DirectoryDto();
		directoryDto.setParentId(parentId);
		directoryDto.setFolderName(name);
		int maxId = directoryService.addChild(directoryDto);
		System.out.println(maxId);
		map.put(PageCodeEnum.KEY, (long) maxId);
		return map;
	}

	@RequestMapping("/removeAll")
	public void removeAll(@RequestParam("id") Long id, @RequestParam("parentId") Long parentId) {
		System.out.println(parentId);
		if (id != 1) {
			directoryService.deleteAllChild(id);
		}
		List<DirectoryDto> list = directoryService.selectChildsByParentId(parentId);
		if (list.size() == 0) {
			directoryService.updateIsNotParent(parentId);
		}

	}

	@RequestMapping("/judgeIsParent")
	@ResponseBody
	public Map<String, String> judgeIsParent(@RequestParam("id") Long id) {
		Map<String, String> map = new HashMap<String, String>();
		if (directoryService.judgeIsParent(id)) {
			System.out.println(directoryService.judgeIsParent(id));
			map.put(PageCodeEnum.KEY, "isParent");
			return map;
		} else {
			System.out.println(directoryService.judgeIsParent(id));
			map.put(PageCodeEnum.KEY, "isNotParent");
			return map;
		}

	}

	// 选择所有的文件夹列表信息
	@RequestMapping("/selectAllDir")
	public String selectAllDir(Model model) {
		DirectoryDto directoryDto = new DirectoryDto();
		directoryDto.setPage(new Page());
		List<DirectoryDto> list = directoryService.selectAllDirForList(directoryDto);
		model.addAttribute("list", list);
		model.addAttribute("kind", "Directory");
		// model.addAttribute("pictureDto", pictureDto);
		// model.addAttribute("informationCodeDto",
		// new InformationCodeDto<PictureDto>(list, "Image", "YES",
		// pictureDto.getPage()));
		model.addAttribute("CodeDto", new CodeDto<DirectoryDto>(list, "Directory", directoryDto.getPage()));
		return "/content/folder";
	}

	// 根据分页查询目录的列表情况

	@RequestMapping("/selectDir/{currentPage}")
	public String selectDir(@PathVariable("currentPage") int currentPage, Model model) {
		DirectoryDto directoryDto = new DirectoryDto();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		directoryDto.setPage(page);
		List<DirectoryDto> list = directoryService.selectAllDirForList(directoryDto);
		model.addAttribute("list", list);
		model.addAttribute("kind", "Directory");
		// model.addAttribute("pictureDto", pictureDto);
		// model.addAttribute("informationCodeDto",
		// new InformationCodeDto<PictureDto>(list, "Image", "YES",
		// pictureDto.getPage()));
		model.addAttribute("CodeDto", new CodeDto<DirectoryDto>(list, "Directory", directoryDto.getPage()));
		return "/content/folder";
	}

	// 添加目录图片
	@RequestMapping("/HeadImage")
	public String addHeadImage(DirectoryDto directoryDto, RedirectAttributes model) {
		String fileName = directoryDto.getHeadImage().getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println(suffix);
		if (suffix.equals(Suffix.jpg) || suffix.equals(Suffix.jpeg) || suffix.equals(Suffix.bmp)
				|| suffix.equals(Suffix.gif)) {
			if (directoryService.addHeadImage(directoryDto)) {
				model.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.IMAGE_ADD_SUCCESS);
			} else {
				model.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.IMAGE_ADD_FAIL);
			}
		} else {
			model.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.IMAGE_FORMAT_WRONG);
		}
		return "redirect:/directory/JumpToHome";
	}

}
