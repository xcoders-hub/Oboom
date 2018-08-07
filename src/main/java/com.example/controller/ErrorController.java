package com.example.controller;

import com.example.utils.urlUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Random;
/**
 * @author liangkanglin
 * @date 2018/8/7 9:19
 * @param
 * @return
 * code:错误页面的控制器
 */

@Controller
@RequestMapping(value = "/errorPage")
public class ErrorController  {

    @RequestMapping(value = "/404")
    public ModelAndView notFound() {
        ModelAndView mav = new ModelAndView();
        String info = urlUtils.infoByGet("http://api.laifudao.com/open/xiaohua.json");
        JSONArray jsonArray =new JSONArray(new JSONObject(info).getString("msg"));
        JSONObject json  =new JSONObject(jsonArray.get(new Random().nextInt(jsonArray.length())).toString());
        String context = StringEscapeUtils.unescapeJava(json.getString("content").replaceAll("[</br><br>]",""));
        mav.addObject("title", ":( 页面不见了呢-404");
        for(int i = 0;i<10;i++){
            context= context.trim().replaceAll("　","");
        }
        mav.addObject("context",context );
        mav.setViewName("errorPage");
        return mav;
    }


}
