package universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions;

public class FichierOuvertureException extends Exception {
    public FichierOuvertureException() {
        super("Impossible d'ouvrir le fichier");
    }
}
