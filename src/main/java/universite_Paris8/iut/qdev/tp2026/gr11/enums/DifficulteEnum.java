package universite_Paris8.iut.qdev.tp2026.gr11.enums;

public enum DifficulteEnum {

    SIMPLE(1, "Simple"),
    INTERMEDIAIRE(2, "Intermédiaire"),
    DIFFICILE(3, "Difficile");

    private int niveau;
    private String libelle;

    DifficulteEnum(int niveau, String libelle) {
        this.niveau = niveau;
        this.libelle = libelle;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getLibelle() {
        return libelle;
    }
}