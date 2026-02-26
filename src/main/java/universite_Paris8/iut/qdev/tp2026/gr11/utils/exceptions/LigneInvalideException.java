package universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions;

public class LigneInvalideException extends Exception {
    public LigneInvalideException(int numeroLigne) {
        super("La ligne " + numeroLigne + " est invalide ou corrompue.");
    }
}
