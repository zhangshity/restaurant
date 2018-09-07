package com.zcy.restaurant.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Key;

import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class KeyUtilTest {

    @Test
    public void genUniqueKey() {
        System.out.println(KeyUtil.genUniqueKey());
    }
}