package org.qianyue.dto;

public class WangEditorDto {

	private int errno;

	private String[] data;

	public WangEditorDto(int errno, String[] data) {

		this.errno = errno;
		this.data = data;
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public String[] getData() {
		return data;
	}

	public void setData(String[] data) {
		this.data = data;
	}

}
