package tienda_informatica;

import mysql.MySQL;
import javax.swing.*;

public class TiendaInformaticaApp {

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
		String table = JOptionPane.showInputDialog("Introduce el nombre de la nueva tabla de : " + bd);
		String data = "codigo_fabricante int PRIMARY KEY, nombre varchar(100)";
		mysql.createTable(bd, table, data);

		// creamos la tabla introduciendo los valores
		table = JOptionPane.showInputDialog("Introduce el nombre de la nueva tabla de : " + bd);
		data = "codigo_articulo int PRIMARY KEY, nombre varchar(100), precio int, fabricante int, FOREIGN KEY (fabricante) REFERENCES fabricantes(codigo_fabricante)";
		mysql.createTable(bd, table, data);

		String deci = JOptionPane.showInputDialog("Quieres introducir datos? ");
		// Inserciones de diferentes articulos y fabricantes
		if (deci.equalsIgnoreCase("si")) {
			for (int i = 0; i < 5; i++) {
				table = JOptionPane.showInputDialog("En que tabla quieres introducir un valor: ");
				if (table.equals("fabricantes")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Id: ");
					String nombre = JOptionPane.showInputDialog("nombre: ");
					total += id + ",\'" + nombre + "\'";
					mysql.insertData(bd, table, "(codigo_fabricante, nombre)", total);
				} else if (table.equals("articulos")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Id: ");
					String nombre = JOptionPane.showInputDialog("nombre: ");
					String precio = JOptionPane.showInputDialog("precio: ");
					total += id + ",\'" + nombre + "\'," + precio;
					mysql.insertData(bd, table, "(codigo_articulo,nombre,precio)", total);
				}
			}
		}

		// cerramos la conexion con el servidor
		mysql.closeConnection();

	}

}
