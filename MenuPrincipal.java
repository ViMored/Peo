import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Entidades.Libro;
import Entidades.Reserva;
import Entidades.Usuario;
import Utils.Utils;

public class MenuPrincipal extends JFrame {

    private List<Libro> listaLibros;
    private List<Usuario> listaUsuarios;
    private List<Reserva> listaReservas;
    private JButton buscarLibroButton;
    private JButton prestarLibroButton;
    private JButton agregarLibroButton;
    private JButton devolverLibroButton;
    private JPanel panel;

    private Usuario usuarioActual;


    public MenuPrincipal() {
        listaLibros = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        listaReservas = new ArrayList<>();
        cargarLibros();
        cargarUsuarios();
        setContentPane(panel);
        setTitle("Menú Principal");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        prestarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = JOptionPane.showInputDialog(null, "Ingrese el ISBN del libro:");

                Libro libro = buscarLibroPorISBN(isbn);
                if (libro != null) {
                    if (libro.getCopias() > 0) {

                        libro.setCopias(libro.getCopias() - 1);
                        Reserva reserva = new Reserva(usuarioActual.getRut(), usuarioActual.getNombre(), usuarioActual.getApellido(), libro.getIsbn(), libro.getTitulo(), "Prestamo");
                        guardarReservas(reserva);
                        JOptionPane.showMessageDialog(null, "Préstamo exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "El libro con el ISBN " + isbn + " no tiene copias disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El libro con el ISBN " + isbn + " no existe en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        agregarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = JOptionPane.showInputDialog(null, "Ingrese el ISBN del libro:");

                Libro libroExistente = buscarLibroPorISBN(isbn);

                if (libroExistente != null) {
                    JOptionPane.showMessageDialog(null, "El libro con el ISBN " + isbn + " ya está registrado en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String titulo = JOptionPane.showInputDialog(null, "Ingrese el título del libro:");
                    String autor = JOptionPane.showInputDialog(null, "Ingrese el autor del libro:");
                    String categoria = JOptionPane.showInputDialog(null, "Ingrese la categoría del libro:");
                    int copias = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de copias del libro:"));
                    int precio = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el precio del libro:"));
                    Libro nuevoLibro = new Libro(isbn, titulo, autor, categoria, copias, precio);
                    listaLibros.add(nuevoLibro);
                    guardarLibros(listaLibros);
                    JOptionPane.showMessageDialog(null, "Libro agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        devolverLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = JOptionPane.showInputDialog(null, "Ingrese el ISBN del libro:");
                Libro libro = buscarLibroPorISBN(isbn);
                if (libro != null) {

                    libro.setCopias(libro.getCopias() + 1);
                    Reserva reserva = new Reserva(usuarioActual.getRut(), usuarioActual.getNombre(), usuarioActual.getApellido(), libro.getIsbn(), libro.getTitulo(), "Devolución");
                    guardarReservas(reserva);
                    JOptionPane.showMessageDialog(null, "Devolución exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "El libro con el ISBN " + isbn + " no existe en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buscarLibroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = JOptionPane.showInputDialog("Ingrese el código ISBN del libro:");

                Libro libroEncontrado = buscarLibroPorISBN(isbn);

                if (libroEncontrado != null) {
                    JOptionPane.showMessageDialog(null, "Libro encontrado:\n" +
                            "ISBN: " + libroEncontrado.getIsbn() + "\n" +
                            "Título: " + libroEncontrado.getTitulo() + "\n" +
                            "Autor: " + libroEncontrado.getAutor() + "\n" +
                            "Categoría: " + libroEncontrado.getCategoria() + "\n" +
                            "Copias: " + libroEncontrado.getCopias() + "\n" +
                            "Stock: " + libroEncontrado.getStock());
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún libro con el ISBN proporcionado.");
                }
            }
        });
    }
    private Libro buscarLibroPorISBN(String isbn) {
        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }
    private void cargarUsuarios() {
        Utils.leerArchivoUsuarios(listaUsuarios);
    }
    private void cargarLibros() {
        Utils.leerArchivoLibros(listaLibros);
    }

    private void guardarLibros(List<Libro> listaLibros) {
        Utils.sobreEscribir(this.listaLibros);
    }

    private void guardarReservas(Reserva reserva) {
        Utils.reservas(reserva);
    }

    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
    }
}