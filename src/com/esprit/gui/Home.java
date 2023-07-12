
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;


public class Home extends Form{
     private Button btnAdd;
    private Button btnShow;
    private Button btnDelete;

    public Home() {
        super("Home", BoxLayout.y());
        OnGui();
        addActions();
    }
    
    private void OnGui() {
        btnAdd = new Button("Ajouter");
        btnShow = new Button("Afficher");
        this.addAll(new Label("Choisissez une option :"), btnAdd, btnShow);
    }
    
    private void addActions() {
        /*btnAdd.addActionListener((evt) -> {
            new Ajouter(this).show();
        });*/
        btnShow.addActionListener((evt) -> {
            new Afficher(this).show();
        });
    }
    
}
