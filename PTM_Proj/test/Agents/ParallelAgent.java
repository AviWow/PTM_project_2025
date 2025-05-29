package test.Agents;//package test;

import test.Message;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ParallelAgent implements Agent {
    private Agent agent;
    private BlockingQueue<Message> queue;
    private volatile boolean closed;

    public ParallelAgent(Agent agent,int size){
        closed = false;
        this.agent = agent;
        this.queue = new ArrayBlockingQueue<Message>(size);
        Thread t=new Thread(()->{
            while(!closed) {
                try {
                    Message m = queue.take();
                    if(!closed) {
                        String[] s = m.asText.toString().split("\n");
                        agent.callback(s[0], new Message(s[1]));
                    }
                } catch (InterruptedException e) {}
            }
        });
        t.start();
    }

    @Override
    public String getName() {
        return agent.getName();
    }

    @Override
    public void reset() {
        agent.reset();
    }

    @Override
    public void callback(String topic, Message msg)
    {
        try {
            queue.put(new Message(topic +"\n"+ msg.asText));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() {
        closed = true;
        try{
            queue.put(new Message("stop"));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        agent.close();

    }
}
