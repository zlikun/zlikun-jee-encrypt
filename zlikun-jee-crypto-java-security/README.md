# zlikun-jee-crypto-java-security

#### 测试用JKS
```
# 生成密钥库，密码：123456
$ keytool -genkey -alias api.zlikun.com -keyalg RSA -keysize 2048
 -keystore api.zlikun.com.keystore -validity 3650 -v -dname "C=CN,ST=SH,L=SH,O=z
likun.com,OU=zlikun.com,CN=api.zlikun.com"
输入密钥库口令:
再次输入新口令:
正在为以下对象生成 2,048 位RSA密钥对和自签名证书 (SHA256withRSA) (有效期为 3,650
 天):
         C=CN, ST=SH, L=SH, O=zlikun.com, OU=zlikun.com, CN=api.zlikun.com
输入 <api.zlikun.com> 的密钥口令
        (如果和密钥库口令相同, 按回车):
[正在存储api.zlikun.com.keystore]


# 查看生成的密钥库(-rfc，以文本方式查看)
$ keytool -list -rfc -keystore api.zlikun.com.keystore
输入密钥库口令:

密钥库类型: JKS
密钥库提供方: SUN

您的密钥库包含 1 个条目

别名: api.zlikun.com
创建日期: 2017-5-9
条目类型: PrivateKeyEntry
证书链长度: 1
证书[1]:
-----BEGIN CERTIFICATE-----
MIIDczCCAlugAwIBAgIEW3t2LzANBgkqhkiG9w0BAQsFADBqMRcwFQYDVQQDEw5h
cGkuemxpa3VuLmNvbTETMBEGA1UECxMKemxpa3VuLmNvbTETMBEGA1UEChMKemxp
a3VuLmNvbTELMAkGA1UEBxMCU0gxCzAJBgNVBAgTAlNIMQswCQYDVQQGEwJDTjAe
Fw0xNzA1MDkwODIxMDZaFw0yNzA1MDcwODIxMDZaMGoxFzAVBgNVBAMTDmFwaS56
bGlrdW4uY29tMRMwEQYDVQQLEwp6bGlrdW4uY29tMRMwEQYDVQQKEwp6bGlrdW4u
Y29tMQswCQYDVQQHEwJTSDELMAkGA1UECBMCU0gxCzAJBgNVBAYTAkNOMIIBIjAN
BgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuGuxlT2Mzz/vC+ut659RvhLtXXy5
LdOQtuEdJJiAvpfuyY9aGLMGEG5BZoal+aDtDesb9EViI3Q2k7tJUHNoPoPFcwx7
b5R+a0y1o/OeuTmH0Fh3iuO/Vj5d+gwHgoe1jNpVtLUFoGA88okGiEzIuTGLkNFO
tltfT4uZOr7azEYEqWbTjDU+9ntOnONXvLJyXOa+QT14LxhOtP7C6jg3ENmKqaqD
B6QCFNxZfG23LnowzvS/X4pFRFRDeT5eNZ6SnWbEYDx6zKDbAm+1imVf5SqY3lmg
K0ubp1g+Ey6TNuN1Zu8Kc6ASN75sRQ4Fd/61z6dzmxarcyeQeP8Jd4b7lQIDAQAB
oyEwHzAdBgNVHQ4EFgQUIw9pCXyzqBcbOp3sdyGvlZ09lvEwDQYJKoZIhvcNAQEL
BQADggEBAKe4/OoSuwYWz8dnt9N5epjxEZgo39DYDx9YpfkHm28Fy3Ldl4w8DpAi
mXg5AXGPTIMhWg8ZnUfj5Q/E4qwY2Occ5dsZBuS71Bjks119AuCilJhpUF+1ck+4
EDhhGOGHRYgsIl6P8Gn7kPc5+IFEYbHWfXKvVLzbKTF4LhpLpYMb2gSX4ZxDdxE1
i85n1XJoD/2mwEz/e1fs4JYD5f2WOy5EfCjfzKg73jmbMWhzpA6tpFJ7Ba2Ho6e6
QhA2z6njHedDu7P3oVeCwPuYhV7Fa8P58xkz1UlZYqyng9cBC7rrNtdRAS3YiVf3
yZGYwnrJ6P33s8sYxy2mLMz3BAdWrJ4=
-----END CERTIFICATE-----


*******************************************
*******************************************



```

