package com.zlikun.crypto;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * JDK自带摘要算法实现
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/9 9:29
 */
public class JdkHashTest {

    private String msg = "111111" ;

    @Test
    public void md5() throws NoSuchAlgorithmException {

        // algorithm参数可选值：[MD2,MD5,SHA1]
        // provider参数表示密码服务提供者，如BC (Bouncy Castle)
        MessageDigest md = MessageDigest.getInstance("MD5") ;
//        MessageDigest md = MessageDigest.getInstance("SHA1") ;

        // 密码服务提供者：SUN version 1.8
        System.out.println(String.format("密码服务提供者：%s" ,md.getProvider()));

        // 计算摘要
        md.update(this.msg.getBytes());

        // 生成摘要
        byte [] digest = md.digest() ;

        md.reset();

        // 96e79218965eb72c92a549dd5a330112
        System.out.println(String.format("生成摘要：%s" ,toHexString(digest))); ;

    }

    /**
     * 转换成16进制字符串
     * @param bytes
     * @return
     */
    public String toHexString(byte[] bytes) {
        StringBuilder buf = new StringBuilder() ;
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            buf.append(hex) ;
        }
        return buf.toString();
    }

}
