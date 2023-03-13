package Presentation;

import Metier.IEtudiantMetier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller("controleur")
@Data @AllArgsConstructor @NoArgsConstructor
public class EtudiantControleur implements IEtudiantControleur{
    @Autowired
    @Qualifier("metier")
    IEtudiantMetier etudiantMetier;
    @Override
    public void afficherMoyenneEtudiant(int id) throws Exception {
        var etudiant = etudiantMetier.calculerMoyenne(id);
        System.out.println(etudiant);
    }
}
