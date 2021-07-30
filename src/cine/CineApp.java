package cine;

import javax.swing.JOptionPane;

import mysql.MySQL;

public class CineApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		MySQL mysql = new MySQL();

		// establecemos conexionc con el servidor
		mysql.openConnection();

		// introducimos el nombre de la nueva base de datos
		// Tienda de informatica
		String bd = JOptionPane.showInputDialog("Introduce el nombre de la nueva base de datos: ");
		mysql.createDB(bd);// creamos una base de datos con el nomrbe introducido anteriormente

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla peliculas a la base de datos " + bd);
		String data = "codigo_peli int PRIMARY KEY, nombre varchar(100), calEdad int";
		mysql.createTable(bd, "peliculas", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla salas a la base de datos " + bd);
		data = "codigo_sala char(5) PRIMARY KEY, nombre varchar(100), pelicula int, FOREIGN KEY (pelicula) REFERENCES peliculas(codigo_peli)";
		mysql.createTable(bd, "salas", data);

		String deci = JOptionPane.showInputDialog("Quieres introducir datos? ");

		if (deci.equalsIgnoreCase("si")) {
			// Insertamos diferentes datos a las tablas que seleccionemos
			for (int i = 0; i < 5; i++) {
				String table = JOptionPane.showInputDialog("En que tabla quieres introducir un valor: ");
				if (table.equals("peliculas")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Codigo: ");
					String nombre = JOptionPane.showInputDialog("Lugar: ");
					String calEdad = JOptionPane.showInputDialog("CalEdad: ");
					total += id + ",\'" + nombre + "\'," + calEdad;
					mysql.insertData(bd, table, "(codigo_peli, nombre, calEdad)", total);
				} else if (table.equals("salas")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Codigo: ");
					String nombre = JOptionPane.showInputDialog("Nombre: ");
					total += "\'" + id + "\',\'" + nombre + "\'";
					mysql.insertData(bd, table, "(codigo_sala,nombre)", total);
				}
			}
		}

		// cerramos la conexion con el servidor
		mysql.closeConnection();
	}

}
