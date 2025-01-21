package test;//package test;

import java.util.function.BinaryOperator;

//import test.test.TopicManagerSingleton.TopicManager;

public class BinOpAgent implements Agent {
    private String name;
    private Topic inTopicRight;
    private Topic inTopicLeft;
    private Topic outTopic;
    private BinaryOperator<Double> operator;
    private TopicManagerSingleton.TopicManager topicManager;
    Double valRight = null, valLeft = null;

    public BinOpAgent(String name,String inTopic1,String inTopic2,String outTopic,BinaryOperator<Double> operator)
    {
        topicManager = TopicManagerSingleton.get();
        this.name = name;
        inTopicLeft = topicManager.getTopic(inTopic1);
        inTopicRight = topicManager.getTopic(inTopic2);
        this.outTopic = topicManager.getTopic(outTopic);
        this.operator = operator;
        sub();
    }
    private void sub()
    {
        inTopicLeft.subscribe(this);
        inTopicRight.subscribe(this);
        outTopic.addPublisher(this);

    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void reset() {
        valLeft = null;
        valRight = null;
    }

    @Override
    public void callback(String topic, Message msg) {

        if(topic.equals(this.inTopicRight.name))
            valRight = msg.asDouble;
        if(topic.equals(this.inTopicLeft.name))
            valLeft = msg.asDouble;

        if(valLeft!= null && valRight != null)
        {
            outTopic.publish(new Message(operator.apply(valLeft,valRight)));
            reset();
        }

    }

    @Override
    public void close() {
        reset();
        this.outTopic.removePublisher(this);
        this.inTopicLeft.unsubscribe(this);
        this.inTopicRight.unsubscribe(this);
        this.inTopicLeft = null;
        this.inTopicRight = null;
        this.outTopic = null;
    }
}
