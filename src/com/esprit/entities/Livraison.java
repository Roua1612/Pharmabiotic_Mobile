/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

import java.util.Objects;

/**
 *
 * @author gdallegi
 */
public class Livraison {
    private int id;
    private String reference;
    private int id_commande;
    private int id_livreur;
    private String nom;
     // Constructeur
    public Livraison(int id, String reference, int id_commande, int id_livreur) {
        this.id = id;
        this.reference = reference;
        this.id_commande = id_commande;
        this.id_livreur = id_livreur;
    }
        public Livraison(int id, String reference, int id_commande, String nom) {
        this.id = id;
        this.reference = reference;
        this.id_commande = id_commande;
        this.nom = nom;
    }
        public Livraison( String reference, int id_commande, int id_livreur) {
       
        this.reference = reference;
        this.id_commande = id_commande;
        this.id_livreur = id_livreur;
    }

    public Livraison() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    // Méthodes d'accès (getters) et de modification (setters)
    public int getId() {
        return id;
    }
   
    public void setId(int id) {
        this.id = id;
    }
        public String getNom() {
        return nom;
    }
   
    public void setNom(String nom) {
        this.nom = nom;
    }
   
   
    public String getReference() {
        return reference;
    }
   
    public void setReference(String reference) {
        this.reference = reference;
    }
   
    public int getIdCommande() {
        return id_commande;
    }
   
    public void setIdCommande(int id_commande) {
        this.id_commande = id_commande;
    }
   
    public int getIdLivreur() {
        return id_livreur;
    }
   
    public void setIdLivreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }
   
    // Méthode toString()
    @Override
    public String toString() {
        return "Livraison{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", id_commande=" + id_commande +
                ", id_livreur=" + id_livreur +
                '}';
    }
}
