package iut.info63.vraifauxandroid.metier;

import java.util.Random;

import iut.info63.vraifauxandroid.metier.database.DataBaseHelper;
import iut.info63.vraifauxandroid.metier.database.DatabaseQuestionAccessor;

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

    public void randomQuestion(DataBaseHelper dbh) {
        DatabaseQuestionAccessor dqg = new DatabaseQuestionAccessor(dbh);

        numberOfQuestion = dqg.count();
        Random random = new Random();

        question = dqg.getByIndex(random.nextInt(numberOfQuestion));
    }

    public String getQuestionCourante() {
        return question.getQuestion();
    }

    public Boolean getReponseCourante() {
        return question.getAnswer();
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