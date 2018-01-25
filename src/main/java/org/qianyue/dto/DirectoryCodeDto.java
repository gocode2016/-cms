package org.qianyue.dto;

import java.util.List;

import org.qianyue.bean.Page;

public class DirectoryCodeDto<T> {

	// 实体列表
	private List<T> list;

	// 种类
	private String kind;

	// 分页信息
	private Page page;

	public DirectoryCodeDto(List<T> list, String kind, Page page) {

		this.list = list;
		this.kind = kind;
		this.page = page;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
