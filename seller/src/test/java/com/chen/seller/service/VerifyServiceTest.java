package com.chen.seller.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * VerifyServiceTest
 *
 * @Author LeifChen
 * @Date 2018-12-04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VerifyServiceTest {

    @Autowired
    private VerifyService verifyService;

    @Test
    public void makeVerificationFile() {
        Date day = new GregorianCalendar(2018, 11, 3).getTime();
        File file = verifyService.makeVerificationFile("C1", day);
        System.out.println(file.getAbsoluteFile());
    }

    @Test
    public void saveChannelOrders() {
        Date day = new GregorianCalendar(2018, 11, 3).getTime();
        verifyService.saveChannelOrders("C1", day);
    }

    @Test
    public void verifyOrder() {
        Date day = new GregorianCalendar(2018, 11, 3).getTime();
        System.out.println(String.join(";", verifyService.verifyOrder("C1", day)));
    }
}