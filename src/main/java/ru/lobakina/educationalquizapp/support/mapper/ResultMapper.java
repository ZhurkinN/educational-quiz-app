package ru.lobakina.educationalquizapp.support.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lobakina.educationalquizapp.model.question.Question;
import ru.lobakina.educationalquizapp.service.QuestionService;
import ru.lobakina.educationalquizapp.support.dto.EndTestDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ResultMapper {

    private final QuestionService questionService;

    public Map<Question, String> putResultsToMap(EndTestDTO dto) {
        Map<Question, String> results = new HashMap<>();
        if (Objects.isNull(dto.question1())) return results;
        results.put(questionService.getById(dto.question1()), dto.answer1());
        if (Objects.isNull(dto.question2())) return results;
        results.put(questionService.getById(dto.question2()), dto.answer2());
        if (Objects.isNull(dto.question3())) return results;
        results.put(questionService.getById(dto.question3()), dto.answer3());
        if (Objects.isNull(dto.question4())) return results;
        results.put(questionService.getById(dto.question4()), dto.answer4());
        if (Objects.isNull(dto.question5())) return results;
        results.put(questionService.getById(dto.question5()), dto.answer5());
        if (Objects.isNull(dto.question6())) return results;
        results.put(questionService.getById(dto.question6()), dto.answer6());
        if (Objects.isNull(dto.question7())) return results;
        results.put(questionService.getById(dto.question7()), dto.answer7());
        if (Objects.isNull(dto.question8())) return results;
        results.put(questionService.getById(dto.question8()), dto.answer8());
        if (Objects.isNull(dto.question9())) return results;
        results.put(questionService.getById(dto.question9()), dto.answer9());
        if (Objects.isNull(dto.question10())) return results;
        results.put(questionService.getById(dto.question10()), dto.answer10());
        if (Objects.isNull(dto.question11())) return results;
        results.put(questionService.getById(dto.question11()), dto.answer11());
        if (Objects.isNull(dto.question12())) return results;
        results.put(questionService.getById(dto.question12()), dto.answer12());
        if (Objects.isNull(dto.question13())) return results;
        results.put(questionService.getById(dto.question13()), dto.answer13());
        if (Objects.isNull(dto.question14())) return results;
        results.put(questionService.getById(dto.question14()), dto.answer14());
        if (Objects.isNull(dto.question15())) return results;
        results.put(questionService.getById(dto.question15()), dto.answer15());
        if (Objects.isNull(dto.question16())) return results;
        results.put(questionService.getById(dto.question16()), dto.answer16());
        if (Objects.isNull(dto.question17())) return results;
        results.put(questionService.getById(dto.question17()), dto.answer17());
        if (Objects.isNull(dto.question18())) return results;
        results.put(questionService.getById(dto.question18()), dto.answer18());
        if (Objects.isNull(dto.question19())) return results;
        results.put(questionService.getById(dto.question19()), dto.answer19());
        if (Objects.isNull(dto.question20())) return results;
        results.put(questionService.getById(dto.question20()), dto.answer20());
        if (Objects.isNull(dto.question21())) return results;
        results.put(questionService.getById(dto.question21()), dto.answer21());
        if (Objects.isNull(dto.question22())) return results;
        results.put(questionService.getById(dto.question22()), dto.answer22());
        if (Objects.isNull(dto.question23())) return results;
        results.put(questionService.getById(dto.question23()), dto.answer23());
        if (Objects.isNull(dto.question24())) return results;
        results.put(questionService.getById(dto.question24()), dto.answer24());
        if (Objects.isNull(dto.question25())) return results;
        results.put(questionService.getById(dto.question25()), dto.answer25());
        if (Objects.isNull(dto.question26())) return results;
        results.put(questionService.getById(dto.question26()), dto.answer26());
        if (Objects.isNull(dto.question27())) return results;
        results.put(questionService.getById(dto.question27()), dto.answer27());
        if (Objects.isNull(dto.question28())) return results;
        results.put(questionService.getById(dto.question28()), dto.answer28());
        if (Objects.isNull(dto.question29())) return results;
        results.put(questionService.getById(dto.question29()), dto.answer29());
        if (Objects.isNull(dto.question30())) return results;
        results.put(questionService.getById(dto.question30()), dto.answer30());
        if (Objects.isNull(dto.question31())) return results;
        results.put(questionService.getById(dto.question31()), dto.answer31());
        if (Objects.isNull(dto.question32())) return results;
        results.put(questionService.getById(dto.question32()), dto.answer32());
        if (Objects.isNull(dto.question33())) return results;
        results.put(questionService.getById(dto.question33()), dto.answer33());
        if (Objects.isNull(dto.question34())) return results;
        results.put(questionService.getById(dto.question34()), dto.answer34());
        if (Objects.isNull(dto.question35())) return results;
        results.put(questionService.getById(dto.question35()), dto.answer35());
        if (Objects.isNull(dto.question36())) return results;
        results.put(questionService.getById(dto.question36()), dto.answer36());
        if (Objects.isNull(dto.question37())) return results;
        results.put(questionService.getById(dto.question37()), dto.answer37());
        if (Objects.isNull(dto.question38())) return results;
        results.put(questionService.getById(dto.question38()), dto.answer38());
        if (Objects.isNull(dto.question39())) return results;
        results.put(questionService.getById(dto.question39()), dto.answer39());
        if (Objects.isNull(dto.question40())) return results;
        results.put(questionService.getById(dto.question40()), dto.answer40());
        if (Objects.isNull(dto.question41())) return results;
        results.put(questionService.getById(dto.question41()), dto.answer41());
        if (Objects.isNull(dto.question42())) return results;
        results.put(questionService.getById(dto.question42()), dto.answer42());
        if (Objects.isNull(dto.question43())) return results;
        results.put(questionService.getById(dto.question43()), dto.answer43());
        if (Objects.isNull(dto.question44())) return results;
        results.put(questionService.getById(dto.question44()), dto.answer44());
        if (Objects.isNull(dto.question45())) return results;
        results.put(questionService.getById(dto.question45()), dto.answer45());
        if (Objects.isNull(dto.question46())) return results;
        results.put(questionService.getById(dto.question46()), dto.answer46());
        if (Objects.isNull(dto.question47())) return results;
        results.put(questionService.getById(dto.question47()), dto.answer47());
        if (Objects.isNull(dto.question48())) return results;
        results.put(questionService.getById(dto.question48()), dto.answer48());
        if (Objects.isNull(dto.question49())) return results;
        results.put(questionService.getById(dto.question49()), dto.answer49());
        if (Objects.isNull(dto.question50())) return results;
        results.put(questionService.getById(dto.question50()), dto.answer50());

        return results;
    }

}
