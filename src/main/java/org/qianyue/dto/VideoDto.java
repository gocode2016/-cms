package org.qianyue.dto;

import org.qianyue.bean.Video;
import org.springframework.web.multipart.MultipartFile;

public class VideoDto extends Video {
	private String video;

	private MultipartFile videoFile;

	private String category;

	// 项目路径
	private String path;
	// 已转换为String格式时间
	private String time;

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public MultipartFile getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(MultipartFile videoFile) {
		this.videoFile = videoFile;
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
