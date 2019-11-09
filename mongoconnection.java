package mongoconnection;
import java.util.*;

import org.bson.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.mongodb.client.FindIterable;
import com.mongodb.MongoCredential;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class mongoconnection {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		Scanner sc1=new Scanner(System.in);
		//connecting to database
		MongoClient client=new MongoClient("localhost",27017);
		List<String>database=client.getDatabaseNames();
		System.out.println(database);
		
		MongoCredential credentials=MongoCredential.createCredential("aditi", "aditidb", "password".toCharArray());
		//accessing database
		MongoDatabase db=client.getDatabase("aditidb");
		
		
		
		MongoCollection<Document>col=db.getCollection("sample1");
		System.out.println("Database connection successful");
		
		//inserting object into database
		Document d1=new Document("title","exampractice").append("Name", "Aditi").append("Class", "TE");
		col.insertOne(d1);
		System.out.println("document inserted successfully");
		int k=1;
		while(k==1)
		{
			System.out.println("Enter name");
			String name=sc.next();
			System.out.println("Enter class");
			String classname=sc.next();
			System.out.println("Enter 1 to insert more");
			k=sc1.nextInt();
			Document d2=new Document("Name",name).append("Classname", classname);
			col.insertOne(d2);
		}
		
		FindIterable<Document>iter=col.find();
		Iterator<Document>i=iter.iterator();
		System.out.println("Printing documents..");
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		
		//updating the document
		col.updateOne(Filters.eq("Name", "Aditi"), Updates.set("Name", "Aditi Kukde"));
		iter=col.find();
		i=iter.iterator();
		System.out.println("Displaying documents...");
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		System.out.println("Want to delete?(1)");
		int choice=sc.nextInt();
		if(choice==1)
		{
			//deleting a document
			col.deleteOne(Filters.eq("Name","Aditi Kukde"));
		}
		
		//listing of collections
		System.out.println("Listing of collections..");
		for(String name: db.listCollectionNames())
		{
			System.out.println(name);
		}
		col.drop();
		System.out.println("After dropping colletcion..");
		for(String name: db.listCollectionNames())
		{
			System.out.println(name);
		}
		
		
	}

}
