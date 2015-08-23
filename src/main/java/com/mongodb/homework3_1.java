package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
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
 * Created by manish on 22/8/15.
 */
public class homework3_1 {

    public static void printJson(Document document) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(), new JsonWriterSettings(JsonMode.SHELL, false));
        new DocumentCodec().encode(jsonWriter, document, EncoderContext.builder().isEncodingCollectibleDocument(true).build());
        System.out.println(jsonWriter.getWriter());
        System.out.flush();
    }

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        MongoDatabase schoolDatabase = client.getDatabase("school");
        MongoCollection<Document> studentsCollection = schoolDatabase.getCollection("students", Document.class);

        //Bson filter = new Document("type", "homework");
        Bson sortingFilter = new Document("_id", 1).append("scores.score", 1);
        //  Bson projectionFilter=new Document("_id",1).append("scores",1);
        List<Document> students = studentsCollection.find().sort(sortingFilter).into(new ArrayList<Document>());
        int counter = 1, counter1 = 1, counter2 = 1, counter3 = 1;
        for (Document student : students) {
            //printJson(student);
            System.out.println("counter---->" + counter);
            System.out.println("_id---->" + student.get("_id"));
            ArrayList<Document> arrayOfDocumentOfScoresOfDiffTypes = (ArrayList<Document>) student.get("scores");
            System.out.println("scores--->" + arrayOfDocumentOfScoresOfDiffTypes);
            double lowestHomeworkScore = 9999999.9;
            int iterationCount = 0;
            for (Document scoreDoc : arrayOfDocumentOfScoresOfDiffTypes) {
                double currentHomeworkScore = scoreDoc.getDouble("score");

                if (scoreDoc.get("type").equals("homework")) {
                    iterationCount++;
                    if (iterationCount == 1) {
                        lowestHomeworkScore = currentHomeworkScore;
                    }
                    System.out.println("type =homework...count---->" + counter1++);
                    if ((currentHomeworkScore < lowestHomeworkScore) && (iterationCount == 2)) {
                        System.out.println("iteration no. in innermost loop---->" + counter2);
                        lowestHomeworkScore = currentHomeworkScore;
                        counter2++;

                    }
                }
            }
            System.out.println("lowest score in homework of student id :" + student.get("_id") + "--->" + lowestHomeworkScore);
            studentsCollection.updateOne(new Document("_id", student.get("_id")), new Document("$pull", new Document("scores", new Document("type", "homework")
                    .append("score", lowestHomeworkScore))));
/*
 //           for (Document document:arrayOfDocumentOfScoresOfDiffTypes){
                System.out.println("document-->" + document);
                System.out.println("counter in looping through the arrayOfDocumentOfScoresOfDiffTypes :document"+counter1);
                counter1++;

                *//*for(String key: document.keySet()){
                    System.out.println("counter in looping through the document.keySet() :key-->"+counter3);
                    counter3++;
                    System.out.println("key--->"+key);
                }*//*
              *//* int lowestScoreInHomework=0;
                for (Map.Entry field:document.entrySet()){
                    System.out.println("counter in looping through the document.entrySet() :field-->"+counter2);
                    counter2++;
                    System.out.println("Key-------------->"+field.getKey()+"value----------->"+field.getValue());
                    if (field.getValue().equals("homework")){

                    }
                }*//*


            }*/
           /* int n=counter%2;
            // commenting so that doesn't get deleted by mistake .
            if (n!= 0) {
                coll.deleteOne(grade);
            }*/
            counter++;


        }
        System.out.printf("total students----->" + studentsCollection.count());
        System.out.printf("count of student containing homework--->" + students.size());
    }


}
