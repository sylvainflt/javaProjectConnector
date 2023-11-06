package javaProjectCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Commandes {

	public void dropTables(Connection cnx) throws SQLException {
		
		PreparedStatement ps = cnx.prepareStatement("DROP TABLE IF EXISTS COMPOSER");
		ps.execute();
		ps = cnx.prepareStatement("DROP TABLE IF EXISTS CONCERNER");
		ps.execute();
		ps = cnx.prepareStatement("DROP TABLE IF EXISTS PRODUIT");
		ps.execute();
		ps = cnx.prepareStatement("DROP TABLE IF EXISTS TVA");
		ps.execute();
		ps = cnx.prepareStatement("DROP TABLE IF EXISTS LIVRAISON");
		ps.execute();
		ps = cnx.prepareStatement("DROP TABLE IF EXISTS FACTURE");
		ps.execute();
		ps = cnx.prepareStatement("DROP TABLE IF EXISTS COMMANDE");
		ps.execute();
		ps = cnx.prepareStatement("DROP TABLE IF EXISTS CLIENT");
		ps.execute();
		ps = cnx.prepareStatement("DROP TABLE IF EXISTS AGENCE");
		ps.execute();
		
	}
	
	public void miseEnPlaceTables(Connection cnx) throws SQLException {
		
		PreparedStatement ps = cnx.prepareStatement("CREATE TABLE IF NOT EXISTS `AGENCE` (\n"
				+ "  `Noag` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Nomag` int(11) NOT NULL\n"
				+ ")");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE IF NOT EXISTS `CLIENT` (\n"
				+ "  `Cocli` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Nomcli` varchar(255) NOT NULL,\n"
				+ "  `Ruecli` varchar(255) NOT NULL,\n"
				+ "  `Villecli` varchar(255) NOT NULL,\n"
				+ "  `Noag` int(11) NOT NULL,\n"
				+ "FOREIGN KEY (`Noag`) REFERENCES `AGENCE` (`Noag`)\n"
				+ ")");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE IF NOT EXISTS `COMMANDE` (\n"
				+ "  `Nobc` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Datebc` date NOT NULL,\n"
				+ "  `CoCli` int(11) NOT NULL,\n"
				+ "FOREIGN KEY (`CoCli`) REFERENCES `CLIENT` (`Cocli`)"
				+ ")");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE IF NOT EXISTS `FACTURE` (\n"
				+ "  `Nofac` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Datefac` date NOT NULL\n"
				+ ")");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE IF NOT EXISTS `LIVRAISON` (\n"
				+ "  `Nobl` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Datebl` date NOT NULL,\n"
				+ "  `Nobc` int(11) NOT NULL,\n"
				+ "  `Nofac` int(11) NOT NULL,\n"
				+ "FOREIGN KEY (`Nobc`) REFERENCES `COMMANDE` (`Nobc`),\n"
				+ "FOREIGN KEY (`Nofac`) REFERENCES `FACTURE` (`Nofac`)"
				+ ")");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE IF NOT EXISTS `TVA` (\n"
				+ "  `Codetva` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Tauxtva` int(11) NOT NULL\n"
				+ ")");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE IF NOT EXISTS `PRODUIT` (\n"
				+ "  `Refprod` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Desiprod` varchar(255) NOT NULL,\n"
				+ "  `Codetva` int(11) NOT NULL,\n"
				+"FOREIGN KEY (`Codetva`) REFERENCES `TVA` (`Codetva`)"
				+ ")");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE IF NOT EXISTS `COMPOSER` (\n"
				+ "  `Nobl` int(11) NOT NULL,\n"
				+ "  `Refprod` int(11) NOT NULL,\n"
				+ "  `Qteliv` int(11) NOT NULL,\n"
				+ "PRIMARY KEY (`Nobl`,`Refprod`),\n"
				+ "FOREIGN KEY (`Nobl`) REFERENCES `LIVRAISON` (`Nobl`),\n"
				+ "FOREIGN KEY (`Refprod`) REFERENCES `PRODUIT` (`Refprod`)"
				+ ")");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE IF NOT EXISTS `CONCERNER` (\n"
				+ "  `Nobc` int(11) NOT NULL,\n"
				+ "  `Refprod` int(11) NOT NULL,\n"
				+ "  `Qtecom` int(11) NOT NULL,\n"
				+ "  `Punit` int(11) NOT NULL,\n"
				+ "PRIMARY KEY (`Nobc`,`Refprod`),\n"
				+ "FOREIGN KEY (`Nobc`) REFERENCES `COMMANDE` (`Nobc`),\n"
				+ "FOREIGN KEY (`Refprod`) REFERENCES `PRODUIT` (`Refprod`)"
				+ ")");
		ps.execute();
		
	}
	
	public void miseEnPlaceDonnees(Connection cnx) throws SQLException {
		
		PreparedStatement ps = cnx.prepareStatement("INSERT INTO `AGENCE` (`Nomag`) \n"
				+ "VALUES\n"
				+ "(1)");
		ps.execute();	
		
		ps = cnx.prepareStatement("INSERT INTO `CLIENT` (`Nomcli`, `Ruecli`, `Villecli`, `Noag`) VALUES\n"
				+ "('Sylvain', 'avenue Roger Salengro', 'Croix', 1),\n"
				+ "('Rémi', 'rue Roger Salengro', 'Hellemmes', 1)");
		ps.execute();
		
		ps = cnx.prepareStatement("INSERT INTO `TVA` (`Tauxtva`) \n"
				+ "VALUES\n"
				+ "(20)");
		ps.execute();		
		
		ps = cnx.prepareStatement("INSERT INTO `PRODUIT` (`Desiprod`, `Codetva`) \n"
				+ "VALUES\n"
				+ "('Radio réveil', 1),\n"
				+ "('Four micro-ondes', 1)");
		ps.execute();
		
		ps = cnx.prepareStatement("INSERT INTO `FACTURE` (`Datefac`)\n"
				+ "VALUES\n"
				+ "('2023-10-22'),\n"
				+ "('2023-11-03')");
		ps.execute();
		
		ps = cnx.prepareStatement("INSERT INTO `COMMANDE` (`Datebc`,`CoCli`)\n"
				+ "VALUES\n"
				+ "('2023-10-20',1),\n"
				+ "('2023-11-01',2)");
		ps.execute();
		
		ps = cnx.prepareStatement("INSERT INTO `LIVRAISON` (`Datebl`,`Nobc`,`Nofac`)\n"
				+ "VALUES\n"
				+ "('2023-10-25',1,1),\n"
				+ "('2023-11-05',2,2)");
		ps.execute();
		
		ps = cnx.prepareStatement("INSERT INTO `COMPOSER`\n"
				+ "VALUES\n"
				+ "(1,1,1),\n"
				+ "(2,2,1)");
		ps.execute();		
		
		ps = cnx.prepareStatement("INSERT INTO `CONCERNER`\n"
				+ "VALUES\n"
				+ "(1,1,1,10),\n"
				+ "(2,2,1,200)");
		ps.execute();
		
		
	}
	
	public void ajoutProduits(Connection cnx) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement("INSERT INTO `PRODUIT` (`Desiprod`, `Codetva`) \n"
				+ "VALUES\n"
				+ "('Entretoise', 1),\n"
				+ "('Etrier', 1),\n"
				+ "('Tuile', 1),\n"
				+ "('Serviette', 1),\n"
				+ "('Parapluie', 1),\n"
				+ "('Télévision', 1),\n"
				+ "('Armoire', 1),\n"
				+ "('Beret', 1),\n"
				+ "('Cafetière', 1)");
		ps.execute();
	}
	
	public void requete1(Connection cnx) throws SQLException {
		
		System.out.println("-------- requete 1 --------");
		
		String req = "select * from PRODUIT order by Desiprod";
		
		System.out.println(req + " :");
		
		Statement st = cnx.createStatement();
		ResultSet rs = st.executeQuery(req);
		
		while(rs.next()) {
			System.out.println(rs.getString("Desiprod"));
		}
		System.out.println("---------------------------");
	}
	
	public void requete2(Connection cnx) throws SQLException {
		
		System.out.println("-------- requete 2 --------");
		
		String req = "select * from PRODUIT where Refprod < 10";
		
		System.out.println(req + " :");
		
		Statement st = cnx.createStatement();
		ResultSet rs = st.executeQuery(req);
		
		while(rs.next()) {
			System.out.println(rs.getInt("Refprod")+" - "+
					rs.getString("Desiprod"));
		}
		System.out.println("---------------------------");
	}
	
	public void requete6(Connection cnx) throws SQLException {
		
		System.out.println("-------- requete 6 --------");
		
		String req = "select * from PRODUIT where Desiprod like 'E%'";
		
		System.out.println(req + " :");
		
		Statement st = cnx.createStatement();
		ResultSet rs = st.executeQuery(req);
		
		while(rs.next()) {
			System.out.println(rs.getInt("Refprod")+" - "+
					rs.getString("Desiprod"));
		}
		System.out.println("---------------------------");
	}
	
	public void requete7(Connection cnx) throws SQLException {
		
		System.out.println("-------- requete 7 --------");
		
		String req = "select * from PRODUIT where Desiprod like '%E'";
		
		System.out.println(req + " :");
		
		Statement st = cnx.createStatement();
		ResultSet rs = st.executeQuery(req);
		
		while(rs.next()) {
			System.out.println(rs.getInt("Refprod")+" - "+
					rs.getString("Desiprod"));
		}
		System.out.println("---------------------------");
	}

	public void requete8(Connection cnx) throws SQLException {
	
		System.out.println("-------- requete 8 --------");
		
		String req = "select * from PRODUIT where Desiprod like '%E%'";
		
		System.out.println(req + " :");
		
		Statement st = cnx.createStatement();
		ResultSet rs = st.executeQuery(req);
		
		while(rs.next()) {
			System.out.println(rs.getInt("Refprod")+" - "+
					rs.getString("Desiprod"));
		}
		System.out.println("---------------------------");
	}
	
	public void requeteBonus(Connection cnx) throws SQLException {
		
		System.out.println("-------- requete bonus --------");
		
		String req = "select l.datebl, com.datebc, cli.nomcli, p.Desiprod\n"
				+ "from LIVRAISON l, COMMANDE com, CLIENT cli, CONCERNER con, PRODUIT p\n"
				+ "where l.nobc = com.nobc\n"
				+ "and com.cocli = cli.cocli\n"
				+ "and con.Nobc = com.Nobc\n"
				+ "and con.Refprod = p.Refprod";
		
		System.out.println(req + " :");
		
		Statement st = cnx.createStatement();
		ResultSet rs = st.executeQuery(req);
		
		while(rs.next()) {
			System.out.println(rs.getDate("datebl")
							+" - "+rs.getDate("datebc")
							+" - "+rs.getString("nomcli")
							+" - "+rs.getString("Desiprod"));
		}
		System.out.println("---------------------------");
	}
	
}
