package universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos;

import universite_Paris8.iut.qdev.tp2026.gr11.enums.DifficulteEnum;

public class QuestionDTO {

    private static int idQuestionnaire;
    private String libelleQuestionnaire;
    private String langue;
    private int numQuestion;
    private String libelleQuestion;
    private String reponse;
    private DifficulteEnum difficulte;
    private String explication;
    private String reference;

    public QuestionDTO(int idQuestionnaire, String libelleQuestionnaire, String langue,
                       int numQuestion, String libelleQuestion, String reponse,
                       DifficulteEnum difficulte, String explication, String reference) {
        this.idQuestionnaire = idQuestionnaire;
        this.libelleQuestionnaire = libelleQuestionnaire;
        this.langue = langue;
        this.numQuestion = numQuestion;
        this.libelleQuestion = libelleQuestion;
        this.reponse = reponse;
        this.difficulte = difficulte;
        this.explication = explication;
        this.reference = reference;
    }

    public int getIdQuestionnaire() {
        return idQuestionnaire;
    }

    public String getLibelleQuestionnaire() {
        return libelleQuestionnaire;
    }

    public String getLangue() {
        return langue;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public String getLibelleQuestion() {
        return libelleQuestion;
    }

    public String getReponse() {
        return reponse;
    }

    public DifficulteEnum getDifficulte() {
        return difficulte;
    }

    public String getExplication() {
        return explication;
    }

    public String getReference() {
        return reference;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "idQuestionnaire=" + idQuestionnaire +
                ", libelleQuestionnaire='" + libelleQuestionnaire + '\'' +
                ", langue='" + langue  +
                ", numQuestion=" + numQuestion +
                ", libelleQuestion='" + libelleQuestion +
                ", reponse='" + reponse +
                ", difficulte=" + difficulte +
                ", explication='" + explication +
                ", reference='" + reference + +
                '}';
    }
}