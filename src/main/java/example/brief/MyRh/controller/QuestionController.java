package example.brief.MyRh.controller;

import example.brief.MyRh.dtos.QuestionDto;
import example.brief.MyRh.dtos.ResponseDto;
import example.brief.MyRh.services.QuestionService;
import example.brief.MyRh.services.serviceImpl.QuestionServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("question")
public class QuestionController {

    private final QuestionServiceImp questionServiceImp;

    public QuestionController(QuestionServiceImp questionServiceImp) {
        this.questionServiceImp = questionServiceImp;
    }

    @PostMapping("/add")
    public ResponseEntity createQuestions(@RequestBody QuestionDto questionDto){

        QuestionDto questionDto1 =  questionServiceImp.create(questionDto);
        return ResponseEntity.ok(questionDto1);

    }

    @PostMapping("/response")
    public ResponseEntity createRessponse(@RequestBody ResponseDto responseDto){

        ResponseDto responseDto1 = questionServiceImp.createResponse(responseDto);
        return ResponseEntity.ok(responseDto1);

    }





}
