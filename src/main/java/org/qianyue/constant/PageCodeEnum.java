package org.qianyue.constant;

public enum PageCodeEnum {

	ADD_SUCCESS(1000, "新增成功"), ADD_FAIL(1001, "新增失败"), REMOVE_SUCCESS(1002, "删除成功"), REMOVE_FAIL(1003,
			"删除失败"), MODIFY_SUCCESS(1004, "修改成功"), MODIFY_FAIL(1005,
					"修改失败"), LOGIN_FAIL(1301, "登录失败！用户名密码错误！"), VIDEO_FORMAT_WRONG(1400,
							"视频格式错误"), VIDEO_ADD_SUCCESS(1401, "视频添加成功"), VIDEO_ADD_FAIL(1402,
									"视频添加失败"), SESSION_TIMEOUT(1302, "session超时，请重新登录！"), MODIFYPW_SUCCESS(1500,
											"修改密码成功"), MODIFYPW_FAIL(1501, "修改密码失败"), MODIFYPW_EMPTY(1502,
													"原密码不存在"), IMAGE_FORMAT_WRONG(1601, "图片格式错误"), IMAGE_ADD_FAIL(1602,
															"图片设置失败"), IMAGE_ADD_SUCCESS(1603, "图片设置成功");

	private Integer code;
	private String msg;

	public static final String KEY = "pageCode";

	PageCodeEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
