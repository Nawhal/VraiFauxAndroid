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
        FakeQuestionAccessor fqa = new FakeQuestionAccessor();

        //faire avec DQG
        numberOfQuestion = fqa.count();

        dqg.putAll(fqa.getAll());
        Random random = new Random();

        //question = fqa.getByIndex(random.nextInt(numberOfQuestion));
        question = dqg.getByIndex(random.nextInt(numberOfQuestion));

        //pas sa responsabilité
        dbh.close();

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
