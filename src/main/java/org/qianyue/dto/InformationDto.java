package org.qianyue.dto;

import org.qianyue.bean.Information;
import org.springframework.web.multipart.MultipartFile;

public class InformationDto extends Information {

	// ��ת��ΪString��ʽʱ��
	private String time;

	// �ϴ����µ���ҳͼƬ
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
