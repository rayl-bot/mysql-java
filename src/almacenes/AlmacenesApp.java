package almacenes;

import javax.swing.JOptionPane;

import mysql.MySQL;

public class AlmacenesApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
				System.out.println("Se ha introducido la tabla almacenes a la base de datos " + bd);
				String data = "codigo_alm int PRIMARY KEY, lugar varchar(100), capacidad int";
				mysql.createTable(bd, "almacenes", data);

				// creamos la tabla introduciendo los valores
				System.out.println("Se ha introducido la tabla cajas a la base de datos " + bd);
				data = "numRef char(5) PRIMARY KEY, contenido varchar(100), valor int, almacen int, FOREIGN KEY (almacen) REFERENCES almacenes(codigo_alm)";
				mysql.createTable(bd, "cajas", data);

				String deci = JOptionPane.showInputDialog("Quieres introducir datos? ");

				if (deci.equalsIgnoreCase("si")) {
					// Insertamos diferentes datos a las tablas que seleccionemos
					for (int i = 0; i < 5; i++) {
						String table = JOptionPane.showInputDialog("En que tabla quieres introducir un valor: ");
						if (table.equals("almacenes")) {
							String total = "";
							String id = JOptionPane.showInputDialog("Codigo: ");
							String lugar = JOptionPane.showInputDialog("Lugar: ");
							String capacidad = JOptionPane.showInputDialog("Capacidad: ");
							total += id + ",\'" + lugar + "\'," + capacidad;
							mysql.insertData(bd, table, "(codigo_alm, lugar, capacidad)", total);
						} else if (table.equals("cajas")) {
							String total = "";
							String ref = JOptionPane.showInputDialog("Ref: ");
							String contenido = JOptionPane.showInputDialog("Contenido: ");
							String valor = JOptionPane.showInputDialog("Valor: ");
							total += "\'" + ref + "\',\'" + contenido + "\'," + valor;
							mysql.insertData(bd, table, "(numRef,contenido,valor)", total);
						}
					}
				}

				// cerramos la conexion con el servidor
				mysql.closeConnection();
	}

}
