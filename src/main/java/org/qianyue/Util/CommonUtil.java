package org.qianyue.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	// Date格式转Strig
	public static String DateTran(Date date) {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String time = dateFormater.format(date);
		System.out.print(dateFormater.format(date));
		return time;
	}

	// 判断是否为空
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

}
