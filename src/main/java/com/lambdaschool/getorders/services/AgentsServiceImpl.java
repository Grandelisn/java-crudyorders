package com.lambdaschool.getorders.services;

import com.lambdaschool.getorders.models.Agent;
import com.lambdaschool.getorders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

//Implements the AgentsService Interface
@Transactional
@Service(value = "agentsService")
public class AgentsServiceImpl implements AgentsService {

    //Connects this service to the agents table
    @Autowired
    private AgentsRepository agentsrepos;

    @Override
    public Agent findAgentById(long id)
        throws EntityNotFoundException{
        return agentsrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Agent Id " + id + " Not Found"));
    }

}
