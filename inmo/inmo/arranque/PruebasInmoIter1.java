package inmo.arranque;

import java.util.List;

import inmo.controladores.ControladorSesionAdministrador;
import inmo.controladores.ControladorSesionCliente;
import inmo.controladores.ControladorSesionEmpleado;
import inmo.excepciones.UsuarioException;
import inmo.excepciones.ViviendaException;
import inmo.usuarios.GestorUsuarios;
import inmo.viviendas.GestorAnuncio;
import inmo.viviendas.Tipo;
import inmo.viviendas.Zona;



public class PruebasInmoIter1 {

	/**
	 * Método main(). No se esperan parámetros.
	 * @param args parámetros por línea de comandos que no se tratan.
	 * @throws UsuarioException 
	 */
	public static void main(String[] args) throws UsuarioException {

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
		ControladorSesionCliente cscli = new ControladorSesionCliente(gu);
		ControladorSesionEmpleado csemp = new ControladorSesionEmpleado(gu);

		System.out.println("////////////////////////////////////////////////////////");
		System.out.println("// CASOS DE USO ITERACIÓN 0");
		System.out.println("////////////////////////////////////////////////////////\n");	
		casosUsoClienteIter0(cscli);
		casosUsoAdmin(csadmin);

		System.out.println("\n////////////////////////////////////////////////////////");
		System.out.println("// CASOS DE USO ITERACIÓN 1");
		System.out.println("////////////////////////////////////////////////////////\n");	
		casosUsoClienteIter1a(cscli);
		casosUsoEmpleadoIter1(csemp);
		casosUsoClienteIter1b(cscli);
		casosUsoClienteIter1opt(cscli);
	}

