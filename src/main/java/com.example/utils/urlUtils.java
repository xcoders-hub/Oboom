package com.example.utils;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*生成0－2之间的随机数，包括2
Random rand = new Random();
int randNum = rand.nextInt(3);
生成5－26之间的随机数，包括26
int randNum = rand.nextInt(22)+5;*/

/**
 * @author liangkanglin
 * @date 2018/7/30 11:41
 * @param
 * @return
 * code:根据传入的API来获取返回API的值
 */
public final class urlUtils {

//    public static void main(String[] args) {
//        String info = infoByGet("http://api.laifudao.com/open/xiaohua.json");
//        JSONArray jsonArray =new JSONArray(new JSONObject(info).getString("msg"));
//        JSONObject jsonObject  =new JSONObject(jsonArray.get(new Random().nextInt(jsonArray.length())).toString());
////        System.out.println(""+jsonObject);
//        String str = "kdla8405118*)(^%*YTILKH89015jfkhka";
//        str = jsonObject.toString().replaceAll("[</><>br]","" );
//        String[] regs = { "！", "，", "。", "；", "!", ",", ".", ";" };
//        for ( int i = 0; i < regs.length / 2; i++ )
//        {
//            str = str.replaceAll (regs[i], regs[i + regs.length / 2]);
//        }
////        String[] regs = { "！", "，", "；", ",", ".", ";","“","”" };
//        String infos = StringEscapeUtils.unescapeJava(str);
//        System.out.println(infos.replace("　", ""));
//
////      String str="123assume345contribute";
////      System.out.println(str.replaceAll("\\d+",""));
//
//    }

    public static String infoByGet(final String urls){
        JSONObject result =new JSONObject();
        HttpURLConnection connection;
        try {
            URL url=new URL(urls);
            connection=(HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(4000);//设置连接超时时间
            connection.setReadTimeout(4000); //设置读取超时时间
            connection.setDoInput(true);
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Connection", "Keep-Alive");// 设置维持长连接
            connection.setUseCaches(false);// 设置不用缓存
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", "UTF-8");  //设置编码语言
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //设置请求体的长度
            connection.connect();
            if(connection.getResponseCode()==200){
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String rs = reader.readLine();
                reader.close();
                inputStream.close();
                result.put("flag",true);
                result.put("msg",""+rs);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.put("flag",false);
            result.put("msg","");
        }
        return result.toString();
    }
    /// 转全角的函数(SBC case) ///
    ///任意字符串
    /// 全角字符串 ///
    ///全角空格为12288,半角空格为32
    ///其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248 ///

    public static String ToSBC(String input)
    { //半角转全角：
        char[] c=input.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if (c[i]==32)
            {
                c[i]=(char)12288; continue;
            }
            if (c[i]<127) c[i]=(char)(c[i]+65248);
        }
        return new String(c);
    }

    /// /// 转半角的函数(DBC case) ///
    ///任意字符串
    /// 半角字符串 ///
    ///全角空格为12288，半角空格为32
    ///其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248 ///
    public static String ToDBC(String input)
    {
        char[] c=input.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if (c[i]==12288)
            {
                c[i]= (char)32; continue;
            }
            if (c[i]>65280 && c[i]<65375)
                c[i]=(char)(c[i]-65248);
        }
        return new String(c);
    }


}
