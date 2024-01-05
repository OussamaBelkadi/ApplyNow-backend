package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.repositories.AgentRepository;
import example.brief.MyRh.services.AgentService;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements AgentService {
    private AgentRepository agentRepository;

    public AgentServiceImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }


}
