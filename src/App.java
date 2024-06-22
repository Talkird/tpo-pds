
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import interfaces.Notificador;
import medico.Medico;
import medico.MedicoFactory;
import paciente.Paciente;
import paciente.PacienteFactory;
import paciente.Tipo;
import turno.Estado;
import turno.TurnoFactory;

public class App {
        public static void main(String[] args) throws Exception {
                AdministradorSistema.getInstance().registerObserver(new Notificador());
                PacienteFactory pf = new PacienteFactory();
                TurnoFactory tf = new TurnoFactory();
                MedicoFactory mf = new MedicoFactory();

                Paciente paciente1 = pf.generatePaciente("Juan", "Perez", "Calle A", "123456789", "OSDE",
                                new java.sql.Date(new GregorianCalendar(1980, Calendar.JANUARY, 1).getTimeInMillis()),
                                new String("juanperez@gmail.com"), Tipo.REGULAR, 12345678);

                Paciente paciente2 = pf.generatePaciente("Maria", "Gomez", "Calle B", "987654321", "Swiss Medical",
                                new java.sql.Date(new GregorianCalendar(1990, Calendar.FEBRUARY, 2).getTimeInMillis()),
                                new String("mariagomez@gmail.com"), Tipo.SEGURO, 87654321);

                Paciente paciente3 = pf.generatePaciente("Pedro", "Lopez", "Calle C", "456789123", "Galeno",
                                new java.sql.Date(new GregorianCalendar(1975, Calendar.MARCH, 3).getTimeInMillis()),
                                new String("pedrolopez@gmail.com"), Tipo.JUBILADO, 56789123);

                Paciente paciente4 = pf.generatePaciente("Ana", "Diaz", "Calle D", "789123456", "Medife",
                                new java.sql.Date(new GregorianCalendar(1985, Calendar.APRIL, 4).getTimeInMillis()),
                                new String("anadiaz@gmail.com"), Tipo.REGULAR, 78912345);

                Paciente paciente5 = pf.generatePaciente("Luis", "Martinez", "Calle E", "321654987", "PAMI",
                                new java.sql.Date(new GregorianCalendar(1995, Calendar.MAY, 5).getTimeInMillis()),
                                new String("luismartinez@gmail.com"), Tipo.SEGURO, 65498732);

                Medico medico1 = mf.generateMedico("Raul", "Sanchez", "Urologo", 123456789);
                Medico medico2 = mf.generateMedico("Jack", "Losauro", "Clinico", 182313);

                AdministradorSistema.getInstance().registrarPaciente(paciente1);
                AdministradorSistema.getInstance().registrarPaciente(paciente2);
                AdministradorSistema.getInstance().registrarPaciente(paciente3);
                AdministradorSistema.getInstance().registrarPaciente(paciente4);
                AdministradorSistema.getInstance().registrarPaciente(paciente5);

                // Registrar turnos para cada paciente
                AdministradorSistema.getInstance().registrarTurno(paciente1,
                                tf.generateTratamiento(medico1, paciente1, new Date(2024, 6, 21, 16, 33),
                                                Estado.PROGRAMADO,
                                                "Infección urinal", 1));

                // Registrar turnos para cada paciente
                AdministradorSistema.getInstance().registrarTurno(paciente2,
                                tf.generateTratamiento(medico2, paciente2, new Date(2024, 6, 21, 12, 33),
                                                Estado.PROGRAMADO,
                                                "Chequeo", 2));

                // int idFactura = 1;
                // SistemaPagos.pagar(AdministradorSistema.getInstance().getMontoFactura(idFactura),
                // idFactura);

        }
}
