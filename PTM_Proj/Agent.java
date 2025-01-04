//package test;
package PTM_project_2025;

public interface Agent {
    String getName();
    void reset();
    void callback(String topic, Message msg);
    void close();
}
