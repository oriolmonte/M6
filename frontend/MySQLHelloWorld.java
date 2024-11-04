package frontend;

import java.sql.*;
import java.util.Scanner;

public class MySQLHelloWorld {

	public static void main(String[] args)  throws SQLException{
		try
		{
		String connectionUrl = "jdbc:mysql://localhost:3306/hospital?serverTimezone=UTC";
		
		Connection conn = DriverManager.getConnection(connectionUrl, "admin", "12345");
		
		if (conn!=null) System.out.println("CONNECTED TO HOSPITAL");
		Scanner s = new Scanner(System.in);
		String linia;
		System.out.println("Com et dius? ");
		linia = s.nextLine();
		System.out.println("Hola " + linia + " T'has Connectat a MySQL.");
		
		Statement st = conn.createStatement();
	
		String sSql = "SELECT * FROM DOCTOR";
		ResultSet resultSet = st.executeQuery(sSql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        StringBuilder result = new StringBuilder();
        while (resultSet.next()) 
        {
            for (int i = 1; i <= metaData.getColumnCount(); i++) 
            {
                result.append(resultSet.getString(i)).append("\t");
            }
            result.append("\n");
        }
        System.out.println(result.toString());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
