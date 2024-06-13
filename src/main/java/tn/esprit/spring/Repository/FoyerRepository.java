package tn.esprit.spring.Repository;

import tn.esprit.spring.entity.Bloc;
import tn.esprit.spring.entity.Foyer;
import tn.esprit.spring.entity.TypeC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entity.Universite;

import java.util.List;
import java.util.Optional;

public interface FoyerRepository extends JpaRepository<Foyer, Long> {

    //Get list foyer qui a un typeChambre en parametre
    @Query("SELECT f FROM Foyer f JOIN Chambre c on (c.bloc.foyers.idFoyer = f.idFoyer) WHERE c.TypeChambre = :TypeChambre")
    List<Foyer> findFoyersByTypeChambre(@Param("TypeChambre") TypeC TypeChambre);

    //Get CapaciteFoyer  qui a un blocId en parametre
    @Query("SELECT f.capaciteFoyer FROM Foyer f JOIN f.blocs b WHERE b.idBloc = :Idbloc")
    long findCapaciteFoyerByIdBloc(@Param("Idbloc") Long Idbloc);

    @Query("SELECT u FROM Universite u WHERE u.idUniversite NOT IN (SELECT f.universite.idUniversite FROM Foyer f WHERE f.universite.idUniversite IS NOT NULL)")
    List<Universite> findAllUniversiteNoAffecte();
    @Query("SELECT b FROM Bloc b WHERE b.foyers.idFoyer IS NULL")
    List<Bloc> FindAllBlocsNull();
    @Query("SELECT f.nomFoyer AS nomFoyer, COUNT(e.idEtudiant) AS nbEtudiants " +
            "FROM Foyer f " +
            "JOIN f.blocs b " +
            "JOIN b.chambres c " +
            "JOIN c.Reservations r " +
            "JOIN r.etudiants e " +
            "WHERE r.estValide = true " +
            "GROUP BY f.nomFoyer")
    List<Object[]> getNombreEtudiantsParFoyer();
    @Query("SELECT f.nomFoyer AS nomFoyer, COUNT(c.idChambre) AS nbChambres " +
            "FROM Foyer f " +
            "JOIN f.blocs b " +
            "JOIN b.chambres c " +
            "WHERE f.idFoyer = b.foyers.idFoyer " +
            "GROUP BY f.nomFoyer")
    List<Object[]> getNombreChambresParFoyer();
    @Query("SELECT f.nomFoyer AS nomFoyer, " +
            "SUM(CASE WHEN c.TypeChambre = 'DOUBLE' THEN 1 ELSE 0 END) AS ChambreDouble, " +
            "SUM(CASE WHEN c.TypeChambre = 'TRIPLE' THEN 1 ELSE 0 END) AS ChambreTriple, " +
            "SUM(CASE WHEN c.TypeChambre = 'SIMPLE' THEN 1 ELSE 0 END) AS ChambreSimple " +
            "FROM Foyer f " +
            "JOIN f.blocs b " +
            "JOIN b.chambres c " +
            "WHERE f.idFoyer = b.foyers.idFoyer AND c.TypeChambre IN ('DOUBLE', 'TRIPLE', 'SIMPLE') " +
            "GROUP BY f.nomFoyer")
    List<Object[]> getNombreChambresParFoyerByType();


    @Query("SELECT f FROM Foyer f WHERE f.universite.idUniversite = :universiteId")
    Optional<Foyer> findByUniversiteIdFoyer(@Param("universiteId") Long universiteId);

}