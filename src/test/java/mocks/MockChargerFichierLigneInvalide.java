package mocks;

import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.interfaces.IservicesQuestionnaire;
import universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions.*;

/**
 * Mock manuel pour le cas :
 * chargerFichier("questionsQuizz_2025_V1.csv") avec une ligne corrompue.
 *
 * Simule une ligne qui n'a pas les 9 colonnes attendues, par exemple :
 *   "1;Sport niv 1;4;fr;Quel tapis à ressorts..."   (référence manquante = seulement 8 colonnes)
 *
 * → lève LigneInvalideException sur la ligne 4 (numéro de ligne dans le fichier)
 */
public class MockChargerFichierLigneInvalide implements IservicesQuestionnaire {

    /** Numéro de la ligne corrompue dans le fichier CSV (ligne 4 = question n°4 de Sport niv 1). */
    private static final int NUMERO_LIGNE_INVALIDE = 4;

    @Override
    public QuestionnaireDTO chargerFichier(String cheminFichier)
            throws FichierPasTrouveException, FichierOuvertureException, FichierVideException,
            LigneInvalideException, DifficulteInvalideException, QuestionInvalideException,
            ListeQuestionsVideException {

        throw new LigneInvalideException(NUMERO_LIGNE_INVALIDE);
    }

    @Override
    public void fournirUnQuestionnaire(QuestionnaireDTO questionnaire) {
        // non utilisé dans ce cas de test
    }
}
