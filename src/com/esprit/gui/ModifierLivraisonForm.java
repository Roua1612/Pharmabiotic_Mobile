package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Livraison;
import com.esprit.services.ServiceLivraison;

/**
 * Classe pour la modification d'une livraison.
 */
public class ModifierLivraisonForm extends Form {
private AfficherLivraisonForm afficherLivraisonForm;

    private Livraison livraison;
    private TextField tfReference;
    private TextField tfIdCommande;
    private TextField tfIdLivreur;
    private Button btnModifier;

    private Form previousForm;

    public ModifierLivraisonForm(Form f, Livraison livraison,AfficherLivraisonForm afficherLivraisonForm) {
        super("Modification", BoxLayout.y());
        previousForm = f;
        this.afficherLivraisonForm = afficherLivraisonForm;
        this.livraison = livraison;
        onGui();
        addActions();
    }

  

    private void onGui() {
        tfReference = new TextField(livraison.getReference(), "Référence");
        tfIdCommande = new TextField(String.valueOf(livraison.getIdCommande()), "ID Commande");
        tfIdLivreur = new TextField(String.valueOf(livraison.getIdLivreur()), "ID Livreur");
        btnModifier = new Button("Modifier");
        this.addAll(tfReference, tfIdCommande, tfIdLivreur, btnModifier);
    }

    private void addActions() {
        btnModifier.addActionListener((evt) -> {
            if (tfReference.getText().isEmpty() || tfIdCommande.getText().isEmpty() || tfIdLivreur.getText().isEmpty()) {
                Dialog.show("Alerte", "Veillez remplir tous les champs", "OK", null);
            } else {
                ServiceLivraison serviceLivraison = new ServiceLivraison();
                livraison.setReference(tfReference.getText());
                livraison.setIdCommande(Integer.parseInt(tfIdCommande.getText()));
                livraison.setIdLivreur(Integer.parseInt(tfIdLivreur.getText()));

                if (serviceLivraison.modifier(livraison)) {
                    Dialog.show("SUCCESS", "Livraison modifiée !", "OK", null);
                    afficherLivraisonForm.refresh(); 
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
