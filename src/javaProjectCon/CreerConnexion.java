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
	
	
	public static void main(String[] args) throws SQLException {
		CreerConnexion db = new CreerConnexion();
		Connection cnx = null;
		cnx = db.myCnx();
		
		PreparedStatement ps;
		Statement st;
		ResultSet rs;
		/*
		ps = cnx.prepareStatement("CREATE TABLE `CLIENT` (\n"
				+ "  `Cocli` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Nomcli` varchar(255) NOT NULL,\n"
				+ "  `Ruecli` varchar(255) NOT NULL,\n"
				+ "  `Villecli` varchar(255) NOT NULL,\n"
				+ "  `Noag` int(11) NOT NULL\n"
				+ ")");
		ps.execute();
		
		ps = cnx.prepareStatement("INSERT INTO `CLIENT` (`Nomcli`, `Ruecli`, `Villecli`, `Noag`) VALUES\n"
				+ "('Sylvain', 'avenue Roger Salengro', 'Croix', 1),\n"
				+ "('Rémi', 'rue Roger Salengro', 'Hellemmes', 1)");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE `COMMANDE` (\n"
				+ "  `Nobc` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Datebc` date NOT NULL,\n"
				+ "  `CoCli` int(11) NOT NULL\n"
				+ ")");
		ps.execute();
		ps = cnx.prepareStatement("INSERT INTO `COMMANDE` (`Datebc`,`CoCli`)\n"
				+ "VALUES\n"
				+ "('2023-10-20',1),\n"
				+ "('2023-11-01',2)");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE `FACTURE` (\n"
				+ "  `Nofac` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Datefac` date NOT NULL\n"
				+ ")");
		ps.execute();
		ps = cnx.prepareStatement("INSERT INTO `FACTURE` (`Datefac`)\n"
				+ "VALUES\n"
				+ "('2023-10-22'),\n"
				+ "('2023-11-03')");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE `LIVRAISON` (\n"
				+ "  `Nobl` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Datebl` date NOT NULL,\n"
				+ "  `Nobc` int(11) NOT NULL,\n"
				+ "  `Nofac` int(11) NOT NULL\n"
				+ ")");
		ps.execute();
		ps = cnx.prepareStatement("INSERT INTO `LIVRAISON` (`Datebl`,`Nobc`,`Nofac`)\n"
				+ "VALUES\n"
				+ "('2023-10-25',1,1),\n"
				+ "('2023-11-05',2,2)");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE `PRODUIT` (\n"
				+ "  `Refprod` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Desiprod` varchar(255) NOT NULL,\n"
				+ "  `Codetva` int(11) NOT NULL\n"
				+ ")");
		ps.execute();
		ps = cnx.prepareStatement("INSERT INTO `PRODUIT` (`Desiprod`, `Codetva`) \n"
				+ "VALUES\n"
				+ "('Radio réveil', 1),\n"
				+ "('Four micro-ondes', 1)");
		ps.execute();
		
		ps = cnx.prepareStatement("CREATE TABLE `TVA` (\n"
				+ "  `Codetva` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Tauxtva` int(11) NOT NULL\n"
				+ ")");
		ps.execute();
		ps = cnx.prepareStatement("INSERT INTO `TVA` (`Tauxtva`) \n"
				+ "VALUES\n"
				+ "(20)");
		ps.execute();		
		
		ps = cnx.prepareStatement("CREATE TABLE `AGENCE` (\n"
				+ "  `Noag` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
				+ "  `Nomag` int(11) NOT NULL\n"
				+ ")");
		ps.execute();
		ps = cnx.prepareStatement("INSERT INTO `AGENCE` (`Nomag`) \n"
				+ "VALUES\n"
				+ "(1)");
		ps.execute();		
		
		ps = cnx.prepareStatement("CREATE TABLE `COMPOSER` (\n"
				+ "  `Nobl` int(11) NOT NULL,\n"
				+ "  `Refprod` int(11) NOT NULL,\n"
				+ "  `Qteliv` int(11) NOT NULL\n"
				+ ")");
		ps.execute();
		ps = cnx.prepareStatement("INSERT INTO `COMPOSER`\n"
				+ "VALUES\n"
				+ "(1,1,1),\n"
				+ "(2,2,1)");
		ps.execute();		
		
		ps = cnx.prepareStatement("CREATE TABLE `CONCERNER` (\n"
				+ "  `Nobc` int(11) NOT NULL,\n"
				+ "  `Refprod` int(11) NOT NULL,\n"
				+ "  `Qtecom` int(11) NOT NULL,\n"
				+ "  `Punit` int(11) NOT NULL\n"
				+ ")");
		ps.execute();
		ps = cnx.prepareStatement("INSERT INTO `CONCERNER`\n"
				+ "VALUES\n"
				+ "(1,1,1,10),\n"
				+ "(2,2,1,200)");
		ps.execute();
		
		ps = cnx.prepareStatement("ALTER TABLE `COMPOSER`\n"
				+ "  ADD PRIMARY KEY (`Nobl`,`Refprod`)");
		ps.execute();
		ps = cnx.prepareStatement("ALTER TABLE `CONCERNER`\n"
				+ "  ADD PRIMARY KEY (`Nobc`,`Refprod`)");
		ps.execute();		
		
		ps = cnx.prepareStatement("ALTER TABLE `CLIENT`\n"
				+ "  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`Noag`) REFERENCES `AGENCE` (`Noag`)");
		ps.execute();		
		
		ps = cnx.prepareStatement("ALTER TABLE `COMMANDE`\n"
				+ "  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`CoCli`) REFERENCES `CLIENT` (`Cocli`)");
		ps.execute();
		
		ps = cnx.prepareStatement("ALTER TABLE `LIVRAISON`\n"
				+ "  ADD CONSTRAINT `livraison_ibfk_1` FOREIGN KEY (`Nobc`) REFERENCES `COMMANDE` (`Nobc`),\n"
				+ "  ADD CONSTRAINT `livraison_ibfk_2` FOREIGN KEY (`Nofac`) REFERENCES `FACTURE` (`Nofac`)");
		ps.execute();
		
		ps = cnx.prepareStatement("ALTER TABLE `PRODUIT`\n"
				+ "  ADD CONSTRAINT `produit_ibfk_1` FOREIGN KEY (`Codetva`) REFERENCES `TVA` (`Codetva`)");
		ps.execute();
		
		ps = cnx.prepareStatement("ALTER TABLE `COMPOSER`\n"
				+ "  ADD CONSTRAINT `composer_ibfk_1` FOREIGN KEY (`Nobl`) REFERENCES `LIVRAISON` (`Nobl`),\n"
				+ "  ADD CONSTRAINT `composer_ibfk_2` FOREIGN KEY (`Refprod`) REFERENCES `PRODUIT` (`Refprod`)");
		ps.execute();
		ps = cnx.prepareStatement("ALTER TABLE `CONCERNER`\n"
				+ "  ADD CONSTRAINT `concerner_ibfk_1` FOREIGN KEY (`Nobc`) REFERENCES `COMMANDE` (`Nobc`),\n"
				+ "  ADD CONSTRAINT `concerner_ibfk_2` FOREIGN KEY (`Refprod`) REFERENCES `PRODUIT` (`Refprod`)");
		ps.execute();
		*/
		
		//db.ajoutProduits(cnx);
		
		db.requete7(cnx);
		db.requete8(cnx);
		
	}
	
	private void ajoutProduits(Connection cnx) throws SQLException {
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
	
	private void requete1(Connection cnx) throws SQLException {
		
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
	
	private void requete2(Connection cnx) throws SQLException {
		
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
	
	private void requete6(Connection cnx) throws SQLException {
		
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
	
	private void requete7(Connection cnx) throws SQLException {
		
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

	private void requete8(Connection cnx) throws SQLException {
	
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
	
	private void requeteBonus(Connection cnx) throws SQLException {
		
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