测试文件：`api.zlikun.com.keystore`，输出私钥：
```
-----BEGIN RSA PRIVATE KEY-----
MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC4a7GVPYzPP+8L663rn1G+Eu1d
fLkt05C24R0kmIC+l+7Jj1oYswYQbkFmhqX5oO0N6xv0RWIjdDaTu0lQc2g+g8VzDHtvlH5rTLWj
8565OYfQWHeK479WPl36DAeCh7WM2lW0tQWgYDzyiQaITMi5MYuQ0U62W19Pi5k6vtrMRgSpZtOM
NT72e06c41e8snJc5r5BPXgvGE60/sLqODcQ2YqpqoMHpAIU3Fl8bbcuejDO9L9fikVEVEN5Pl41
npKdZsRgPHrMoNsCb7WKZV/lKpjeWaArS5unWD4TLpM243Vm7wpzoBI3vmxFDgV3/rXPp3ObFqtz
J5B4/wl3hvuVAgMBAAECggEAYgLqOBSkFyk1VDKHFihv+wJ4IC0/BTnhOTBemqpqEeERSRHkoifS
2zydiW2aHBcIpVMEYkfVpl4G+3CXwCf1iPPjJgfGOkDDByeXiXDaNDp9EZ9SuP/dGmq+6zslapvR
QvBuiEGEof1DpupoDFwyeSn5sJdsq8HX36CjaPMxuUm7+a9Hn2q592Fl5SMwcN1jAusXcxGql0AX
jZcxXAigYOChBXR3wnaWqZEjoP3zVF0Coq1PYn2lS2eUiLK+AQD0aMeA2lMQODR+sBTsAs08Z3WG
9Al7s9Hh/tjuAXOqPJk0x4IvF5Q54v0AyLNVGw9xTVz+FXDCahfNmrg0njHKaQKBgQD4hUaFvA4e
3wCHqjAkwd4Uk533NcxUt0YJNGD9r+424L7cjnMVn/9mAPnluZFFdlgMhgVY7WaD6TMxgaOwwrHI
W7v0rnsu+yyPf+B9I83qcmXJuKVERqP41v+kT0qnttg54kXn9a/yO6XDQrU0icm+07diq1nPR+CQ
jhBnewjgPwKBgQC9+I+aP3GZlhDTN/VSnR5WZ5ZnxeNf4NF4xzq+kwSobscJuwnRsieUAZlp+FP3
ojpYYkc/K0N292Xs4IuXF5ZFNYLQvUQIoUb36DKrKltP9VHaNHIFGFBGgeKzWuQN9HjEPMVPZvAC
4nP6Y/Bu3TWuss2g0Q19jcP7Jqya+LJvKwKBgH41IKVV5U3kLh63XQ+trJyCgtxCY+w5zsVp5j+v
Eqin5gWYCfXXdRpWMJJrdMzz1q81eI26si8XRLpFfd2MYkC/5WnTGhe30t9lL5odjrsbMXoJ3VQZ
E7iRZu4Inn8hqhlrQdkUui+n/vx0ahWuS9gvKJhhMJDFjdObcdU7dDx9AoGAJ8Ihq9KjX9V8Z/hK
K6QeeU7st4M+9w4CSRjJPqFeVDH7DhpLD8wNZHiBNyB10lnthBntd1cCo59bgZvtGw4gsRNl8XHr
DzNRuwipshEHU1CwdM6NHmvcQSVl4Mug9xq3O7rMQLX2fWQPidPougsp9QoO5x1ZiXFmFuYdMcHi
9kkCgYEAxiROS8FcomB6O+yphk+PoyRF+ixL6ZY4qHX/yK+EEQafuYWho1SHlWDj691bIVY79NI1
K7ESdTGtBAACg2/QPjlsJeW9lBnyqtJIb8w31L76BOA6W3rTRG7AR0U4uLS3bNqM43B+e9hVJTtD
YxN1XJkKbt7qAiMoGuKA0FeFqcU=
-----END RSA PRIVATE KEY-----
```