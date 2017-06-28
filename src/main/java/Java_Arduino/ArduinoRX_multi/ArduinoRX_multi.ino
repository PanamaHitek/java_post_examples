/*
   Este código permite imprime un mensaje en el Puerto Serie a intérvalos de
   1 segundo entre un mensaje y otro. El mensaje consiste en los valores de las variables
   a, b y c, los cuales aumentan cada vez que se imprimen
   
   La variable a aumenta en 1
   La variable b aumenta en 10
   La variable c aumenta en 100

   Estos datos son impresos y serán recibidos por una aplicación en Java que será
   la encargada de procesarlos. 

   @author Antony Garcia Gonzalez, Panama Hitek
*/
int a = 0;
int b = 100;
int c = 1000;
void setup() {
  Serial.begin(9600);
}

void loop() {
  //Los datos son impresos en el orden que se desea recibirlos en Java
  Serial.println(a);
  Serial.println(b);
  Serial.println(c);
  a = a + 1;
  b = b + 10;
  c = c + 100;
  delay(1000);
}
