package Dao;

import Model.Etudiant;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component("dao")
public class EtudiantDao implements IEtudiantDao<Etudiant, Integer> {

    public static Set<Etudiant> BDEtudiant()
    {
        return new HashSet<Etudiant>(
                Arrays.asList(
                        new Etudiant(1, "Moussa", 20, new int[]{10, 12, 15},0.0),
                        new Etudiant(2, "Ahmed", 19, new int[]{10, 9, 10},0.0),
                        new Etudiant(3, "Ali", 23, new int[]{7, 12, 16},0.0),
                        new Etudiant(4, "Mohamed", 18, new int[]{19, 12, 15},0.0),
                        new Etudiant(5, "Abdou", 21, new int[]{5,8,10},0.0),
                        new Etudiant(6, "Hassan", 22, new int[]{8, 18, 15},0.0)
                )
        );
    }
    @Override
    public Etudiant trouverParId(Integer id) {
        return BDEtudiant()
                            .stream()
                            .filter(e -> e.getId() == id)
                            .findFirst()
                            .orElse(null);
    }
}
