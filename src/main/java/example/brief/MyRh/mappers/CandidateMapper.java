package example.brief.MyRh.mappers;

import example.brief.MyRh.dtos.CandidateDTO;
import example.brief.MyRh.entities.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidateMapper {
    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);
    Candidate toEntity(CandidateDTO condidatDTO);
    CandidateDTO toDTO(Candidate condidate);
}
