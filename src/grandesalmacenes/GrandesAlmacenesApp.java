package grandesalmacenes;

import javax.swing.JOptionPane;

import mysql.MySQL;

public class GrandesAlmacenesApp {

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
		System.out.println("Se ha introducido la tabla cajeros a la base de datos " + bd);
		String data = "codi int PRIMARY KEY, nomApels varchar(255)";
		mysql.createTable(bd, "cajeros", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla productos a la base de datos " + bd);
		data = "codi int PRIMARY KEY, nom varchar(100), precio int";
		mysql.createTable(bd, "productos", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla maquinas_registradoras a la base de datos " + bd);
		data = "codi int PRIMARY KEY, piso int";
		mysql.createTable(bd, "maquinas_registradoras", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla venta a la base de datos " + bd);
		data = "cajero int, maquina int, producto int, " + "FOREIGN KEY (cajero) REFERENCES cajeros(codi),"
				+ "FOREIGN KEY (maquina) REFERENCES maquinas_registradoras(codi),"
				+ "FOREIGN KEY (producto) REFERENCES productos(codi)," + "PRIMARY KEY (cajero,maquina,producto)";
		mysql.createTable(bd, "venta", data);

		String deci = JOptionPane.showInputDialog("Quieres introducir datos? ");

		if (deci.equalsIgnoreCase("si")) {
			// Insertamos diferentes datos a las tablas que seleccionemos
			for (int i = 0; i < 5; i++) {
				String table = JOptionPane.showInputDialog("En que tabla quieres introducir un valor: ");
				if (table.equals("cajero")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Id: ");
					String nombre = JOptionPane.showInputDialog("Nombre: ");
					total += id + ",\'" + nombre + "\'";
					mysql.insertData(bd, table, "(codi, nomApels)", total);
				} else if (table.equals("productos")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Id: ");
					String nom = JOptionPane.showInputDialog("Nombre: ");
					String precio = JOptionPane.showInputDialog("Precio: ");
					total += id + ",\'" + nom + "\'" + precio;
					mysql.insertData(bd, table, "(codi,nom,precio)", total);
				} else if (table.equals("maquinas_registradoras")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Id: ");
					String piso = JOptionPane.showInputDialog("Piso: ");
					total += id + "," + piso;
					mysql.insertData(bd, table, "(codi,piso)", total);
				}
			}
		}

		// cerramos la conexion con el servidor
		mysql.closeConnection();
	}

}
