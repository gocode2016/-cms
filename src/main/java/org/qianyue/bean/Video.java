package org.qianyue.bean;

import java.util.Date;

public class Video extends BaseBean {
	// ��Ƶid
	private Long id;
	// ��Ƶ����
	private String title;
	// ��Ƶ����
	private String videoName;
	// �ļ���id
	private Long folderId;
	// ����ʱ��
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
