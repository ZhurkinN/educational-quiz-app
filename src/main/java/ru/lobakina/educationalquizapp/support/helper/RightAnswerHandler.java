package ru.lobakina.educationalquizapp.support.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.lobakina.educationalquizapp.model.question.Answer;
import ru.lobakina.educationalquizapp.model.question.Question;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RightAnswerHandler {

    public static String getRightQuestionAnswer(Question question) {
        return question.getQuestionAnswers()
                .stream()
                .filter(Answer::getIsRight)
                .findFirst()
                .orElseThrow()
                .getText();
    }
}
