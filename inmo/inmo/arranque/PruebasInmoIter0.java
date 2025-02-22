package inmo.arranque;

import java.util.List;


import inmo.controladores.ControladorSesionAdministrador;
import inmo.controladores.ControladorSesionCliente;
import inmo.excepciones.UsuarioException;
import inmo.usuarios.GestorUsuarios;

public class PruebasInmoIter0 {

	/**
	 * Método main(). No se esperan parámetros.
	 * @param args parámetros por línea de comandos que no se tratan.
	 */
	public static void main(String[] args) {

		//*************************************
		//*******INICIALIZACION GESTORES*******
		//*************************************
		// Instancio el gestor de usuarios
		GestorUsuarios gu = new GestorUsuarios();
		// Creo administrador inicial
		try {
			gu.crearUsuario("admin", "admin", "Marga Admin", "Administrador");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		} 

		//*************************************
		//****INICIALIZACION CONTROLADORES*****
		//*************************************				
		// Instancio controladores de sesión
		ControladorSesionAdministrador csadmin = new ControladorSesionAdministrador(gu);
		ControladorSesionCliente cscli = new ControladorSesionCliente(gu); // TODO: ajustar alumnos

		System.out.println("////////////////////////////////////////////////////////");
		System.out.println("// CASOS DE USO ITERACIÓN 0");
		System.out.println("////////////////////////////////////////////////////////\n");	
		casosUsoClienteIter0(cscli);
		casosUsoAdmin(csadmin);
	}

	/**
	 * Método que realiza el caso de uso de creación de clientes
	 * @param csadmin controlador de sesión de cliente
	 */
	private static void casosUsoClienteIter0(ControladorSesionCliente cscli) {
		System.out.println("/// REGISTRO DE CLIENTES ///\n");
		System.out.println("Se registran tres clientes\n");
		try {
			cscli.crearCliente("cli0", "clave", "Plácida Landlady");
			cscli.crearCliente("cli1", "clave", "Gonzalo Mogul");
			cscli.crearCliente("cli2", "clave", "Mario Homeless");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Método que realiza los casos de uso de los administradores
	 * @param csadmin controlador de sesión de administrador
	 */
	private static void casosUsoAdmin(ControladorSesionAdministrador csadmin) {
		System.out.println("/// CASOS DE USO ADMINISTRADOR ///\n");

		// ------------------------------------
		// -- Usuario admin (ADMINISTRATIVO) --
		// ------------------------------------
		System.out.println("<<inicio sesión admin>>");
		System.out.println("(admin inicial creado en el main)\n");
		try {
			csadmin.identificarAdministrador("admin","admin");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}		
		//*************************************
		//*********CREACION DE USUARIOS********
		//*************************************			
		System.out.println("CREACION DE USUARIOS");		
		try {			
			System.out.println("admin crea tres empleados");			
			csadmin.crearEmpleado("emp0", "clave", "Nora Freerider");
			csadmin.crearEmpleado("emp1", "clave", "Eugenio Donkeyseller");
			csadmin.crearEmpleado("emp2", "clave", "Alicia Dealer");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		//*************************************
		//************LISTAR USUARIOS**********
		//*************************************	
		System.out.println("\nLISTAR USUARIOS");
		System.out.println("\nlista de clientes:");
		try {
			List<String> descs = csadmin.listarUsuariosTipo("Cliente");
			for (String desc :descs)
				System.out.println(desc+"\n");
			System.out.println("hay "+descs.size()+" usuarios de tipo \"Cliente\"");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}		
		System.out.println("\nlista de empleados:");
		try {
			List<String> descs = csadmin.listarUsuariosTipo("Empleado");
			for (String desc :descs)
				System.out.println(desc+"\n");
			System.out.println("hay "+descs.size()+" usuarios de tipo \"Empleado\"");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}		
		System.out.println("\nlista de administradores:");
		try {
			List<String> descs = csadmin.listarUsuariosTipo("Administrador");
			for (String desc : descs)
				System.out.println(desc+"\n");
			System.out.println("hay "+descs.size()+" usuarios de tipo \"Administrador\"");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}	
		System.out.println("\n<<cierre sesión admin>>");
		csadmin.cerrarSesion();
	}
}
