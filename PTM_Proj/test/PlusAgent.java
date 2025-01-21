package test;//package test;

import java.util.ArrayList;

public class PlusAgent implements Agent {
    private TopicManagerSingleton.TopicManager topicManager;
    private String[] subs;
    private String[] pubs;
    private double x,y;

    public PlusAgent(String[] subs, String[] pubs) {
        topicManager = TopicManagerSingleton.get();
        this.subs = subs;
        this.pubs = pubs;
        x = 0.0;
        y = 0.0;
        sub();


    }
    private void sub()
    {
        Topic tx = topicManager.getTopic(this.subs[0]);
        tx.subscribe(this);
        Topic ty = topicManager.getTopic(this.subs[1]);
        ty.subscribe(this);
        Topic out = topicManager.getTopic(this.pubs[0]);
        out.addPublisher(this);
    }

    @Override
    public String getName() {
        return "test.PlusAgent";
    }

    @Override
    public void reset() {
        x = 0.0;
        y = 0.0;
    }

    @Override
    public void callback(String topic, Message msg) {
        if(subs[0].equals(topic))
            x = msg.asDouble;
        if(subs[1].equals(topic))
            y = msg.asDouble;

        if(x!= 0.0 && y != 0.0)
        {
            Topic out = topicManager.getTopic(this.pubs[0]);
            out.publish(new Message(x+y));
            reset();
        }
    }

    @Override
    public void close() {

    }
}
