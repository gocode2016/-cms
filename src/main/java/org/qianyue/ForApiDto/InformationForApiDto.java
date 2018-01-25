package org.qianyue.ForApiDto;

import java.util.List;

import org.qianyue.bean.Page;
import org.qianyue.dto.InformationDto;

//前台返回json格式
public class InformationForApiDto {
	// 资讯对象集合
	private List<InformationDto> list;
	// 分页对象
	private Page page;

	public InformationForApiDto(List<InformationDto> list, Page page) {
		this.list = list;
		this.page = page;
	}

	public List<InformationDto> getList() {
		return list;
	}

	public void setList(List<InformationDto> list) {
		this.list = list;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
