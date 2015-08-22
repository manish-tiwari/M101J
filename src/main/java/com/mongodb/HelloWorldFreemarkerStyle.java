/*
package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * Created by manish on 11/8/15.
 *//*

public class HelloWorldFreemarkerStyle {
    public static void main(String[] args) {
        Configuration configuration=new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");
        try {
        Template hellotemplate = configuration.getTemplate("hello.ftl");
        StringWriter writer = new StringWriter();
        Map<String,Object> helloMap=new HashMap<String, Object>();
        helloMap.put("name", "Freemarker");

            hellotemplate.process(helloMap,writer);
            System.out.println(writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
*/
