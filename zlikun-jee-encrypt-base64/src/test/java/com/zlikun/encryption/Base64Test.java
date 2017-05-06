package com.zlikun.encryption;

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
	public void test() {
		
		String msg = "待加密字符串(BASE64)" ;
		
		// 加密后字符串
		String encryptMsg = Base64.encodeBase64String(msg.getBytes()) ;
		Assert.assertEquals("5b6F5Yqg5a+G5a2X56ym5LiyKEJBU0U2NCk=", encryptMsg);

		// 解密
		byte [] bytes = Base64.decodeBase64(encryptMsg) ;
		Assert.assertEquals("待加密字符串(BASE64)", new String(bytes));
	}
	
}
