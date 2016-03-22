package iut.info63.vraifauxandroid.metier;

import java.io.Serializable;

import iut.info63.vraifauxandroid.metier.database.DataBaseHelper;

public abstract class IGameManager implements Serializable{

    public abstract int getCompteurGoodAnswer();

    public abstract int getCompteurBadAnswer();

    public abstract void setCompteurGoodAnswer(int value);

    public abstract void setCompteurBadAnswer(int value);

    public abstract void randomQuestion(DataBaseHelper dbh);

    public abstract String getQuestionCourante();

    public abstract Boolean getReponseCourante();

    public abstract boolean verifyAnswer(boolean answer);

}
