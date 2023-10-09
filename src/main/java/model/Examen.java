package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.util.Date;

@Entity
public class Examen {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
    // Ajoutez d'autres attributs selon vos besoins

    // Getters et setters

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
// Constructeurs

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
