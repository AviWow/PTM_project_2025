package test;//package test;
import java.util.ArrayList;

public class IncAgent implements Agent {
    private TopicManagerSingleton.TopicManager topicManager;
    private String[] subs;
    private String[] pubs;


    public IncAgent(String[] subs, String[] pubs) {
        topicManager = TopicManagerSingleton.get();
        this.subs = subs;
        this.pubs = pubs;
        sub();
    }

    private void sub() {
        Topic tm = topicManager.getTopic(subs[0]);
        tm.subscribe(this);
        tm = topicManager.getTopic(pubs[0]);
        tm.addPublisher(this);
    }

    @Override
    public String getName() {
        return "IncAgent";
    }

    @Override
    public void reset() {

    }

    @Override
    public void callback(String topic, Message msg) {
        if(subs[0].equals(topic)) {
            double x = msg.asDouble;
            if(x != Double.NaN) {
            topicManager.getTopic(pubs[0]).publish(new Message(x+1));
            }
        }
    }

    @Override
    public void close() {

    }
    
}
