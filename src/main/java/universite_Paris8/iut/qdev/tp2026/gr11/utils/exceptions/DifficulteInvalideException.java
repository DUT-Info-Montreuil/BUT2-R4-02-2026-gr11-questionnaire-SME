package universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions;

public class DifficulteInvalideException extends Exception {
    public DifficulteInvalideException(String valeur) {
        super("La difficulté " + valeur + " est invalide.");
    }
}
