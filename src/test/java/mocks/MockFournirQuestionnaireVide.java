package mocks;

import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.interfaces.IservicesQuestionnaire;
import universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions.*;

/**
 * Mock manuel pour le cas :
 * fournirUnQuestionnaire(questionnaireVide)
 * → lève IllegalArgumentException car la liste de questions est vide
 */
public class MockFournirQuestionnaireVide implements IservicesQuestionnaire {

    @Override
    public QuestionnaireDTO chargerFichier(String cheminFichier)
            throws FichierPasTrouveException, FichierOuvertureException, FichierVideException,
            LigneInvalideException, DifficulteInvalideException, QuestionInvalideException,
            ListeQuestionsVideException {
        // non utilisé dans ce cas de test
        return null;
    }

    @Override
    public void fournirUnQuestionnaire(QuestionnaireDTO questionnaire) {
        throw new IllegalArgumentException("Le questionnaire ne contient aucune question.");
    }
}
