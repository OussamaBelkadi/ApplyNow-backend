package example.brief.MyRh.services;

import example.brief.MyRh.dtos.CandidateDTO;

public interface CandidateService {
    Boolean registration(CandidateDTO condidatDTO);
    CandidateDTO login(CandidateDTO condidatDTO);

}
