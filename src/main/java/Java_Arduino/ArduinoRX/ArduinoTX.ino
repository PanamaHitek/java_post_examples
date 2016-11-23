/*
   Este código permite imprime un mensaje en el Puerto Serie a intérvalos de
   1 segundo entre un mensaje y otro. El mensaje consiste en la palabra "mensaje"
   seguida de un número que corresponde al valor del contador n.

   @author Antony Garcia Gonzalez, Panama Hitek
*/

int n = 0; //contador
void setup() {
  //Iniciamos la comunicación con el puerto serie
  Serial.begin(9600);
}

void loop() {
  //Se crea la variable que contendrá el mensaje a imprimir
  String output = "Mensaje ";
  //Se concatena el valor del contador al mensaje
  output += n;
  //Se imprime el mensaje en el Puerto Serie
  Serial.println(output);
  n++; //Se aumenta el valor del contador
  delay(1000); //Retraso de 1 segundo
}

