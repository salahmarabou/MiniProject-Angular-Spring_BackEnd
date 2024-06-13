package tn.esprit.spring.Service;

import tn.esprit.spring.Repository.BlocRepository;
import tn.esprit.spring.Repository.ChambreRepository;
import tn.esprit.spring.entity.Bloc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Chambre;

import java.util.List;

@Service
@Slf4j
public class BlocService implements BlocServiceImpl {

    @Autowired
    BlocRepository blocRepository;
    @Autowired
    ChambreRepository chambreRepository;
    @Override
    public Bloc addBloc(Bloc bloc) {
    return blocRepository.save(bloc);
    }
    @Override
    public List<Bloc> getBlocByCapacite(long capacite) {
        return blocRepository.findByCapaciteBlocLike(capacite);
    }
    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }
    @Override
    public Bloc updateBloc(Bloc bloc) {
        if (blocRepository.findById(bloc.getIdBloc()).isPresent()) {
            Bloc bToUpdate = blocRepository.findById(bloc.getIdBloc()).get();
            if(bloc.getFoyers() != null){
                bToUpdate.setFoyers(bloc.getFoyers());
            }
            bToUpdate.setNomBloc(bloc.getNomBloc());
//            bToUpdate.setChambres(bloc.getChambres());
            List<Chambre> chambres = chambreRepository.findAll();
            chambres.forEach((chambre) -> {
                if(bloc.getChambres().contains(chambre)){
                    if(!bToUpdate.getChambres().contains(chambre)){
                        chambre.setBloc(bToUpdate);
                    }
                }else if (bToUpdate.getChambres().contains(chambre)){
                    chambre.setBloc(null);
                }
            });
            bToUpdate.setCapaciteBloc(bloc.getCapaciteBloc());
            return blocRepository.save(bloc);
        }
        return null ;
    }
    @Override
    public void deleteBloc(Long id) {
    blocRepository.deleteById(id);
    }


    @Override
    @Scheduled(fixedRate = 30000)
    public void afficherListeBlocs() {
      /*  // Récupère la liste des blocs
        List<Bloc> listeBlocs = blocRepository.findAll();
        // Affiche la liste des blocs
        System.out.println("Liste des blocs chaque 30 secondes :");
        for (Bloc bloc : listeBlocs) {
            log.info(bloc.toString());
        }*/
    }
    @Override
    public Bloc findByIdChambre(Long idChambre){
       return blocRepository.findByIdChambre(idChambre);
    }


}
