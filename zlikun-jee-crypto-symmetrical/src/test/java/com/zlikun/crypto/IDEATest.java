package com.zlikun.crypto;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

/**
 * IDEA加密算法用例
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/7 9:56
 */
public class IDEATest {

    // 算法名称
    private final String KEY_ALGORITHM = "IDEA";
    // 算法名称/加密模式/填充方式
    private final String CIPHER_ALGORITHM = "IDEA/ECB/ISO10126Padding";

    // 实例化Cipher对象，它用于完成实际的加解密操作
    private Cipher cipher ;

    // 密钥字节信息(不直接使用SecretKey实例原因是为了模拟网络传输情形)
    private byte [] secretKeyBytes ;

    static {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider()) ;
    }

    @Before
    public void init() throws NoSuchAlgorithmException, NoSuchPaddingException {

        this.cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM) ;
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey() ;
        this.secretKeyBytes = secretKey.getEncoded() ;
    }

    private  SecretKey toKey(byte [] key) {
        return new SecretKeySpec(key ,KEY_ALGORITHM) ;
    }

    @Test
    public void idea() throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // 要加密的字符串
        final String message = "这是一个要加密的字符串。" ;

        // I 加密

        // 初始化Cipher对象，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, toKey(this.secretKeyBytes), new SecureRandom());
        // 执行加密操作
        byte [] encryptBytes = cipher.doFinal(message.getBytes());
        // 加密后的结果通常都会用Base64编码进行传输
        System.out.println(String.format("加密后字符串(使用BASE64编码)：%s" , Base64.encodeBase64String(encryptBytes)));


        // II 解密

        // 初始化Cipher对象，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, toKey(this.secretKeyBytes), new SecureRandom());
        // 执行解密操作
        Assert.assertEquals(message ,new String(cipher.doFinal(encryptBytes))) ;
    }

}