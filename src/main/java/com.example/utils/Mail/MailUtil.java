package com.example.utils.Mail;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.util.ResourceUtils;

public class MailUtil {

    public static String getTemplates(){
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            //获取模板html文档
            document = reader.read(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "templates/Mail/mail1.html").getPath());
            Element root = document.getRootElement();
            Element message = getNodes(root,"id","url");
            Element time = getNodes(root, "id", "time");
            String url = "http://www.oboom.com";
            //设置收件人姓名，通知信息、当前时间
            Calendar calendar = Calendar.getInstance();
            time.setText(calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+calendar.get(Calendar.DATE)+"日");
            //随便写的
            message.setText("<a href=\""+url+"\">"+url+"</a>");
            //保存到临时文件
            FileWriter fwriter = new FileWriter("d:/temp.html");
            XMLWriter writer = new XMLWriter(fwriter);
            writer.write(document);
            writer.flush();
            //读取临时文件，并把html数据写入到字符串str中，通过邮箱工具发送
            FileReader in = new FileReader("d:/temp.html");
            char[] buff = new char[1024*10];
            in.read(buff);
            String str = new String(buff);
            String info =str.toString().replaceAll("&lt;","<");
            String rs = info.toString().replaceAll("&gt;",">");
            return rs.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 方法描述：递归遍历子节点，根据属性名和属性值，找到对应属性名和属性值的那个子孙节点。
     * @param node 要进行子节点遍历的节点
     * @param attrName 属性名
     * @param attrValue 属性值
     * @return 返回对应的节点或null
     */
    public static Element getNodes(Element node, String attrName, String attrValue) {
        final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性
        for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性
            final String name = attr.getName();// 属性名称
            final String value = attr.getValue();// 属性的值
            System.out.println("属性名称：" + name + "---->属性值：" + value);
            if(attrName.equals(name) && attrValue.equals(value)){
                return node;
            }
        }
        // 递归遍历当前节点所有的子节点
        final List<Element> listElement = node.elements();// 所有一级子节点的list
        for (Element e : listElement) {// 遍历所有一级子节点
            Element temp = getNodes(e,attrName,attrValue);
            // 递归
            if(temp != null){
                return temp;
            };
        }
        return null;
    }
}
