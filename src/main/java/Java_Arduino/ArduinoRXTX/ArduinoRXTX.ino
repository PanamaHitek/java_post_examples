/*
   Este código permite controlar el LED asociado al pin 13 del Arduino. A través
   del Monitor Serie, enviando un "1" encendemos un LED y con cualquier otro caracter
   apagamos el LED. Cuando se produce una acción, ya sea de encendido o apagado,
   Arduino devuelve un mensaje ("Encendido" o "Apagado"), seguido del tiempo en
   milisegundos en el cual se produjo la acción desde el inicio de la ejecución
   del algoritmo en Arduino(función millis())

   @author Antony Garcia Gonzalez, Panama Hitek
*/

void setup() {
  // Declaramos que utilizaremos el pin 13 como salida
  pinMode(13, OUTPUT);
  //Iniciamos la comunicación con el puerto serie
  Serial.begin(9600);
}

void loop() {
  //En caso que haya información en el Serial Port, se entra en esta estructura
  if (Serial.available() > 0) {
    //Se lee la información entrante en el Serial Port
    int input = Serial.read();
    if (input == '1') {
      //Si el valor de input es 1, se enciende el led
      digitalWrite(13, HIGH);
      /**
         Se envía el mensaje "Encendido", seguido de un símbolo de @
         y el tiempo de ejecución en milisegundos, obtenido a través de millis()
      */
      String output = "Encendido @";
      output += millis();
      output += "ms";
      //Se imprime el mensaje en el puerto serie
      Serial.println(output);
    }
    else
    {
      //Si el valor de input es diferente de 1, se apaga el LED
      digitalWrite(13, LOW);
      /**
         Se envía el mensaje "Apagado", seguido de un símbolo de @
         y el tiempo de ejecución en milisegundos, obtenido a través de millis()
      */
      String output = "Apagado @";
      output += millis();
      output += "ms";
      //Se imprime el mensaje en el puerto serie
      Serial.println(output);
    }
  }
}


