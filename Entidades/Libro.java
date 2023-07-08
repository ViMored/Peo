package Entidades;

public class Libro {

    private String isbn;
    private String titulo;
    private String autor;
    private String categoria;
    private int copias;
    private int stock;

    public Libro(String isbn, String titulo, String autor, String categoria, int copias, int stock) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.copias = copias;
        this.stock = stock;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCopias() {
        return copias;
    }

    public int getStock() {
        return stock;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }
}
