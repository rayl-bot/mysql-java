package piezasyproveedores;

import javax.swing.JOptionPane;

import mysql.MySQL;

public class PiezasyProveedoresApp {

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
		System.out.println("Se ha introducido la tabla piezas a la base de datos " + bd);
		String data = "codigo_pieza int PRIMARY KEY, nombre varchar(100)";
		mysql.createTable(bd, "piezas", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla proveedores a la base de datos " + bd);
		data = "id char(4) PRIMARY KEY, nombre varchar(100)";
		mysql.createTable(bd, "proveedores", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla suministra a la base de datos " + bd);
		data = "cod_pieza int, id_prov int, precio int, "
				+ "FOREIGN KEY (cod_pieza) REFERENCES piezas(codigo_pieza),"
				+ "FOREIGN KEY (id_prov) REFERENCES proveedores(id),"
				+ "PRIMARY KEY (cod_pieza,id_prov)";
		mysql.createTable(bd, "suministra", data);

		String deci = JOptionPane.showInputDialog("Quieres introducir datos? ");

		if (deci.equalsIgnoreCase("si")) {
			// Insertamos diferentes datos a las tablas que seleccionemos
			for (int i = 0; i < 5; i++) {
				String table = JOptionPane.showInputDialog("En que tabla quieres introducir un valor: ");
				if (table.equals("piezas")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Codigo: ");
					String nombre = JOptionPane.showInputDialog("Capacidad: ");
					total += id + ",\'" + nombre+"\'";
					mysql.insertData(bd, table, "(codigo_pieza, nombre)", total);
				} else if (table.equals("proveedores")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Id: ");
					String nom = JOptionPane.showInputDialog("Nombre: ");
					total += "\'"+id + "\',\'" + nom + "\'";
					mysql.insertData(bd, table, "(id,nombre)", total);
				}
			}
		}

		// cerramos la conexion con el servidor
		mysql.closeConnection();
	}

}
