package arrayandsimpleobjects;
import java.util.*;

import org.bson.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
public class arrayobjects {

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		//connecting to databse
		MongoClient client=new MongoClient("localhost",27017);
		List<String>database=client.getDatabaseNames();
		System.out.println(database);
		
		MongoCredential credentials=MongoCredential.createCredential("aditi", "aditidb", "password".toCharArray());
		MongoDatabase db=client.getDatabase("aditidb");
		System.out.println("Connection successful");
		
		MongoCollection<Document>col=db.getCollection("sample2");
		MongoCollection<Document>col1=db.getCollection("sample3");
		
		JSONObject obj=new JSONObject();
		System.out.println("Enter Name");
		String name=sc.next();
		System.out.println("Enter class");
		String classname=sc.next();
		obj.put("Name", name);
		obj.put("Class",classname);
		System.out.println(obj);
		
		JSONObject obj1=new JSONObject();
		System.out.println("Enter Name");
		String name1=sc.next();
		System.out.println("Enter class");
		String classname1=sc.next();
		obj1.put("Name", name1);
		obj1.put("Class",classname1);
		System.out.println(obj1);
		
		JSONObject obj2=new JSONObject();
		System.out.println("Enter Name");
		String name2=sc.next();
		System.out.println("Enter class");
		String classname2=sc.next();
		obj2.put("Name", name2);
		obj2.put("Class",classname2);
		System.out.println(obj2);
		
		JSONArray arr=new JSONArray();
		arr.add(obj);
		arr.add(obj1);
		arr.add(obj2);
		
		System.out.println(arr);
		System.out.println("First object of array:-"+ arr.get(0));
		
		//inserting into database
		for(int i=0;i<3;i++)
		{
			Document d1=Document.parse(arr.get(i).toString());
			col.insertOne(d1);
		}
		
		FindIterable<Document>iter=col.find();
		Iterator<Document>i=iter.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		List<Document>listdoc=new ArrayList<Document>();
		for(int k=0;k<3;k++)
		{
			Document d2=Document.parse(arr.get(k).toString());
			listdoc.add(d2);
		}
		System.out.println("listdoc:-"+listdoc);
		col1.insertMany(listdoc);
		
		iter=col1.find();
		i=iter.iterator();
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		
		
		
		
		
		
		
		
		
		
	}
}
