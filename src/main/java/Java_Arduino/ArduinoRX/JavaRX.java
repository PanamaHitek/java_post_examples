package Java_Arduino.ArduinoRX;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * Ejemplo sobre recepción de datos desde Java hasta Arduino
 *
 * @author Antony García González
 */
public class JavaRX {

    //Se crea una instancia de la librería PanamaHitek_Arduino
    private static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();

    /*
     * Se crea una variable SerialPortEventListener Al crear esta variable es
     * necesario implementar el método Abstracto serialEvent Cada vez que se
     * reciba un dato a través del puerto serie, se ejecutará el serialEvent
     */
    private static final SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {

                /**
                 * En esta instrucción se evalúa constantemente el puerto serie
                 * hasta encontrar un mensaje disponible para ser impreso
                 *
                 * Se considera que un mensaje ha sido recibido en su totalidad
                 * cuando se reciben los bytes 13 y 10, los cuales son enviados
                 * por Arduino cuando se utiliza Serial.println().
                 *
                 */
                if (ino.isMessageAvailable()) {
                    //Se imprime el mensaje recibido en la consola
                    System.out.println(ino.printMessage());
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(JavaRX.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    };

    public static void main(String[] args) {
        try {
            /*
             * Se inicia la comunicación con el Puerto Serie utilizando la función RX,
             * la cual es exclusiva para recibir datos desde el Puerto Serie
             */
            ino.arduinoRX("COM4", 9600, listener);
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(JavaRX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
