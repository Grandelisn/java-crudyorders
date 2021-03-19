package com.lambdaschool.crudyorders.repositories;

import com.lambdaschool.crudyorders.models.Agent;
import org.springframework.data.repository.CrudRepository;

//The CRUD Repository connecting Agent to the rest of the application
public interface AgentsRepository extends CrudRepository<Agent, Long>
{
    Agent findAgentByAgentname(String name);
}
