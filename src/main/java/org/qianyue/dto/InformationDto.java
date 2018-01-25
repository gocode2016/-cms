package org.qianyue.dto;

import org.qianyue.bean.Information;
import org.springframework.web.multipart.MultipartFile;

public class InformationDto extends Information {

	// 已转换为String格式时间
	private String time;

	// 上传文章的首页图片
	private MultipartFile imageFile;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

}
