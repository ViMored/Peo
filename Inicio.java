import Entidades.Libro;
import Entidades.Usuario;
import Utils.Utils;

import javax.swing.*;
import java.awt.event.ContainerAdapter;
import java.util.ArrayList;
import java.util.List;

public class Inicio extends JFrame implements ISistema{
    private JTextField rutField;
    private JPasswordField contraseniaField;
    private JButton iniciarSesionButton;
    private JButton cerrarProgramaButton;
    private JPanel Iniciarsecion;

    private List<Libro> listaLibros;
    private List<Usuario> listaUsuarios;

    private Usuario usuarioActual;

    public Inicio(){
        listaLibros = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        cargarUsuarios();
        cargarLibros();
        setContentPane(Iniciarsecion);
        setTitle("Inicio de sesión");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        iniciarSesionButton.addActionListener(e -> IniciarSesion());
        cerrarProgramaButton.addActionListener(e -> CerrarPrograma());
        iniciarSesionButton.addContainerListener(new ContainerAdapter() {
        });
    }

    @Override
    public void IniciarSesion() {
        try {
            String rut = rutField.getText();
            String contrasenia = String.valueOf(contraseniaField.getPassword());
            if (!rut.isEmpty() && !contrasenia.isEmpty()) {
                if (verificarUsuario(rut, contrasenia)) {
                    usuarioActual = obtenerUsuarioPorRut(rut);
                    MenuPrincipal menuPrincipal = new MenuPrincipal();
                    menuPrincipal.setUsuarioActual(usuarioActual);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(iniciarSesionButton, "Los datos ingresados no pertenecen a un usuario.");
                }
            } else {
                JOptionPane.showMessageDialog(iniciarSesionButton,"Porfavor llene los campos.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(iniciarSesionButton,"¡Ha ocurrido un error!");
        }
    }

    private boolean verificarUsuario(String rut, String contrasenia) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getRut().equals(rut)) {
                if (usuario.getContrasenia().equals(contrasenia)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }


    @Override
    public void CerrarPrograma() {
        //TODO hacer que el programa se cierre y si se cierra guardar
        //los datos en el tXT

        System.exit(0);
    }

    private void cargarLibros() {
        Utils.leerArchivoLibros(listaLibros);
    }

    private void cargarUsuarios() {
        Utils.leerArchivoUsuarios(listaUsuarios);
    }

    private Usuario obtenerUsuarioPorRut(String rut) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getRut().equals(rut)) {
                return usuario;
            }
        }
        return null;
    }
}
