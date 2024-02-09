package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.dtos.QuestionDto;
import example.brief.MyRh.dtos.ResponseDto;
import example.brief.MyRh.entities.Question;
import example.brief.MyRh.entities.Response;
import example.brief.MyRh.entities.Speciality;
import example.brief.MyRh.mappers.QuestionMapper;
import example.brief.MyRh.mappers.ResponseMapper;
import example.brief.MyRh.repositories.QuestionRepository;
import example.brief.MyRh.repositories.ResponseRepository;
import example.brief.MyRh.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionServiceImp implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ResponseRepository responseRepository;


    @Autowired
    public QuestionServiceImp(QuestionRepository questionRepository, ResponseRepository responseRepository) {
        this.questionRepository = questionRepository;
        this.responseRepository = responseRepository;
    }

    @Override
    public QuestionDto create(QuestionDto questionDto) {

        Question question = QuestionMapper.QMC.toEntity(questionDto);
        Question question1 = questionRepository.save(question);
        QuestionDto questionDto1 = QuestionMapper.QMC.toDto(question1);
        return questionDto1;

    }

    @Override
    public ResponseDto createResponse(ResponseDto responseDto) {



        Response response = ResponseMapper.RMC.toEntity(responseDto);
        Response response1 = responseRepository.save(response);
        ResponseDto responseDto1 = ResponseMapper.RMC.toDto(response1);
        return responseDto1;


    }

    @Override
    public List<QuestionDto> fetchQuestionSpeciality(Long SpecialityID) {
        Speciality speciality = new Speciality();
        speciality.setId(SpecialityID);
        List<QuestionDto> questionDtos = new ArrayList<>();
        List<Question> questions =  questionRepository.findAllBySpec(speciality);

        for (Question Q: questions ){
            QuestionDto questionDto = QuestionMapper.QMC.toDto(Q);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }



}