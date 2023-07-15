
package com.esprit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.esprit.entities.Ordonnance;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class ServiceOrdonnance /*implements IService<Ordonnance>*/ {

    private boolean responseResult;
    private List<Ordonnance> ordonnances;
    private final String URI = Statics.BASE_URL + "/ordonnance/";

    public ServiceOrdonnance() {
        ordonnances = new ArrayList<>();
    }

    public boolean ajouter(Ordonnance ordonnance) {
    System.out.println("1");
    ConnectionRequest request = new ConnectionRequest();
    
    request.setUrl(URI);
    request.setHttpMethod("POST");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String newDate = sdf.format(ordonnance.getDate());
    
    request.addArgument("reference", String.valueOf(ordonnance.getReference()));
    request.addArgument("id_medecin", String.valueOf(ordonnance.getId_Medecin()));
    request.addArgument("id_medicament", String.valueOf(ordonnance.getId_Medicament()));
    request.addArgument("date_ordonnance", newDate.toString());
    request.addArgument("statut", ordonnance.getStatut());
    
    request.addResponseListener((evt) -> {
        System.out.println("2");
        responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
    });
    NetworkManager.getInstance().addToQueueAndWait(request);
    System.out.println("3");
   
    return responseResult;
}


    public boolean modifier(Ordonnance ordonnance) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI +ordonnance.getId() );
        request.setHttpMethod("PUT");

        request.addArgument("reference", String.valueOf(ordonnance.getReference()));
        request.addArgument("id_Medecin", String.valueOf(ordonnance.getId_Medecin()));
        request.addArgument("id_Medicament", String.valueOf(ordonnance.getId_Medicament()));
        request.addArgument("date_ordonnance", String.valueOf(ordonnance.getDate()));
        request.addArgument("statut", ordonnance.getStatut());

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    
    public boolean supprimer(Ordonnance ordonnance) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI + ordonnance.getId());
        request.setHttpMethod("DELETE");

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    
    public List<Ordonnance> afficher() {
//        List<Ordonnance> ordonnances = new ArrayList<>();

        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(URI);
        request.setHttpMethod("GET");

        request.addResponseListener((evt) -> {
            try {
                InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
                Map<String, Object> result = new JSONParser().parseJSON(jsonText);
                List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");
 
                for (Map<String, Object> obj : list) {
                    int id = (int) Float.parseFloat(obj.get("id").toString());  
                    int reference = (int) Float.parseFloat(obj.get("reference").toString());
                    int id_Medecin = (int) Float.parseFloat(obj.get("id_medecin").toString());
                    int id_Medicament = (int) Float.parseFloat(obj.get("id_medicament").toString());
                    
                    String dateString = obj.get("date_ordonnance").toString();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
//
                    try {
                        date = format.parse(dateString);
                    } catch (ParseException e) {
                        
                        System.out.println("dzaddkjdjbjdhsjdsbj");
                    }
                    
                     String statut = obj.get("statut").toString();
                    Ordonnance ordonnance = new Ordonnance(reference, id_Medecin, id_Medicament, date, statut);
                    ordonnances.add(ordonnance);
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(request);
        System.out.println(ordonnances.toString());
        return ordonnances;
    }
    public List<Ordonnance> rechercher(String recherche) {
    List<Ordonnance> livraisonsTrouvees = new ArrayList<>();

    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(URI + "search/" + recherche);
    request.setHttpMethod("GET");

    request.addResponseListener((evt) -> {
        try {
            InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
            Map<String, Object> result = new JSONParser().parseJSON(jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : list) {
                
                    int reference = (int) Float.parseFloat(obj.get("reference").toString());
                    int id_Medecin = (int) Float.parseFloat(obj.get("id_medecin").toString());
                    int id_Medicament = (int) Float.parseFloat(obj.get("id_medicament").toString());
                    String statut = obj.get("statut").toString();
                    String dateString = obj.get("date_ordonnance").toString();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                livraisonsTrouvees.add(new Ordonnance(reference, id_Medecin, id_Medicament, date, statut));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(request);

    return livraisonsTrouvees;
}

}
