package procedures;

import java.sql.*;

public class StoredProcedures {
	
	public static void main (String[] args) throws SQLException
	{
		String connectionUrl = "jdbc:mysql://localhost:3306/employeedb?serverTimezone=UTC";
		Connection connection = DriverManager.getConnection(connectionUrl, "admin", "12345");
		String storedProcedureCall = "{call GetEmployee(?, ?)}";
		CallableStatement cS= connection.prepareCall(storedProcedureCall);
		// Set the input parameter (employeeId)
		int employeeId = 103;
		cS.setInt(1, employeeId);
		// Register the output parameter (employeeName)
		cS.registerOutParameter(2, Types.VARCHAR);
		// Execute the stored procedure
		cS.execute();
		// Retrieve the output parameter value
		String employeeName = cS.getString(2);
		// Print the result
		System.out.println("Employee Name for ID " + employeeId + ": " + employeeName);
	}
}
