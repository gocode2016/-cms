package org.qianyue.service.serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.qianyue.ForApiDto.PictureForApiDto;
import org.qianyue.Util.CommonUtil;
import org.qianyue.bean.Page;
import org.qianyue.bean.Picture;
import org.qianyue.dao.PictureDao;
import org.qianyue.dto.PictureDto;
import org.qianyue.service.DirectoryService;
import org.qianyue.service.PictureService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

	@Autowired
	PictureDao pictureDao;

	@Autowired
	DirectoryService directoryService;

	@Value("${directoryImage.savePath}")
	private String directoryImageSavePath;

	@Value("${directoryImage.url}")
	private String directoryImageUrl;

	@Value("${directoryCategory.Image}")
	private String directoryCategoryImage;

	public boolean addPicture(PictureDto pictureDto) {
		Picture picture = new Picture();
		picture.setFolderId(pictureDto.getFolderId());
		Date createTime = new Date();
		picture.setCreateTime(createTime);
		if (pictureDto.getImgFile() != null && pictureDto.getImgFile().getSize() > 0) {
			String fileName = System.currentTimeMillis() + "_" + pictureDto.getImgFile().getOriginalFilename();
			System.out.println(fileName);
			File file = new File(directoryImageSavePath + fileName);
			File fileFolder = new File(directoryImageSavePath);
			if (!fileFolder.exists()) {
				fileFolder.mkdirs();
			}

			try {
				pictureDto.getImgFile().transferTo(file);
				picture.setPicName(fileName);
				pictureDao.add(picture);
				return true;

			} catch (Exception e) {
				// TODO需要添加日志
				return false;

			}

		}
		return false;
	}

	// 后台用接口
	public List<PictureDto> selectAllOrFolder(PictureDto picDto) {
		Picture picture = new Picture();
		BeanUtils.copyProperties(picDto, picture);
		List<Picture> list = pictureDao.selectByPage(picture);
		List<PictureDto> result = new ArrayList<PictureDto>();
		for (Picture pic : list) {
			PictureDto pictureDto = new PictureDto();
			BeanUtils.copyProperties(pic, pictureDto);
			pictureDto.setImg(directoryImageUrl + pic.getPicName());
			pictureDto.setCategory(directoryCategoryImage);
			pictureDto.setPath(directoryService.allPath(pic.getFolderId()));
			pictureDto.setTime(CommonUtil.DateTran(pic.getCreateTime()));
			result.add(pictureDto);
		}
		return result;
	}

	public boolean deleteOnePicture(Long id) {
		if (pictureDao.deleteOnePicture(id) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void findAndDeleteIdByFolderId(Long id) {

		List<Picture> listPic = pictureDao.findPicByFolderId(id);
		if (listPic.size() > 0) {
			for (Picture pic : listPic) {
				pictureDao.deleteOnePicture(pic.getId());
				File filePic = new File(directoryImageSavePath + pic.getPicName());
				if (filePic.exists()) {
					filePic.delete();
				}

			}

		}

	}

	// 前台用接口
	public PictureForApiDto selectPicByFoldId(Long id) {
		Picture picture = new Picture();
		Page page = new Page();
		picture.setPage(page);
		picture.setFolderId(id);
		List<Picture> listPic = pictureDao.selectByPage(picture);
		// List<Picture> listPic = pictureDao.findPicByFolderId(id);
		List<PictureDto> result = new ArrayList<PictureDto>();
		for (Picture pic : listPic) {
			PictureDto pictureDto = new PictureDto();
			BeanUtils.copyProperties(pic, pictureDto);
			pictureDto.setImg(directoryImageUrl + pic.getPicName());
			pictureDto.setCategory(directoryCategoryImage);
			pictureDto.setPath(directoryService.allPath(pic.getFolderId()));
			pictureDto.setTime(CommonUtil.DateTran(pic.getCreateTime()));
			result.add(pictureDto);
		}
		return new PictureForApiDto(result, picture.getPage());
	}

	// 前台用接口某一页
	public PictureForApiDto selectPicByFoldIdByPage(Long id, int currentPage, int pageNumber) {
		Picture picture = new Picture();
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPageNumber(pageNumber);
		picture.setPage(page);
		picture.setFolderId(id);
		List<Picture> listPic = pictureDao.selectByPage(picture);
		// List<Picture> listPic = pictureDao.findPicByFolderId(id);
		List<PictureDto> result = new ArrayList<PictureDto>();
		for (Picture pic : listPic) {
			PictureDto pictureDto = new PictureDto();
			BeanUtils.copyProperties(pic, pictureDto);
			pictureDto.setImg(directoryImageUrl + pic.getPicName());
			pictureDto.setCategory(directoryCategoryImage);
			pictureDto.setPath(directoryService.allPath(pic.getFolderId()));
			pictureDto.setTime(CommonUtil.DateTran(pic.getCreateTime()));
			result.add(pictureDto);
		}
		return new PictureForApiDto(result, picture.getPage());

	}

}
