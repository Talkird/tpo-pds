package paciente;

import java.sql.Date;
import java.util.logging.Logger;

public class Paciente {
    private String nombre, apellido, direccion, telefono, obraSocial;
    private Date fechaNacimiento;
    private String correo;
    private Tipo tipo;
    private int dni;
    private Logger logger;

    public Paciente(String nombre, String apellido, String direccion, String telefono, String obraSocial,
            Date fechaNacimiento, String correo, Tipo tipo, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.obraSocial = obraSocial;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.tipo = tipo;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getDni() {
        return dni;
    }

}
