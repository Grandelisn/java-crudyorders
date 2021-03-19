package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agent;
import com.lambdaschool.crudyorders.repositories.AgentsRepository;
import com.lambdaschool.crudyorders.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

//Implements the AgentsService Interface
@Transactional
@Service(value = "agentsService")
public class AgentsServiceImpl implements AgentsService {

    //Connects this service to the agents table
    @Autowired
    private AgentsRepository agentsrepos;

    //Connects this service to the customers table
    @Autowired
    private CustomersRepository custrepos;

    @Override
    public Agent findAgentById(long id)
        throws EntityNotFoundException{
        return agentsrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Agent Id " + id + " Not Found"));
    }

    @Transactional
    @Override
    public void deleteUnassigned(long agentid){
        if(agentsrepos.findById(agentid).isPresent()){
            if(custrepos.findFirstByAgent_Agentcode(agentid) == null){
                agentsrepos.deleteById(agentid);
            }else{
                throw new EntityExistsException("Found A Customer For Agent " + agentid);
            }
        }else{
            throw new EntityNotFoundException("Agent Id " + agentid + " Not Found");
        }
    }
}
