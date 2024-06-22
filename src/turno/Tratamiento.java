package turno;

import java.util.Date;

import medico.Medico;
import paciente.Paciente;

public class Tratamiento extends Turno {

    public Tratamiento(Medico medico, Paciente paciente, Date fecha, Estado estado, String motivo, int id) {
        super(medico, paciente, fecha, estado, motivo, id);
    }

}
