package jdbchandout;

import java.sql.*;
import java.util.Random;

import jdk.internal.org.objectweb.asm.Type;
import model.Doctor;


public class jbdc {
	static String connectionUrl = "jdbc:mysql://localhost:3306/hospital?serverTimezone=UTC";
	public static void main (String[] args) throws SQLException
	{
		Connection con = DriverManager.getConnection(connectionUrl, "admin", "12345");
		
		//revertirExercici1(con);
		
		//Try Catch a cada exercici
		//1.Statements Eliminar id = 9999 update 10995
		doExercici1(con,"9999", "10995");
		System.out.println("---------------------------------------");
		//2.Prepared statements
		doExercici2(con,30000);
		System.out.println("---------------------------------------");
		//3.ExecuteBatch i AddBatch
		doExercici3(con);
		System.out.println("---------------------------------------");
		//4.Crear procediment emmagatzemat UN SOL VALOR
		doExercici4(con, "PEPITO U.");
		System.out.println("---------------------------------------");
		//5.Crear procediment emmagatzemat UN CURSOR
		doExercici5(con, "Cardiologia");
		System.out.println("---------------------------------------");
		//6.Funcio transaccional 
	}
	
	public static void doExercici1(Connection con, String id_malalt_elimina, String id_malalt_update)
	{
		try {
			Statement st = con.createStatement();
			String sSql = "DELETE FROM MALALT WHERE MALALT_NUM=" + id_malalt_elimina +";";
			int n = st.executeUpdate(sSql);
			sSql =  "UPDATE MALALT "
					+ "SET MALALT_NOM = 'PEPITO U.'"
					+ "WHERE MALALT_NUM ="
					+ id_malalt_update+";";
			n = st.executeUpdate(sSql);
		}
			
		catch (SQLException e) 
		{
			System.out.println("S'ha llençat una excepció");
		}
		
	}
	public static void doExercici2(Connection con, int id_malalt)
	{
		try
		{
			String sSql = "SELECT MALALT_NOM FROM MALALT WHERE MALALT_NUM<?;";
			PreparedStatement pS = con.prepareStatement(sSql);
			pS.setInt(1, id_malalt);
			ResultSet resultSet = pS.executeQuery();
			while(resultSet.next())
			{
				System.out.println("NOM: " + resultSet.getString(1));
			}		
		}
		catch(SQLException e)
		{
			System.out.println("S'ha llençat una excepció");
		}
	}
	
	public static void doExercici3(Connection con)
	{
		try {
			String sSql = "INSERT INTO `malalt` (`malalt_num`, `malalt_nom`, `malalt_adreca`, `malalt_dnaixa`, `malalt_sexe`, `malalt_nss`) VALUES (?, 'Laguia M.', 'Goya 20', '1956-05-16', 'M', 280862482)";
			Random r = new Random();
			PreparedStatement pS = con.prepareStatement(sSql);
			pS.setInt(1, r.nextInt(9999));
			pS.addBatch();
			pS.setInt(1, r.nextInt(9999));
			pS.addBatch();
			pS.setInt(1, r.nextInt(9999));
			pS.addBatch();
			int[]count = pS.executeBatch();
			
		} catch (SQLException e) {
			System.out.println("S'ha llençat una excepció");
		}
		
	}
	
	public static void doExercici4(Connection con, String nameIn)
	{
		try {
			
			String storedProcedure = "{call GetMalalt(?,?)}";
			CallableStatement cS = con.prepareCall(storedProcedure);
			cS.setString(1, nameIn);
			cS.registerOutParameter(2, Types.INTEGER);
			cS.execute();
			int malaltId = cS.getInt(2);
			System.out.println("Stored Procedure GET MALALT " + nameIn + "-> ID Malalt: " + malaltId);			
			
		}
		catch (SQLException e)
		{
			System.out.println("S'ha llençat una excepció");
		}
	}
	
	public static void doExercici5 (Connection con, String especialitat)
	{
		try {
			String storedProcedure = "{call GetDoctorsByEspecialitat(?)}";
			CallableStatement cS;
			cS = con.prepareCall(storedProcedure);
			cS.setString(1, especialitat);
			boolean hasResults = cS.execute();
			if (hasResults)
			{
				ResultSet rS = cS.getResultSet();
				while(rS.next())
				{
					System.out.println("NOM: " + rS.getString(1));
				}
			}			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("S'ha llençat una excepció");
		}
		

	}
	public static void revertirExercici1(Connection con)
	{
		Statement st;
		try {
			st = con.createStatement();
			String sSql = "INSERT INTO `malalt` (`malalt_num`, `malalt_nom`, `malalt_adreca`, `malalt_dnaixa`, `malalt_sexe`, `malalt_nss`) VALUES (9999, 'Laguia M.', 'Goya 20', '1956-05-16', 'M', 280862482)";
			int n = st.executeUpdate(sSql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("S'ha llençat una excepció");
		}

	}
	
	
}
