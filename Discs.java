/**
   This class makes discs discB
*/

public class Discs
{
   byte[][] discB = new byte[6][7];
   
   // Constructor
   public Discs()
   {
      discB = new byte[][]
      {
         {0,0,0,0,0,0,0,},{0,0,0,0,0,0,0,},{0,0,0,0,0,0,0,},
         {0,0,0,0,0,0,0,},{0,0,0,0,0,0,0,},{0,0,0,0,0,0,0,}  
      };
    } 
   
   // Check illegar move
   public boolean movCheck(int column)
   {
      return discB[0][column] == 0;
   }
    
   // Put a disc on the board 
   public boolean placeMove(int column, int disc)
   { 
      if(!movCheck(column)) 
      {
         System.out.println("This is illegal move."); 
         return false;
      }
      
      for(int i = 5; i >= 0; --i)
      {
         if(discB[i][column] == 0) 
         {
            discB[i][column] = (byte)disc;
            return true;
         }
      }
      return false;
   }
   
   // Cancle the move
   public void cancleM(int column)
   {
      for(int i = 0; i <= 5; ++i)
      {
         if(discB[i][column] != 0) 
         {
            discB[i][column] = 0;
            break;
         }
      }        
   }
   
   // Display the board
   public void displayDB()
   {
      System.out.println();
      for(int i = 0; i <= 5; ++i)
      {
         for(int j = 0; j <= 6; ++j)
         {
            if(discB[i][j] == 2)
               System.out.print("U" + " ");
            else if(discB[i][j] == 1)
               System.out.print("C" + " ");
            else
               System.out.print(discB[i][j] + " ");
         }
         System.out.println();
      }
      System.out.println();
   }
}