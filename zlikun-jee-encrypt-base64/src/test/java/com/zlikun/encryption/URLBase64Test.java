package com.zlikun.encryption;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

/**
 * 针对URL的BASE编解码
 * @author	zlikun
 * @date	2016年5月14日 下午4:09:33
 */
public class URLBase64Test {

	@Test
	public void test() {
		
		String url = "http://zlikun.com/?search=加密&encode=UTF-8" ;
		String encryptMsg = Base64.encodeBase64URLSafeString(url.getBytes()) ;
		Assert.assertEquals("aHR0cDovL3psaWt1bi5jb20vP3NlYXJjaD3liqDlr4YmZW5jb2RlPVVURi04", encryptMsg);
		
		byte [] bytes = Base64.decodeBase64(encryptMsg) ;
		Assert.assertEquals("http://zlikun.com/?search=加密&encode=UTF-8", new String(bytes));

	}
	
}
