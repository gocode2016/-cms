package org.qianyue.dto;

import java.util.List;

import org.qianyue.bean.Page;

public class CodeDto<T> {

	// ʵ���б�
	private List<T> list;

	// ����
	private String kind;

	// �Ƿ���ȫ������
	private String isAll;

	// ��ҳ��Ϣ
	private Page page;

	public CodeDto(List<T> list, String kind, String isAll, Page page) {

		this.list = list;
		this.kind = kind;
		this.isAll = isAll;
		this.page = page;
	}

	public CodeDto(List<T> list, String kind, Page page) {

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

	public String getIsAll() {
		return isAll;
	}

	public void setIsAll(String isAll) {
		this.isAll = isAll;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
