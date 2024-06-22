package medico;

public class MedicoFactory {
    public Medico generateMedico(String nombre, String apellido, String especialidad,
            int matricula) {

        return new Medico(nombre, apellido, especialidad, matricula);
    }

}
