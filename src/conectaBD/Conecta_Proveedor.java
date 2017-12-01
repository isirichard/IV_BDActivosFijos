package conectaBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conecta_Proveedor {
	public static void main(String[] args) {
		try {
			//1. Crear CONEXIÓN
			Connection miConextion=DriverManager.getConnection("jdbc:mysql://localhost:3306/activos_fijos", "root", "");
			//2. CREAR OBJETO STATEMENT
			Statement miStatement=miConextion.createStatement();
			//3. EJECUTAR SQL
			ResultSet miResulset=miStatement.executeQuery("SELECT * FROM PROVEEDOR");
			//4. RECORRER EL RESULSET
			while(miResulset.next()){
				System.out.println(miResulset.getString("PROCOD")+" "+miResulset.getString("PRONOM"));
			}
			
		} catch (Exception e) {
			System.out.println("No conecta!!");
			e.printStackTrace();
		}
	}
}
