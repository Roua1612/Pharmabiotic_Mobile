package com.esprit.services;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.esprit.entities.Medecin;
import com.esprit.services.IService;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceMedecin  {

    private final String URI = Statics.BASE_URL + "/medecin/";
     private boolean responseResult;

   


  
public List<Medecin> afficher() {
    List<Medecin> list = new ArrayList<>();
    ConnectionRequest request = new ConnectionRequest();
    
    request.setUrl(URI);
    request.setHttpMethod("GET");

    request.addResponseListener((evt) -> {
        try {
            InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
            Map<String, Object> result = new JSONParser().parseJSON(jsonText);
            List<Map<String, Object>> medecins = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> medecin : medecins) {
                int id = (int) Float.parseFloat(medecin.get("id").toString());
                String nom = medecin.get("nom").toString();
                String prenom = medecin.get("prenom").toString();
                String email = medecin.get("email").toString();
                list.add(new Medecin(id, nom, prenom, email));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(request);

    return list;
}


   public List<String> getMedecins() {
    List<String> list = new ArrayList<>();
    ConnectionRequest request = new ConnectionRequest();
    
    request.setUrl(URI);
    request.setHttpMethod("GET");

    request.addResponseListener((evt) -> {
        try {
            InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
            Map<String, Object> result = new JSONParser().parseJSON(jsonText);
            List<Map<String, Object>> medecins = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> medecin : medecins) {
                String nom = medecin.get("nom").toString();
                String prenom = medecin.get("prenom").toString();
                String med = nom + " " + prenom;
                list.add(med);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(request);

    return list;
}


}