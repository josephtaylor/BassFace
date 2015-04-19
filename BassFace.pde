import hypermedia.net.*;

UDP udp;

void setup() {
  size(100,100);
  
  udp = new UDP(this, 5202);
  udp.listen(true);
}

void receive(byte[] data, String ip, int port) {
  String message = new String(data);
  println((int) data[0]);
}

void draw() {
}

