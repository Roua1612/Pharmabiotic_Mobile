package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Livraison;
import com.esprit.services.ServiceLivraison;
import java.util.List;

/**
 * Affiche la liste des livraisons avec un bouton de suppression pour chaque livraison.
 */
public class AfficherLivraisonForm extends Form {
private TextField tfRecherche;
private Button btnRechercher;
    private Form previousForm;

    public AfficherLivraisonForm(Form f) {
        super("Livraisons", BoxLayout.y());
        previousForm = f;
        onGui();
        addActions();
    }

    private void onGui() {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        List<Livraison> livraisons = serviceLivraison.afficher();
        tfRecherche = new TextField("", "Recherche");
        btnRechercher = new Button("Rechercher");
        addAll(tfRecherche, btnRechercher, BoxLayout.encloseY(new Label(" ")));
        for (Livraison livraison : livraisons) {
            String label = "Référence: " + livraison.getReference() + "\n"
                    + "ID Commande: " + livraison.getIdCommande() + "\n"
                    + "ID Livreur: " + livraison.getIdLivreur() + "\n";

            SpanLabel spanLabel = new SpanLabel(label);
Button editButton = new Button("Modifier");
            editButton.addActionListener(evt -> {
                // Code pour la modification de la livraison
                 new ModifierLivraisonForm(this,livraison,this).show();
            });
            Button deleteButton = new Button("Supprimer");
            deleteButton.addActionListener(evt -> {
                // Code pour la suppression de la livraison
                boolean success = serviceLivraison.supprimer(livraison);
                if (success) {
                    removeComponent(spanLabel);
                    removeComponent(deleteButton);
                    removeComponent(editButton);
                    revalidate();
                }
            });
            
            
            add(spanLabel);
            add(deleteButton);
            add(editButton);
        }
    }
    public void refresh() {
    removeAll(); // Vider le contenu actuel
    onGui(); // Recharger la liste des livraisons
    revalidate(); // Rafraîchir l'affichage
    new AfficherLivraisonForm(this).show();
}
    
private void rechercherLivraison(String recherche) {
    removeAll(); // Supprime tous les éléments existants

    ServiceLivraison serviceLivraison = new ServiceLivraison();
    List<Livraison> livraisons = serviceLivraison.rechercher(recherche);

    for (Livraison livraison : livraisons) {
        String label = "Référence: " + livraison.getReference() + "\n"
                + "ID Commande: " + livraison.getIdCommande() + "\n"
                + "ID Livreur: " + livraison.getIdLivreur() + "\n";

        SpanLabel spanLabel = new SpanLabel(label);
        Button editButton = new Button("Modifier");
            editButton.addActionListener(evt -> {
                // Code pour la modification de la livraison
                 new ModifierLivraisonForm(this,livraison,this).show();
            });
        Button deleteButton = new Button("Supprimer");
        deleteButton.addActionListener(evt -> {
            // Code pour la suppression de la livraison
            boolean success = serviceLivraison.supprimer(livraison);
            if (success) {
                removeComponent(spanLabel);
                removeComponent(deleteButton);
                revalidate();
            }
        });

        add(spanLabel);
        add(deleteButton);
        add(editButton);

    }

    revalidate(); // Rafraîchir l'affichage
}

    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
        btnRechercher.addActionListener(evt -> {
        String recherche = tfRecherche.getText();
        rechercherLivraison(recherche);
    });
    }
}
