import ws.BanqueService;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {

        Endpoint.publish("http://0.0.0.0:8080/BanqueWS", new BanqueService());
        System.out.println("Web Service deployed");
    }
}
