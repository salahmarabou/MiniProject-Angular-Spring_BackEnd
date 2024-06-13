package tn.esprit.spring.Service;

import tn.esprit.spring.entity.Chambre;

import java.util.List;
import java.util.Map;

public interface ChambreServiceImpl {
    Chambre addChambre(Chambre chambre);
    List<Chambre> getAllChambres();
    Chambre updateChambres(Chambre chambre);
    void deleteChambre(Long id);
    Chambre GetChambreByEtudiant(Long idEtudiant);


    List<Chambre> findUnassignedChambres();

    Long CountChambre();
    Map<String, Long> getStatistiquesChambres();
}
