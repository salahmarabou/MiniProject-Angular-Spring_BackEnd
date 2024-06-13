package tn.esprit.spring.Controller;

import tn.esprit.spring.Config.RandomStringGenerator;
import tn.esprit.spring.Service.EmailService;
import tn.esprit.spring.Service.EtudiantServiceImpl;
import tn.esprit.spring.entity.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/etudiant")
@CrossOrigin(origins = "http://localhost:4200/")
public class EtudiantController {
    @Autowired
    EtudiantServiceImpl etudiantService;

    @Autowired
    private EmailService emailService;


    @PostMapping("/addEtudiant")
    Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addEtudiant(etudiant);
    }

    @PutMapping("/updateEtudiant")
    Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.updateEtudiant(etudiant);
    }

    @GetMapping("/getAllEtudiants")
    List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @GetMapping("/getByCinEtudiant/{cinEtudiant}")
    Optional<Etudiant> getByCinEtudiant(@PathVariable Long cinEtudiant) {
        return etudiantService.getByCinEtudiant(cinEtudiant);
    }
    @DeleteMapping("/deleteEtudiant/{id}")
    void deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
    }

    @GetMapping("/getTous/{cin}")
    Map<String, Object> getAllByCin(@PathVariable Long cin) {
        return etudiantService.getEtudiantInfoByCIN(cin);
    }

    @PostMapping("/email/send")
    public String sendEmail(@RequestBody String Email) {
        String subject="Réinitialiser mot de passe";
        String text = RandomStringGenerator.generateRandomString(6);
        emailService.sendEmail(Email, subject, text);
        return text;
    }


}
