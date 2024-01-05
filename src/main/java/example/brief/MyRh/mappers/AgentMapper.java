package example.brief.MyRh.mappers;

import example.brief.MyRh.entities.Agent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import example.brief.MyRh.dtos.AgentDTO;

@Mapper
public interface AgentMapper {
    AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);

    Agent toEntity(AgentDTO agentDTO);

    AgentDTO toDto(Agent agent);
}
