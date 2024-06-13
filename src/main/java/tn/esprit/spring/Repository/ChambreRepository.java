package tn.esprit.spring.Repository;

import tn.esprit.spring.entity.Chambre;
import tn.esprit.spring.entity.TypeC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    //Get chambre qui a un etudiantId en parametre
    @Query("SELECT c FROM Chambre c JOIN c.Reservations r JOIN r.etudiants e WHERE e.idEtudiant = :IdEtudiant")
    Chambre findChambreByEtudiantId(@Param("IdEtudiant") Long IdEtudiant);
    @Query( "SELECT c FROM Chambre c " +
            "LEFT JOIN c.Reservations r " +
            "WHERE c.TypeChambre = :typeChambre " +
            "AND (r IS NULL OR r.estValide = false)"
          )
    Chambre findAvailableChambreByType(@Param("typeChambre") TypeC typeChambre);
/***************/
/*@Query("SELECT c FROM Chambre c WHERE c.TypeChambre = :type AND c.capaciteChambre > 0 ORDER BY c.idChambre ASC LIMIT 1")
Chambre findChambreDisponible(@Param("type") TypeC type);*/

@Query("SELECT c FROM Chambre c " +
        "JOIN c.bloc b " +
        "JOIN b.foyers f " +
        "JOIN f.universite u " +
        "WHERE c.TypeChambre= :type " +
        "AND c.capaciteChambre > 0 " +
        "AND u.idUniversite = :idUniversite " +
        "ORDER BY c.idChambre ASC LIMIT 1")
Chambre findChambreDisponible(@Param("type") TypeC type, @Param("idUniversite") Long idUniversite);


    @Query("SELECT c FROM Chambre c WHERE c.bloc IS NULL")
    List<Chambre> findAllUnassignedChambres();

    @Query("SELECT count(*) FROM Chambre c")
    Long CountChambre();
    @Query("SELECT c.TypeChambre, COUNT(c) FROM Chambre c GROUP BY c.TypeChambre")
    List<Object[]> getStatistiquesChambres();
}
