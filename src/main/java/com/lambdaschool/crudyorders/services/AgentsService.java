package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agent;

//The service that works with the Agent model
public interface AgentsService {

    /*
        Returns the Agent with the given primary key
        @param id The primary key (long) of the agent you seek
        @return The given agent or throws an exception if not found
     */
    Agent findAgentById(long id);

    /*
        Deletes an agent if the given agent has no customers
        Throws exception if the agent primary key is not found or the agent has no customers

        @param agentid      The primary key (long) of the agent you are checking
     */
    void deleteUnassigned(long agentid);
}
