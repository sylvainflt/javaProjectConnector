package javaProjectCon;

import java.sql.*;

public class CreerConnexion {

	public Connection myCnx() {
		Connection cn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mariadb://localhost/Commandes", "root", "");
			System.out.println("connexion réussie");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
	
	
	

}
