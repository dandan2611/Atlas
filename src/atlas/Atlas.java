package atlas;

import atlas.agencies.Agence;
import atlas.agencies.DGSE;

public class Atlas {

    /*

    Je tiens à remercier Osaris, déjà pour avoir fait Atlas, et également pour m'avoir appris tous ce que je sais actuellement de la programmation
    J'ai donc décidé de l'aider à mon tour en reprenant l'Atlas et en le modifiant de mon mieux pour l'optimiser

    N'oubliez surtout pas que c'est Osaris le créateur de ce contenu, et que je l'ai modifié, tous les droits lui reviennent !

    Bonne journée à vous.
    - dandan2611

     */

    /**
     * Mettre en place les bases d'Atlas
     */
    static {
        Agence.agences.add(new atlas.agencies.Atlas());
        Agence.agences.add(new DGSE());
    }

}