	/**
	 * Método que realiza el caso de uso de creación de clientes
	 * @param cscli controlador de sesión de cliente
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

	/**
	 * Método que realiza los casos de uso de clientes de la iteración 1 (primera parte)
	 * @param cscli controlador de sesión de cliente
	 */
	private static void casosUsoClienteIter1a(ControladorSesionCliente cscli) throws UsuarioException {
		System.out.println("/// CASOS DE USO CLIENTES ITERACIÓN 1 (primera parte) ///\n");

		//*************************************
		//*******CREAR ANUNCIO*****
		//*************************************
		System.out.println("CREAR ANUNCIO\n");

		// ------------------------------------
		// -- Cliente cli0 --
		// ------------------------------------
		System.out.println("<<inicio sesión cli0>>");
		try {
			cscli.identificarCliente("cli0","clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\ncli0 crea varios anuncios de viviendas");
		try {
			cscli.crearAnuncioVivienda(Tipo.PISO, Zona.CENTRO, 3, 100, 600);
			cscli.crearAnuncioVivienda(Tipo.PISO, Zona.CENTRO, 2, 70, 450);
			cscli.crearAnuncioVivienda(Tipo.PISO, Zona.RONDILLA, 3, 70, 400);
			cscli.crearAnuncioVivienda(Tipo.PISO, Zona.RONDILLA, 2, 50, 300);
			cscli.crearAnuncioVivienda(Tipo.PISO, Zona.PILARICA, 4, 90, 500);
		}catch (UsuarioException | ViviendaException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n<<cierre sesión cli0>>");
		cscli.cerrarSesion();


		// ------------------------------------
		// -- Cliente cli1 --
		// ------------------------------------
		System.out.println("\n<<inicio sesión cli1>>");
		try {
			cscli.identificarCliente("cli1","clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\ncli1 crea varios anuncios de viviendas");
		try {
			cscli.crearAnuncioVivienda(Tipo.CASA, Zona.PERAL, 5, 180, 1100);
			cscli.crearAnuncioVivienda(Tipo.CASA, Zona.PILARICA, 4, 140, 900);
			cscli.crearAnuncioVivienda(Tipo.PISO, Zona.CENTRO, 4, 120, 1200);
			cscli.crearAnuncioVivienda(Tipo.PISO, Zona.CENTRO, 2, 60, 800);
			cscli.crearAnuncioVivienda(Tipo.PISO, Zona.PILARICA, 2, 60, 400);
			System.out.println("\n(cli1 intenta ahora poner un sexto anuncio, fallará al superar el límite de 5 anuncios como máximo)");
			cscli.crearAnuncioVivienda(Tipo.PISO, Zona.RONDILLA, 3, 80, 500);
		} catch (UsuarioException | ViviendaException e) {
			System.out.println(e.getMessage());
		}
		GestorAnuncio.listarAnunciosPendientes();
		System.out.println("\n<<cierre sesión cli1>>");
		cscli.cerrarSesion();
	}


	/**
	 * Método que realiza los casos de uso de empleados de la iteración 1
	 * @param csemp controlador de sesión de empleado
	 */
	private static void casosUsoEmpleadoIter1(ControladorSesionEmpleado csemp){
		System.out.println("\n/// CASOS DE USO EMPLEADOS ITERACIÓN 1 ///\n");

		//*************************************
		//*******LISTAR ANUNCIOS PENDIENTES*****
		//*************************************
		System.out.println("LISTAR ANUNCIOS PENDIENTES\n");
		//csemp.setPend(cscli.getListaAnuncioCliente());

		// ------------------------------------
		// -- Empleado emp0 --
		// ------------------------------------

		System.out.println("<<inicio sesión emp0>>");
		try {
			csemp.identificarEmpleado("emp0","clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\nemp0 lista los anuncios de viviendas pendientes de validación:\n");
		try {
			for (String s : csemp.recuperarViviendasPendientesValidacion())
				System.out.println(s + "\n");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		//*************************************
		//*******VALIDAR ANUNCIOS PENDIENTES*****
		//*************************************
		System.out.println("VALIDAR ANUNCIOS PENDIENTES\n");
		System.out.println("emp0 valida unos cuantos anuncios");
		try {
			csemp.validarAnuncioVivienda(0, true);
			csemp.validarAnuncioVivienda(1, true);
			csemp.validarAnuncioVivienda(2, true);
			csemp.validarAnuncioVivienda(3, true);
			csemp.validarAnuncioVivienda(5, true);
			csemp.validarAnuncioVivienda(6, true);
			csemp.validarAnuncioVivienda(7, false);
			csemp.validarAnuncioVivienda(8, true);
			System.out.println("\n(emp0 intenta ahora validar una vivienda que ya hizo)");
			csemp.validarAnuncioVivienda(0, true);
		} catch (UsuarioException | ViviendaException e) {
			System.out.println(e.getMessage());
		}		

		System.out.println("\n<<cierre sesión emp0>>");
		csemp.cerrarSesion();
	}

	/**
	 * Método que realiza los casos de uso de clientes de la iteración 1 (segunda parte)
	 * @param cscli controlador de sesión de cliente
	 */
	private static void casosUsoClienteIter1b(ControladorSesionCliente cscli) {
		System.out.println("\n/// CASOS DE USO CLIENTES ITERACIÓN 1 (segunda parte) ///\n");

		//*************************************
		//*******LISTAR ANUNCIOS CLIENTE*****
		//*************************************
		System.out.println("LISTAR ANUNCIOS CLIENTE\n");

		// ------------------------------------
		// -- Cliente cli0 --
		// ------------------------------------
		System.out.println("<<inicio sesión cli0>>");
		try {
			cscli.identificarCliente("cli0","clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\ncli0 lista sus anuncios de viviendas:\n");
		try {
			for (String s : cscli.recuperarMisAnuncios())
				System.out.println(s + "\n");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("<<cierre sesión cli0>>");
		cscli.cerrarSesion();


		// ------------------------------------
		// -- Cliente cli1 --
		// ------------------------------------
		System.out.println("\n<<inicio sesión cli1>>");
		try {
			cscli.identificarCliente("cli1","clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\ncli1 lista sus anuncios de viviendas:\n");
		try {
			for (String s : cscli.recuperarMisAnuncios())
				System.out.println(s + "\n");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("<<cierre sesión cli1>>");
		cscli.cerrarSesion();			
	}


	/**
	 * Método que realiza el caso de uso optativo de la iteración 1
	 * @param cscli controlador de sesión de cliente
	 * @throws UsuarioException 
	 */
	private static void casosUsoClienteIter1opt(ControladorSesionCliente cscli) throws UsuarioException {
		System.out.println("\n/// CASO DE USO OPTATIVO ITER 1 ///\n");

		System.out.println("RETIRAR ANUNCIO");		

		// ------------------------------------
		// -- Cliente cli1 --
		// ------------------------------------
		System.out.println("\n<<inicio sesión cli1>>");
		try {
			cscli.identificarCliente("cli1","clave");
		} catch (UsuarioException e) {
			System.out.println(e.getMessage());
		}

		//System.out.println("\ncli1 retira el anuncio de la vivienda #9");
		//try {
		//cscli.retirarAnuncioVivienda(9);
		//} catch (UsuarioException | ViviendaException e) {
		//System.out.println(e.getMessage());
		//}

		System.out.println("\ncli1 lista sus anuncios de viviendas:\n");
		for (String s : cscli.recuperarMisAnuncios())
			System.out.println(s + "\n");		

		System.out.println("<<cierre sesión cli1>>");
		cscli.cerrarSesion();		
	}	
}
