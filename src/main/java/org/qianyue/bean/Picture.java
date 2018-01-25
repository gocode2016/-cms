package org.qianyue.bean;

import java.util.Date;

public class Picture extends BaseBean {
	// 图片id
	private Long id;
	// 图片名称
	private String picName;
	// 所属文件夹id
	private Long folderId;
	// 创建时间
	private Date CreateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
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
