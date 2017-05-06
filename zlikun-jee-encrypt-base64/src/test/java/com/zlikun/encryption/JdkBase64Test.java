package com.zlikun.encryption;

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
	public void test() {
		
		String msg = "待加密字符串(BASE64)" ;
		
		// 加密，加密后字符串
		Encoder encoder = Base64.getEncoder() ;
		String encryptMsg = encoder.encodeToString(msg.getBytes()) ;
		Assert.assertEquals("5b6F5Yqg5a+G5a2X56ym5LiyKEJBU0U2NCk=", encryptMsg);
		
		// 解密
		byte [] bytes = Base64.getDecoder().decode(encryptMsg) ;
		Assert.assertEquals("待加密字符串(BASE64)", new String(bytes));

	}
	
}
