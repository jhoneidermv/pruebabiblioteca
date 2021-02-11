package dominio.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dominio.Libro;
import dominio.repositorio.RepositorioLibro;
import dominio.repositorio.RepositorioPrestamo;
import testdatabuilder.LibroTestDataBuilder;

public class LibroTest {

	private static final int ANIO = 2010;
	private static final String ISBN = "1234";
	private static final String NOMBRE_LIBRO = "Cien años de soledad";

	Libro libro;

	@Test
	public void crearLibroTest() {
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder()
				.conTitulo(NOMBRE_LIBRO)
				.conIsbn(ISBN)
				.conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();

		// assert
		assertEquals(NOMBRE_LIBRO, libro.getTitulo());
		assertEquals(ISBN, libro.getIsbn());
		assertEquals(ANIO, libro.getAnio());
	}

	@Test
	public void isbnEsPalindromoTest() {
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder()
				.conTitulo(NOMBRE_LIBRO)
				.conIsbn("ana")
				.conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();
		boolean esPalindromo = libro.esPalindromo(libro.getIsbn());

		// assert
		assertTrue(esPalindromo);
	}

	@Test
	public void isbnNoEsPalindromoTest() {

		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder()
				.conTitulo(NOMBRE_LIBRO)
				.conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();
		boolean esPalindromo = libro.esPalindromo(libro.getIsbn());

		// assert
		Assert.assertFalse(esPalindromo);
	}

	@Test
	public void sumaDigitosIsbnMayorATreintaTest() {
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder().conTitulo(NOMBRE_LIBRO)
				.conIsbn("9999gft").conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();
		boolean sumaDigitos = libro.digitosMayorATreinta(libro.getIsbn());

		// assert
		assertTrue(sumaDigitos);
	}

	@Test
	public void sumaDigitosIsbnMenorATreintaTest() {
		// arrange
		LibroTestDataBuilder libroTestDataBuilder = new LibroTestDataBuilder()
				.conTitulo(NOMBRE_LIBRO).conIsbn("999gft")
				.conAnio(ANIO);

		// act
		Libro libro = libroTestDataBuilder.build();
		boolean sumaDigitos = libro.digitosMayorATreinta(libro.getIsbn());

		// assert
		Assert.assertFalse(sumaDigitos);
	}
}
