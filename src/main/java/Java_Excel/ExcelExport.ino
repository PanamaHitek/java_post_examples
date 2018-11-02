int t = 0;
int h = 0;
void setup() {
  Serial.begin(9600);
}

void loop() {
  t = random(20,40);
  h = t + 50;
  Serial.println(t);
  Serial.println(h);
  delay(3000);
}
