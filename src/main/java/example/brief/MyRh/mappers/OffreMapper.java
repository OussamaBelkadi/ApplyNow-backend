package example.brief.MyRh.mappers;

import example.brief.MyRh.entities.Offre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import example.brief.MyRh.dtos.OffreDTO;

import java.util.List;

@Mapper
public interface OffreMapper {
    OffreMapper INSTANCE = Mappers.getMapper(OffreMapper.class);

    Offre toEntity(OffreDTO offreDTO);

    OffreDTO toDTO(Offre offre);
    List<OffreDTO> toDtoList(List<Offre> offers);

}
