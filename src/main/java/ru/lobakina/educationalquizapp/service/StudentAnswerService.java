package ru.lobakina.educationalquizapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.model.test.StudentAnswer;
import ru.lobakina.educationalquizapp.model.test.TestStudents;
import ru.lobakina.educationalquizapp.repository.StudentAnswerRepository;
import ru.lobakina.educationalquizapp.repository.test.TestStudentsRepository;

import java.sql.Date;
import java.util.Map;

import static ru.lobakina.educationalquizapp.support.helper.RightAnswerHandler.getRightQuestionAnswer;

@Service
@RequiredArgsConstructor
public class StudentAnswerService {

    private final StudentAnswerRepository studentAnswerRepository;
    private final TestStudentsRepository testStudentsRepository;

    @Transactional
    public void handleResults(TestStudents testStudents,
                              Map<Question, String> results) {

        int score = 0;
        for (Map.Entry<Question, String> entry : results.entrySet()) {
            StudentAnswer studentAnswer = handleSingleAnswer(entry.getKey(), entry.getValue(), testStudents);
            if (studentAnswer.getIsRight()) {
                score++;
            }
        }
        testStudents.setTestDate(new Date(System.currentTimeMillis()));
        testStudents.setIsDone(true);
        testStudents.setScore(score);
        testStudentsRepository.save(testStudents);

    }

    private StudentAnswer handleSingleAnswer(Question question,
                                             String answer,
                                             TestStudents testStudents) {
        String rightAnswer = getRightQuestionAnswer(question).toLowerCase().trim();
        Boolean isRight = rightAnswer.equals(answer.toLowerCase().trim());
        StudentAnswer studentAnswer = new StudentAnswer()
                .setQuestion(question)
                .setAnswer(answer)
                .setIsRight(isRight)
                .setTest(testStudents);
        return studentAnswerRepository.save(studentAnswer);
    }
}
