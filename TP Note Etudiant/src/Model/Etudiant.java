package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data @NoArgsConstructor @AllArgsConstructor
public class Etudiant {
    private int id;
    private String nomComplete;
    private int ageEtudiant;
    private int[] notes;
    private double moyenne;

    @Override
    public String toString() {
        String strEtudiant  ="**************** Etudiant ******************\n";
               strEtudiant +="=> ID         : " + getId()           + "\n";
               strEtudiant +="=> Nom        : " + getNomComplete()  + "\n";
               strEtudiant +="=> Age        : " + getAgeEtudiant()  + "\n";
               strEtudiant +="=> Moyenne    : " + getMoyenne()      + "\n";
               strEtudiant +="*******************************************";
               return strEtudiant;
    }
    public double sommeNotes(){
        return Arrays.stream(notes).sum();
    }
    public static void main(String[] args) {

    }
}
