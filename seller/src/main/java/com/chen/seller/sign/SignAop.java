package com.chen.seller.sign;

import com.chen.seller.service.SignService;
import com.chen.util.RsaUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 验签 AOP
 *
 * @Author LeifChen
 * @Date 2018-12-03
 */
@Component
@Aspect
public class SignAop {

    private final SignService signService;

    public SignAop(SignService signService) {
        this.signService = signService;
    }

    @Before(value = "execution(* com.chen.seller.controller.*.*(..)) && args(authId,sign,text,..)")
    public void verify(String authId, String sign, SignText text) {
        String publicKey = signService.getPublicKey(authId);
        Assert.isTrue(RsaUtil.verify(text.toText(), sign, publicKey), "验签失败");
    }
}
