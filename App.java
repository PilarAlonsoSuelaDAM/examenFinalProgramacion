package examen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
//nota 10, Perfecto María del Pilar. Enhorabuena por tu trabajo durante todo el curso. Eres una alumna ejemplar 






public class App implements ExamenElearning1Ord {
	public static void main(String[] args) throws SQLException, FileNotFoundException {
		App a = new App();
		String[] conexion = new String[3];
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

		conexion = a.cargarParametrosConexion("config.txt");
		/*
		 * for (int i = 0; i < conexion.length; i++) { System.out.println(conexion[i]);
		 * }
		 */
		Connection conn = DriverManager.getConnection(conexion[0], conexion[1], conexion[2]);

		// Connection conn =
		// DriverManager.getConnection("@cloud.riberadeltajo.es:8011:XE", "matricula",
		// "matricula");

		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("select dni,nombre,apellidos from personas");
		a.personasToCsv(rset, "test.csv");

		stmt.close();

		////////////////////////////////////////////////////////////////
		// Esta lista es para los alumnos que tienen todas las unidades
		ArrayList<Integer> lista = new ArrayList<>();
		lista.add(16);
		lista.add(17);
		lista.add(8);
		lista.add(7);
		lista.add(29);
		lista.add(32);
		lista.add(34);
		lista.add(25);
		lista.add(17);
		lista.add(18);
		lista.add(19);
		lista.add(7);
		lista.add(38);
		lista.add(40);

		// System.out.println("La mejor racha es: "+a.mejorRacha(lista));
	}

	/*
	 * leerá los valores de la conexion de la BD de un fichero de texto cuyo nombre
	 * se pasa como parametro y los almacena en un array de String que devuelve
	 * Estos parametros la cadena de conexion en la pos 0,el usuario en la posicion
	 * 1 y su password en la dos
	 */
	@Override
	public String[] cargarParametrosConexion(String fichero) throws FileNotFoundException {
		// creo el objeto file
		File fiche = new File(fichero);
		// creo el array donde almacenaré los datos que me interesan
		ArrayList<String> dats = new ArrayList<>();
		// creo el Scanner para leer el fichero
		Scanner leer = new Scanner(fiche);
		String cadena = "";
		String[] linea;
		String[] datosConexion = new String[3];
		int contador = 0;
		// leo el texto partiendolo por el =
		while (leer.hasNext()) {
			cadena = leer.nextLine();
			linea = cadena.split("=");
			// almaceno el resultado de lo que quiero en dats
			dats.add(linea[1]);
		}
		// recorro el array y con el contador añado los datos en las posiciones del
		// array que devolverá el método
		for (String s : dats) {
			datosConexion[contador] = s;
			contador++;
		}
		return datosConexion;
	}

	@Override
	public void personasToCsv(ResultSet rset, String fichero) throws FileNotFoundException, SQLException {
		// creamos el archivo
		File fich = new File(fichero);
		// Creamos el objeto para poder escribir en el file
		PrintWriter escribir = new PrintWriter(fich);
		// recorro la consulta de BD
		while (rset.next()) {
			// escribo en cada linea del fichero separandolo por ;
			escribir.println(rset.getString(1) + ";" + rset.getString(2) + ";" + rset.getString(3));
			
			/*
			 * no entiendo por qué me da este error:Exception in thread "main"
			 * java.sql.SQLException: Fallo al convertir a representación interna at
			 * oracle.jdbc.driver.CharCommonAccessor.getInt(CharCommonAccessor.java:147) at
			 * oracle.jdbc.driver.T4CVarcharAccessor.getInt(T4CVarcharAccessor.java:814) at
			 * oracle.jdbc.driver.OracleResultSetImpl.getInt(OracleResultSetImpl.java:928)
			 * at examen.App.personasToCsv(App.java:101) at examen.App.main(App.java:32)
			 */

		}
		escribir.flush();
		escribir.close();

	}

}
