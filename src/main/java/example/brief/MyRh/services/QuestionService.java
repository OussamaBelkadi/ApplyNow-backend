package example.brief.MyRh.services;

import example.brief.MyRh.dtos.QuestionDto;
import example.brief.MyRh.dtos.ResponseDto;
import example.brief.MyRh.entities.Question;

import java.util.List;

public interface QuestionService {

    QuestionDto create(QuestionDto questionDto);

    ResponseDto createResponse(ResponseDto responseDto);


    List<QuestionDto> fetchQuestionSpeciality(Long SpecialityID);
}