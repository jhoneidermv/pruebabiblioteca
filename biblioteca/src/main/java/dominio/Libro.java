package dominio;

public class Libro {

	private String isbn;
	private String titulo;
	private int anio;

	public Libro(String isbn, String titulo, int anio) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.anio = anio;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAnio() {
		return anio;
	}

	public String getIsbn() {
		return isbn;
	}
	
	public boolean esPalindromo(String isbn) {
		String isbnInvertido = "";
		int tamanoIsbn = isbn.length();
		for (int i = tamanoIsbn - 1; i >= 0; i--) {
			isbnInvertido += isbn.charAt(i);
		}
		
		return isbn.equalsIgnoreCase(isbnInvertido);
	}

	public boolean digitosMayorATreinta(String isbn) {
		int suma = 0;
		for (int i = 0; i < isbn.length(); i++) {
			if (Character.isDigit(isbn.charAt(i))) {
				suma += Character.getNumericValue(isbn.charAt(i));
			}
		}
		return suma >= 30;
	}
}
