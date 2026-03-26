package universite_Paris8.iut.qdev.tp2026.gr11.services;

import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.commons.dtos.QuestionnaireDTO;
import universite_Paris8.iut.qdev.tp2026.gr11.enums.DifficulteEnum;
import universite_Paris8.iut.qdev.tp2026.gr11.interfaces.IservicesQuestionnaire;
import universite_Paris8.iut.qdev.tp2026.gr11.utils.exceptions.*;

import java.io.BufferedReader;       // Lit le fichier ligne par ligne
import java.io.FileNotFoundException; // Exception si le fichier n'existe pas
import java.io.FileReader;           // Ouvre le fichier
import java.io.IOException;          // Exception si erreur de lecture
import java.util.ArrayList;          // Implémentation concrète de la liste
import java.util.Collections;        // Pour mélanger aléatoirement la liste
import java.util.List;               // Type de la liste de questions

public class ServiceQuestionnaire implements IservicesQuestionnaire {

    // Nombre de questions sélectionnées pour une partie
    private static final int NB_QUESTIONS_PARTIE = 10;

    // Nombre de colonnes attendues dans chaque ligne du CSV
    private static final int NB_COLONNES = 9;

    /**
     * Convertit un niveau entier (1, 2, 3) vers l'enum DifficulteEnum correspondant.
     *
     * @param valeur    la valeur brute lue dans le CSV
     * @param numLigne  numéro de la ligne en cours (pour les messages d'erreur)
     * @return le DifficulteEnum correspondant au niveau
     * @throws DifficulteInvalideException si le niveau ne correspond à aucun enum connu
     * @throws LigneInvalideException      si la valeur n'est pas un entier
     */
    private DifficulteEnum parseDifficulte(String valeur, int numLigne)
            throws DifficulteInvalideException, LigneInvalideException {
        try {
            int niveau = Integer.parseInt(valeur.trim()); // Convertit la chaîne en entier
            for (DifficulteEnum d : DifficulteEnum.values()) { // Parcourt SIMPLE, INTERMEDIAIRE, DIFFICILE
                if (d.getNiveau() == niveau) return d;         // Retourne l'enum dont le niveau correspond
            }
            throw new DifficulteInvalideException(valeur.trim()); // Aucun enum trouvé pour ce niveau
        } catch (NumberFormatException e) {
            throw new LigneInvalideException(numLigne); // La valeur n'est pas un entier valide
        }
    }

    /**
     * Charge toutes les questions depuis un fichier CSV sans en-tête (séparateur ';').
     * Format de chaque ligne :
     * idQuestionnaire ; libelleQuestionnaire ; numQuestion ; langue ;
     * libelleQuestion ; reponse ; difficulte ; explication ; reference
     *
     * La difficulté est un entier : 1 = Simple, 2 = Intermédiaire, 3 = Difficile.
     *
     * @param cheminFichier chemin vers le fichier CSV
     * @return QuestionnaireDTO rempli avec toutes les questions du fichier
     * @throws FichierPasTrouveException   le fichier est introuvable
     * @throws FichierOuvertureException   le fichier ne peut pas être ouvert ou lu
     * @throws FichierVideException        le fichier est vide
     * @throws LigneInvalideException      une ligne ne contient pas 9 colonnes valides
     * @throws DifficulteInvalideException la difficulté ne correspond à aucun niveau connu
     * @throws QuestionInvalideException   le libellé ou la réponse est vide
     * @throws ListeQuestionsVideException aucune question valide n'a pu être chargée
     */
    @Override
    public QuestionnaireDTO chargerFichier(String cheminFichier)
            throws FichierPasTrouveException, FichierOuvertureException, FichierVideException,
            LigneInvalideException, DifficulteInvalideException, QuestionInvalideException,
            ListeQuestionsVideException {

        QuestionnaireDTO questionnaire = new QuestionnaireDTO("Quiz"); // Questionnaire qui recevra les questions

        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) { // Ouverture du fichier

            if (!reader.ready()) { // Vérifie que le fichier n'est pas vide
                throw new FichierVideException();
            }

            String ligne;      // Ligne courante lue dans le fichier
            int numLigne = 0;  // Compteur de lignes pour les messages d'erreur

            while ((ligne = reader.readLine()) != null) { // Lit ligne par ligne jusqu'à la fin
                numLigne++; // Incrémente le compteur à chaque ligne

                if (numLigne == 1) {
                    ligne = ligne.replace("\uFEFF", ""); // Supprime le BOM UTF-8 présent sur la première ligne
                }

                if (ligne.isBlank()) continue; // Ignore les lignes vides

                String[] colonnes = ligne.split(";", -1); // Découpe la ligne selon le séparateur ';'

                if (colonnes.length != NB_COLONNES) { // Vérifie qu'il y a exactement 9 colonnes
                    throw new LigneInvalideException(numLigne);
                }

                String libelleQuestion = colonnes[4].trim(); // Récupère le libellé de la question
                String reponse         = colonnes[5].trim(); // Récupère la réponse

                if (libelleQuestion.isBlank() || reponse.isBlank()) { // Vérifie que ces champs ne sont pas vides
                    throw new QuestionInvalideException("Champ obligatoire vide à la ligne " + numLigne);
                }

                DifficulteEnum difficulte = parseDifficulte(colonnes[6], numLigne); // Convertit la difficulté en enum

                QuestionDTO question = new QuestionDTO(
                        Integer.parseInt(colonnes[0].trim()),  // idQuestionnaire
                        colonnes[1].trim(),                    // libelleQuestionnaire
                        colonnes[3].trim(),                    // langue
                        Integer.parseInt(colonnes[2].trim()),  // numQuestion
                        libelleQuestion,                       // libelleQuestion
                        reponse,                               // reponse
                        difficulte,                            // difficulte
                        colonnes[7].trim(),                    // explication
                        colonnes[8].trim()                     // reference
                );

                questionnaire.ajouterQuestion(question); // Ajoute la question au questionnaire
            }

        } catch (FileNotFoundException e) {
            throw new FichierPasTrouveException();  // Le fichier n'existe pas au chemin donné
        } catch (IOException e) {
            throw new FichierOuvertureException();  // Erreur lors de la lecture du fichier
        }

        if (questionnaire.estVide()) { // Vérifie qu'au moins une question a été chargée
            throw new ListeQuestionsVideException();
        }

        return questionnaire; // Retourne le questionnaire rempli
    }

    /**
     * Sélectionne aléatoirement jusqu'à 10 questions dans le questionnaire et les affiche.
     *
     * @param questionnaire le questionnaire chargé
     */
    @Override
    public void fournirUnQuestionnaire(QuestionnaireDTO questionnaire) {
        List<QuestionDTO> questions = new ArrayList<>(questionnaire.getListeQuestions()); // Copie la liste pour ne pas modifier l'originale
        Collections.shuffle(questions); // Mélange aléatoirement les questions

        List<QuestionDTO> selection = questions.subList(0, Math.min(NB_QUESTIONS_PARTIE, questions.size())); // Prend les 10 premières

        for (int i = 0; i < selection.size(); i++) { // Parcourt les questions sélectionnées
            QuestionDTO q = selection.get(i);
            System.out.println("Question " + (i + 1) + " [" + q.getDifficulte().getLibelle() + "]"); // Affiche le numéro et la difficulté
            System.out.println("  " + q.getLibelleQuestion()); // Affiche la question
            System.out.println("  Réponse : " + q.getReponse()); // Affiche la réponse
            System.out.println(); // Ligne vide pour aérer l'affichage
        }
    }
}