package ru.lobakina.educationalquizapp.support.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.lobakina.educationalquizapp.model.test.TestGroups;
import ru.lobakina.educationalquizapp.model.test.TestStudents;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AverageScoreCalculator {

    public static double getAverageGroupScore(TestGroups testGroups) {
        Set<TestStudents> testStudentsSet = testGroups.getTestStudents();
        double scoreAccum = 0;
        int studAccum = 0;
        for (TestStudents testStudents : testStudentsSet) {
            if (testStudents.getIsDone()) {
                scoreAccum += testStudents.getScore();
                studAccum++;
            }

        }
        if (studAccum == 0) {
            return 0;
        }
        return scoreAccum / studAccum;
    }
}
