package factura;

import java.util.Date;

import paciente.Paciente;
import turno.Turno;

public class Factura {
    private Date fecha;
    private String nombre, apellido, direccion, telefono, desc;
    private double monto;
    private String correo;
    private int dni, id;
    private boolean pagado;

    public Factura(Date fecha, Paciente p, Turno t) {
        this.pagado = false;
        this.fecha = fecha;
        this.nombre = p.getNombre();
        this.apellido = p.getNombre();
        this.direccion = p.getDireccion();
        this.telefono = p.getTelefono();
        this.desc = t.getMotivo();

        switch (p.getTipo()) {
            case SEGURO:
                this.monto = 0;
                break;

            case REGULAR:
                this.monto = t.getCosto();
                break;

            case JUBILADO:
                this.monto = t.getCosto() * 0.5;
                break;
        }

        this.correo = p.getCorreo();
        this.dni = p.getDni();
        this.id = t.getId();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public StringgetCorreo() {
        return correo;
    }

    public void setCorreo(Stringcorreo) {
        this.correo = correo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public int getId() {
        return id;
    }

}
