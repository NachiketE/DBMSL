package mongo;
import org.bson.*;
import org.json.simple.*;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;

import java.util.*;

public class B6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Mongodb connectivity
		Scanner sc= new Scanner(System.in);
		MongoClient client=new MongoClient("localhost",27017);
		List<String>database=client.getDatabaseNames();
		System.out.println(database);
		
		//create credentials for authentication
		MongoCredential credentials=MongoCredential.createCredential("aditi", "database1", "database1".toCharArray());
		MongoDatabase db=client.getDatabase("database1");
		System.out.println("COnnection successful");
		
		MongoCollection<Document>col=db.getCollection("sample1");
		MongoCollection<Document>col1=db.getCollection("sample2");
		
		int n;
		System.out.println("Enter the number of documnets to be inserted");
		n=sc.nextInt();
		JSONArray arr=new JSONArray();
		
		for(int i=0;i<n;i++)
		{
			JSONObject obj=new JSONObject();
			System.out.println("Enter name: ");
			String name=sc.next();
			System.out.println("Enter class: ");
			String classname=sc.next();
			obj.put("Name", name);
			obj.put("Class", classname);
			System.out.println(obj);
			arr.add(obj);
		}
		
		System.out.println("The array of json objects is:\n"+ arr);
		
		//inserting into collection a single document at a time
		for(int i=0;i<n;i++)
		{
			Document d1=Document.parse(arr.get(i).toString());
			col.insertOne(d1);
		}
	
		//inserting into collection the entire arr at a time
		//first add all the documents into a list and then insert the list into collection
		List<Document>docs=new ArrayList<Document>();
		for(int i=0;i<n;i++)
		{
			Document d2=Document.parse(arr.get(i).toString());
			docs.add(d2);
		}
		System.out.println("ArrayList of documnets: "+docs);
		//inserting list into collection
		col1.insertMany(docs);
		
		//Displaying the data from col
		FindIterable<Document>iter=col.find();
		Iterator<Document>i=iter.iterator();	
		while(i.hasNext())
		{
			System.out.println(i.next());
		}
		
		//Displaying the data from col1
		FindIterable<Document>iter1=col.find();
		Iterator<Document>k=iter1.iterator();
		while(k.hasNext())
		{
			System.out.println(k.next());
		}
		
	}
	

}
