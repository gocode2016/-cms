package org.qianyue.ForApiDto;

import java.util.List;

import org.qianyue.bean.Page;
import org.qianyue.dto.InformationDto;

//ǰ̨����json��ʽ
public class InformationForApiDto {
	// ��Ѷ���󼯺�
	private List<InformationDto> list;
	// ��ҳ����
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
