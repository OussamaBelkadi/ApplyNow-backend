package example.brief.MyRh.mappers;

import example.brief.MyRh.entities.Societe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import example.brief.MyRh.dtos.SocieteDTO;

@Mapper
public interface SocieteMapper {
    SocieteMapper INSTANCE = Mappers.getMapper(SocieteMapper.class);

    Societe toEntity(SocieteDTO societeDTO);

    SocieteDTO toDto(Societe societe);

}
