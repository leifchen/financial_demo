package com.chen.seller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 签名 Service
 *
 * @Author LeifChen
 * @Date 2018-12-03
 */
@Service
public class SignService {

    static Map<String, String> PUBLIC_KEYS = new HashMap<>();

    static {
        PUBLIC_KEYS.put("1000", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLZWmOZJR8+s8VdwIcSWSKCLue\n" +
                "Xwv37xNoHe4/3XAFOpExwbghfsU7yhX242QbzEOKN/qptuBjL/bfmz0JymxC3z1S\n" +
                "sWNo1kq060NIwAve7T0ufCLKqaXrKCRGpbG41WGXgO32tB39Xzxq9u79TcLQF79Q\n" +
                "mqivA6f9rvTTDM74rwIDAQAB");
    }

    /**
     * 根据授权编号获取公钥
     *
     * @param authId
     * @return
     */
    public String getPublicKey(String authId) {
        return PUBLIC_KEYS.get(authId);
    }
}
