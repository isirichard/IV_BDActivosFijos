package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ModificaBBDD {
	public static void main(String[] args) {
		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			String instruccionSql="INSERT INTO PROVEEDOR(PROCOD,PRONOM) VALUES (3,ROSA)";
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
