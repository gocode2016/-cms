package org.qianyue.ForApiDto;

import java.util.List;

import org.qianyue.bean.Page;
import org.qianyue.dto.VideoDto;

public class VideoForApiDto {

	// ��Ƶ����
	private List<VideoDto> listVid;
	// ��Ƶ���Ϸ�ҳ��Ϣ
	private Page pagelistVid;

	public VideoForApiDto(List<VideoDto> listVid, Page pagelistVid) {
		this.listVid = listVid;
		this.pagelistVid = pagelistVid;
	}

	public List<VideoDto> getListVid() {
		return listVid;
	}

	public void setListVid(List<VideoDto> listVid) {
		this.listVid = listVid;
	}

	public Page getPagelistVid() {
		return pagelistVid;
	}

	public void setPagelistVid(Page pagelistVid) {
		this.pagelistVid = pagelistVid;
	}

}
