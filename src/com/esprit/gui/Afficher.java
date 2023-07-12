
package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.services.ServiceOrdonnance;


public class Afficher extends Form{
     private Form previousForm;

    public Afficher(Form f) {
        super("Affichage", BoxLayout.y());
        previousForm = f;
        OnGui();
        addActions();
    }

    private void OnGui() {
        this.add(new SpanLabel(new ServiceOrdonnance().afficher().toString()));
    }

    private void addActions() {
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previousForm.showBack();
        });
    }
    
}
