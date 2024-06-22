
import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import paciente.PacienteFactory;
import paciente.Tipo;

public class PacienteTest {

    @Test
    public void testAgregarPaciente() {
        PacienteFactory pf = new PacienteFactory();
        AdministradorSistema.getInstance().registrarPaciente(
                pf.generatePaciente("Juan", "Perez", "Lima 123", "23367899", "OSDE", new Date(),
                        new String("luismartinez@gmail.com"), Tipo.REGULAR, 45001377));
        assertEquals("Juan", AdministradorSistema.getInstance().getPacientes().get(0).getNombre());
    }

    @Test
    public void testAgregarPacienteMock() {
        Logger loggerMock = mock(Logger.class);
        Admin admin = new Admin(loggerMock);
        PacienteFactory pf = new PacienteFactory();

        admin.registrarPaciente(
                pf.generatePaciente("Santiago", "Perez", "Lima 123", "23367899", "OSDE", new Date(),
                        new String("luismartinez@gmail.com"), Tipo.REGULAR, 45001377));

        verify(loggerMock).log("Nuevo paciente registrado: Santiago"); // remplazando "Santiago" por "Santiaga" devuelve
                                                                       // un error
    }

}
