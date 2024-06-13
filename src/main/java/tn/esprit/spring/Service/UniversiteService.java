package tn.esprit.spring.Service;

import tn.esprit.spring.Repository.UniversiteRepository;
import tn.esprit.spring.entity.Foyer;
import tn.esprit.spring.entity.Universite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UniversiteService implements UniversiteServiceImpl {
    @Autowired
    UniversiteRepository universiteRepository;
    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }
    @Override
    public List<Universite> getAllUniversites() { return universiteRepository.findAll();}
    @Override
    public Universite updateUniversite(Universite universite) {
        if (universiteRepository.existsById(universite.getIdUniversite())) {
            Universite uni = universiteRepository.findById(universite.getIdUniversite()).get();
            uni.setNomUniversite(universite.getNomUniversite());
            uni.setAdresse(universite.getAdresse());
            uni.setFoyer(universite.getFoyer());
            return universiteRepository.save(uni);
        }

        return null;
    }
    @Override
    public void deleteUniversite(Long id) { universiteRepository.deleteById(id); }

    @Override
    public Universite findUniversitesByChambreId(Long chambreId) {
        return universiteRepository.findUniversitesByChambreId(chambreId);

    }
    @Override
    public Universite findUniversitesByIdEtudiant(Long IdEtudiant) {
        return universiteRepository.findUniversitesByIdEtudiant(IdEtudiant);
    }
    @Override
    public List<Foyer> findFoyerByIdUniversite() {
        return universiteRepository.findFoyerByIdUniversite();
    }



    @Override
    public Universite findById(long id) {
        return universiteRepository.findById(id).get();
    }

}
