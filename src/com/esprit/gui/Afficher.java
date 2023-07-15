
package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.esprit.entities.Ordonnance;
import com.esprit.services.ServiceOrdonnance;
import java.util.List;


public class Afficher extends Form{
     private Form previousForm;
     private TextField tfRecherche;
     private Button btnRechercher;
    public Afficher(Form f) {
        super("Ordonnances", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }
    private void rechercherOrdonnace(String recherche) {
    removeAll(); // Supprime tous les éléments existants

    ServiceOrdonnance serviceLivraison = new ServiceOrdonnance();
    List<Ordonnance> Ordonnaces = serviceLivraison.rechercher(recherche);

    for (Ordonnance Ordonnace : Ordonnaces) {
        String label = "Référence: " + Ordonnace.getReference() + "\n"
                + "ID medecin: " + Ordonnace.getId_Medecin()+ "\n"
                + "ID medicament: " + Ordonnace.getId_Medicament() + "\n";
               
        SpanLabel spanLabel = new SpanLabel(label);
        

        add(spanLabel);


    }

    revalidate(); // Rafraîchir l'affichage
}

    private void OnGui() {
        tfRecherche = new TextField("", "Recherche");
        btnRechercher = new Button("Rechercher");
        addAll(tfRecherche, btnRechercher, BoxLayout.encloseY(new Label(" ")));
       ServiceOrdonnance serviceLivraison = new ServiceOrdonnance();
        List<Ordonnance> ordonnances = serviceLivraison.afficher();
        for (Ordonnance ordonnance : ordonnances) {
      String label = "Référence: " + ordonnance.getReference() + "\n"
                + "ID medecin: " + ordonnance.getId_Medecin()+ "\n"
                + "ID medicament: " + ordonnance.getId_Medicament() + "\n";
               
            SpanLabel spanLabel = new SpanLabel(label);
Button editButton = new Button("Modifier");
            editButton.addActionListener(evt -> {
                // Code pour la modification de la livraison
                 new ModifierOrdonnanceForm(this,ordonnance,this).show();
            });
            Button deleteButton = new Button("Supprimer");
deleteButton.addActionListener(evt -> {
    // Code pour la suppression de la livraison
    
    // Create a confirmation dialog
    Dialog dialog = new Dialog("Confirmation");
    dialog.setLayout(new BorderLayout());

    // Add a message to the dialog
    dialog.add(BorderLayout.CENTER, new Label("Are you sure you want to delete?"));

    // Add buttons for confirmation
    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");

    // When Yes button is pressed, perform the deletion
    yesButton.addActionListener(e -> {
        boolean success = serviceLivraison.supprimer(ordonnance);
        if (success) {
            removeComponent(spanLabel);
            removeComponent(deleteButton);
            removeComponent(editButton);
            revalidate();
        }

        dialog.dispose(); // Close the dialog after deletion
    });

    // When No button is pressed, simply close the dialog
    noButton.addActionListener(e -> dialog.dispose());

    // Add buttons to the dialog
    dialog.add(BorderLayout.SOUTH, FlowLayout.encloseRight(yesButton, noButton));

    // Show the confirmation dialog
    dialog.showPacked(BorderLayout.CENTER, true);
});

            
            
            add(spanLabel);
            add(deleteButton);
            add(editButton);
        }
    }
    public void refresh() {
    removeAll(); // Vider le contenu actuel
    OnGui(); // Recharger la liste des livraisons
    revalidate(); // Rafraîchir l'affichage
    new Afficher(this).show();
}
    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
         btnRechercher.addActionListener(evt -> {
        String recherche = tfRecherche.getText();
        rechercherOrdonnace(recherche);
    });
    }
    
}
