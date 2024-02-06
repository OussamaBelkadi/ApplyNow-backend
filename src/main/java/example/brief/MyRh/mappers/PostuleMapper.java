package example.brief.MyRh.mappers;

import example.brief.MyRh.entities.Postule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import example.brief.MyRh.dtos.PostuleDto;

@Mapper
public interface PostuleMapper {
    PostuleMapper INSTANCE = Mappers.getMapper(PostuleMapper.class);


    @Mapping(target = "candidate",source = "candidateDTO")
    Postule toEntity(PostuleDto postuleDto);


    @Mapping(target = "candidateDTO",source = "candidate")
    PostuleDto toDto(Postule postule);
}
