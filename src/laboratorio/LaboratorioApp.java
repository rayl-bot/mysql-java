package laboratorio;

import javax.swing.JOptionPane;

import mysql.MySQL;

public class LaboratorioApp {

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
		System.out.println("Se ha introducido la tabla cientificos a la base de datos " + bd);
		String data = "dni varchar(8) PRIMARY KEY, nomApels varchar(255)";
		mysql.createTable(bd, "cientificos", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla proyectos a la base de datos " + bd);
		data = "id char(4) PRIMARY KEY, nombre varchar(255), horas int";
		mysql.createTable(bd, "proyectos", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla asignado_a a la base de datos " + bd);
		data = "cientifico varchar(8), proyecto char(4), " + "FOREIGN KEY (cientifico) REFERENCES cientificos(dni),"
				+ "FOREIGN KEY (proyecto) REFERENCES proyectos(id)," + "PRIMARY KEY (cientifico,proyecto)";
		mysql.createTable(bd, "asignado_a", data);

		String deci = JOptionPane.showInputDialog("Quieres introducir datos? ");

		if (deci.equalsIgnoreCase("si")) {
			// Insertamos diferentes datos a las tablas que seleccionemos
			for (int i = 0; i < 5; i++) {
				String table = JOptionPane.showInputDialog("En que tabla quieres introducir un valor: ");
				if (table.equals("cientificos")) {
					String total = "";
					String dni = JOptionPane.showInputDialog("DNI: ");
					String nombre = JOptionPane.showInputDialog("Nombre: ");
					total += "\'" + dni + "\',\'" + nombre + "\'";
					mysql.insertData(bd, table, "(dni, nomApels)", total);
				} else if (table.equals("proyectos")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Id: ");
					String nom = JOptionPane.showInputDialog("Nombre: ");
					String horas = JOptionPane.showInputDialog("Horas: ");
					total += "\'" + id + "\',\'" + nom + "\'," + horas;
					mysql.insertData(bd, table, "(id,nombre,horas)", total);
				}
			}
		}

		// cerramos la conexion con el servidor
		mysql.closeConnection();
	}

}
