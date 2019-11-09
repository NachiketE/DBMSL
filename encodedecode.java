package encodedecodelast;
import java.util.*;

import org.bson.*;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.util.JSON;

public class encodedecode {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		Scanner sc1=new Scanner(System.in);
		MongoClient client=new MongoClient("localhost",27017);
		List<String>database=client.getDatabaseNames();
		System.out.println(database);
		MongoCredential credentials=MongoCredential.createCredential("user","database", "password".toCharArray());
		
		MongoDatabase db=client.getDatabase("datbase");
		MongoCollection<Document>col=db.getCollection("sample1");
		System.out.println("Connected successfully");
		int choice=0;
		boolean exit=false;
		while(!exit)
		{
			System.out.println("Enter choice\t1.encode\t2.decode\t3.exit");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1: //encoding
					JSONObject obj=new JSONObject();
					System.out.println("Enter name");
					String name=sc1.nextLine();
					System.out.println("Enter college");
					String college=sc1.nextLine();
					obj.put("Name",name);
					obj.put("College",college);
					Document doc=Document.parse(obj.toString());
					System.out.println("Object inserted:-"+obj);
					col.insertOne(doc);
					break;
			case 2: //decoding
					FindIterable<Document>iter=col.find();
					Iterator i=iter.iterator();
					while(i.hasNext())
					{
						doc=(Document)i.next();
						String json=JSON.serialize(doc);
						 obj=(JSONObject)JSONValue.parse(json);
						 System.out.println("Name:="+obj.get("Name")+"\tcollege:-"+obj.get("College"));
					}break;
			case 3: break;
			}
		}
		
		
	}

}
