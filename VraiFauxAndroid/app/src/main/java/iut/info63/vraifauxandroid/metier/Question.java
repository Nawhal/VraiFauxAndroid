package iut.info63.vraifauxandroid.metier;

import java.io.Serializable;

public class Question implements Serializable{

    private String question;
    private boolean answer;

    public Question(String question, boolean answer)
    {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion()
    {
        return question;
    }

    public boolean getAnswer()
    {
        return answer;
    }

    @Override
    public String toString() {
        return question;
    }
}
