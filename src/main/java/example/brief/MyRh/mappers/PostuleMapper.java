package example.brief.MyRh.mappers;

import example.brief.MyRh.entities.Postule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import example.brief.MyRh.dtos.PostuleDto;

@Mapper
public interface PostuleMapper {
    PostuleMapper INSTANCE = Mappers.getMapper(PostuleMapper.class);

    Postule toEntity(PostuleDto postuleDto);

    PostuleDto toDto(Postule postule);
}
