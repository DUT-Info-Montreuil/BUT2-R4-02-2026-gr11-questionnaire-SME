package mocks;

import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.interfaces.IservicesQuestionnaire;
import universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions.*;

/**
 * Mock manuel pour le cas :
 * chargerFichier("questionsQuizz_2025_V1.csv") avec une difficulté invalide.
 *
 * Dans le CSV réel la colonne difficulté (index 6) ne peut valoir que 1, 2 ou 3,
 * correspondant à DifficulteEnum.SIMPLE / INTERMEDIAIRE / DIFFICILE.
 *
 * Simule une ligne dont la colonne difficulté vaut "4" (valeur inconnue) :
 *   "1;Sport niv 1;5;fr;...;Grand chelem;4;...;..."
 *
 * → lève DifficulteInvalideException("4")
 */
public class MockChargerFichierDifficulteInvalide implements IservicesQuestionnaire {

    /** Valeur de difficulté illégale trouvée dans le CSV (ni 1, ni 2, ni 3). */
    private static final String VALEUR_INVALIDE = "4";

    @Override
    public QuestionnaireDTO chargerFichier(String cheminFichier)
            throws FichierPasTrouveException, FichierOuvertureException, FichierVideException,
            LigneInvalideException, DifficulteInvalideException, QuestionInvalideException,
            ListeQuestionsVideException {

        throw new DifficulteInvalideException(VALEUR_INVALIDE);
    }

    @Override
    public void fournirUnQuestionnaire(QuestionnaireDTO questionnaire) {
        // non utilisé dans ce cas de test
    }
}
