package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.Enum.ConnectedStatus;
import example.brief.MyRh.dtos.CandidateDTO;
import example.brief.MyRh.entities.Candidate;
import example.brief.MyRh.exceptions.exception.LoginSocieteException;
import example.brief.MyRh.exceptions.exception.NotExist;
import example.brief.MyRh.mappers.CandidateMapper;
import example.brief.MyRh.repositories.CondidateRepository;
import example.brief.MyRh.services.CandidateService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CondidateServiceImpl implements CandidateService {
    private CondidateRepository condidateRepository;
    private CandidateMapper candidateMapper;
    @Autowired
    public CondidateServiceImpl(CondidateRepository condidatRepository) {
        this.condidateRepository = condidatRepository;
        this.candidateMapper = CandidateMapper.INSTANCE;
    }

    @Override
    public Boolean registration(CandidateDTO condidatDTO) {
        boolean result = false;
        Candidate condidate = candidateMapper.toEntity(condidatDTO);
        String hashPassword = BCrypt.hashpw(condidate.getPassword(), BCrypt.gensalt());
        if (!hashPassword.isEmpty()) {
            condidate.setPassword(hashPassword);
            condidateRepository.save(condidate);
            result = true;
        }
        return result;
    }

    @Override
    public CandidateDTO login(CandidateDTO  candidateDTO) {
        Candidate candidate = candidateMapper.toEntity(candidateDTO);
        Candidate candidateOpt = condidateRepository.findByEmail(candidate.getEmail()).orElseThrow(()->new NotExist("this condidate don't exist!"));
        if(BCrypt.checkpw(candidate.getPassword(), candidateOpt.getPassword())){
            candidateOpt.setConnected(ConnectedStatus.CONNECTED);
            candidateDTO = candidateMapper.toDTO(candidateOpt);
            return candidateDTO;
        }else{
            throw  new LoginSocieteException("the password is not correct");
        }
    }
}
