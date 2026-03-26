package universite_Paris8.iut.qdev.tp2026.gr11.interfaces;

import mocks.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.enums.DifficulteEnum;
import universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ============================================================
 *  Tests de IservicesQuestionnaire
 * ============================================================
 *
 *  FONCTIONNEMENT :
 *  - Chaque test instancie son propre mock du package mocks/.
 *  - Les mocks retournent directement le résultat attendu.
 *
 *  UTILISATION FUTURE (quand l'implémentation réelle sera prête) :
 *  Pour chaque test, commenter la ligne mock et décommenter
 *  la ligne "implémentation réelle".
 *
 *  Fichiers CSV de référence (src/test/resources/) :
 *    - questions_valides.csv              → 3 questions valides Sport niv 1
 *    - questions_vide.csv                 → fichier vide (0 octet)
 *    - questions_ligne_invalide.csv       → ligne 4 avec colonnes manquantes
 *    - questions_difficulte_invalide.csv  → difficulté "4" inconnue en ligne 2
 *    - questions_question_invalide.csv    → libelleQuestion vide en ligne 2
 *    - questions_toutes_invalides.csv     → toutes les lignes corrompues
 *
 *  Format CSV (sans entête, séparateur ';') :
 *    idQuestionnaire ; libelleQuestionnaire ; numQuestion ; langue ;
 *    libelleQuestion ; reponse ; difficulte(1/2/3) ; explication ; reference
 * ============================================================
 */
@DisplayName("Tests de IservicesQuestionnaire")
class IservicesQuestionnaireTest {

    private static final String CSV_VALIDE         = "questions_valides.csv";
    private static final String CSV_VIDE           = "questions_vide.csv";
    private static final String CSV_LIGNE_INVALIDE = "questions_ligne_invalide.csv";
    private static final String CSV_DIFF_INVALIDE  = "questions_difficulte_invalide.csv";
    private static final String CSV_QUEST_INVALIDE = "questions_question_invalide.csv";
    private static final String CSV_TOUT_INVALIDE  = "questions_toutes_invalides.csv";
    private static final String CSV_INEXISTANT     = "chemin/qui/nexiste/pas.csv";


    // ===============================================================
    //  chargerFichier — CAS NOMINAUX
    // ===============================================================

    @Test
    @DisplayName("[chargerFichier] Fichier valide → retourne un QuestionnaireDTO non null")
    void chargerFichier_fichierValide_retourneObjetNonNull() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionnaireDTO resultat = service.chargerFichier(CSV_VALIDE);

        assertNotNull(resultat);
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → le questionnaire n'est pas vide")
    void chargerFichier_fichierValide_questionnaireNonVide() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionnaireDTO resultat = service.chargerFichier(CSV_VALIDE);

        assertFalse(resultat.estVide());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → libellé questionnaire = 'Sport niv 1'")
    void chargerFichier_fichierValide_libelleQuestionnaireCorrect() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionnaireDTO resultat = service.chargerFichier(CSV_VALIDE);

        assertEquals("Sport niv 1", resultat.getLibelleQuestionnaire());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → 3 questions chargées")
    void chargerFichier_fichierValide_nombreQuestionsCorrect() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionnaireDTO resultat = service.chargerFichier(CSV_VALIDE);

        assertEquals(3, resultat.getListeQuestions().size());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → question 1 : libellé correct")
    void chargerFichier_fichierValide_question1_libelleCorrect() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionDTO q1 = service.chargerFichier(CSV_VALIDE).getListeQuestions().get(0);

        assertEquals(
            "De quel petit objet se munit le golfeur pour surélever sa balle avant de la frapper ?",
            q1.getLibelleQuestion()
        );
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → question 1 : réponse = 'Tee'")
    void chargerFichier_fichierValide_question1_reponseCorrecte() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionDTO q1 = service.chargerFichier(CSV_VALIDE).getListeQuestions().get(0);

        assertEquals("Tee", q1.getReponse());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → question 1 : difficulté SIMPLE")
    void chargerFichier_fichierValide_question1_difficulteSimple() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionDTO q1 = service.chargerFichier(CSV_VALIDE).getListeQuestions().get(0);

        assertEquals(DifficulteEnum.SIMPLE, q1.getDifficulte());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → question 1 : langue = 'fr'")
    void chargerFichier_fichierValide_question1_langueCorrecte() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionDTO q1 = service.chargerFichier(CSV_VALIDE).getListeQuestions().get(0);

        assertEquals("fr", q1.getLangue());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → question 1 : numéro = 1")
    void chargerFichier_fichierValide_question1_numeroCorrect() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionDTO q1 = service.chargerFichier(CSV_VALIDE).getListeQuestions().get(0);

        assertEquals(1, q1.getNumQuestion());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → question 1 : référence URL correcte")
    void chargerFichier_fichierValide_question1_referenceCorrecte() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionDTO q1 = service.chargerFichier(CSV_VALIDE).getListeQuestions().get(0);

        assertEquals("https://fr.wikipedia.org/wiki/Matériel_de_golf", q1.getReference());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → question 2 : réponse = 'Badminton'")
    void chargerFichier_fichierValide_question2_reponseCorrecte() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionDTO q2 = service.chargerFichier(CSV_VALIDE).getListeQuestions().get(1);

        assertEquals("Badminton", q2.getReponse());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → question 3 : réponse = 'Onze'")
    void chargerFichier_fichierValide_question3_reponseCorrecte() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionDTO q3 = service.chargerFichier(CSV_VALIDE).getListeQuestions().get(2);

        assertEquals("Onze", q3.getReponse());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier valide → questions numérotées dans l'ordre 1, 2, 3")
    void chargerFichier_fichierValide_questionsNumeroteesEnOrdre() throws Exception {
        IservicesQuestionnaire service = new MockChargerFichierValide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        List<QuestionDTO> questions = service.chargerFichier(CSV_VALIDE).getListeQuestions();

        assertEquals(1, questions.get(0).getNumQuestion());
        assertEquals(2, questions.get(1).getNumQuestion());
        assertEquals(3, questions.get(2).getNumQuestion());
    }


    // ===============================================================
    //  chargerFichier — CAS D'EXCEPTION
    // ===============================================================

    @Test
    @DisplayName("[chargerFichier] Chemin null → FichierPasTrouveException")
    void chargerFichier_cheminNull_leveFichierPasTrouveException() {
        IservicesQuestionnaire service = new MockChargerFichierCheminNull();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        assertThrows(FichierPasTrouveException.class,
            () -> service.chargerFichier(null));
    }

    @Test
    @DisplayName("[chargerFichier] Chemin null → message 'Le fichier est introuvable'")
    void chargerFichier_cheminNull_messageErreurCorrect() {
        IservicesQuestionnaire service = new MockChargerFichierCheminNull();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        FichierPasTrouveException ex = assertThrows(FichierPasTrouveException.class,
            () -> service.chargerFichier(null));

        assertEquals("Le fichier est introuvable", ex.getMessage());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier inexistant → FichierPasTrouveException")
    void chargerFichier_fichierInexistant_leveFichierPasTrouveException() {
        IservicesQuestionnaire service = new MockChargerFichierInexistant();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        assertThrows(FichierPasTrouveException.class,
            () -> service.chargerFichier(CSV_INEXISTANT));
    }

    @Test
    @DisplayName("[chargerFichier] Fichier inexistant → message 'Le fichier est introuvable'")
    void chargerFichier_fichierInexistant_messageErreurCorrect() {
        IservicesQuestionnaire service = new MockChargerFichierInexistant();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        FichierPasTrouveException ex = assertThrows(FichierPasTrouveException.class,
            () -> service.chargerFichier(CSV_INEXISTANT));

        assertEquals("Le fichier est introuvable", ex.getMessage());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier non ouvrable → FichierOuvertureException")
    void chargerFichier_fichierNonOuvrable_leveFichierOuvertureException() {
        IservicesQuestionnaire service = new MockChargerFichierNonOuvrable();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        assertThrows(FichierOuvertureException.class,
            () -> service.chargerFichier(CSV_VALIDE));
    }

    @Test
    @DisplayName("[chargerFichier] Fichier non ouvrable → message 'Impossible d'ouvrir le fichier'")
    void chargerFichier_fichierNonOuvrable_messageErreurCorrect() {
        IservicesQuestionnaire service = new MockChargerFichierNonOuvrable();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        FichierOuvertureException ex = assertThrows(FichierOuvertureException.class,
            () -> service.chargerFichier(CSV_VALIDE));

        assertEquals("Impossible d'ouvrir le fichier", ex.getMessage());
    }

    @Test
    @DisplayName("[chargerFichier] Fichier vide → FichierVideException")
    void chargerFichier_fichierVide_leveFichierVideException() {
        IservicesQuestionnaire service = new MockChargerFichierVide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        assertThrows(FichierVideException.class,
            () -> service.chargerFichier(CSV_VIDE));
    }

    @Test
    @DisplayName("[chargerFichier] Fichier vide → message 'Le fichier est vide.'")
    void chargerFichier_fichierVide_messageErreurCorrect() {
        IservicesQuestionnaire service = new MockChargerFichierVide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        FichierVideException ex = assertThrows(FichierVideException.class,
            () -> service.chargerFichier(CSV_VIDE));

        assertEquals("Le fichier est vide.", ex.getMessage());
    }

    @Test
    @DisplayName("[chargerFichier] Ligne 4 corrompue → LigneInvalideException")
    void chargerFichier_ligneCorompue_leveLigneInvalideException() {
        IservicesQuestionnaire service = new MockChargerFichierLigneInvalide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        assertThrows(LigneInvalideException.class,
            () -> service.chargerFichier(CSV_LIGNE_INVALIDE));
    }

    @Test
    @DisplayName("[chargerFichier] Ligne 4 corrompue → message contient le numéro 4")
    void chargerFichier_ligneCorompue_messageContientNumeroLigne() {
        IservicesQuestionnaire service = new MockChargerFichierLigneInvalide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        LigneInvalideException ex = assertThrows(LigneInvalideException.class,
            () -> service.chargerFichier(CSV_LIGNE_INVALIDE));

        assertTrue(ex.getMessage().contains("4"),
            "Le message devrait indiquer la ligne 4, reçu : " + ex.getMessage());
    }

    @Test
    @DisplayName("[chargerFichier] Difficulté '4' inconnue → DifficulteInvalideException")
    void chargerFichier_difficulteInconnue_leveDifficulteInvalideException() {
        IservicesQuestionnaire service = new MockChargerFichierDifficulteInvalide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        assertThrows(DifficulteInvalideException.class,
            () -> service.chargerFichier(CSV_DIFF_INVALIDE));
    }

    @Test
    @DisplayName("[chargerFichier] Difficulté '4' inconnue → message contient la valeur '4'")
    void chargerFichier_difficulteInconnue_messageContientValeurFautive() {
        IservicesQuestionnaire service = new MockChargerFichierDifficulteInvalide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        DifficulteInvalideException ex = assertThrows(DifficulteInvalideException.class,
            () -> service.chargerFichier(CSV_DIFF_INVALIDE));

        assertTrue(ex.getMessage().contains("4"),
            "Le message devrait contenir '4', reçu : " + ex.getMessage());
    }

    @Test
    @DisplayName("[chargerFichier] libelleQuestion vide → QuestionInvalideException")
    void chargerFichier_libelleQuestionVide_leveQuestionInvalideException() {
        IservicesQuestionnaire service = new MockChargerFichierQuestionInvalide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        assertThrows(QuestionInvalideException.class,
            () -> service.chargerFichier(CSV_QUEST_INVALIDE));
    }

    @Test
    @DisplayName("[chargerFichier] libelleQuestion vide → message 'Le libellé de la question est vide.'")
    void chargerFichier_libelleQuestionVide_messageErreurCorrect() {
        IservicesQuestionnaire service = new MockChargerFichierQuestionInvalide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        QuestionInvalideException ex = assertThrows(QuestionInvalideException.class,
            () -> service.chargerFichier(CSV_QUEST_INVALIDE));

        assertEquals("Le libellé de la question est vide.", ex.getMessage());
    }

    @Test
    @DisplayName("[chargerFichier] Toutes les lignes invalides → ListeQuestionsVideException")
    void chargerFichier_toutesLignesInvalides_leveListeQuestionsVideException() {
        IservicesQuestionnaire service = new MockChargerFichierListeVide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        assertThrows(ListeQuestionsVideException.class,
            () -> service.chargerFichier(CSV_TOUT_INVALIDE));
    }

    @Test
    @DisplayName("[chargerFichier] Toutes les lignes invalides → message 'La liste des questions est vide.'")
    void chargerFichier_toutesLignesInvalides_messageErreurCorrect() {
        IservicesQuestionnaire service = new MockChargerFichierListeVide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();

        ListeQuestionsVideException ex = assertThrows(ListeQuestionsVideException.class,
            () -> service.chargerFichier(CSV_TOUT_INVALIDE));

        assertEquals("La liste des questions est vide.", ex.getMessage());
    }


    // ===============================================================
    //  fournirUnQuestionnaire — CAS NOMINAUX
    // ===============================================================

    @Test
    @DisplayName("[fournirUnQuestionnaire] Questionnaire valide → aucune exception levée")
    void fournirUnQuestionnaire_questionnaireValide_aucuneException() {
        MockFournirQuestionnaireValide service = new MockFournirQuestionnaireValide();
        // ServiceQuestionnaire service = new ServiceQuestionnaire();
        QuestionnaireDTO questionnaire = new QuestionnaireDTO("Sport niv 1");

        assertDoesNotThrow(() -> service.fournirUnQuestionnaire(questionnaire));
    }

    @Test
    @DisplayName("[fournirUnQuestionnaire] Questionnaire valide → le bon objet est bien reçu")
    void fournirUnQuestionnaire_questionnaireValide_bonObjetRecu() {
        MockFournirQuestionnaireValide service = new MockFournirQuestionnaireValide();
        // ServiceQuestionnaire service = new ServiceQuestionnaire();
        QuestionnaireDTO questionnaire = new QuestionnaireDTO("Sport niv 1");

        service.fournirUnQuestionnaire(questionnaire);

        assertSame(questionnaire, service.getQuestionnaireFourni());
    }

    @Test
    @DisplayName("[fournirUnQuestionnaire] Questionnaire valide → libellé conservé après appel")
    void fournirUnQuestionnaire_questionnaireValide_libelleConserve() {
        MockFournirQuestionnaireValide service = new MockFournirQuestionnaireValide();
        // ServiceQuestionnaire service = new ServiceQuestionnaire();
        QuestionnaireDTO questionnaire = new QuestionnaireDTO("Football niv 1");

        service.fournirUnQuestionnaire(questionnaire);

        assertEquals("Football niv 1", service.getQuestionnaireFourni().getLibelleQuestionnaire());
    }

    @Test
    @DisplayName("[fournirUnQuestionnaire] Appelée exactement une fois → compteur = 1")
    void fournirUnQuestionnaire_appeleeExactementUneFois() {
        MockFournirQuestionnaireValide service = new MockFournirQuestionnaireValide();
        // ServiceQuestionnaire service = new ServiceQuestionnaire();
        QuestionnaireDTO questionnaire = new QuestionnaireDTO("Sport niv 1");

        service.fournirUnQuestionnaire(questionnaire);

        assertEquals(1, service.getNombreAppels());
    }

    @Test
    @DisplayName("[fournirUnQuestionnaire] Avant tout appel → compteur = 0 et questionnaire null")
    void fournirUnQuestionnaire_avantAppel_etatInitialCorrect() {
        MockFournirQuestionnaireValide service = new MockFournirQuestionnaireValide();
        // ServiceQuestionnaire service = new ServiceQuestionnaire();

        assertEquals(0, service.getNombreAppels());
        assertNull(service.getQuestionnaireFourni());
    }

    @Test
    @DisplayName("[fournirUnQuestionnaire] Appelée deux fois → compteur = 2")
    void fournirUnQuestionnaire_appeleeDeuxFois_compteurCorrect() {
        MockFournirQuestionnaireValide service = new MockFournirQuestionnaireValide();
        // ServiceQuestionnaire service = new ServiceQuestionnaire();

        service.fournirUnQuestionnaire(new QuestionnaireDTO("Sport niv 1"));
        service.fournirUnQuestionnaire(new QuestionnaireDTO("Célébrités niv 1"));

        assertEquals(2, service.getNombreAppels());
    }


    // ===============================================================
    //  fournirUnQuestionnaire — CAS D'ERREUR
    // ===============================================================

    @Test
    @DisplayName("[fournirUnQuestionnaire] Questionnaire sans questions → IllegalArgumentException")
    void fournirUnQuestionnaire_questionnaireVide_leveIllegalArgumentException() {
        IservicesQuestionnaire service = new MockFournirQuestionnaireVide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();
        QuestionnaireDTO questionnaireVide = new QuestionnaireDTO("Vide");

        assertThrows(IllegalArgumentException.class,
            () -> service.fournirUnQuestionnaire(questionnaireVide));
    }

    @Test
    @DisplayName("[fournirUnQuestionnaire] Questionnaire sans questions → message correct")
    void fournirUnQuestionnaire_questionnaireVide_messageErreurCorrect() {
        IservicesQuestionnaire service = new MockFournirQuestionnaireVide();
        // IservicesQuestionnaire service = new ServiceQuestionnaire();
        QuestionnaireDTO questionnaireVide = new QuestionnaireDTO("Vide");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> service.fournirUnQuestionnaire(questionnaireVide));

        assertEquals("Le questionnaire ne contient aucune question.", ex.getMessage());
    }
}
