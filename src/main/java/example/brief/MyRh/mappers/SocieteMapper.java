package example.brief.MyRh.mappers;

import example.brief.MyRh.entities.Societe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import example.brief.MyRh.dtos.SocieteDTO;

@Mapper
public interface SocieteMapper {
    SocieteMapper INSTANCE = Mappers.getMapper(SocieteMapper.class);

    @Mapping(source ="phone" ,target ="phone" )
    Societe toEntity(SocieteDTO societeDTO);

    @Mapping(source ="phone" ,target ="phone" )
    SocieteDTO toDto(Societe societe);

}
