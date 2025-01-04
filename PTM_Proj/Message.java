//package test;
package PTM_project_2025;
import java.util.Date;

public class Message {
    public final byte[] data;
    public final String asText;
    public final double asDouble;
    public final Date date;

   public Message(byte[] date)
   {
       this.data = date;
       this.asText = new String(data);
       try{
           this.asDouble = Double.parseDouble(asText);
          }
       catch(NumberFormatException e)
       {
           this.asDouble = Double.NaN
       }
       this.date = new Date();
   }
   public Message(String asText)
   {
       this.data = asText.getBytes();
       this.asText = asText;
       try{
           this.asDouble = Double.parseDouble(asText);
       }
       catch(NumberFormatException e)
       {
           this.asDouble = Double.NaN
       }
       this.date = new Date();
   }
   public Message(double asDouble)
   {
       this.data = new byte[]{(byte)(asDouble)};
       this.asText = new String(asDouble);
       this.asDouble = asDouble;
       this.date = new Date();
   }




}
