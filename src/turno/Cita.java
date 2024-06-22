package turno;

import java.util.Date;

import medico.Medico;
import paciente.Paciente;

public class Cita extends Turno {

    public Cita(Medico medico, Paciente paciente, Date fecha, Estado estado, String motivo, int id) {
        super(medico, paciente, fecha, estado, motivo, id);
    }

}
