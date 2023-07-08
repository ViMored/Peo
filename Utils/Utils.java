package Utils;
import Entidades.Libro;
import Entidades.Reserva;
import Entidades.Usuario;

import java.io.*;
import java.util.List;
public class Utils {

    /**
     * Método encargado de leer el archivo de "libros.txt".
     */
    public static void leerArchivoLibros(List<Libro> listaLibros) {

        // Leer el archivo "libros.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("libros.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] chain = line.split(",");
                String isbn = chain[0];
                String titulo = chain[1];
                String autor = chain[2];
                String categoria = chain[3];
                int copias = Integer.parseInt(chain[4]);
                int precio = Integer.parseInt(chain[5]);

                Libro libro = new Libro(isbn,titulo,autor,categoria,copias,precio);
                listaLibros.add(libro);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * Método encargado de leer el archivo de "usuarios.txt".
     */
    public static void leerArchivoUsuarios(List<Usuario> listaUsuarios) {

        // Leer el archivo "usuarios.txt"
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] chain = line.split(",");
                String rut = chain[0];
                String nombre = chain[1];
                String apellido = chain[2];
                String contrasenia = chain[3];

                Usuario usuarios = new Usuario(rut,nombre,apellido,contrasenia);
                listaUsuarios.add(usuarios);
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    public static void reservas(List<Reserva> listaReserva) {
        // Escribir el archivo "usuarios.txt"
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("reservas.txt"));
            for (Reserva reserva : listaReserva) {
                String linea = reserva.getRutVendedor() + "," + reserva.getNombre() + "," + reserva.getApellido() + "," +
                        reserva.getIsbnLibro() + "," + reserva.getNombreLibro() + "," + reserva.getTipoTransaccion();
                writer.write(linea);
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e){

            System.out.println("[!] Ha ocurrido un error al leer el archivo [!]");
            e.printStackTrace();

        }
    }

    public static void sobreEscribir(List<Libro> listaLibros) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("libros.txt"));

            for (Libro libro : listaLibros) {
                String linea = libro.getIsbn() + "," + libro.getTitulo() + "," + libro.getAutor() + "," + libro.getCategoria()
                        + "," + libro.getCopias() + "," + libro.getStock();
                writer.write(linea);
                writer.newLine();
            }
            //9788498383620,El nombre del viento,Patrick Rothfuss,Fantasía,5,20

            writer.close();
        }
        catch (IOException e){

            System.out.println("[!] Ha ocurrido un error al crear el archivo [!]");
            e.printStackTrace();

        }
    }
}
