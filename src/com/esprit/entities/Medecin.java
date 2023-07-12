
package com.esprit.entities;

import javafx.scene.control.cell.PropertyValueFactory;


public class Medecin {

    public static void setCellValueFactory(PropertyValueFactory<Object, Object> propertyValueFactory) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    private int id;
    private String nom;
    private String prenom,email;
     
    public Medecin(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Medecin() {
    }

    public Medecin(int id, String nom, String prenom ,String email ) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        
        this.email=email;
    }

    public int getId() {
        return id;
    }

    public Medecin(String nom, String prenom,String email) {
        this.nom = nom;
        this.prenom = prenom;
       
        this.email=email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
      

    
    
    

    @Override
    public String toString() {
        return "Médecin{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom +",email"+email+'}';
    }
}
