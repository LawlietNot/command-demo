package com.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
        //https://blog.csdn.net/qq_38361800/article/details/90437824 复制剪切板失败
        builder.headless(false).run(args);
    }

}
