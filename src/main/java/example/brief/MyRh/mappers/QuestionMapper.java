package example.brief.MyRh.mappers;

import example.brief.MyRh.dtos.QuestionDto;
import example.brief.MyRh.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionMapper {

    QuestionMapper QMC = Mappers.getMapper(QuestionMapper.class);

    @Mapping(source = "qst",target = "qst")
    @Mapping(source = "specialityID",target = "speciality.id")
    Question toEntity(QuestionDto questionDto);

    @Mapping(source = "qst",target = "qst")
    @Mapping(source = "speciality.id",target = "specialityID")
    @Mapping(source = "id",target = "id")
    @Mapping(source = "responseList",target = "responseDtoList")
    QuestionDto toDto(Question question);

}
