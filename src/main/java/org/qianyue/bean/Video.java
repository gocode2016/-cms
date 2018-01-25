package org.qianyue.bean;

import java.util.Date;

public class Video extends BaseBean {
	// 视频id
	private Long id;
	// 视频标题
	private String title;
	// 视频名字
	private String videoName;
	// 文件夹id
	private Long folderId;
	// 创建时间
	private Date CreateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

}
