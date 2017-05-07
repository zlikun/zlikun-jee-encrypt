package com.zlikun.crypto;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

/**
 * commons-codec BASE64加解密测试用例
 * @author	zlikun
 * @date	2016年5月14日 下午3:56:21
 */
public class Base64Test {

	@Test
	public void string() {

		// 原始字符串
		String msg = "待加密字符串(BASE64)" ;
		
		// 编码
		String encryptMsg = Base64.encodeBase64String(msg.getBytes()) ;
		Assert.assertEquals("5b6F5Yqg5a+G5a2X56ym5LiyKEJBU0U2NCk=", encryptMsg);

		// 解码
		byte [] bytes = Base64.decodeBase64(encryptMsg) ;
		Assert.assertEquals(msg, new String(bytes));
	}

	/**
	 * #encodeBase64URLSafeString() 方法的价值何在？
	 */
	@Test
	public void url() {

		String url = "http://link.zlikun.com/?target=http://www.zlikun.com" ;

		// 编码
		String encryptMsg = Base64.encodeBase64URLSafeString(url.getBytes()) ;
		Assert.assertEquals("aHR0cDovL2xpbmsuemxpa3VuLmNvbS8_dGFyZ2V0PWh0dHA6Ly93d3cuemxpa3VuLmNvbQ", encryptMsg);

		// 解码
		Assert.assertEquals(url ,new String(Base64.decodeBase64(encryptMsg)));

	}

}
