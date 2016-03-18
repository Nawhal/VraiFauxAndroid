package iut.info63.vraifauxandroid.metier;

import java.util.Random;

import iut.info63.vraifauxandroid.metier.database.DataBaseHelper;
import iut.info63.vraifauxandroid.metier.database.DatabaseQuestionAccessor;
import iut.info63.vraifauxandroid.metier.database.FakeQuestionAccessor;

/**
 * Created by aupilleves on 18/03/16.
 */
public class GameManager {

    int numberOfQuestion;
    Question question;

    public GameManager() {
    }

    public Question randomQuestion(DataBaseHelper dbh) {
        DatabaseQuestionAccessor dqg = new DatabaseQuestionAccessor(dbh);
        FakeQuestionAccessor fqa = new FakeQuestionAccessor();

        dqg.putAll(fqa.getAll());

        Random random = new Random();
        //question = dqg.getByIndex(0);
        numberOfQuestion = fqa.count();

        question = fqa.getByIndex(random.nextInt(numberOfQuestion));

        //pas sa responsabilité
        //dbh.close();

        return question;
    }
}
