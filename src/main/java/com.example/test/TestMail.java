package com.example.test;


import com.example.utils.Mail.MailContentTypeEnum;
import com.example.utils.Mail.MailSender;
import com.example.utils.Mail.MailUtil;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;


public class TestMail {
    public static void main(String[] args) throws Exception
    {
        String info  = MailUtil.getTemplates();
        String path =ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "templates/Mail/mail1.html").getPath() ;
        new MailSender()
                .title("测试SpringBoot发送邮件")
                .content(""+info)
                .contentType(MailContentTypeEnum.HTML)
                .targets(new ArrayList<String>(){{
                    add("15182659077@163.com");
                }}).send();
        System.out.println("发送成功！");
    }
}
