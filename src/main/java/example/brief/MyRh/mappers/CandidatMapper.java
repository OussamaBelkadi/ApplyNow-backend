package example.brief.MyRh.mappers;

import example.brief.MyRh.dtos.CandiatDto;
import example.brief.MyRh.entities.Candidat;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidatMapper {

    CandidatMapper Instance = Mappers.getMapper(CandidatMapper.class);

    CandiatDto toDto(Candidat candidat);
    Candidat toEntity(CandiatDto candiatDto);

}
