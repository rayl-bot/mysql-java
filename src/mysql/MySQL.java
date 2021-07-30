package mysql;

//CRUD A MYSQL
import java.sql.*;

import java.text.*;

import java.util.*;
import java.util.Date;

import javax.swing.*;

public class MySQL {
	public static Connection connection;

	// metodo para estableces la conexion con el servidor
	public void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://192.168.155.52:3306?useTimezone=true&serverTimezone=UTC", "remote", "PASS21word!");// credenciales
																														// temporales
			System.out.print("Server Connected");
			fecha();

		} catch (SQLException | ClassNotFoundException ex) {
			System.out.print("No se ha podido conectar con mi base de datos");
			fecha();
			System.out.println(ex.getMessage());

		}
	}

	// metodo para crear una base de datos en el servidor al que nos hemos conectado
	public void createDB(String name) {
		try {
			String Query = "CREATE DATABASE IF NOT EXISTS " + name;
			Statement st = connection.createStatement();
			st.executeUpdate(Query);
			System.out.println("DB creada con exito!");

			JOptionPane.showMessageDialog(null, "Se ha creado la DB " + name + "de forma exitosa.");
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error creando la DB.");
		}
	}

	// metodo para crear una tabla dentro de la base de datos que hayamos
	// introducido
	public void createTable(String db, String name, String data) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = connection.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE IF NOT EXISTS " + name + "(" + data + ");";
			Statement st = connection.createStatement();
			st.executeUpdate(Query);
			System.out.println("Tabla creada con exito!");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error crando tabla.");

		}
	}

	// metodo para insertar valores en la tabla que deseemos
	public void insertData(String db, String table, String columns, String values) {
		try {
			String Querydb = "USE " + db + ";";
			Statement stdb = connection.createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO " + table + " " + columns + " VALUES(" + values + "); ";
			Statement st = connection.createStatement();
			st.executeUpdate(Query);

			System.out.println("Datos almacenados correctamente");
			;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error en el almacenamiento");
		}
	}
	
	//metodo que nos devuelve los valores que nosotros introduzcamos
	public void getValues(String db, String table) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb= connection.createStatement();
			stdb.executeUpdate(Querydb);
						
			String Query = "SELECT * FROM " + table;
			Statement st = connection.createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);
			while (resultSet.next()) {
				System.out.println("ID: " +  resultSet.getString("ID"));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}
	}
	
	//metodo para eliminar un registro de una tabla por el id
	public void deleteRecord(String db, String table_name, String ID) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb= connection.createStatement();
			stdb.executeUpdate(Querydb);
						
			String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
			Statement st = connection.createStatement();
			st.executeUpdate(Query);
			
			System.out.println("Registros de tabla ELIMINADOS con exito!");
						
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
		}
	}
	
	//metodo para eliminar una tabla de una base de datos
	public void deleteTable(String db, String table_name) {
		try {
			String Querydb = "USE "+db+";";
			Statement stdb= connection.createStatement();
			stdb.executeUpdate(Querydb);
						
			String Query = "DROP TABLE " + table_name + ";";
			Statement st = connection.createStatement();
			st.executeUpdate(Query);
			
			System.out.println("TABLA ELIMINADA con exito!");
						
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "Error borrando la tabla");
		}
	}

	// metodo para cerrar conexion con el servidor
	public void closeConnection() {
		try {

			connection.close();
			System.out.print("Server Disconnected");
			fecha();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.print("Error cerrando conexion");
			fecha();
		}
	}

	// metodo que nos devuelve la fecha actual
	public static void fecha() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		System.out.println(" - " + hourdateFormat.format(date));
	}
}
