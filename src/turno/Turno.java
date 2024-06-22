package turno;

import java.util.Date;

import medico.Medico;
import paciente.Paciente;

public abstract class Turno {
    private Medico medico;
    private Paciente paciente;
    private Date fecha;
    private Estado estado;
    private String motivo;
    private double costo;
    private int id;

    public Turno(Medico medico, Paciente paciente, Date fecha, Estado estado, String motivo, int id) {
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.estado = estado;
        this.motivo = motivo;
        this.id = id;

        // TODO calcular costo
        switch (medico.getEspecialidad()) {
            case "Clinico":
                this.costo = 1000;
                break;
            case "Pediatra":
                this.costo = 1500;
                break;
            case "Cardiologo":
                this.costo = 2000;
                break;
            case "Dermatologo":
                this.costo = 2500;
                break;
            case "Ginecologo":
                this.costo = 3000;
                break;
            case "Oftalmologo":
                this.costo = 3500;
                break;
            case "Otorrinolaringologo":
                this.costo = 4000;
                break;
            case "Traumatologo":
                this.costo = 4500;
                break;
            case "Urologo":
                this.costo = 5000;
                break;
            default:
                this.costo = 0;
                break;
        }
    }

    public double getCosto() {
        return costo;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getMotivo() {
        return motivo;
    }

    // TODO notificar cambios de estado
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void finalizar() {
        this.setEstado(Estado.REALIZADO);
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

}
