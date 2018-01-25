package org.qianyue.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Directory extends BaseBean {
	// TODO
	private Long id;
	// TODO
	private String folderName;
	// TODOs
	private Long parentId;
	// TODO
	private Long isParent;
	// 首图的文件名
	private String headImageName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getIsParent() {
		return isParent;
	}

	public void setIsParent(Long isParent) {
		this.isParent = isParent;
	}

	public String getHeadImageName() {
		return headImageName;
	}

	public void setHeadImageName(String headImageName) {
		this.headImageName = headImageName;
	}

	@Override
	public String toString() {
		return "Directory [id=" + id + ", folderName=" + folderName + ", parentId=" + parentId + ", isParent="
				+ isParent + "]";
	}

}
