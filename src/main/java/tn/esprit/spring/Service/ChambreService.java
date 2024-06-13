package tn.esprit.spring.Service;

import tn.esprit.spring.Repository.BlocRepository;
import tn.esprit.spring.Repository.ChambreRepository;
import tn.esprit.spring.entity.Chambre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.TypeC;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChambreService implements ChambreServiceImpl {
    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    BlocRepository blocRepository;
    @Override
    public Chambre addChambre(Chambre chambre) {
//        chambre.getBloc().setCapaciteBloc(chambre.getBloc().getCapaciteBloc()+chambre.getCapaciteChambre());
        this.blocRepository.save(chambre.getBloc());
        return chambreRepository.save(chambre);
    }
    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }
    @Override
    public Chambre updateChambres(Chambre chambre) {
        if (chambreRepository.existsById(chambre.getIdChambre())) {
            if(chambre.getBloc() != null){
                blocRepository.save(chambre.getBloc());
            }
            return chambreRepository.save(chambre);
        }
        return null ;
    }
    @Override
    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public Chambre GetChambreByEtudiant(Long idEtudiant) {
        return chambreRepository.findChambreByEtudiantId(idEtudiant);
    }
    @Override
    public List<Chambre> findUnassignedChambres() {
        return chambreRepository.findAllUnassignedChambres();
    }
    @Override
    public Long CountChambre() {
        return chambreRepository.CountChambre();
    }

    @Override
    public Map<String, Long> getStatistiquesChambres() {
        List<Object[]> statistiques = chambreRepository.getStatistiquesChambres();
        Map<String, Long> resultat = new HashMap<>();

        for (Object[] stat : statistiques) {
            TypeC typeChambre = (TypeC) stat[0];
            Long count = (Long) stat[1];
            resultat.put(typeChambre.name(), count);
        }

        return resultat;
    }

}
