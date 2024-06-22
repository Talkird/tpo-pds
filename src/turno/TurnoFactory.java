package turno;

import java.util.Date;

import medico.Medico;
import paciente.Paciente;

public class TurnoFactory {
    public Cita generateCita(Medico medico, Paciente paciente, Date fecha, Estado estado, String motivo, int id) {
        return new Cita(medico, paciente, fecha, estado, motivo, id);
    }

    public Turno generateTratamiento(Medico medico, Paciente paciente, Date fecha, Estado estado, String motivo,
            int id) {
        return new Tratamiento(medico, paciente, fecha, estado, motivo, id);
    }

}
