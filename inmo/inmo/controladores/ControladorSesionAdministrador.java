package inmo.controladores;

import java.util.ArrayList;
import java.util.List;

import inmo.excepciones.UsuarioException;
import inmo.usuarios.Administrador;
import inmo.usuarios.GestorUsuarios;
import inmo.usuarios.Usuario;

public class ControladorSesionAdministrador {
	
	private GestorUsuarios gu; /** el gestor de usuarios */
	private Administrador admin; /** el administrador de la sesión */

	/**
	 * Constructor que asigna el gestor de usuarios que recibe como parámetro	 * 
	 * @param gesu es el gestor de usuarios 
	 */
	public ControladorSesionAdministrador(GestorUsuarios gesu) {
		gu = gesu;
	}

	/**
	 * Metodo para identificar a un administrativo en el sistema, guardando una referencia en el atributo admin
	 * @param login del administrativo
	 * @param clave del administrativo
	 * @throws ExcepcionUsuario si las credenciales de usuario no son válidas
	 * 			o si las credenciales no son de un administrativo
	 */
	public void identificarAdministrador(String login, String clave) throws UsuarioException {
		if (gu.validarUsuario(login, clave)) {
			// admin válido
			try {
				admin = (Administrador) gu.getUsuario(login);
			} catch (ClassCastException e) {
				throw new UsuarioException("Usuario "+login+" no es Administrador");
			}
		}
		else // admin no válido
			throw new UsuarioException("Credenciales de usuario no válidas");
	}

	/**
	 * Metodo para crear un empleado
	 * @param login del empleado a crear (ÚNICO)
	 * @param clave del empleado a crear
	 * @param nombre del empleado a crear
	 * @throws ExcepcionUsuario si el administrador no se ha identificado en el sistema,
	 *  		si ya existe un usuario con ese login
	 * 			o si hubo un error interno en la creación del empleado
	 */
	public void crearEmpleado(String login, String clave, String nombre) throws UsuarioException {
		if (admin== null)
			throw new UsuarioException("Autenticación requerida");
		// creo empleado
		gu.crearUsuario(login, clave, nombre, "Empleado");
	}
	
	/**
	 * Metodo para recuperar una lista de descripciones de usuario de cierto tipo
	 * @param tipo de usuario de interés
	 * @return lista de descripciones de usuarios del tipo de interés
	 * @throws ExcepcionUsuario si el administrativo no se ha identificado en el sistema,  		
	 * 			o si el tipo de usuario solicitado no existe
	 */
	public List<String> listarUsuariosTipo(String tipo) throws UsuarioException {
		if (admin== null)
			throw new UsuarioException("Autenticación requerida");
		// obtengo lista de descripciones de ususarios y la devuelvo
		List<String> descUsuarios = new ArrayList<>();
		for (Usuario us : gu.listarUsuariosTipo(tipo)) 
			descUsuarios.add(us.toString());
		return descUsuarios;
	}
	
	/**
	 * Metodo para cerrar sesión que elimina la referencia a admin
	 */
	public void cerrarSesion() {
		admin = null;
	}
}
