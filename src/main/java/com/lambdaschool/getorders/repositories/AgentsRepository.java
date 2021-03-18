package com.lambdaschool.getorders.repositories;

import com.lambdaschool.getorders.models.Agent;
import org.springframework.data.repository.CrudRepository;

//The CRUD Repository connecting Agent to the rest of the application
public interface AgentsRepository extends CrudRepository<Agent, Long>
{
    Agent findAgentByAgentname(String name);
}
