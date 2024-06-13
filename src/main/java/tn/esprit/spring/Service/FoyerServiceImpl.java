package tn.esprit.spring.Service;

import tn.esprit.spring.entity.Bloc;
import tn.esprit.spring.entity.Foyer;
import tn.esprit.spring.entity.TypeC;
import tn.esprit.spring.entity.Universite;

import java.util.List;
import java.util.Optional;

public interface FoyerServiceImpl {
    Foyer addFoyer(Foyer foyer);
    List<Foyer> getAllFoyers();
    Foyer updateFoyer(Foyer foyer);
    void deleteFoyer(Long id);
    List<Foyer> findFoyersByTypeChambre(TypeC TypeChambre);

    List<Universite> findAllUniversiteNoAffecte();

    List<Bloc> FindAllBlocsNull();

    long findCapaciteFoyerByIdBloc(Long Idbloc);

    Optional<Foyer> findFoyerByIdf(Long idFoyer);

    String assignBlocsToFoyersById(Long idFoyer, List<Bloc> blocs);

    List<Object[]> getNombreEtudiantsParFoyer();

    List<Object[]> getNombreChambresParFoyer();

    List<Object[]> getNombreChambresParFoyerByType();

    Foyer findFoyerById(Long id);

    String AnnulerFoyer(Universite universite);
}