package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.dtos.CandidateDTO;
import example.brief.MyRh.dtos.QuestionDto;
import example.brief.MyRh.dtos.test.RequestRecord;
import example.brief.MyRh.dtos.test.ResponseRegisterTest;
import example.brief.MyRh.dtos.test.TestRegisterDto;
import example.brief.MyRh.entities.*;
import example.brief.MyRh.exceptions.exception.ExpiredAttempt;
import example.brief.MyRh.exceptions.exception.NotExist;
import example.brief.MyRh.mappers.CandidateMapper;
import example.brief.MyRh.mappers.QuestionMapper;
import example.brief.MyRh.repositories.*;
import example.brief.MyRh.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final CandidateMapper candidateMapper = CandidateMapper.INSTANCE;
    private final CondidateRepository condidateRepository;
    private final SpecialityRepository specialityRepository;
    private final HistoryRepository historyRepository;
    private final QuestionRepository questionRepository;
    private static int recordAvr = 8;

    @Override
    public TestRegisterDto registerToTest(CandidateDTO candidateDTO) {

        TestRegisterDto registerTest = new TestRegisterDto();
        List<QuestionDto> questionDtos = new ArrayList<>();
        Candidate candidate = new Candidate();
        Optional<Candidate> candidate1 = this.condidateRepository.findByEmail(candidateDTO.getEmail());
        if(candidate1.isPresent()){
            System.out.println(candidate1.get().getEmail());
            System.out.println(candidate1.get().getFullname());
        }else{
            throw new NotExist("no candidat with this email");
        }
        String titre = candidate1.get().getTitre();
        Speciality speciality = this.specialityRepository.findByName(titre).orElseThrow(() -> new NotExist("the speciality dont exist"));
        List<LocalDate> Dates = this.historyRepository.findLast3RegistrationForCandidate(candidate1.get().getId(), speciality.getId());
        long record = verificationLimitedRegistrationTest(Dates);
        if ( record <= 2){

            LocalDate date= LocalDate.now();
            HistoriqueTest historiqueTest = HistoriqueTest.builder().candidate(candidate1.get()).speciality(speciality).date(date).score(0).build();
            HistoriqueTest historiqueTest1 = this.historyRepository.save(historiqueTest);

            registerTest.setQuestionList(fetchQuestionSpeciality(speciality.getId()));
            registerTest.setCandidateId(candidate1.get().getId());
            registerTest.setTestId(historiqueTest1.getId());
        }else throw new ExpiredAttempt("You have expired the number of attempts allowed");

        return registerTest;

    }

    @Override
    public boolean recordTest(RequestRecord requestRecord) {

        boolean result = false;
        HistoriqueTest historiqueTest = this.historyRepository.findLastRegistrationForCandidate(requestRecord.getTestId()).orElseThrow(()-> new NotExist("the test don't exit"));
        historiqueTest.setScore(requestRecord.getScore());
        this.historyRepository.save(historiqueTest);
        System.out.println(historiqueTest.getScore() );
        System.out.println( recordAvr );
        if (historiqueTest.getScore() >= recordAvr) {
            Test test = new Test("os", LocalDate.now(), historiqueTest.getCandidate(), historiqueTest.getSpeciality());
            this.testRepository.save(test);
            List<HistoriqueTest> historiqueTests = this.historyRepository.findAllByCandidate(test.getCandidate());
            historiqueTests.stream().forEach(historiqueTest1 -> this.historyRepository.delete(historiqueTest1));
            result = true;
        }else throw new ExpiredAttempt("you didn't pass the test, next time");
        return result;

    }

    @Override
    public List<QuestionDto> fetchQuestionSpeciality(Long specialityID) {
        Speciality speciality = Speciality.builder().id(specialityID).build();
        List<QuestionDto> questionDtos = new ArrayList<>();
        List<Question> questions =  questionRepository.findAllBySpec(speciality);

        for (Question Q: questions ){
            QuestionDto questionDto = QuestionMapper.QMC.toDto(Q);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    public long verificationLimitedRegistrationTest(List<LocalDate> listDates){
        int month = LocalDate.now().getDayOfMonth();
        return listDates.stream()
                .flatMap(date -> date.getDayOfMonth() == month ? Stream.of(date) : Stream.empty())
                .count();
    }


}
