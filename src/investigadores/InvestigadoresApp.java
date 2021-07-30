package investigadores;

import javax.swing.JOptionPane;

import mysql.MySQL;

public class InvestigadoresApp {

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
		System.out.println("Se ha introducido la tabla facultad a la base de datos " + bd);
		String data = "codi int PRIMARY KEY, nombre varchar(100)";
		mysql.createTable(bd, "facultad", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla investigadores a la base de datos " + bd);
		data = "dni varchar(8) PRIMARY KEY, nomApels varchar(255), facualtad int, "
				+ "FOREIGN KEY (facultad) REFERENCES facultad(codi)";
		mysql.createTable(bd, "investigadores", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla equipos a la base de datos " + bd);
		data = "numSerie char(4) PRIMARY KEY, nombre varchar(100), facualtad int, "
				+ "FOREIGN KEY (facultad) REFERENCES facultad(codi)";
		mysql.createTable(bd, "equipos", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla reserva a la base de datos " + bd);
		data = "dni varchar(8), numSerie char(4), comiezo dateTime, fin dateTime, "
				+ "FOREIGN KEY (dni) REFERENCES investigadores(dni),"
				+ "FOREIGN KEY (numSerie) REFERENCES equipos(numSerie)," + "PRIMARY KEY (dni,numSerie)";
		mysql.createTable(bd, "reserva", data);

		String deci = JOptionPane.showInputDialog("Quieres introducir datos? ");

		if (deci.equalsIgnoreCase("si")) {
			// Insertamos diferentes datos a las tablas que seleccionemos
			for (int i = 0; i < 5; i++) {
				String table = JOptionPane.showInputDialog("En que tabla quieres introducir un valor: ");
				if (table.equals("investigadores")) {
					String total = "";
					String dni = JOptionPane.showInputDialog("DNI: ");
					String nombre = JOptionPane.showInputDialog("Nombre: ");
					total += "\'" + dni + "\',\'" + nombre + "\'";
					mysql.insertData(bd, table, "(dni, nomApels)", total);
				} else if (table.equals("facultad")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Id: ");
					String nom = JOptionPane.showInputDialog("Nombre: ");
					total += "\'" + id + "\',\'" + nom + "\'";
					mysql.insertData(bd, table, "(id,nombre)", total);
				} else if (table.equals("equipos")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Id: ");
					String nom = JOptionPane.showInputDialog("Nombre: ");
					total += "\'" + id + "\',\'" + nom + "\'";
					mysql.insertData(bd, table, "(id,nombre)", total);
				}
			}
		}

		// cerramos la conexion con el servidor
		mysql.closeConnection();
	}

}
