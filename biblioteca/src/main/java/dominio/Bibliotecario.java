package dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dominio.excepcion.PrestamoException;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;

public class Bibliotecario {

	public static final String EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE = "El libro no se encuentra disponible";

	private RepositorioLibro repositorioLibro;
	private RepositorioPrestamo repositorioPrestamo;

	public Bibliotecario(RepositorioLibro repositorioLibro, RepositorioPrestamo repositorioPrestamo) {
		this.repositorioLibro = repositorioLibro;
		this.repositorioPrestamo = repositorioPrestamo;
	}

	public void prestar(String isbn, String nombreUsuario) {

		if (esPrestado(isbn)) {
			throw new PrestamoException(EL_LIBRO_NO_SE_ENCUENTRA_DISPONIBLE);
		} else {
			Libro libro = repositorioLibro.obtenerPorIsbn(isbn);
			if (libro != null) {
				if (libro.esPalindromo(isbn)) {
					throw new PrestamoException(
							"Los libros palíndromos solo se pueden utilizar en la biblioteca");
				} else {
					if (libro.digitosMayorATreinta(isbn)) {
						Prestamo prestamo = new Prestamo(new Date(), libro, fechaDeEntrega(new Date()), nombreUsuario);
						repositorioPrestamo.agregar(prestamo);
					} else {
						Prestamo prestamo = new Prestamo(new Date(), libro, null, nombreUsuario);
						repositorioPrestamo.agregar(prestamo);
					}
				}
			} else {
				throw new PrestamoException("El libro no existe en la base de datos");
			}
		}

	}

	public boolean esPrestado(String isbn) {
		Libro libroPrestado = repositorioPrestamo.obtenerLibroPrestadoPorIsbn(isbn);
		return libroPrestado != null;
	}

	
	
	public Date fechaDeEntrega(Date fecha) {
		Calendar calendar = new GregorianCalendar(); //Calendar.getInstance();
		calendar.setTime(fecha);
		int contador = 1;
		while (contador < 15) {
			if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				calendar.add(Calendar.DATE, 1);
				contador++;
			} else {
				calendar.add(Calendar.DATE, 1);
			}
		}
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			calendar.add(Calendar.DATE, 1);
		}
		
		return calendar.getTime();
	}
}
