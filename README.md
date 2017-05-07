# zlikun-jee-encrypt
加密技术学习工程

#### 工程清单
- zlikun-jee-encrypt-base64	BASE64加密
- zlikun-jee-encrypt-hash 哈希加密(MD5、SHA)  
- zlikun-jee-encrypt-symmetrical 对称加密，AES等
- zlikun-jee-encrypt-rsa 非对称加密  

#### DES/DES3/AES
> DES、AES 或者 3DES 属于块加密算法，一般来说原文必须是 8 的整数倍，所以块加密算法除了加密模式之外，还涉及到填充模式。  
> 如果使用 NoPadding 的话，那么必须保证原文字节是 8 的倍数，否则的话需要使用其他的填充模式。  
> JCE 提供另外两种填充模式：PKCS5Padding 和 ISO10126Padding

参考资料：<http://bbs.csdn.net/topics/360204005#post-371051116>

#### 参考资料
- [让你完全理解base64是怎么回事](https://segmentfault.com/a/1190000004533485)