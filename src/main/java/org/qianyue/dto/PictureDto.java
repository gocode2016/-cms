package org.qianyue.dto;

import org.qianyue.bean.Picture;
import org.springframework.web.multipart.MultipartFile;

public class PictureDto extends Picture {
	private String img;

	private MultipartFile imgFile;

	private String category;
	// 项目路径
	private String path;
	// 已转换为String格式时间
	private String time;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public MultipartFile getImgFile() {
		return imgFile;
	}

	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
