package universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions;

public class FichierPasTrouveException extends Exception {

    public FichierPasTrouveException() {
        super("Le fichier est introuvable");
    }
}
