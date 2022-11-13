import proxy.BanqueService;
import proxy.BanqueWS;
import proxy.Compte;

public class Main {
    public static void main(String[] args) {

        BanqueService stub = new BanqueWS().getBanqueServicePort();
        System.out.println(stub.convert(100));
        Compte c = stub.getCompte(1);
        System.out.println(c.getCode() + "\n" + c.getSolde());

    }
}
