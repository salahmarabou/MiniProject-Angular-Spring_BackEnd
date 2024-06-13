package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="etudiant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude={"idEtudiant"},includeFieldNames= false)
public class Etudiant implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idEtudiant")
    private Long idEtudiant;
    private String nomEt ;
    private String prenomEt;
    private Long cin;
    private String ecole;
    @Temporal (TemporalType.DATE)
    private LocalDate dateNaissance;
    @ManyToMany
    private Set<Reservation> reservations;

    @JsonBackReference
    @ManyToOne
    private Universite universite;
}
