package org.qianyue.ForApiDto;

import java.util.List;

import org.qianyue.bean.Page;
import org.qianyue.dto.VideoDto;

public class VideoForApiDto {

	// 视频集合
	private List<VideoDto> listVid;
	// 视频集合分页信息
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
