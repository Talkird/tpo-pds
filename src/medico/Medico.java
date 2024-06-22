package medico;

public class Medico {
    private String nombre, apellido, especialidad;
    private int matricula;

    public Medico(String nombre, String apellido, String especialidad, int matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getMatricula() {
        return matricula;
    }

}
