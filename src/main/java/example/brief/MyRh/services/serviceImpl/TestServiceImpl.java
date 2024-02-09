package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.dtos.CandidateDTO;
import example.brief.MyRh.dtos.test.RequestRecord;
import example.brief.MyRh.dtos.test.ResponseRegisterTest;
import example.brief.MyRh.entities.Candidate;
import example.brief.MyRh.entities.HistoriqueTest;
import example.brief.MyRh.entities.Speciality;
import example.brief.MyRh.entities.Test;
import example.brief.MyRh.exceptions.exception.ExpiredAttempt;
import example.brief.MyRh.exceptions.exception.NotExist;
import example.brief.MyRh.mappers.CandidateMapper;
import example.brief.MyRh.repositories.CondidateRepository;
import example.brief.MyRh.repositories.HistoryRepository;
import example.brief.MyRh.repositories.SpecialityRepository;
import example.brief.MyRh.repositories.TestRepository;
import example.brief.MyRh.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
    private static int recordAvr = 8;

    @Override
    public ResponseRegisterTest registerToTest(CandidateDTO candidateDTO) {
        Candidate candidate = this.candidateMapper.toEntity(candidateDTO);
        candidate= this.condidateRepository.findByEmail(candidate.getEmail()).orElseThrow(() -> new NotExist("the candidate dont exist"));
        String titre = candidate.getTitre();
        Speciality speciality = this.specialityRepository.findByName(titre).orElseThrow(() -> new NotExist("the speciality dont exist"));
        List<LocalDate> Dates = this.historyRepository.findLast3RegistrationForCandidate(candidate.getId());
        long record = verificationLimitedRegistrationTest(Dates);
        if ( record <= 2){
            LocalDate date= LocalDate.now();
            HistoriqueTest historiqueTest =  HistoriqueTest.builder().candidate(candidate).speciality(speciality).date(date).build();
            this.historyRepository.save(historiqueTest);
        }else throw new ExpiredAttempt("You have expired the number of attempts allowed");


        return null;
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
            List<HistoriqueTest> historiqueTests = this.historyRepository.findAllByCandidateAndAndId(test.getCandidate(),test.getId());
            historiqueTests.stream().forEach(historiqueTest1 -> this.historyRepository.delete(historiqueTest1));
            result = true;
        }else throw new ExpiredAttempt("you didn't pass the test, next time");
        return result;
    }

    public long verificationLimitedRegistrationTest(List<LocalDate> listDates){
        AtomicBoolean result = new AtomicBoolean(false);
        int month = LocalDate.now().getDayOfMonth();
         AtomicInteger count = new AtomicInteger();
        return listDates.stream()
                .flatMap(date -> date.getDayOfMonth() == month ? Stream.of(date) : Stream.empty())
                .count();
    }

}
