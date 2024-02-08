package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.dtos.CandidateDTO;
import example.brief.MyRh.dtos.test.ResponseRegisterTest;
import example.brief.MyRh.entities.Candidate;
import example.brief.MyRh.entities.Speciality;
import example.brief.MyRh.entities.Test;
import example.brief.MyRh.exceptions.exception.NotExist;
import example.brief.MyRh.mappers.CandidateMapper;
import example.brief.MyRh.repositories.CondidateRepository;
import example.brief.MyRh.repositories.SpecialityRepository;
import example.brief.MyRh.repositories.TestRepository;
import example.brief.MyRh.services.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final CandidateMapper candidateMapper = CandidateMapper.INSTANCE;
    private final CondidateRepository condidateRepository;
    private final SpecialityRepository specialityRepository;

    @Override
    public ResponseRegisterTest registerToTest(CandidateDTO candidateDTO) {
        Candidate candidate = this.candidateMapper.toEntity(candidateDTO);
        candidate= this.condidateRepository.findByEmail(candidate.getEmail()).orElseThrow(() -> new NotExist("the candidate dont exist"));
        String titre = candidate.getTitre();
        Speciality speciality = this.specialityRepository.findByName(titre).orElseThrow(() -> new NotExist("the speciality dont exist"));
        List<LocalDate> Dates = this.testRepository.findLast3RegistrationForCandidate(candidate.getId());
        for (LocalDate date: Dates){
            System.out.println( date + "  " + date.getDayOfMonth());
        }
        LocalDate date= LocalDate.now();
        Test test = new Test("os",date, candidate, speciality);
        this.testRepository.save(test);
        return null;
    }

}
