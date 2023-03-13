package Metier;

import Dao.IEtudiantDao;
import Model.Etudiant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("metier")
@Data @NoArgsConstructor @AllArgsConstructor
public class EtudiantMetier implements IEtudiantMetier {
    @Autowired
    @Qualifier("dao")
    IEtudiantDao<Etudiant, Integer> etudiantDao ;
    @Override
    public Etudiant calculerMoyenne(int id) {
        var etudiant = etudiantDao.trouverParId(id);
        if (etudiant == null) throw new RuntimeException("Etudiant introuvable");
        else {
            double sommeNotes = etudiant.sommeNotes();
            double moyenne = sommeNotes / 3;
            etudiant.setMoyenne(moyenne);
            return etudiant;
        }
    }
}
