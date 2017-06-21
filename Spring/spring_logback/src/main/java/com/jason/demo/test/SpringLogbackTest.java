package com.jason.demo.test;

import org.junit.Test;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/6/21.
 */
public class SpringLogbackTest {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(SpringLogbackTest.class);
    @Test
    public void test(){
        logger.info("logback info 成功了");
        logger.error("logback error 成功了");
        logger.debug("logback debug 成功了");
    }
}
