package com.ljheee.xml.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.*;

/**
 *  dom4J 解析web.xml
 */
public class ResolveXml {



    public static Map resolveXml() throws DocumentException, ClassNotFoundException {
        Map<String,Class<?>> urlServletMapping = new HashMap<>();
        Map<String,Class<?>> servletMapping = new HashMap<>();

        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("web.xml");

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);

        // 获取根节点对象
        Element rootElement = document.getRootElement();
        List<Element> childs = rootElement.elements();
        for (Element childElement : childs) {
            String childElementName = childElement.getName();
            switch (childElementName){
                case "servlet":
                    String servletName = childElement.element("servlet-name").getText();
                    String servletClass = childElement.element("servlet-class").getText();
                    servletMapping.put(servletName,Class.forName(servletClass));
                    break;
                case "servlet-mapping":
                    String servletName2 = childElement.element("servlet-name").getText();
                    String urlPattern = childElement.element("url-pattern").getText();
                    urlServletMapping.put(urlPattern, servletMapping.get(servletName2));
                    break;
            }
        }
        return urlServletMapping;
    }


    public static void main(String[] args) throws FileNotFoundException, DocumentException, ClassNotFoundException {

        Map map = resolveXml();
        System.out.println(Arrays.toString(new Set[]{map.entrySet()}));
    }

}
