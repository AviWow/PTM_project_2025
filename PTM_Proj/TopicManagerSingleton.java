package test;
import java.util.Collection;
import java.util.HashMap;
public class TopicManagerSingleton {

    public static class TopicManager{
        private HashMap<String,Topic> topicMap;
        private static final TopicManager instance = new TopicManager();

        private TopicManager(){
            topicMap=new HashMap<String,Topic>();
        }
        public Topic getTopic(String name)
        {
            Topic topic = topicMap.get(name);
            if(topic==null)
            {
                topic =new Topic(name);
                topicMap.put(name,topic);
            }
                return topic;
        }
        public HashMap<String, Topic> getTopics()
        {
            return topicMap;
        }
        public void clear()
        {
            topicMap.clear();
        }


    }



    public static TopicManager get(){
        return TopicManager.instance;
    }
}
