package interfaces;

public class Notificador implements Observer {

    @Override
    public void update(String message) {
        System.out.println("Notificacion recibida: " + message);
    }

}
