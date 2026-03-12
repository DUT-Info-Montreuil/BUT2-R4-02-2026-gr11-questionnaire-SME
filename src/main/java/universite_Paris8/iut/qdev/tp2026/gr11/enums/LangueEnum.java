package universite_Paris8.iut.qdev.tp2026.gr11.enums;

public enum LangueEnum {

    FRANCAIS("FR", "Français"),
    ANGLAIS("EN", "Anglais"),
    ESPAGNOL("ES", "Espagnol"),
    ALLEMAND("DE", "Allemand");

    private String code;
    private String libelle;

    LangueEnum(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }
}