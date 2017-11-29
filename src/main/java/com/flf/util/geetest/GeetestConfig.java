package com.flf.util.geetest;

/**
 * GeetestWeb配置文件
 * 
 *
 */
public class GeetestConfig {

	// 填入自己的captcha_id和private_key
	private static final String geetest_id = "a6c2d96f86c101395cfba3142cc44bea";
	private static final String geetest_key = "156159a9d8bd6bf4195d6093a5d33a07";
	private static final boolean newfailback = true;

	public static final String getGeetest_id() {
		return geetest_id;
	}

	public static final String getGeetest_key() {
		return geetest_key;
	}

	public static final boolean isnewfailback() {
		return newfailback;
	}

}
