/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.List;
import java.util.Map;
import model.Choice;
import model.Question;

/**
 *
 * @author Viet
 */
public class GradeUtils {
    public static int grade(List<Question> questionList, Map<Question, Integer> userAnswer) {
        int grade = 0;
        for (Question q : questionList) {
            int answerID = userAnswer.get(q);
            for (Choice c : q.getChoices()) {
                if (c.isIsTrueAnswer() == true && c.getChoiceID() == answerID) {
                    grade++;
                    break;
                }
            }
        }
        return grade;
    }
}
