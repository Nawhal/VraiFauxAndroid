package iut.info63.vraifauxandroid.metier;

import android.view.View;

import java.util.Random;

import iut.info63.vraifauxandroid.R;
import iut.info63.vraifauxandroid.metier.database.DataBaseHelper;
import iut.info63.vraifauxandroid.metier.database.DatabaseQuestionAccessor;
import iut.info63.vraifauxandroid.metier.database.FakeQuestionAccessor;

/**
 * Created by aupilleves on 18/03/16.
 */
public class GameManager extends IGameManager{

    int numberOfQuestion, compteurGoodAnswer = 0, compteurBadAnswer = 0;
    Question question;

    public GameManager() {
    }

    public int getCompteurGoodAnswer() {
        return compteurGoodAnswer;
    }

    public int getCompteurBadAnswer() {
        return compteurBadAnswer;
    }

    public void setCompteurGoodAnswer(int value) {
        this.compteurGoodAnswer = value;
    }

    public void setCompteurBadAnswer(int value) {
        this.compteurBadAnswer = value;
    }

    public Question randomQuestion(DataBaseHelper dbh) {
        DatabaseQuestionAccessor dqg = new DatabaseQuestionAccessor(dbh);

        numberOfQuestion = dqg.count();
        Random random = new Random();

        question = dqg.getByIndex(random.nextInt(numberOfQuestion));

<<<<<<< HEAD
        //pas sa responsabilité
        dbh.close();

=======
>>>>>>> 7473776466ace91f28443d4dc25c838f801e3d1b
        return question;
    }

    public boolean verifyAnswer(boolean answer) {
        if(answer == question.getAnswer()) {
            compteurGoodAnswer++;
            return true;
        }

        compteurBadAnswer++;
        return false;
    }
}
