package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manish on 11/8/15.
 */
public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration=new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, spark.Response response) {
                StringWriter writer = new StringWriter();
                try {
                    Template hellotemplate = configuration.getTemplate("hello.ftl");

                    Map<String,Object> helloMap=new HashMap<String, Object>();
                    helloMap.put("name", "Freemarker");

                    hellotemplate.process(helloMap,writer);
                    System.out.println(writer);
                } catch (TemplateException e) {
                    halt(500);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }
}
