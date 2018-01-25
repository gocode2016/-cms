package org.qianyue.controller.content;

import java.util.List;

import org.qianyue.bean.Page;
import org.qianyue.constant.PageCodeEnum;
import org.qianyue.constant.Suffix;
import org.qianyue.dto.CodeDto;
import org.qianyue.dto.VideoDto;
import org.qianyue.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/video")
public class VideoController {

	@Autowired
	VideoService videoService;

	@RequestMapping("/addVideo")
	public String addVideo(VideoDto videoDto, Model model) {
		String fileName = videoDto.getVideoFile().getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println(suffix);
		if (suffix.equals(Suffix.avi) || suffix.equals(Suffix.flv) || suffix.equals(Suffix.mkv)
				|| suffix.equals(Suffix.mp4) || suffix.equals(Suffix.rm) || suffix.equals(Suffix.rmvb)
				|| suffix.equals(Suffix.wmv)) {
			if (videoService.addVideo(videoDto)) {
				model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.VIDEO_ADD_SUCCESS);
			} else {
				model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.VIDEO_ADD_FAIL);
			}
		} else {
			model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.VIDEO_FORMAT_WRONG);
		}
		System.out.println(videoDto.getVideoFile().getOriginalFilename());
		System.out.println(videoDto.getFolderId());
		System.out.println(videoDto.getTitle());

		return "/content/folder";
	}

	@RequestMapping("/selectVideo")
	public String select(Model model) {
		Page page = new Page();
		VideoDto videoDto = new VideoDto();
		videoDto.setPage(page);
		List<VideoDto> list = videoService.selectAllOrFolder(videoDto);
		model.addAttribute("list", list);
		model.addAttribute("kind", "Video");
		model.addAttribute("CodeDto", new CodeDto<VideoDto>(list, "Video", "YES", videoDto.getPage()));
		return "/content/folder";
	}

	@RequestMapping("/deleteOneVideo/{id}")
	public String deleteOneVideo(@PathVariable("id") Long id) {
		videoService.deleteOneVideo(id);
		return "/content/folder";

	}

	@RequestMapping(value = "/{folderId}", method = RequestMethod.GET)
	public String selectVideoById(@PathVariable("folderId") Long folderId, Model model) {
		VideoDto videoDto = new VideoDto();
		videoDto.setFolderId(folderId);
		Page page = new Page();
		videoDto.setPage(page);
		videoDto.setFolderId(folderId);
		List<VideoDto> list = videoService.selectAllOrFolder(videoDto);
		model.addAttribute("list", list);
		model.addAttribute("kind", "Video");
		model.addAttribute("CodeDto", new CodeDto<VideoDto>(list, "Video", "NO", videoDto.getPage()));
		return "/content/folder";
	}

	// 全文分页视频搜索
	@RequestMapping("/selectVideo/{currentPage}")
	public String select(@PathVariable("currentPage") int currentPage, Model model) {
		Page page = new Page();
		page.setCurrentPage(currentPage);
		VideoDto videoDto = new VideoDto();
		videoDto.setPage(page);
		List<VideoDto> list = videoService.selectAllOrFolder(videoDto);
		model.addAttribute("list", list);
		model.addAttribute("kind", "Video");
		model.addAttribute("CodeDto", new CodeDto<VideoDto>(list, "Video", "YES", videoDto.getPage()));
		return "/content/folder";
	}

	// 文件夹分页搜索
	@RequestMapping(value = "/{folderId}/{currentPage}", method = RequestMethod.GET)
	public String selectVideoByIdByPage(@PathVariable("folderId") Long folderId,
			@PathVariable("currentPage") int currentPage, Model model) {
		VideoDto videoDto = new VideoDto();
		videoDto.setFolderId(folderId);
		Page page = new Page();
		page.setCurrentPage(currentPage);
		videoDto.setPage(page);
		videoDto.setFolderId(folderId);
		List<VideoDto> list = videoService.selectAllOrFolder(videoDto);
		model.addAttribute("list", list);
		model.addAttribute("kind", "Video");
		model.addAttribute("CodeDto", new CodeDto<VideoDto>(list, "Video", "NO", videoDto.getPage()));
		return "/content/folder";
	}

}
