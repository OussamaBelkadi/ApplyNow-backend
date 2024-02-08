package example.brief.MyRh.controller;

import example.brief.MyRh.dtos.QuestionDto;
import example.brief.MyRh.services.serviceImpl.QuestionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("speciality")
public class SpecialityController {

    private final QuestionServiceImp questionServiceImp;

    @Autowired
    public SpecialityController(QuestionServiceImp questionServiceImp) {
        this.questionServiceImp = questionServiceImp;
    }


    @GetMapping("/test/{speciality}")
    public ResponseEntity fetchQuestion(@PathVariable Long speciality) {


        List<QuestionDto> questionDtoList =  questionServiceImp.fetchQuestionSpeciality(speciality);
        return ResponseEntity.ok(questionDtoList);

    }

}
