package com.lhrl;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.kuanter.common.util.ThreadLocalUserUtils;

@ContextConfiguration(locations = {"/spring/lhrl_test_context.xml"})
public abstract class TestSupport extends AbstractTransactionalJUnit4SpringContextTests  {


    @BeforeClass
    public static void testStart() {
        System.out.println("test start...");
        ThreadLocalUserUtils.setUser("lhrl");
    }

    @AfterClass
    public static void endTest() {
        System.out.println("test End...");
    }

    @Test
    public void test() {
        System.out.println("test default...");
    }
}