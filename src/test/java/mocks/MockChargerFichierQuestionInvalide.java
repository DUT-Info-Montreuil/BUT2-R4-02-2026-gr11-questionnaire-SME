package mocks;

import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.interfaces.IservicesQuestionnaire;
import universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions.*;

/**
 * Mock manuel pour le cas :
 * chargerFichier("questionsQuizz_2025_V1.csv") avec un libellé de question vide.
 *
 * Simule une ligne où la colonne libelleQuestion (index 4) est une chaîne vide :
 *   "1;Sport niv 1;6;fr;;Risque d'avalanche;1;...;..."
 *
 * → lève QuestionInvalideException("Le libellé de la question est vide.")
 */
public class MockChargerFichierQuestionInvalide implements IservicesQuestionnaire {

    @Override
    public QuestionnaireDTO chargerFichier(String cheminFichier)
            throws FichierPasTrouveException, FichierOuvertureException, FichierVideException,
            LigneInvalideException, DifficulteInvalideException, QuestionInvalideException,
            ListeQuestionsVideException {

        throw new QuestionInvalideException("Le libellé de la question est vide.");
    }

    @Override
    public void fournirUnQuestionnaire(QuestionnaireDTO questionnaire) {
        // non utilisé dans ce cas de test
    }
}
