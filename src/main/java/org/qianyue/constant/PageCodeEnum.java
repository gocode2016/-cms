package org.qianyue.constant;

public enum PageCodeEnum {

	ADD_SUCCESS(1000, "�����ɹ�"), ADD_FAIL(1001, "����ʧ��"), REMOVE_SUCCESS(1002, "ɾ���ɹ�"), REMOVE_FAIL(1003,
			"ɾ��ʧ��"), MODIFY_SUCCESS(1004, "�޸ĳɹ�"), MODIFY_FAIL(1005,
					"�޸�ʧ��"), LOGIN_FAIL(1301, "��¼ʧ�ܣ��û����������"), VIDEO_FORMAT_WRONG(1400,
							"��Ƶ��ʽ����"), VIDEO_ADD_SUCCESS(1401, "��Ƶ��ӳɹ�"), VIDEO_ADD_FAIL(1402,
									"��Ƶ���ʧ��"), SESSION_TIMEOUT(1302, "session��ʱ�������µ�¼��"), MODIFYPW_SUCCESS(1500,
											"�޸�����ɹ�"), MODIFYPW_FAIL(1501, "�޸�����ʧ��"), MODIFYPW_EMPTY(1502,
													"ԭ���벻����"), IMAGE_FORMAT_WRONG(1601, "ͼƬ��ʽ����"), IMAGE_ADD_FAIL(1602,
															"ͼƬ����ʧ��"), IMAGE_ADD_SUCCESS(1603, "ͼƬ���óɹ�");

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
