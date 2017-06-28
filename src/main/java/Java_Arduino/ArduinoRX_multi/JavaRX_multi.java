package Java_Arduino.ArduinoRX_multi;

import Java_Arduino.ArduinoRX.*;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.PanamaHitek_MultiMessage;
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
public class JavaRX_multi {

    //Se crea una instancia de la librería PanamaHitek_Arduino
    private static PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    private static PanamaHitek_MultiMessage multi = new PanamaHitek_MultiMessage(3, ino);
    private static final SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {

                /*
                Aquí es donde se reciben los datos
                multi.dataReceptionCompleted() devuelve TRUE cuando se ha recibido
                los datos de todos los sensores
                 */
                if (multi.dataReceptionCompleted()) {
                    /*
                     * Con multi.getMessage() se leen los datos de los sensores
                     * Los numeros 0, 1 y 2 son los índices de los sensores, los
                     * cuales han sido impresos en ese mismo orden en el Arduino
                     * utilizando Serial.println(). Si no se usa
                     * Serial.println(), la clase MultiMessage no funcionará.
                     *
                     * A pesar de que en la instancia de la clase se colocaron 3
                     * sensores, los índices son 0, 1 y 2 porque se cuenta desde
                     * el cero.
                     */
                    System.out.println("Valor de a --> " + multi.getMessage(0));
                    System.out.println("Valor de b --> " + multi.getMessage(1));
                    System.out.println("Valor de c --> " + multi.getMessage(2));
                    System.out.println("-----------------------------------");
                    multi.flushBuffer();
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(JavaRX_multi.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    };

    public static void main(String[] args) {
        try {
            ino.arduinoRX("COM8", 9600, listener);
        } catch (ArduinoException | SerialPortException ex) {
            Logger.getLogger(JavaRX_multi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
