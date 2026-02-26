package universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos;

import universite_Paris8.iut.qdev.tp2026.gr11.enums.DifficulteDTO;

public class QuestionDTO {

    private int id;
    private String intitule;
    private String reponse;
    private DifficulteDTO difficulte;

    public QuestionDTO(int id, String intitule, String reponse, DifficulteDTO difficulte) {
        this.id = id;
        this.intitule = intitule;
        this.reponse = reponse;
        this.difficulte = difficulte;
    }

    public int getId() {
        return id;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getReponse() {
        return reponse;
    }

    public DifficulteDTO getDifficulte() {
        return difficulte;
    }

    @Override
    public String toString() {
        return "QuestionDTO{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                ", reponse='" + reponse + '\'' +
                ", difficulte=" + difficulte +
                '}';
    }
}