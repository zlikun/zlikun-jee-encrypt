package com.zlikun.crypto;

import java.util.Base64;
import java.util.Base64.Encoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * JDK 自带BASE64 API，JDK8开始提供
 * @author	zlikun
 * @date	2016年5月14日 下午4:05:27
 */
public class JdkBase64Test {

	@Test
	public void string() {

		String msg = "待加密字符串(BASE64)" ;

		// 加密，加密后字符串
		Encoder encoder = Base64.getEncoder() ;
		String encryptMsg = encoder.encodeToString(msg.getBytes()) ;
		Assert.assertEquals("5b6F5Yqg5a+G5a2X56ym5LiyKEJBU0U2NCk=", encryptMsg);

		// 解密
		byte [] bytes = Base64.getDecoder().decode(encryptMsg) ;
		Assert.assertEquals("待加密字符串(BASE64)", new String(bytes));

	}

	@Test
	public void url() {

		String url = "http://zlikun.com/?search=加密&encode=UTF-8" ;

		// 加密，加密后字符串
		Encoder encoder = Base64.getUrlEncoder() ;
		String encryptMsg = encoder.encodeToString(url.getBytes()) ;
		Assert.assertEquals("aHR0cDovL3psaWt1bi5jb20vP3NlYXJjaD3liqDlr4YmZW5jb2RlPVVURi04", encryptMsg);

		// 解密
		byte [] bytes = Base64.getUrlDecoder().decode(encryptMsg) ;
		Assert.assertEquals(url, new String(bytes));

	}

}
