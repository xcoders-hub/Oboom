package com.example.config.SpringConfig;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author liangkanglin
 * @date 2018/8/2 14:30
 * @param 
 * @return
 * code:Springboot配置错误页面
 */
@Configuration
public class ErrorPageConfig {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {

            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
//                ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/400.html");
//                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/errorPage/404");
//                container.addErrorPages(error400Page, error401Page, error404Page, error500Page);
                container.addErrorPages( error404Page);
            }
        };
    }

}