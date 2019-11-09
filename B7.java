package mongo;
import org.bson.*;
import org.json.simple.*;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import java.util.*;

public class B7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc= new Scanner(System.in);
		//getting the moogoclient and displaying the database names;
		
		MongoClient client=new MongoClient("localhost",27017);
		List<String>databases=client.getDatabaseNames();
		System.out.println("Databses are: "+ databases);
		
		//creating credentials and then connection
		MongoCredential credentials=MongoCredential.createCredential("aditi", "database2", "database2".toCharArray());
		//get the database
		MongoDatabase db=client.getDatabase("database2");
		System.out.println("Connected to database");
		
		//accesing the collections to enable encoding and decoding
		MongoCollection<Document>col=db.getCollection("sample1");
		
		int ch;
		do
		{
			System.out.println("Choice?1.Encoding\n2.Decoding\n3.Exit");
			ch=sc.nextInt();
			JSONObject obj=new JSONObject();
			Document doc=new Document();
			switch(ch)
			{
			case 1:
			{
				List<Document>doclist=new ArrayList<Document>();
				JSONArray arr=new JSONArray();
				int k=1;
				int cnt=0;
				do
				{
					System.out.println("Enter name: ");
					String name=sc.next();
					System.out.println("Enter class: ");
					String classname=sc.next();
					obj.put("Name", name);
					obj.put("Class", classname);
					arr.add(obj);
					System.out.println("Enter 1 to put more documents");
					k=sc.nextInt();
					cnt++;
					
				}while(k!=0);
				System.out.println("Documents entered in collection are: "+cnt);
				//putting these documents into an arraylist
				for(int i=0;i<cnt;i++)
				{
					Document d1=Document.parse(arr.get(i).toString());
					doclist.add(d1);
				}
				col.insertMany(doclist);
				break;
			
				
			}
			case 2:
			{
				FindIterable<Document>iter=col.find();
				Iterator<Document>i=iter.iterator();
				while(i.hasNext())
				{
					doc=(Document)i.next();
					String json=JSON.serialize(doc);
					obj=(JSONObject)JSONValue.parse(json);
					System.out.println("Name: "+obj.get("Name")+"\tClass: "+obj.get("Class"));
				}
				break;
			}
			case 3:
			{
				System.out.println("Exiting");
				break;
			}
			}
			
		}while(ch!=3);

}
}