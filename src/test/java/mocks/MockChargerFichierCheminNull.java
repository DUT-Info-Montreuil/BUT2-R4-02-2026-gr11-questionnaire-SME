package mocks;

import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.interfaces.IservicesQuestionnaire;
import universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions.*;

/**
 * Mock manuel pour le cas :
 * chargerFichier(null)
 * → lève FichierPasTrouveException
 */
public class MockChargerFichierCheminNull implements IservicesQuestionnaire {

    @Override
    public QuestionnaireDTO chargerFichier(String cheminFichier)
            throws FichierPasTrouveException, FichierOuvertureException, FichierVideException,
            LigneInvalideException, DifficulteInvalideException, QuestionInvalideException,
            ListeQuestionsVideException {

        throw new FichierPasTrouveException();
    }

    @Override
    public void fournirUnQuestionnaire(QuestionnaireDTO questionnaire) {
        // non utilisé dans ce cas de test
    }
}
