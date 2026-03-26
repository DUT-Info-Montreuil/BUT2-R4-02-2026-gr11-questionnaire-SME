package mocks;

import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.enums.DifficulteEnum;
import universite_Paris8.iut.qdev.tp2026.gr11.interfaces.IservicesQuestionnaire;
import universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions.*;

/**
 * Mock manuel pour le cas :
 * chargerFichier("questionsQuizz_2025_V1.csv")
 * → retourne un QuestionnaireDTO "Sport niv 1" (idQuestionnaire=1)
 *   avec les 3 premières questions du fichier réel.
 *
 * Format CSV réel (sans entête, séparateur ';') :
 *   idQuestionnaire ; libelleQuestionnaire ; numQuestion ; langue ;
 *   libelleQuestion ; reponse ; difficulte(1/2/3) ; explication ; reference
 */
public class MockChargerFichierValide implements IservicesQuestionnaire {

    @Override
    public QuestionnaireDTO chargerFichier(String cheminFichier)
            throws FichierPasTrouveException, FichierOuvertureException, FichierVideException,
            LigneInvalideException, DifficulteInvalideException, QuestionInvalideException,
            ListeQuestionsVideException {

        QuestionnaireDTO questionnaire = new QuestionnaireDTO("Sport niv 1");

        // Ligne 1 du CSV
        questionnaire.ajouterQuestion(new QuestionDTO(
                1, "Sport niv 1", "fr", 1,
                "De quel petit objet se munit le golfeur pour surélever sa balle avant de la frapper ?",
                "Tee",
                DifficulteEnum.SIMPLE,
                "Le joueur peut poser sa balle sur une cheville de bois ou de plastique "
                        + "qui ne peut pas être utilisée en dehors des départs.",
                "https://fr.wikipedia.org/wiki/Matériel_de_golf"
        ));

        // Ligne 2 du CSV
        questionnaire.ajouterQuestion(new QuestionDTO(
                1, "Sport niv 1", "fr", 2,
                "Quel sport de raquette porte le nom de la ville anglaise où il fut inventé ?",
                "Badminton",
                DifficulteEnum.SIMPLE,
                "Le badminton est toujours pratiqué en intérieur car avec le vent, "
                        + "en extérieur, le volant peut brusquement changer de direction.",
                "https://fr.wikipedia.org/wiki/Badminton"
        ));

        // Ligne 3 du CSV
        questionnaire.ajouterQuestion(new QuestionDTO(
                1, "Sport niv 1", "fr", 3,
                "Combien y a-t-il de joueurs sur le terrain dans une équipe de football ?",
                "Onze",
                DifficulteEnum.SIMPLE,
                "Codifié par les Britanniques à la fin du XIXe siècle, le football s'est "
                        + "doté en 1904 d'une fédération internationale, la FIFA.",
                "https://fr.wikipedia.org/wiki/Football"
        ));

        return questionnaire;
    }

    @Override
    public void fournirUnQuestionnaire(QuestionnaireDTO questionnaire) {
        // non utilisé dans ce cas de test
    }
}
