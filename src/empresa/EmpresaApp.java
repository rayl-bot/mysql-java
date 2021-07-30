package empresa;

import javax.swing.JOptionPane;

import mysql.MySQL;

public class EmpresaApp {

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
		System.out.println("Se ha introducido la tabla departamentos a la base de datos " + bd);
		String data = "codigo_dept int PRIMARY KEY, nombre varchar(100), presupuesto int";
		mysql.createTable(bd, "departamentos", data);

		// creamos la tabla introduciendo los valores
		System.out.println("Se ha introducido la tabla empleados a la base de datos " + bd);
		data = "DNI varchar(8) PRIMARY KEY, nombre varchar(100), apellidos varchar(255), dept int, FOREIGN KEY (dept) REFERENCES departamentos(codigo_dept)";
		mysql.createTable(bd, "empleados", data);

		String deci = JOptionPane.showInputDialog("Quieres introducir datos? ");

		if (deci.equalsIgnoreCase("si")) {
			// Insertamos diferentes datos a las tablas que seleccionemos
			for (int i = 0; i < 5; i++) {
				String table = JOptionPane.showInputDialog("En que tabla quieres introducir un valor: ");
				if (table.equals("departamentos")) {
					String total = "";
					String id = JOptionPane.showInputDialog("Codigo: ");
					String presupuesto = JOptionPane.showInputDialog("Presupuesto: ");
					total += id + "," + presupuesto;
					mysql.insertData(bd, table, "(codigo_dept, presupuesto)", total);
				} else if (table.equals("empleados")) {
					String total = "";
					String dni = JOptionPane.showInputDialog("DNI: ");
					String nombre = JOptionPane.showInputDialog("nombre: ");
					String apellidos = JOptionPane.showInputDialog("apellidos: ");
					total += "\'" + dni + "\',\'" + nombre + "\',\'" + apellidos + "\'";
					mysql.insertData(bd, table, "(DNI,nombre,apellidos)", total);
				}
			}
		}

		// cerramos la conexion con el servidor
		mysql.closeConnection();
	}

}
