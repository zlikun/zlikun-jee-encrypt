package com.zlikun.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA非对称加密算法
 * @author	zhanglikun
 * @date	2015年11月21日
 */
public class RSATest {
	
    private final static String KEY_RSA = "RSA";
    private final static String SIGNATURE_ALGORITHM = "MD5withRSA" ;

	private KeyFactory factory ;
	private Cipher cipher ;
	private Signature sign ;

    private PublicKey pubKey ;
    private PrivateKey priKey ;

	private String msg = "这是一条需要加密的消息" ;

	/**
	 * 生成公私钥
	 * @throws NoSuchAlgorithmException
	 */
	@Before
	public void init() throws NoSuchAlgorithmException, NoSuchPaddingException {

		this.factory = KeyFactory.getInstance(KEY_RSA) ;
		this.cipher = Cipher.getInstance(factory.getAlgorithm());
		this.sign = Signature.getInstance(SIGNATURE_ALGORITHM) ;

    	// 构建密钥对生成器
		KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_RSA);
		generator.initialize(2048);

		// 生成密钥对
		KeyPair keyPair = generator.generateKeyPair() ;

		// 生成公钥
		this.pubKey = keyPair.getPublic() ;

		// 生成私钥
		this.priKey = keyPair.getPrivate();

		// 输出公钥信息
		Assert.assertEquals("RSA" ,pubKey.getAlgorithm());
		Assert.assertEquals("X.509" ,pubKey.getFormat());

		// 输出私钥信息
		Assert.assertEquals("RSA" ,priKey.getAlgorithm());
		Assert.assertEquals("PKCS#8" ,priKey.getFormat());

		// 使用Base64编码
		String pubKeyString = Base64.encodeBase64String(pubKey.getEncoded()) ;
		String priKeyString = Base64.encodeBase64String(priKey.getEncoded()) ;

		// 打印公钥、私钥
		System.out.println("公钥(BASE64)：" + pubKeyString);
		System.out.println("私钥(BASE64)：" + priKeyString);
	}

	/**
	 * 使用公钥加密
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 */
	@Test
	public void rsa() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, SignatureException {

		// I 公钥加密

		// 加密使用X509EncodedKeySpec类
		this.cipher.init(Cipher.ENCRYPT_MODE, factory.generatePublic(new X509EncodedKeySpec(this.pubKey.getEncoded())));
		byte [] encryptMsg = cipher.doFinal(msg.getBytes());
        System.out.println("加密后(BASE64)：" + Base64.encodeBase64String(encryptMsg));


		// II 私钥解密

		// 解密使用PKCS8EncodedKeySpec类
		this.cipher.init(Cipher.DECRYPT_MODE, factory.generatePrivate(new PKCS8EncodedKeySpec(this.priKey.getEncoded())));
		System.out.println("解密后(String): " + new String(cipher.doFinal(encryptMsg)));


		// III 签名(私钥生成)

		this.sign.initSign(this.priKey);
		this.sign.update(encryptMsg);
		byte [] signByte = sign.sign() ;
        System.out.println("生成的签名：" + Base64.encodeBase64String(signByte));


        // IV 验证签名(公钥验证)

		sign.initVerify(this.pubKey);
		sign.update(encryptMsg);
        Assert.assertTrue(sign.verify(signByte)) ;
		
	}

}
