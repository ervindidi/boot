package com.zy.util;

import java.util.Random;

public class NumberUtil {
	/**
	 * 随机产生一个4位数的手机验证码
	 * @return
	 */
	public static String newCode() {
		Random random = new Random();
		//生成一个0-8999的数字并加上1000 产生一个1000-9999的验证码
		Integer num = random.nextInt(9000)+1000;
		return num.toString();
	}
}
