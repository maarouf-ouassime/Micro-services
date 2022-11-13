package ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// POJO - Plain Old Java Object
@WebService(serviceName = "BanqueWS")
public class BanqueService {

    @WebMethod(operationName = "Convert")
    public double conversion(@WebParam(name = "montant") double montant) {
        return montant * 10.5;
    }

    @WebMethod
    public Compte getCompte(@WebParam(name = "code") int code) {
        return new Compte(code, Math.random() * 9000 + 1000, new Date());
    }

    @WebMethod
    public List<Compte> listComptes() {
       List<Compte> comptes = new ArrayList<Compte>();
         comptes.add(new Compte(1, Math.random() * 9000 + 1000, new Date()));
         comptes.add(new Compte(2, Math.random() * 9000 + 1000, new Date()));
         comptes.add(new Compte(3, Math.random() * 9000 + 1000, new Date()));
         return comptes;
    }
}
