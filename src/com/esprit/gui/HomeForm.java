package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Livraison;
import com.esprit.gui.AfficherLivraisonForm;
import com.esprit.services.ServiceLivraison;
import java.util.List;


public class HomeForm extends Form {
    
    private Button btnAddPerson;
    private Button btnShowPerson;

    public HomeForm() {
        super("Home", BoxLayout.y());
        OnGui();
        addActions();
    }
    
    private void OnGui() {
        btnAddPerson = new Button("Ajouter");
        btnShowPerson = new Button("Afficher");
        this.addAll(new Label("Choisissez une option :"), btnAddPerson, btnShowPerson);
    }
    
    private void addActions() {
        btnAddPerson.addActionListener((evt) -> {
            new AjoutLivraisonForm(this).show();
        });
        btnShowPerson.addActionListener((evt) -> {
            new AfficherLivraisonForm(this).show();
        });
    }
    
}