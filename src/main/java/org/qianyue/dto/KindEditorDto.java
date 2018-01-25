package org.qianyue.dto;

public class KindEditorDto {

	private int error;

	private String message;

	public KindEditorDto(int error, String message) {
		this.error = error;
		this.message = message;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
