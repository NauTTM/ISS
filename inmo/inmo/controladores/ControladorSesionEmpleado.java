package inmo.controladores;

import java.util.ArrayList;



import java.util.List;



import inmo.excepciones.UsuarioException;
import inmo.excepciones.ViviendaException;

import inmo.usuarios.Empleado;
import inmo.usuarios.GestorUsuarios;

import inmo.viviendas.Anuncio;

import inmo.viviendas.GestorAnuncio;


public class ControladorSesionEmpleado {
	private GestorUsuarios gu; /** el gestor de usuarios */
	private Empleado emple;
	
	public ControladorSesionEmpleado(GestorUsuarios gesu) {
		this.gu = gesu;
	}
	
	
	public boolean identificarEmpleado(String login, String clave) throws UsuarioException {
		if (gu.validarUsuario(login, clave)) {
			// usuario válido
			try {
				this.setEmple((Empleado) gu.getUsuario(login));
				return true;
			} catch (ClassCastException e) {
				throw new UsuarioException("Usuario "+login+" no es Empleado");
				
			}
		}
		else // usuario no válido
			throw new UsuarioException("Credenciales de usuario no válidas");
	}
	
	public List<String> recuperarViviendasPendientesValidacion()throws UsuarioException {
		if (emple == null)
			throw new UsuarioException("Autenticación requerida");
		// obtengo lista de descripciones de ususarios y la devuelvo
		List<String> pendientes = new ArrayList<>();
		for (Anuncio a : GestorAnuncio.listarAnunciosPendientes()) 
			pendientes.add(a.toString());
		return pendientes;
	}

	
	
	public void cerrarSesion() {
		this.setEmple(null);
	}

	public void validarAnuncioVivienda(int i, boolean b)throws UsuarioException,ViviendaException {
		if (emple == null){
			throw new UsuarioException("Autenticación requerida");
		}else{
		GestorAnuncio.validarAnuncio(i,b);
		}
	}


	public Empleado getEmple() {
		return emple;
	}


	public void setEmple(Empleado emple) {
		this.emple = emple;
	}
	 
}
