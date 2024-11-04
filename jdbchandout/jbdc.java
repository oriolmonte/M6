package jdbchandout;

import java.sql.*;
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
		//2.Prepared statements
		doExercici2(con,74835);
		//3.ExecuteBatch i AddBatch
		//4.Crear procediment emmagatzemat UN SOL VALOR 
		//5.Crear procediment emmagatzemat UN CURSOR
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
				e.printStackTrace();
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
				System.out.println("NOM: " + resultSet.getString(0));
			}		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
		}

	}
	
	
}
