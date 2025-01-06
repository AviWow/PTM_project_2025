//package test;

import java.util.Date;

public class Message {
    public final byte[] data;
    public final String asText;
    public final double asDouble;
    public final Date date;

    private void init(String s)
    {

    }

   public Message(byte[] date)
   {
       double asDouble1;
       this.data = date;
       this.asText = new String(data);
       try{
           asDouble1 = Double.parseDouble(asText);

       }
       catch(NumberFormatException e){
           asDouble1 = Double.NaN;
       }
       this.asDouble = asDouble1;
       this.date = new Date();
   }
   public Message(String asText)
   {
       double asDouble1;
       this.data = asText.getBytes();
       this.asText = asText;
       try{

           asDouble1 = Double.parseDouble(asText);
       }
       catch(NumberFormatException e){
           asDouble1 = Double.NaN;
       }
       this.asDouble = asDouble1;
       this.date = new Date();
   }
   public Message(double asDouble)
   {

       this.asText = Double.toString(asDouble);
       this.data = asText.getBytes();
       this.asDouble = asDouble;
       this.date = new Date();
   }




}
