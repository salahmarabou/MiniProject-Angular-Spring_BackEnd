package tn.esprit.spring.Controller;

import tn.esprit.spring.Service.FoyerServiceImpl;
import tn.esprit.spring.entity.Bloc;
import tn.esprit.spring.entity.Foyer;
import tn.esprit.spring.entity.TypeC;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Universite;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foyer")
@CrossOrigin(origins = "http://localhost:4200/")
public class FoyerController {
    @Autowired
    FoyerServiceImpl foyerService;
    @PostMapping("/addFoyer")
    Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addFoyer(foyer);
    }

    @PutMapping("/updateFoyer")
    Foyer updateFoyer(@RequestBody Foyer foyer) {
        return foyerService.updateFoyer(foyer);
    }

    @GetMapping("/getAllFoyers")
    List<Foyer> getAllFoyers() {
        return foyerService.getAllFoyers();
    }

    @DeleteMapping("/deleteFoyer/{id}")
    void deleteFoyer(@PathVariable Long id) {
        foyerService.deleteFoyer(id);
    }

    //valide
    @GetMapping("/getFoyersChambre")
    List<Foyer> getFoyerBychambre(@PathParam("TypeChambre") TypeC TypeChambre) {
        return foyerService.findFoyersByTypeChambre(TypeChambre);
    }

    //valide
    @GetMapping("/getCapaciteFoyerBybloc")
    Long getFoyerBychambre(@PathParam("Idbloc") Long Idbloc) {
        return foyerService.findCapaciteFoyerByIdBloc(Idbloc);
    }

    @GetMapping("/{id}")
    public Foyer getFoyerById(@PathVariable("id") Long id){
        return foyerService.findFoyerById(id);
    }
    @GetMapping("/getAllUniversite")
    List<Universite> getAllUniversiteNoAffected() {
        return foyerService.findAllUniversiteNoAffecte();
    }

    @GetMapping("/getAllBlocsNull")
    List<Bloc> getAllBlocsWithFoyerNull() {
        return foyerService.FindAllBlocsNull();
    }

    @PostMapping("/AssignBlocsToFoyer/{idFoyer}")
    public String assignBlocsToFoyer(@PathVariable Long idFoyer, @RequestBody List<Bloc> blocs) {
        return foyerService.assignBlocsToFoyersById(idFoyer, blocs);
    }
    @GetMapping("/findFoyer/{idFoyer}")
    Optional<Foyer> FindFoyerByIdF(@PathVariable Long idFoyer) {
        return foyerService.findFoyerByIdf(idFoyer);
    }
    @GetMapping("/GetNbEtudiantFoyer")
    List<Object[]> getNombreEtudiantsParFoyer() {
        return foyerService.getNombreEtudiantsParFoyer();
    }
    @GetMapping("/GetNbChambreFoyer")
    List<Object[]> getNombreChambresParFoyer() {
        return foyerService.getNombreChambresParFoyer();
    }

    @GetMapping("/GetNbChambreFoyerByType")
    List<Object[]> getNombreChambresParFoyerByType(){
        return foyerService.getNombreChambresParFoyerByType();
    }

    @PostMapping("/AnnulerFoyer")
    String addFoyer(@RequestBody Universite universite) {
        return foyerService.AnnulerFoyer(universite);
    }

}