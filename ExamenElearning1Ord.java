package examen;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ExamenElearning1Ord {
public String []cargarParametrosConexion(String fichero) throws FileNotFoundException;
public void personasToCsv(ResultSet rset,String fichero) throws FileNotFoundException, SQLException;
}
