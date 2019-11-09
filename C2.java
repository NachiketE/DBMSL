package mongo;
import java.util.*;
import org.bson.*;
import org.json.simple.*;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;


public class C2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc=new Scanner(System.in);
		//Getting the mongoclient and displaying the databseses available
		MongoClient client=new MongoClient("localhost",27017);
		List<String>databases=client.getDatabaseNames();
		System.out.println("The databses are: "+ databases);
		
		//Creating mongocredentials for authentication
		MongoCredential credentials=MongoCredential.createCredential("aditi","database3","database3".toCharArray());
		//get the database
		MongoDatabase db=client.getDatabase("database3");
		System.out.println("Connected to database");
		
		//Accessing the collection to enable operations
		MongoCollection<Document>col=db.getCollection("sample1");
		MongoCollection<Document>col1=db.getCollection("sample2");
		//All the documents will be inserted in this collection
		
		int ch;
		do
		{
			System.out.println("Choice\n1.Insert a single document\n2.Display Documents\n3.Update Documnets\n4.Delete Documents\n5.Drop Collection\n6.Decoding\n7Exit");
			ch=sc.nextInt();
			JSONObject obj=new JSONObject();
			Document doc=new Document();
			switch(ch)
			{
			case 1:
			{
				JSONArray arr=new JSONArray();
				//Encoding the array of documents
				int k=1;
				int cnt=0;
				do
				{
					System.out.println("Enter name: ");
					String name=sc.next();
					System.out.println("Enter class: ");
					String classname=sc.next();
					obj.put("Name",name);
					obj.put("Class",classname);
					arr.add(obj);
					System.out.println("Enter 1 to put more documents");
					k=sc.nextInt();
					cnt++;
				}while(k!=0);
				//An array of the objects is created. Now we have to put it in the document and then insert in collection
				System.out.println("No. of documents inserted is: "+cnt);
				for(int i=0;i<cnt;i++)
				{
					Document d1=Document.parse(arr.get(i).toString());
					col.insertOne(d1);
				}
				break;
				
			}
			case 2:
			{
				FindIterable<Document>iter=col.find();
				Iterator<Document>i=iter.iterator();
				while(i.hasNext())
				{
					System.out.println(i.next());
				}
				break;
			}
			case 3:
			{
				int k=1;
				do 
				{
					System.out.println("Enter the field to be updated(Name or Class)?");
					String f=sc.next();
					System.out.println("Enter the value to be updated");
					String old=sc.next();
					System.out.println("Enter the updated value: ");
					String new1=sc.next();
					//Update command
					col.updateOne(Filters.eq(f, old),Updates.set(f,new1));
					System.out.println("Do you want o update more documents(1for yes/0 for no)?");
					k=sc.nextInt();
				}while(k!=0);
				System.out.println("The updated documnets are:");
				FindIterable<Document>iter=col.find();
				Iterator<Document>i=iter.iterator();
				while(i.hasNext())
				{
					System.out.println(i.next());
				}
				
				break;
			}
			case 4:
			{
				int k=1;
				do
				{
					System.out.println("Enter field to be deleted: ");
					String f=sc.next();
					System.out.println("Enter the value to be deleted: ");
					String val=sc.next();
					//Delete command
					col.deleteOne(Filters.eq(f,val));
					System.out.println("Enter 1 to delete more documents ");
					k=sc.nextInt();
				}while(k!=0);
				System.out.println("Documnets after deletion:");
				FindIterable<Document>iter=col.find();
				Iterator<Document>i=iter.iterator();
				while(i.hasNext())
				{
					System.out.println(i.next());
				}
				break;
			}
			case 5:
			{
				System.out.println("The collections in dataase are:");
				for(String name1: db.listCollectionNames())
				{
					System.out.println(name1);
				}
				System.out.println("Enter the name of collection to be droped:");
				String name2=sc.next();
				MongoCollection<Document>coll=db.getCollection(name2);
				coll.drop();
				System.out.println("The list of collections after droping: ");
				for(String name3 : db.listCollectionNames())
				{
					System.out.println(name3);
				}
				break;
			}
			case 6:
			{
				FindIterable<Document>iter=col.find();
				Iterator<Document>i=iter.iterator();
				while(i.hasNext())
				{
					//putting the value into a documnet
					Document d1=(Document)i.next();
					//Converting that documnet to string
					String json=JSON.serialize(d1);
					//now putting this stirng into object as jsonvalue
					obj=(JSONObject)JSONValue.parse(json);
					//now displaying the object
					System.out.println("Name: "+obj.get("Name")+"\tClass: "+obj.get("Class"));
				}
				break;
			}
			case 7:
			{
				System.out.println("Exiting");
				break;
			}
			}
		}while(ch!=7);
		
	}

}
