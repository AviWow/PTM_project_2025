package server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyHTTPServer extends Thread implements HTTPServer{
    int port;
    int nThreads;
    HashMap<String,Servlet> getServlets;
    HashMap<String,Servlet> postServlets;
    HashMap<String,Servlet> deleteServlets;
    ExecutorService tp;
    boolean stop;

    public MyHTTPServer(int port,int nThreads){
        this.port = port;
        this.nThreads = 1;
        this.getServlets = new HashMap<>();
        this.postServlets = new HashMap<>();
        this.deleteServlets = new HashMap<>();
        tp = Executors.newFixedThreadPool(this.nThreads);
        this.stop = false;
    }

    public void addServlet(String httpCommanmd, String uri, Servlet s){
        if(httpCommanmd.equals("GET"))
        {
            getServlets.put(uri,s);
        }
        if(httpCommanmd.equals("POST"))
        {
            postServlets.put(uri,s);
        }
        if(httpCommanmd.equals("DELETE"))
        {
            deleteServlets.put(uri,s);
        }
    }

    public void removeServlet(String httpCommanmd, String uri){
        if(httpCommanmd.equals("GET"))
        {
            getServlets.remove(uri);
        }
        if(httpCommanmd.equals("POST"))
        {
            postServlets.remove(uri);
        }
        if(httpCommanmd.equals("DELETE"))
        {
            deleteServlets.remove(uri);
        }
    }

    public void run(){
        try{
            ServerSocket ss = new ServerSocket(port);
            ss.setSoTimeout(1000);
            while(!stop){
                try{
                    Socket clinet = ss.accept();
                    tp.execute(()->{
                            try{
                                BufferedReader in = new BufferedReader(new InputStreamReader(clinet.getInputStream()));
                                RequestParser.RequestInfo ri = RequestParser.parseRequest(in);
                                String command = ri.getHttpCommand();
                                String uri = ri.getUri();
                                Servlet servlet = null;
                                if(servlet != null){
                                    servlet.handle(ri,clinet.getOutputStream());
                                    servlet.close();
                                }
                                in.close();
                                clinet.close();
                    } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            ss.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private Servlet getServlet(String uri,String httpCommanmd)
    {
        Servlet servlet = null;
        if(httpCommanmd.equals("GET"))
        {
            servlet = getServlets.get(httpCommanmd);
        }
        if(httpCommanmd.equals("POST"))
        {
            servlet = getServlets.get(httpCommanmd);
        }
        if(httpCommanmd.equals("DELETE"))
        {
            servlet = getServlets.get(httpCommanmd);
        }
        return servlet;
    }

    public void close(){
        stop = true;
        tp.shutdown();
    }

}
