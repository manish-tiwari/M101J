package com.mongodb;

import spark.*;
import spark.Response;

/**
 * Created by manish on 11/8/15.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {

                return "Hello world from Spark ";
            }
        });
    }
}
