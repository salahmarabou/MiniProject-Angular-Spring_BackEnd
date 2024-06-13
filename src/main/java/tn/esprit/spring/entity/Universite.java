package tn.esprit.spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="universite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude={"idUniversite"},includeFieldNames= false)
public class Universite implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUniversite")
    private Long idUniversite;
    private String nomUniversite;
    private String adresse;
    @OneToOne(mappedBy="universite")
    @JsonIgnore
    private Foyer foyer;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy="universite")
    private Set<Etudiant> etudiants ;

}
