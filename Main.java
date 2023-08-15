import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	static String DB_URL="jdbc:mysql://localhost/practice";
	static String username="root";
	static String password="Ng02@MySQL!";

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		Connection conn=null;
		Statement stmt=null;
		try {
			int choice=0;
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connecting to the database");
			conn=DriverManager.getConnection(DB_URL,username,password);
			stmt=conn.createStatement();
			
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter choice \n1.Insert\n2.Update\n3.Delete\n4.Display");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Insert");
				String query="insert into emp(id,name)"+"values(?,?)";
				System.out.println("Enter name");
				String name=sc.next();
				System.out.println("Enter id");
				int id=sc.nextInt();
				
				PreparedStatement prep_stmt=null;
				prep_stmt=conn.prepareStatement(query);
				prep_stmt.setInt(1, id);
				prep_stmt.setString(2, name);
				
				prep_stmt.execute();
				conn.close();
				break;
				
			case 2:
				System.out.println("Updating");
				System.out.println("Enter id");
				id=sc.nextInt();
				System.out.println("NAME");
				name=sc.next();
				String query2="Update emp set name=?"+"where id=?";
				prep_stmt=conn.prepareStatement(query2);
				prep_stmt.setInt(2, id);
				prep_stmt.setString(1, name);
				prep_stmt.execute();
				break;
				
			case 3:
				System.out.println("Deleting");
				String query3="delete from emp where id=?";
				System.out.println("Enter id");
				id=sc.nextInt();
				prep_stmt=conn.prepareStatement(query3);
				prep_stmt.setInt(1, id);
				prep_stmt.execute();
				conn.close();
				break;
			
			case 4:
				System.out.println("Display");
				String query1="select * from emp";
				ResultSet rs=stmt.executeQuery(query1);
				int count=0;
				while(rs.next())
				{
					name=rs.getString("name");
					id=rs.getInt("id");
					String output = "User #%d: %d - %s";
					System.out.println(String.format(output,++count,id,name));
				}
				
				break;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
