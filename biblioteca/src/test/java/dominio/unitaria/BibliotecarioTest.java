package dominio.unitaria;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import dominio.Bibliotecario;
import dominio.Libro;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import testdatabuilder.LibroTestDataBuilder;

public class BibliotecarioTest {
	RepositorioPrestamo repositorioPrestamo;
	RepositorioLibro repositorioLibro;
	Bibliotecario bibliotecario;
	
	@Before
	public void setBibliotecario() {
		repositorioPrestamo = mock(RepositorioPrestamo.class);
		repositorioLibro = mock(RepositorioLibro.class);
		bibliotecario = new Bibliotecario(repositorioLibro, repositorioPrestamo);
	}

	@Test
	public void esPrestadoTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(libro);
		
		
		// act 
		boolean esPrestado =  bibliotecario.esPrestado(libro.getIsbn());
		
		//assert
		assertTrue(esPrestado);
	}
	
	@Test
	public void libroNoPrestadoTest() {
		
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder();
		
		Libro libro = libroTestDataBuilder.build(); 
		
		when(repositorioPrestamo.obtenerLibroPrestadoPorIsbn(libro.getIsbn())).thenReturn(null);
		
		// act 
		boolean esPrestado =  bibliotecario.esPrestado(libro.getIsbn());
		
		//assert
		assertFalse(esPrestado);
	}
	
	@Test
	public void fechaDeEntregaTest() {
		// Arrange
		Calendar fecha = new GregorianCalendar(2017, Calendar.MAY, 24);
		
		// act
		Date nuevaFecha = bibliotecario.fechaDeEntrega(fecha.getTime());
		Calendar fechaObtenida = new GregorianCalendar();
		fechaObtenida.setTime(nuevaFecha);

		// assert
		assertEquals(9, fechaObtenida.get(Calendar.DAY_OF_MONTH));
		assertEquals(Calendar.JUNE, fechaObtenida.get(Calendar.MONTH));
		assertEquals(2017, fechaObtenida.get(Calendar.YEAR));
	}
	
	
}
