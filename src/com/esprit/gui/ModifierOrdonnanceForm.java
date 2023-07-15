package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Ordonnance;
import com.esprit.services.ServiceOrdonnance;

/**
 * Classe pour la modification d'une ordonnance.
 */
public class ModifierOrdonnanceForm extends Form {
    private Afficher afficherOrdonnanceForm;

    private Ordonnance ordonnance;
    private TextField tfReference;
    private TextField tfIdMedecin;
    private TextField tfIdMedicament;
    private Button btnModifier;

    private Form previousForm;

    public ModifierOrdonnanceForm(Form f, Ordonnance ordonnance, Afficher afficherOrdonnanceForm) {
        super("Modification", BoxLayout.y());
        previousForm = f;
        this.afficherOrdonnanceForm = afficherOrdonnanceForm;
        this.ordonnance = ordonnance;
        onGui();
        addActions();
    }

    private void onGui() {
        tfReference = new TextField(String.valueOf(ordonnance.getReference()), "Référence");
        tfIdMedecin = new TextField(String.valueOf(ordonnance.getId_Medecin()), "ID Médecin");
        tfIdMedicament = new TextField(String.valueOf(ordonnance.getId_Medicament()), "ID Médicament");
        btnModifier = new Button("Modifier");
        this.addAll(tfReference, tfIdMedecin, tfIdMedicament, btnModifier);
    }

    private void addActions() {
        btnModifier.addActionListener((evt) -> {
            if (tfReference.getText().isEmpty() || tfIdMedecin.getText().isEmpty() || tfIdMedicament.getText().isEmpty()) {
                Dialog.show("Alerte", "Veuillez remplir tous les champs", "OK", null);
            } else {
                ServiceOrdonnance serviceOrdonnance = new ServiceOrdonnance();
                ordonnance.setReference(Integer.parseInt(tfReference.getText()));
                ordonnance.setNom_Medecin(Integer.parseInt(tfIdMedecin.getText()));
                ordonnance.setNom_Medicament(Integer.parseInt(tfIdMedicament.getText()));

                if (serviceOrdonnance.modifier(ordonnance)) {
                    Dialog.show("SUCCÈS", "Ordonnance modifiée !", "OK", null);
                    afficherOrdonnanceForm.refresh();
                } else {
                    Dialog.show("ERREUR", "Erreur serveur", "OK", null);
                }
            }
        });

        this.getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            previousForm.showBack();
        });
    }
}
