package javaProjectCon;

import java.sql.Connection;
import java.sql.SQLException;

public class DemoCommandes {

	public static void main(String[] args) throws SQLException {

		CreerConnexion db = new CreerConnexion();
		Connection cnx = null;
		cnx = db.myCnx();
		
		Commandes cmds = new Commandes();
		
		//cmds.dropTables(cnx);
		//cmds.miseEnPlaceTables(cnx);
		//cmds.miseEnPlaceDonnees(cnx);
		
		cmds.ajoutProduits(cnx);
		
		cmds.requete1(cnx);
		//cmds.requete2(cnx);
		//cmds.requete6(cnx);
		//cmds.requeteBonus(cnx);
		
	}

}
