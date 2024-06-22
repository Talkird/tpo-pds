package paciente;

public class PacienteFactory {
    public Paciente generatePaciente(String nombre, String apellido, String direccion, String telefono,
            String obraSocial, java.util.Date fechaNacimiento, String email, Tipo tipo, int dni) {

        return new Paciente(nombre, apellido, direccion, telefono, obraSocial,
                new java.sql.Date(fechaNacimiento.getTime()),
                email, tipo, dni);
    }
}
