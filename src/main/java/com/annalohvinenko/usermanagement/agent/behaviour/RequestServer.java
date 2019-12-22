package com.annalohvinenko.usermanagement.agent.behaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import com.annalohvinenko.usermanagement.User;
import com.annalohvinenko.usermanagement.agent.SearchAgent;
import com.annalohvinenko.usermanagement.db.DaoFactory;
import com.annalohvinenko.usermanagement.db.DatabaseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

public class RequestServer extends CyclicBehaviour {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void action() {
        ACLMessage message = myAgent.receive();
        if (message == null) {
            block();
        } else {
            if (message.getPerformative() == ACLMessage.REQUEST) {
                myAgent.send(createReply(message));
            } else if (message.getPerformative() == ACLMessage.INFORM) {
                Collection<User> users = parseMessage(message);
                ((SearchAgent) myAgent).showUsers(users);
            }
        }
    }

    private Collection<User> parseMessage(ACLMessage message) {
        Collection<User> users = new ArrayList<>();
        String content = message.getContent();
        if (content != null) {
            StringTokenizer entryTokenizer = new StringTokenizer(content, ";");
            while (entryTokenizer.hasMoreTokens()) {
                StringTokenizer dataTokenizer = new StringTokenizer(entryTokenizer.nextToken(), ",");
                users.add(new User(new Long(dataTokenizer.nextToken()), dataTokenizer.nextToken(), dataTokenizer.nextToken()));
            }
        }
        return users;
    }

    private ACLMessage createReply(ACLMessage message) {
        ACLMessage reply = message.createReply();
        String content = message.getContent();
        StringTokenizer tokenizer = new StringTokenizer(content, ",");
        if (tokenizer.countTokens() == 2) {
            String firstName = tokenizer.nextToken();
            String lastName = tokenizer.nextToken();
            Collection<User> collection = null;
            try {
                collection = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
            } catch (DatabaseException e) {
                e.printStackTrace();
                collection = new ArrayList<>();
            }
            StringBuffer buffer = new StringBuffer();
            for (User user : collection) {
                buffer.append(user.getId()).append(",");
                buffer.append(user.getFirstName()).append(",");
                buffer.append(user.getLastName()).append(";");
            }
            reply.setContent(buffer.toString());
        }
        return reply;
    }
}
