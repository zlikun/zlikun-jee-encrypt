# 非对称加密

非对称加密算法，参考文章：
* <http://www.ruanyifeng.com/blog/2013/06/rsa_algorithm_part_one.html>  
* <http://www.ruanyifeng.com/blog/2013/07/rsa_algorithm_part_two.html>  

Java语言实现RSA算法参考文章：  
* <http://my.oschina.net/jiangli0502/blog/171263> 

加密算法流程  
```
（1）乙方生成两把密钥（公钥和私钥）。公钥是公开的，任何人都可以获得，私钥则是保密的。
（2）甲方获取乙方的公钥，然后用它对信息加密。
（3）乙方得到加密后的信息，用私钥解密。
```
