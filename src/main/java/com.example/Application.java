package com.example;

import com.example.entity.Customers;
import com.example.mapper.CustormersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author: wangxc
 * @GitHub: https://github.com/vector4wang
 * @CSDN: http://blog.csdn.net/qqhjqs?viewmode=contents
 * @BLOG: http://vector4wang.tk
 * @wxid: BMHJQS
 */
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement // 开始事务支持
public class Application {

    @Autowired
    private CustormersMapper mapper;

    @PostConstruct
    public void test() {
        List<Customers> customers = mapper.selectList();
        System.out.println(customers);
    }



    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
