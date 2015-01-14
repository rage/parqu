package rage.parqu.questioncreator;

import java.util.HashMap;
import rage.parqu.domain.Question;
import rage.parqu.util.Operator;
import static rage.parqu.util.Randomizer.randomOperator;
import static rage.parqu.util.Randomizer.randomSmallPositiveInteger;

public class SimpleCalculationQuestionCreator extends QuestionCreator{
    
    private int first;
    private int second;
    private Operator operator;

    public SimpleCalculationQuestionCreator() {
        super.setTemplateName("simpleprint.mustache");
    }

    @Override
    protected void randomizeParameters() {
        first = randomSmallPositiveInteger();
        second = randomSmallPositiveInteger();
        if(first == second){
            randomizeParameters();
        }
        operator = randomOperator();
    }

    @Override
    protected HashMap<String, Object> setUpScope() {
        HashMap<String, Object> scopes = new HashMap<>();
        scopes.put("operator", operator.toString());
        scopes.put("first", first);
        scopes.put("second", second);
        
        return scopes;    
    }

    @Override
    protected Question setUpQuestionAndAnswers() {
        Question question = new Question();
        question.setQuestionText("Mikä numero tulostetaan?");
        question.setAnswers("" + (first + second), "" + (first - second), "" + (second - first), "" + first, "" + second);
        return question;       
    }

    @Override
    protected String determineRightAnswer() {
        if(operator == Operator.PLUS){
            return "" + (first + second);
        } else if (operator == Operator.MINUS){
            return "" + (first - second);
        } else {
            return "töttöröö";
        }    
    }

}