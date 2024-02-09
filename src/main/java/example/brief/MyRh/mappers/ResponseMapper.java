package example.brief.MyRh.mappers;

import example.brief.MyRh.dtos.ResponseDto;
import example.brief.MyRh.entities.Response;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseMapper {

    ResponseMapper RMC = Mappers.getMapper(ResponseMapper.class);

    @Mapping(source = "question.id",target = "questionId")
    ResponseDto toDto(Response response);

    @Mapping(source = "questionId",target = "question.id")
    Response toEntity(ResponseDto responseDto);

}