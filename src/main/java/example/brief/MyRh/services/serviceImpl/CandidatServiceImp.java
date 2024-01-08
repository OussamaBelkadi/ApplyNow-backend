package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.dtos.CandiatDto;
import example.brief.MyRh.entities.Candidat;
import example.brief.MyRh.mappers.CandidatMapper;
import example.brief.MyRh.repositories.CandidatRepository;
import example.brief.MyRh.services.CandidatService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class CandidatServiceImp implements CandidatService {

    private final CandidatRepository candidatRepository;

    public CandidatServiceImp(CandidatRepository candidatRepository) {

        this.candidatRepository = candidatRepository;
    }


    @Override
    public CandiatDto registerCandiat(CandiatDto candiatDto) {
        String hashPassword = BCrypt.hashpw(candiatDto.getPassword(), BCrypt.gensalt());
        candiatDto.setPassword(hashPassword);
        Candidat candidat = CandidatMapper.Instance.toEntity(candiatDto);

        Candidat candidat1 = candidatRepository.save(candidat);
        return CandidatMapper.Instance.toDto(candidat1);

    }


}
