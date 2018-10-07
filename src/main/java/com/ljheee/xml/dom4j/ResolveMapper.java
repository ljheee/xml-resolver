package com.ljheee.xml.dom4j;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 *  dom4J 解析mybatis的 mapper.xml
 */
public class ResolveMapper {


    public static void main(String[] args) throws DocumentException {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("UserMapper.xml");

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);

        // 获取根节点对象
        Element rootElement = document.getRootElement();
        List<Element> childs = rootElement.elements();
        for (Element childElement : childs) {
            String childElementName = childElement.getName();
            switch (childElementName){
                case "resultMap":
                    // 遍历 所有属性
                    childElement.attribute("type");
                    for(Iterator it = childElement.attributeIterator(); it.hasNext(); ) {
                        Attribute attribute = (Attribute) it.next();
                        String text=attribute.getText();
                        System.out.println(text);
                    }
                    break;
                case "insert":
                    break;
                case "delete":
                    break;
                case "update":
                    break;
                case "select":
                    break;
            }
        }

    }




}
