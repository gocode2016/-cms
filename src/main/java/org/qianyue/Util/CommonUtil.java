package org.qianyue.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	// Date��ʽתStrig
	public static String DateTran(Date date) {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		String time = dateFormater.format(date);
		System.out.print(dateFormater.format(date));
		return time;
	}

	// �ж��Ƿ�Ϊ��
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

}
