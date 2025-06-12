package graph;



import static graph.Tests.*;


public class MainTrain { // RequestParser

    public static void main(String[] args) {
    testMessage();
    testAgents();
    testParallels();
    testParseRequest();
    testCycles();
    testBinGraph();
    testTopicsGraph();
        try {
        testServer();
    } catch (Exception e) {
        System.out.println("your server throwed an exception (-60)");


    }
        System.out.println("done");
}

}
