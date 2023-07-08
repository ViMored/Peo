package Entidades;

public class Reserva {
    private String rutVendedor;
    private String nombre;
    private String apellido;
    private String isbnLibro;
    private String nombreLibro;
    private String tipoTransaccion;

    public Reserva(String rutVendedor, String nombre, String apellido, String isbnLibro, String nombreLibro, String tipoTransaccion) {
        this.rutVendedor = rutVendedor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.isbnLibro = isbnLibro;
        this.nombreLibro = nombreLibro;
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getRutVendedor() {
        return rutVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getIsbnLibro() {
        return isbnLibro;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }
}
