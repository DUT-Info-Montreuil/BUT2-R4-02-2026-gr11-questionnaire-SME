package universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDTO {

    private static int idPlus = 0;
    private int idQuestionnaire;
    private String libelleQuestionnaire;
    private List<QuestionDTO> listeQuestions;

    public QuestionnaireDTO(String libelleQuestionnaire) {
        this.idQuestionnaire = ++idPlus;
        this.libelleQuestionnaire = libelleQuestionnaire;
        this.listeQuestions = new ArrayList<>();
    }

    public void ajouterQuestion(QuestionDTO question) {
        listeQuestions.add(question);
    }

    public int getIdQuestionnaire() {
        return idQuestionnaire;
    }

    public String getLibelleQuestionnaire() {
        return libelleQuestionnaire;
    }

    public List<QuestionDTO> getListeQuestions() {
        return listeQuestions;
    }

    public boolean estVide() {
        return listeQuestions.isEmpty();
    }

    @Override
    public String toString() {
        return "QuestionnaireDTO{" +
                "idQuestionnaire=" + idQuestionnaire +
                ", libelleQuestionnaire='" + libelleQuestionnaire + '\'' +
                ", listeQuestions=" + listeQuestions +
                '}';
    }
}