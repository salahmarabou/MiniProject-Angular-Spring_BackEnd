package tn.esprit.spring.Controller;


import tn.esprit.spring.Service.ChambreServiceImpl;
import tn.esprit.spring.entity.Chambre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/chambre")
public class ChambreController {
    @Autowired
    ChambreServiceImpl chambreService;

    @PostMapping("/addChambre")
    Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addChambre(chambre);
    }

    @PutMapping("/updateChambre")
    Chambre updateChambre(@RequestBody Chambre chambre) {
        return chambreService.updateChambres(chambre);
    }

    @GetMapping("/getAllChambres")
    List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    @DeleteMapping("/deleteChambre/{id}")
    void deleteChambre(@PathVariable Long id) {
        chambreService.deleteChambre(id);
    }

    //valid
    @GetMapping("/chambreEtud/{idEtuidant}")
    Chambre getChambreByEtudiant(@PathVariable Long idEtuidant) {
        return chambreService.GetChambreByEtudiant(idEtuidant);
    }
    @GetMapping("/unassigned")
    List<Chambre> getUnassignedChambres(){
        return chambreService.findUnassignedChambres();
    }

    @GetMapping("/getnbChambre")
    Long getCountChambre() {
        return chambreService.CountChambre();
    }

    @GetMapping("/stat")
    public Map<String, Long> getStatistiquesChambres() {
        return chambreService.getStatistiquesChambres();
    }

}

