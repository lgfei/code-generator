package com.lgfei.tools.code.generator.web;

import org.junit.Test;

public class ApiCodeGeneratorTest
{
    @Test
    public void testSplit()
    {
        String str = "com.lgfei.betterme.admin.model.entity";
        String[] arr = str.split("\\.");
        System.err.println(arr.length);
    }
}
