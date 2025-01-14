package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import test.TopicManagerSingleton.TopicManager;

public class Graph extends ArrayList<Node>{
    private HashMap<String,Node> nodeMap;
    public Graph(){
        this.nodeMap = new HashMap<>();
    }
    public boolean hasCycles() {
        for (Node node : this) {
            if(node.hasCycles())
                return true;
        }
        return false;
    }
    private Node getNode(String name)
    {
        if(!nodeMap.containsKey(name)) {
            nodeMap.put(name, new Node(name));
        }

            return nodeMap.get(name);
    }
    public void createFromTopics(){
        nodeMap.clear();
        TopicManagerSingleton.TopicManager tm = TopicManagerSingleton.get();
        Node Topicnode;
        for (Topic topic : tm.getTopics().values()) {
            String nameT = "T"+topic.name;
            Topicnode = getNode(nameT);
            List<Agent> agentsSub = topic.getSubs();
            List<Agent> agentPub = topic.getPubs();
            for (Agent agent : agentsSub)
            {
                String nameA = "A"+agent.getName();
                Node agentNode= getNode(nameA);
                Topicnode.addEdge(agentNode);
            }
            for(Agent agent : agentPub)
            {
                String nameA = "A"+agent.getName();
                Node agentNode= getNode(nameA);
                agentNode.addEdge(Topicnode);
            }

        }
        this.addAll(nodeMap.values());
    }



    
}
