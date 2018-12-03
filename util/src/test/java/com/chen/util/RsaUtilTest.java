package com.chen.util;

import org.junit.Test;

/**
 * RsaUtilTest
 *
 * @Author LeifChen
 * @Date 2018-11-28
 */
public class RsaUtilTest {

    static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLZWmOZJR8+s8VdwIcSWSKCLue\n" +
            "Xwv37xNoHe4/3XAFOpExwbghfsU7yhX242QbzEOKN/qptuBjL/bfmz0JymxC3z1S\n" +
            "sWNo1kq060NIwAve7T0ufCLKqaXrKCRGpbG41WGXgO32tB39Xzxq9u79TcLQF79Q\n" +
            "mqivA6f9rvTTDM74rwIDAQAB";
    static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMtlaY5klHz6zxV3\n" +
            "AhxJZIoIu55fC/fvE2gd7j/dcAU6kTHBuCF+xTvKFfbjZBvMQ4o3+qm24GMv9t+b\n" +
            "PQnKbELfPVKxY2jWSrTrQ0jAC97tPS58IsqppesoJEalsbjVYZeA7fa0Hf1fPGr2\n" +
            "7v1NwtAXv1CaqK8Dp/2u9NMMzvivAgMBAAECgYApzfg6GhYi8fHqOYGnirYtYMof\n" +
            "LO3q0aqtdaloWv0RNg5tqaFM0R98wHZvPiFvTTZ8jDDq/Pl1qYAWSXPsNTy2zBou\n" +
            "uFjcrOP1SYrmpV2bd9+4WTItOzPfh+SODbOxwkQjRhPyJ43CaCGEeUbGvCkVLljI\n" +
            "GuU+dzXDGCI6WAM0sQJBAOTmLN65AjokzDSe4FvYEB9Zdhz9MeY8rh5RrmqGxmSW\n" +
            "ewySTzPBmG4MPUBHKahCzgFSgyEo220d4ysI3xDx33UCQQDjekDzx9bluWLP9Byr\n" +
            "bdV3R35GPXWKG8JiHoCfLYq/vjJEtTPOqflprr3fMFwfvrMmnj7doY3K1mJ4kciI\n" +
            "jHcTAkBPOkx1mtvmfC4iWIuXvqV19GUpi4nmA0LRQ7x3Kscosd1NcKbxZyT3kkzx\n" +
            "HrCQiO79Di5NH/MzRZEHqD5tgwqxAkEAtqZdLOETDhXS34L02jkwo7vGCoQisqgj\n" +
            "ZkFzqVwaAmenWT/dv1z/pPnn/yOHdP3cP2/krnSfs3R8UK9Nw0Z1/QJAB2Xp2ToS\n" +
            "LvMQhxAnsDMp5NkdiGOqg+xgpIccZrEz1zdiUhVdJrRarrBeaui7y2G27O/4Dlml\n" +
            "0jlMG2S961EXAg==";

    @Test
    public void verify() {
    }

    @Test
    public void sign() {
        String text = "{\"amount\":100,\"channelId\":\"C1\",\"channelUserId\":\"U1\",\"gmtCreate\":\"2018-12-03 14:30:00\",\"memo\":\"test\",\"outerOrderId\":\"O001\",\"productId\":\"T001\"}";
        String sign = RsaUtil.sign(text, privateKey);
        System.out.println(sign);
        System.out.println(RsaUtil.verify(text, sign, publicKey));
    }
}