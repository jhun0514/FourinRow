/**
   This is demo program for ConnectFour class and Discs class
*/

public class ConnectFourDemo
{
   public static void main(String[] args)
   {
      Discs d = new Discs();
      ConnectFour cf = new ConnectFour(d);  
      
      // Play
      cf.playCF();
   }
}