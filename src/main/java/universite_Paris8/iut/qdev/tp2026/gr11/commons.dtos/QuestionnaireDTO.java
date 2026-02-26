package universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDTO {

    private List<QuestionDTO> listeQuestions;

    public QuestionnaireDTO() {
        this.listeQuestions = new ArrayList<>();
    }

    public void ajouterQuestion(QuestionDTO question) {
        listeQuestions.add(question);
    }

    public List<QuestionDTO> getListeQuestions() {
        return listeQuestions;
    }

    public boolean estVide() {
        return listeQuestions.isEmpty();
    }

    @Override
    public String toString() {
        return "QuestionnaireDTO{" + "listeQuestions=" + listeQuestions + '}';
    }
}