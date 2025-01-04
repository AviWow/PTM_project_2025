//package test;
package PTM_project_2025;

public class TopicManagerSingleton {

    public static class TopicManager{
        public HashMap<String,Topic> topicMap;
        private static final TopicManager instance = new TopicManager();
        private TopicManager(){
            topicMap=new HashMap<String,Topic>();
        }
        public Topic getTopic(String name)
        {
            Topic topic = topicMap.get(name);
            if(topic==null)
            {
                topicMap.put(name,new Topic(name))
            }
            else
                return topic;
        }
        public Collection getTopics()
        {
            return topicMap;
        }
        public clear()
        {
            topicMap.clear();
        }


    }



    public static TopicManager get(){
        return TopicManager.instance;
    }
}
