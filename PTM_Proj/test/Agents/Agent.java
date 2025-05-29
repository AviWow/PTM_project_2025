package test.Agents;//package test;

import test.Message;

public interface Agent {
    String getName();
    void reset();
    void callback(String topic, Message msg);
    void close();
}
