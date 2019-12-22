package com.annalohvinenko.usermanagement.agent;

import java.util.Collection;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import com.annalohvinenko.usermanagement.agent.behaviour.SearchRequestBehaviour;
//import com.annalohvinenko.usermanagement.agent.exception.SearchException;
//import com.annalohvinenko.usermanagement.gui.SearchGui;

import com.annalohvinenko.usermanagement.agent.behaviour.RequestServer;
import com.annalohvinenko.usermanagement.User;
import com.annalohvinenko.usermanagement.db.DaoFactory;
import com.annalohvinenko.usermanagement.db.DatabaseException;

public class SearchAgent extends Agent {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AID[] aids = new AID[0];

    private SearchGui gui = null;

    @Override
    protected void setup() {
        super.setup();
        gui = new SearchGui(this);
        gui.setVisible(true);
        addBehaviour(new TickerBehaviour(this, 60000) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void onTick() {
                DFAgentDescription description = new DFAgentDescription();
                description.setName(getAID());
                ServiceDescription serviceDescription = new ServiceDescription();
                serviceDescription.setName("JADE-Searching");
                serviceDescription.setType("searching");
                description.addServices(serviceDescription);
                try {
                    DFAgentDescription[] descriptions = DFService.search(myAgent, description);
                    AID[] newAids = new AID[descriptions.length];
                    for (int i = 0; i < newAids.length; i++) {
                        newAids[i] = descriptions[i].getName();
                    }
                    aids = newAids;
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });
        addBehaviour(new RequestServer());
        DFAgentDescription description = new DFAgentDescription();
        description.setName(getAID());
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setName("JADE-Searching");
        serviceDescription.setType("searching");
        description.addServices(serviceDescription);
        try {
            DFService.register(this, description);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        System.out.println("Agent " + getAID().getName() + " is ready! Awaiting orders!");
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        //gui.setVisible(false);
        gui.dispose();
        System.out.println("Agent " + getAID().getName() + " has finished working!");
    }

    public void search(String firstName, String lastName) throws SearchException {
        try {
            Collection<User> users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
            if (users.size() > 0) {
                showUsers(users);
            } else {
                addBehaviour(new SearchRequestBehaviour(firstName, lastName, aids));
            }
        } catch (DatabaseException e) {
            throw new SearchException(e);
        }
    }

    public void showUsers(Collection<User> users) {
        gui.addUsers(users);
    }
}
