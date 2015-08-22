package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.*;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.conversions.Bson;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{

    public static void printJson(Document document) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),new JsonWriterSettings(JsonMode.SHELL,false));
        new DocumentCodec().encode(jsonWriter,document, EncoderContext.builder().isEncodingCollectibleDocument(true).build());
        System.out.println(jsonWriter.getWriter());
        System.out.flush();
    }

    public static void main( String[] args ) throws UnknownHostException
    {
        MongoClient client = new MongoClient();
        MongoDatabase db=client.getDatabase("students");
        MongoCollection<Document> coll=db.getCollection("grades", Document.class);

        Bson filter = new Document("type", "homework");
        Bson sortingFilter = new Document("student_id", 1).append("score", 1);
        Bson projectionFilter=new Document("student_id",1).append("score",1).append("_id",0);
        List<Document> gradesListAfterFilteration = coll.find(filter).projection(projectionFilter).sort(sortingFilter).into(new ArrayList<Document>());
        /*int prevTempStudentId=-1;*/
        int counter=1;
        for (Document grade : gradesListAfterFilteration) {
           /* int tempStudentId=grade.student_id;
            if (prevTempStudentId!=tempStudentId) {
                coll.deleteOne(grade);gradesListAfterFilteration
                prevTempStudentId++;
            }*/
            printJson(grade);
            System.out.println("counter---->"+counter);
            int n=counter%2;
            // commenting so that doesn't get deleted by mistake .
            if (n!= 0) {
                coll.deleteOne(grade);
            }
            counter++;


        }
        System.out.printf("total grades----->"+coll.count());
        System.out.printf("count of grades containing homework--->"+gradesListAfterFilteration.size());
    }
}
