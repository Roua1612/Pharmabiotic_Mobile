/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Livraison;
import com.esprit.services.ServiceLivraison;

/**
 *
 * @author gdallegi
 */
public class AjoutLivraisonForm extends Form {

    private TextField tfReference;
    private TextField tfIdCommande;
    private TextField tfIdLivreur;
    private Button btnAjout;

    private Form previousForm;

    public AjoutLivraisonForm(Form f) {
        super("Ajout Livraison", BoxLayout.y());
        previousForm = f;
        onGui();
        addActions();
    }

    private void onGui() {
        tfReference = new TextField(null, "Référence");
        tfIdCommande = new TextField(null, "ID Commande");
        tfIdLivreur = new TextField(null, "ID Livreur");
        btnAjout = new Button("Ajouter");
        this.addAll(tfReference, tfIdCommande, tfIdLivreur, btnAjout);
    }

    private void addActions() {
        btnAjout.addActionListener((evt) -> {
            if (tfReference.getText().isEmpty() || tfIdCommande.getText().isEmpty() || tfIdLivreur.getText().isEmpty()) {
                Dialog.show("Alerte", "Veillez remplir tous les champs", "OK", null);
            } else {
                ServiceLivraison serviceLivraison = new ServiceLivraison();
                Livraison livraison = new Livraison(tfReference.getText(), Integer.parseInt(tfIdCommande.getText()), Integer.parseInt(tfIdLivreur.getText()));

                if (serviceLivraison.ajouter(livraison)) {

    
                    Dialog.show("SUCCESS", "Livraison ajoutée !", "OK", null);
                } else {
                    Dialog.show("ERROR", "Erreur serveur", "OK", null);
                }

            }
        });

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
}
