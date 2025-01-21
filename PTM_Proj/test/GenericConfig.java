package test;//package test;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenericConfig implements Config{
    private String configName;
    private List<Agent> agents = new ArrayList<>();

    public void setConfFile(String confFile) {
        this.configName = confFile;
    }

    @Override
    public void create() {
       List<Agent> agents = new ArrayList<>();
       Stream<String> s = null;
       try{
           s = Files.lines(Paths.get(configName));
           List<String> lines = s.collect(Collectors.toList());
           if(lines.size()%3==0)
           {
               for(int i = 0; i < lines.size(); i+=3)
               {
                   String className = lines.get(i);
                   String[] subs =lines.get(i+1).split(",");
                   String[] pubs = lines.get(i+2).split(",");

                   Class<?> agentClass = Class.forName(className);
                   if(Agent.class.isAssignableFrom(agentClass))
                   {
                       Agent agent = (Agent) agentClass.getConstructor(String[].class,String[].class).newInstance(subs,pubs);
                       this.agents.add(agent);
                   }
               }

           }

       }
       catch(Exception e){
            System.out.println("Error-"+e.getMessage());
       }


    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public void close() {
        for(Agent agent : agents)
            agent.close();
    }
}
