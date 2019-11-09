package jdbc;
import java.sql.*;
import java.util.*;

public class a2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/";
		String driver="com.mysql.jdbc.Driver";
		String dbname="a2";
		String uname="root";
		String pwd="root";
		String table_name=null;
		Scanner sc= new Scanner(System.in);
		int n;
		
		try 
		{
			Class.forName(driver);
			Connection con= DriverManager.getConnection(url+dbname, uname, pwd);
			Statement st=con.createStatement();
			
			do
			{
				System.out.println("Enter choice\n1.Create view\n2.Display view\n3.Drop view\n4.Create Index\n5.Show Index\n6.Drop index\n7.Create Sequence\n8.Exit");
				n=sc.nextInt();
				switch(n)
				{
				case 1:
				{
					System.out.println("Enter the table and view name");
					table_name=sc.next();
					String view_name=sc.next();
					String sql="create view "+ view_name+" as select prof_fname,prof_lname,salaray from "+table_name+ ";";
					st.executeUpdate(sql);
					System.out.println("View created");
					break;
					
				}
				case 2:
				{
					System.out.println("Enter the view name to be displayed");
					String view_name=sc.next();
					ResultSet re=st.executeQuery("select * from "+ view_name);
					while(re.next())
					{
						String fname=re.getString("prof_fname");
						String lname=re.getString("prof_lname");
						int salary=re.getInt("salaray");
						System.out.println(fname+"\t"+lname+"\t"+salary);
					}
					break;
				}
				case 3:
				{
					System.out.println("Enter the view name to be dropped");
					String view_name=sc.next();
					st.executeUpdate("drop view "+view_name);
					System.out.println("View Dropped");
					break;
				}
				case 4:
				{
					int ch;
					System.out.println("Choice?\n1.Simple index\n2.Unique Index\n3.CompositeIndex");
					ch=sc.nextInt();
					switch(ch)
					{
					case 1:
					{
						System.out.println("Enter the table and index name");
						table_name=sc.next();
						String index_name=sc.next();
						System.out.println("Enter the column name for indexig");
						String column_name=sc.next();
						String sql="create index "+index_name+" on "+table_name+"("+column_name+");";
						st.executeUpdate(sql);
						System.out.println("Simple index created");
						break;
					}
					case 2:
					{
						System.out.println("Enter the table and index name");
						table_name=sc.next();
						String index_name=sc.next();
						System.out.println("Enter the column name for indexig");
						String column_name=sc.next();
						String sql="create unique index "+index_name+" on "+table_name+"("+column_name+");";
						st.executeUpdate(sql);
						System.out.println("Unique index created");
						break;
					}
					case 3:
					{
						System.out.println("Enter the table and index name");
						table_name=sc.next();
						String index_name=sc.next();
						System.out.println("Enter the column names for indexig");
						String column_name_1=sc.next();
						String column_name_2=sc.next();
						String column_name_3=sc.next();
						String sql="create index "+index_name+" on "+table_name+"("+column_name_1+","+column_name_2+","+column_name_3+");";
						st.executeUpdate(sql);
						System.out.println("Composite index created");
						break;
					}
					}
					break;
				}
				case 5:
				{
					String sql="show index from Professors";
					System.out.println("Key_name\tIndex_type");
					ResultSet re=st.executeQuery(sql);
					while(re.next())
					{
						String k_val=re.getString("Key_name");
						String i_type=re.getString("Index_type");
						System.out.println(k_val+"\t"+i_type);
					}
					break;
				}
				case 6:
				{
					System.out.println("Enter the name of index to be dropped");
					String index_name=sc.next();
					String sql="drop index"+index_name+" on "+table_name+";";
					System.out.println("Index dropped");
					break;
					
				}
				case 7:
				{
					System.out.println("Enter the table name and the auto incremented column name");
					String t_name=sc.next();
					String col_1=sc.next();
					System.out.println("Enter the ame for other column");
					String col_2=sc.next();
					String sql="create table "+t_name+"("+col_1+" int auto_increment primary key, "+col_2+"varchar(10));";
					st.executeUpdate(sql);
					int k=1;
					while(k==1)
					{
						System.out.println("Enter name");
						String name=sc.next();
						String sql1="insert into "+t_name+"("+col_2+") values ("+name+");";
						st.executeUpdate(sql1);
						System.out.println("Press 1 to make more entries");
						k=sc.nextInt();
					}
					break;
				}
				}
			}while(n!=8);
		
		con.close();	
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
