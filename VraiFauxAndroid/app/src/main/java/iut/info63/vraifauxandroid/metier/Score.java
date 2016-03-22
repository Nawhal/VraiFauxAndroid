package iut.info63.vraifauxandroid.metier;

public class Score {

    private int rightAnswerNb;
    private int wrongAnswerNb;

    public Score()
    {
        rightAnswerNb = 0;
        wrongAnswerNb = 0;
    }

    public int getNbQuestionsAnswered()
    {
        return rightAnswerNb + wrongAnswerNb;
    }

    public int getRightAnswerNb()
    {
        return rightAnswerNb;
    }

    public int getWrongAnswerNb()
    {
        return  wrongAnswerNb;
    }

    public void addOneRightAnswer()
    {
        ++rightAnswerNb;
    }

    public void addOneWrongAnswer()
    {
        ++wrongAnswerNb;
    }
}
