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
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.Ordonnance;
import com.esprit.services.ServiceOrdonnance;

/**
 *
 * @author pc
 */
public class AjouterForm extends Form{
     private TextField tfref;
    private TextField tfidmedecin;
    private TextField tfmedicament;
    private Picker date_ordonnance;
    private TextField statut;
    private Button btnAjout;

    private Form previousForm;

    public AjouterForm(Form f) {
        super("Ajout", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

    private void OnGui() {
        tfref = new TextField(null, "reff");
        tfidmedecin = new TextField(null, "id medecin ");
        tfmedicament = new TextField(null, "id medicament");
        date_ordonnance = new Picker();
        statut = new TextField(null, "statut");
        btnAjout = new Button("Ajouter");
        this.addAll(tfref, tfidmedecin, tfmedicament, date_ordonnance, statut, btnAjout);
    }

    private void addActions() {
        btnAjout.addActionListener((evt) -> {
            if (tfref.getText().isEmpty() || tfidmedecin.getText().isEmpty()) {
                Dialog.show("Alerte", "Veillez remplir tous les champs", "OK", null);
            } else {
                ServiceOrdonnance sp = new ServiceOrdonnance();
                if (sp.ajouter(new Ordonnance(Integer.parseInt(tfref.getText()), Integer.parseInt(tfidmedecin.getText()), Integer.parseInt(tfmedicament.getText()), date_ordonnance.getDate(), statut.getText()))) {
                    Dialog.show("SUCCESS", "ordonnace ajoutée !", "OK", null);
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
