/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.esprit.entities.Livraison;
import com.esprit.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.*;
import javax.mail.internet.*;
import javax.util.*;
/**
 *
 * @author gdallegi
 */
public class ServiceLivraison implements IService<Livraison> {

    private boolean responseResult;
    private List<Livraison> livraisons;

    private final String URI = Statics.BASE_URL + "/livraison/";

    public ServiceLivraison() {
        livraisons = new ArrayList<>();
    }

    public boolean ajouter(Livraison livraison) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI);
        request.setHttpMethod("POST");

        request.addArgument("reference", livraison.getReference());
        request.addArgument("id_commande", String.valueOf(livraison.getIdCommande()));
        request.addArgument("id_livreur", String.valueOf(livraison.getIdLivreur()));

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 201; // Code HTTP 201 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean modifier(Livraison livraison) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI + livraison.getId());
        request.setHttpMethod("PUT");

        request.addArgument("reference", livraison.getReference());
        request.addArgument("id_commande", String.valueOf(livraison.getIdCommande()));
        request.addArgument("id_livreur", String.valueOf(livraison.getIdLivreur()));

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public boolean supprimer(Livraison livraison) {
        ConnectionRequest request = new ConnectionRequest();

        request.setUrl(URI + livraison.getId());
        request.setHttpMethod("DELETE");

        request.addResponseListener((evt) -> {
            responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public List<Livraison> afficher() {
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
                    String reference = obj.get("reference").toString();
                    int idCommande = (int) Float.parseFloat(obj.get("id_commande").toString());
                    int idLivreur = (int) Float.parseFloat(obj.get("id_livreur").toString());
                    livraisons.add(new Livraison(id, reference, idCommande, idLivreur));
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return livraisons;
    }
    public List<Livraison> rechercher(String recherche) {
    List<Livraison> livraisonsTrouvees = new ArrayList<>();

    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(URI + "search/" + recherche);
    request.setHttpMethod("GET");

    request.addResponseListener((evt) -> {
        try {
            InputStreamReader jsonText = new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8");
            Map<String, Object> result = new JSONParser().parseJSON(jsonText);
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("root");

            for (Map<String, Object> obj : list) {
                int id = (int) Float.parseFloat(obj.get("id").toString());
                String reference = obj.get("reference").toString();
                int idCommande = (int) Float.parseFloat(obj.get("id_commande").toString());
                int idLivreur = (int) Float.parseFloat(obj.get("id_livreur").toString());
                livraisonsTrouvees.add(new Livraison(id, reference, idCommande, idLivreur));
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(request);

    return livraisonsTrouvees;
}
    


}
