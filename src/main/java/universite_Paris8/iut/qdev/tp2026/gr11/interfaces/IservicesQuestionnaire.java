package universite_Paris8.iut.qdev.tp2026.gr11.interfaces;

import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions.*;

public interface IservicesQuestionnaire {

    QuestionnaireDTO chargerFichier(String cheminFichier)
            throws FichierPasTrouveException, FichierOuvertureException, FichierVideException,
            LigneInvalideException, DifficulteInvalideException, QuestionInvalideException,
            ListeQuestionsVideException;


    void fournirUnQuestionnaire(QuestionnaireDTO questionnaire);
}
