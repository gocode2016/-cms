package org.qianyue.controller.api;

import java.util.List;

import org.qianyue.ForApiDto.DirectoryForApiDto;
import org.qianyue.ForApiDto.PictureForApiDto;
import org.qianyue.ForApiDto.VideoForApiDto;
import org.qianyue.dto.DirectoryDto;
import org.qianyue.service.DirectoryService;
import org.qianyue.service.PictureService;
import org.qianyue.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/directoryForApi")
public class DirectoryForApiController {

	@Autowired
	DirectoryService directoryService;

	@Autowired
	VideoService videoService;

	@Autowired
	PictureService pictureService;

	@RequestMapping
	@ResponseBody
	public DirectoryDto init() {
		DirectoryDto directoryDto = directoryService.selectById(1L);
		return directoryDto;
	}

	@RequestMapping("/{foldId}")
	@ResponseBody
	public DirectoryForApiDto init(@PathVariable("foldId") Long foldId) {
		String folderName = directoryService.selectById(foldId).getFolderName();
		List<DirectoryDto> listDir = directoryService.selectChildsByParentId(foldId);
		VideoForApiDto videoForApiDto = videoService.selectVidByFoldId(foldId);
		PictureForApiDto pictureForApiDto = pictureService.selectPicByFoldId(foldId);
		if (listDir == null || listDir.size() == 0) {
			return new DirectoryForApiDto(folderName, false, listDir, pictureForApiDto, videoForApiDto);
		}
		return new DirectoryForApiDto(folderName, true, listDir, pictureForApiDto, videoForApiDto);
	}

	@RequestMapping("/{foldId}/{kind}/{currentPage}/{pageNumber}")
	@ResponseBody
	public DirectoryForApiDto init(@PathVariable("foldId") Long foldId, @PathVariable("kind") Long kind,
			@PathVariable("currentPage") int currentPage, @PathVariable("pageNumber") int pageNumber) {
		String folderName = directoryService.selectById(foldId).getFolderName();
		if (kind == 1) {
			VideoForApiDto videoForApiDto = videoService.selectVidByFoldIdByPage(foldId, currentPage, pageNumber);
			return new DirectoryForApiDto(folderName, false, null, null, videoForApiDto);
		} else if (kind == 2) {
			PictureForApiDto pictureForApiDto = pictureService.selectPicByFoldIdByPage(foldId, currentPage, pageNumber);
			return new DirectoryForApiDto(folderName, false, null, pictureForApiDto, null);
		} else {
			return null;
		}

	}

}
