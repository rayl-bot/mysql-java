package direccion;

import javax.swing.JOptionPane;

import mysql.MySQL;

public class DireccionApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySQL mysql = new MySQL();

		// establecemos conexionc con el servidor
		mysql.openConnection();

		// introducimos el nombre de la nueva base de datos
		// Tienda de informatica
		String bd = JOptionPane.showInputDialog("Introduce el nombre de la nueva base de datos: ");
		mysql.createDB(bd);// creamos una base de datos con el nomrbe introducido anteriormente

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla despachos a la base de datos " + bd);
		String data = "codigo_desp int PRIMARY KEY, capacidad int";
		mysql.createTable(bd, "despachos", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla directores a la base de datos " + bd);
		data = "DNI varchar(8) PRIMARY KEY, nomApels varchar(255),DNI_F varchar(8), despacho int, "
				+ "FOREIGN KEY (DNI_F) REFERENCES directores(DNI) , "
				+ "FOREIGN KEY (despacho) REFERENCES despachos(codigo_desp)";
		mysql.createTable(bd, "directores", data);

		String deci = JOptionPane.showInputDialog("Quieres introducir datos? ");

		if (deci.equalsIgnoreCase("si")) {
			// Insertamos diferentes datos a las tablas que seleccionemos
			for (int i = 0; i < 5; i++) {
				String table = JOptionPane.showInputDialog("En que tabla quieres introducir un valor: ");
				if (table.equals("despachos")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Codigo: ");
					String capacidad = JOptionPane.showInputDialog("Capacidad: ");
					total += id + "," + capacidad;
					mysql.insertData(bd, table, "(codigo_desp, capacidad)", total);
				} else if (table.equals("directores")) {
					String total = "";
					String dni = JOptionPane.showInputDialog("DNI: ");
					String nomApels = JOptionPane.showInputDialog("Nombre y apellidos: ");
					total += "\'" + dni + "\',\'" + nomApels + "\'";
					mysql.insertData(bd, table, "(DNI,nomApels)", total);
				}
			}
		}

		// cerramos la conexion con el servidor
		mysql.closeConnection();
	}

}
