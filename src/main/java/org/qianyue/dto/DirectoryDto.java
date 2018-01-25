package org.qianyue.dto;

import org.qianyue.bean.Directory;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DirectoryDto extends Directory {

	// Longת��boolean ����Dto��ʾ
	private boolean isParent;
	// �Ƿ�ͬʱ����Ŀ¼
	private boolean open;
	// ��ͼ�ļ�
	private MultipartFile headImage;
	// ��ͼ����·��
	private String headImageUrl;
	// �Ƿ�����ͼ
	private String headImageHaveOrnot;
	// ·��

	private String path;

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public MultipartFile getHeadImage() {
		return headImage;
	}

	public void setHeadImage(MultipartFile headImage) {
		this.headImage = headImage;
	}

	public String getHeadImageUrl() {
		return headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

	public String getHeadImageHaveOrnot() {
		return headImageHaveOrnot;
	}

	public void setHeadImageHaveOrnot(String headImageHaveOrnot) {
		this.headImageHaveOrnot = headImageHaveOrnot;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
