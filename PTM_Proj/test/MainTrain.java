package test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import server.RequestParser;

import static test.Test.*;


public class MainTrain { // RequestParser
    public static void main(String[] args) {
        testMessage();
        testAgents();
        testParseRequest();
        testCycles();
        testBinGraph();
        testTopicsGraph();
        try{
            testServer();
        }catch(Exception e){
            System.out.println("your server throwed an exception (-60)");
        }
        System.out.println("done");
    }

}
