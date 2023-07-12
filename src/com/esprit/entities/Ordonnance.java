
package com.esprit.entities;



import java.util.Date;

public class Ordonnance {
    private int id ;
    private int Reference;
    private int id_Medecin ;
    private int id_Medicament ;
    private Date date ;
    private String Statut ; 

    public Ordonnance() {
    }
    
  
    public Ordonnance(int id, int Reference, int id_Medecin, int id_Medicament, Date date, String Statut) {
        this.id = id;
        this.Reference = Reference;
        this.id_Medecin = id_Medecin;
        this.id_Medicament = id_Medicament;
        this.date = date;
        this.Statut = Statut;
    }

      public Ordonnance( int Reference, int id_Medecin, int id_Medicament, Date date, String Statut) {
      
        this.Reference = Reference;
        this.id_Medecin = id_Medecin;
        this.id_Medicament = id_Medicament;
        this.date = date;
        this.Statut = Statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReference() {
        return Reference;
    }

    public void setReference(int Reference) {
        this.Reference = Reference;
    }

    public int getId_Medecin() {
        return id_Medecin;
    }

    public void setNom_Medecin(int id_Medecin) {
        this.id_Medecin = id_Medecin;
    }

    public int getId_Medicament() {
        return id_Medicament;
    }

    public void setNom_Medicament(int id_Medicament) {
        this.id_Medicament = id_Medicament;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatut() {
        return Statut;
    }

    public void setStatut(String Statut) {
        this.Statut = Statut;
    }    

    public Date getDateOrdonnance() {
        return this.date;
       }

    @Override
    public String toString() {
        return "Ordonnance{" + "id=" + id + ", Reference=" + Reference + ", id_Medecin=" + id_Medecin + ", id_Medicament=" + id_Medicament + ", date=" + date + ", Statut=" + Statut + '}';
    }
}
