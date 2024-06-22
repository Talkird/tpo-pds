import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factura.Factura;
import interfaces.Observer;
import interfaces.Subject;
import paciente.Paciente;
import turno.Estado;
import turno.Filtro;
import turno.Turno;

public class Admin implements Subject {
    private static Map<Paciente, ArrayList<Turno>> turnos;
    private List<Factura> facturas;
    private static List<Observer> observers;
    private Logger logger;

    public Admin(Logger logger) {
        turnos = new HashMap<>();
        observers = new ArrayList<>();
        facturas = new ArrayList<>();
        this.logger = logger;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void registrarPaciente(Paciente paciente) {
        turnos.put(paciente, new ArrayList<Turno>());
        logger.log("Nuevo paciente registrado: " + paciente.getNombre());
        this.notifyObservers("Nuevo paciente registrado: " + paciente.getNombre());
    }

    public void registrarTurno(Paciente paciente, Turno turno) {
        List<Turno> turnosProximos = getTurnosProximos();
        if (turnosProximos.isEmpty()) {
            turno.setEstado(Estado.PROGRAMADO);
            turnos.get(paciente).add(turno);
            this.notifyObservers("Nuevo turno registrado para el paciente: " + paciente.getNombre());
            Date now = new Date();
            this.generarFactura(now, paciente, turno);
            return;
        }

        for (Turno t : turnosProximos) {
            if (turno.getFecha().getDate() != t.getFecha().getDate() ||
                    turno.getFecha().getMonth() != t.getFecha().getMonth() ||
                    turno.getFecha().getYear() != t.getFecha().getYear()) {
                continue;

            } else if (turno.getFecha().getDate() == t.getFecha().getDate() &&
                    turno.getFecha().getMonth() == t.getFecha().getMonth() &&
                    turno.getFecha().getYear() == t.getFecha().getYear() &&
                    Math.abs(turno.getFecha().getHours() - t.getFecha().getHours()) >= 1) {
                continue;
            } else {
                this.notifyObservers("Error: Horario tomado por otro turno");
                return;
            }

        }
        turno.setEstado(Estado.PROGRAMADO);
        turnos.get(paciente).add(turno);

        this.notifyObservers("Nuevo turno registrado para el paciente: " + paciente.getNombre());
        Date now = new Date();
        this.generarFactura(now, paciente, turno);
    }

    public void actualizarFactura(int id) {
        for (Factura f : facturas) {
            if (f.getId() == id) {
                f.setPagado(true);
                this.notifyObservers("Paciente: " + f.getNombre() + " ha pagado una factura.");
            }
        }
    }

    public double getMontoFactura(int id) {
        for (Factura f : facturas) {
            if (f.getId() == id) {
                return f.getMonto();
            }
        }
        return 0;
    }

    public void actualizarTurnos() {
        Calendar currentDate = Calendar.getInstance();
        for (Map.Entry<Paciente, ArrayList<Turno>> entry : turnos.entrySet()) {
            ArrayList<Turno> turnosPaciente = entry.getValue();
            for (Turno t : turnosPaciente) {
                if (t.getFecha().before(currentDate.getTime())) {
                    for (Factura f : facturas) {
                        if (f.getId() == t.getId()) {
                            if (f.isPagado()) {
                                t.setEstado(Estado.REALIZADO);
                            } else {
                                t.setEstado(Estado.PENDIENTE);
                            }
                        }
                    }
                    this.notifyObservers("Turno actualizado para el paciente: " + t.getPaciente().getNombre());
                }
            }
        }
    }

    public void generarFactura(Date date, Paciente paciente, Turno turno) {
        Factura factura = new Factura(date, paciente, turno);

        if (factura.getMonto() == 0) {
            factura.setPagado(true);
        }

        facturas.add(factura);
        this.notifyObservers("Email enviado al paciente " + paciente.getCorreo());
    }

    public Map<Paciente, ArrayList<Turno>> getSistema() {
        return turnos;
    }

    public List<Turno> getHistorialMedico(Paciente paciente) {
        List<Turno> turnosCompletados = new ArrayList<>();
        for (Turno t : turnos.get(paciente)) {
            if (t.getEstado() == Estado.REALIZADO) {
                turnosCompletados.add(t);
            }
        }
        return turnosCompletados;
    }

    public List<Turno> getTurnos() {
        List<Turno> turnosProximos = new ArrayList<>();
        for (Map.Entry<Paciente, ArrayList<Turno>> entry : turnos.entrySet()) {
            ArrayList<Turno> turnosPaciente = entry.getValue();
            for (Turno t : turnosPaciente) {
                turnosProximos.add(t);
            }
        }
        return turnosProximos;
    }

    public List<Turno> getTurnosProximos() {
        List<Turno> turnosProximos = new ArrayList<>();
        for (Map.Entry<Paciente, ArrayList<Turno>> entry : turnos.entrySet()) {
            ArrayList<Turno> turnosPaciente = entry.getValue();
            for (Turno t : turnosPaciente) {
                if (t.getEstado() == Estado.PROGRAMADO) {
                    turnosProximos.add(t);
                }
            }
        }
        return turnosProximos;
    }

    public List<Paciente> getPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        for (Map.Entry<Paciente, ArrayList<Turno>> entry : turnos.entrySet()) {
            pacientes.add(entry.getKey());
        }
        return pacientes;
    }

    public List<Turno> filtrarTurnos(Filtro filtro, Object criterio) {
        List<Turno> filtrados = new ArrayList<Turno>();

        for (Map.Entry<Paciente, ArrayList<Turno>> entry : turnos.entrySet()) {
            ArrayList<Turno> turnosPaciente = entry.getValue();

            switch (filtro) {
                case MEDICO:
                    for (Turno t : turnosPaciente) {
                        if (t.getMedico().getApellido().equals(criterio)) {
                            filtrados.add(t);
                        }
                    }
                    break;

                case ESPECIALIDAD:
                    for (Turno t : turnosPaciente) {
                        if (t.getMedico().getEspecialidad().equals(criterio)) {
                            filtrados.add(t);
                        }
                    }
                    break;

                case FECHA:
                    for (Turno t : turnosPaciente) {
                        if (t.getFecha().toString().equals(criterio)) {
                            filtrados.add(t);
                        }
                    }
                    break;
            }
        }
        if (filtrados.isEmpty())
            this.notifyObservers("No se encontraron turnos con el criterio de búsqueda.");

        return filtrados;
    }

}
