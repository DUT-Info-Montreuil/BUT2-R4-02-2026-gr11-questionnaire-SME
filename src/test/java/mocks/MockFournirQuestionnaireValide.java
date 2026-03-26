package mocks;

import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.interfaces.IservicesQuestionnaire;
import universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions.*;

/**
 * Mock manuel pour le cas :
 * fournirUnQuestionnaire(questionnaireValide)
 * → s'exécute sans exception, le questionnaire est mémorisé
 */
public class MockFournirQuestionnaireValide implements IservicesQuestionnaire {

    private QuestionnaireDTO questionnaireFourni = null;
    private int nombreAppels = 0;

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
        this.questionnaireFourni = questionnaire;
        this.nombreAppels++;
        // comportement attendu : rien d'autre, pas d'exception
    }

    /** Permet au test de vérifier que le bon objet a été reçu. */
    public QuestionnaireDTO getQuestionnaireFourni() {
        return questionnaireFourni;
    }

    /** Permet au test de vérifier le nombre d'appels. */
    public int getNombreAppels() {
        return nombreAppels;
    }
}
