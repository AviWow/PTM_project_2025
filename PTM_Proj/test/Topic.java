package test;//package test;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    public final String name;
    private List<Agent> subs;
    private List<Agent> pubs;

    Topic(String name){
        this.name=name;
        subs=new ArrayList<>();
        pubs=new ArrayList<>();
    }

    public void subscribe(Agent a){
        subs.add(a);
    }
    public void unsubscribe(Agent a){
        subs.remove(a);
    }

    public void publish(Message m){
        for(Agent a : subs){
            a.callback(this.name,m);
        }
    }
    public List<Agent> getSubs(){
        return subs;
    }
    public List<Agent> getPubs(){
        return pubs;
    }

    public void addPublisher(Agent a){
        pubs.add(a);
    }

    public void removePublisher(Agent a){
        pubs.remove(a);
    }


}
