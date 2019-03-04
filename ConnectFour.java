/**
   This class contains connect four game's rule and
   computer's action methods according to user's action.
*/

import java.util.Scanner;

public class ConnectFour 
{ 
   private Discs b;
   private int positionN = 0;
   private int limitA = 9;
   private Scanner scan;
    
   public ConnectFour(Discs b)
   {
      this.b = b;
      scan = new Scanner(System.in);
   }
    
   // Choose position to place discs
   public void choosePosition()
   {
      boolean wrongAnswer = true;
      int pos = 0;
      System.out.println("\nChoose the position you want to put the disc(1~7): ");
      
      while(wrongAnswer)
      {
         if(scan.hasNextInt())
         {
            pos = scan.nextInt();
            wrongAnswer = false;
         }
         else
         {
            System.out.println("You must enter an ineger number.\n");
            System.out.println("\nChoose the position(1~7): ");
            scan.next();
         }
      }
      
      while(pos < 1 || pos > 7 || !b.movCheck(pos - 1))
      {
         System.out.println("Invalid position.\n"); 
         System.out.println("\nChoose the position(1~7): ");
         pos = scan.nextInt();
      }
        
      // Put 2 at position
      b.placeMove(pos - 1, (byte)2); 
   }
    
   // Calculate Result
   public int calcResult(Discs b){
      int comPoint = 0, playerPoint = 0;
      for(int i = 5; i >= 0; --i)
      {
         for(int j = 0; j <= 6; ++j)
         {
            if(b.discB[i][j] == 0) 
               continue;
                
            // Check board to the right
            if(j <= 3)
            {
               for(int k = 0; k < 4; ++k)
               { 
                  if(b.discB[i][j+k] == 1) 
                     comPoint++;
                  else if(b.discB[i][j+k] == 2) 
                     playerPoint++;
                  else 
                     break; 
               }
               if(comPoint == 4)
                  return 1; 
               else if (playerPoint == 4)
                  return 2;
               comPoint = 0; playerPoint = 0;
            } 
                
            // Check board to the up
            if(i >= 3)
            {
               for(int k = 0; k < 4; ++k)
               {
                  if(b.discB[i-k][j] == 1) 
                     comPoint++;
                  else if(b.discB[i-k][j] == 2) 
                     playerPoint++;
                  else 
                     break;
               }
               
               if(comPoint == 4)
                  return 1; 
               else if (playerPoint == 4)
                  return 2;
                  
               comPoint = 0; playerPoint = 0;
            } 
                
            // Check board diagonal to up-right
            if(j <= 3 && i >= 3)
            {
               for(int k = 0; k < 4; ++k)
               {
                  if(b.discB[i-k][j+k] == 1) 
                     comPoint++;
                  else if(b.discB[i-k][j+k] == 2) 
                     playerPoint++;
                  else 
                     break;
               }
               if(comPoint == 4)
                  return 1; 
               else if (playerPoint == 4)
                  return 2;
                  
               comPoint = 0; playerPoint = 0;
            }
                
            // Check board diagonal to up-left
            if(j >= 3 && i >= 3)
            {
               for(int k = 0; k < 4; ++k)
               {
                  if(b.discB[i-k][j-k] == 1) 
                     comPoint++;
                  else if(b.discB[i-k][j-k] == 2) 
                     playerPoint++;
                  else 
                     break;
               } 
               if(comPoint == 4)
                  return 1; 
               else if (playerPoint == 4)
                  return 2;
                  
               comPoint = 0; playerPoint = 0;
            }  
         }
      }
        
      for(int j = 0; j < 7; ++j)
      {
         if(b.discB[0][j] == 0)
            return -1;
      }
      
      // Draw
      return 0;
    }
   
   // Calculate score
   int calcScore(int comPoint, int m)
   {   
      int pScore = 4 - m;
      if(comPoint == 0)
         return 0;
      else if(comPoint == 1)
         return 1 * pScore;
      else if(comPoint == 2)
         return 10 * pScore;
      else if(comPoint == 3)
         return 100 * pScore;
      else 
         return 1000;
   }
    
   // Get position favorableness for computer
   public int getFavor(Discs b)
   {
      int comPoint = 1;
      int score = 0;
      int empty = 0;
      int k = 0;
      int incPosition = 0;
      
      for(int i = 5; i >= 0; --i)
      {
         for(int j = 0; j <= 6; ++j)
         {
            if(b.discB[i][j] == 0 || b.discB[i][j] == 2) 
               continue; 
                
            if(j <= 3)
            { 
               for(k = 1; k < 4; ++k)
               {
                  if(b.discB[i][j+k] == 1)
                     comPoint++;
                  else if(b.discB[i][j+k] == 2)
                  {
                     comPoint = 0;
                     empty = 0;
                     break;
                  }
                  else 
                     empty++;
               }
                     
               incPosition = 0; 
               if(empty > 0) 
                  for(int c = 1; c < 4; ++c)
                  {
                     int column = j + c;
                     for(int m = i; m <= 5; m++)
                     {
                        if(b.discB[m][column] == 0)
                           incPosition++;
                        else 
                           break;
                     } 
                  } 
                    
               if(incPosition != 0) 
                  score += calcScore(comPoint, incPosition);
               comPoint = 1;   
               empty = 0;
            } 
                
            if(i >= 3)
            {
               for(k = 1; k < 4; ++k)
               {
                  if(b.discB[i-k][j] == 1)
                     comPoint++;
                  else if(b.discB[i-k][j] == 2)
                  {
                     comPoint = 0;
                     break;
                  } 
               } 
               incPosition = 0; 
                    
               if(comPoint > 0)
               {
                  int column = j;
                  for(int m = i - k + 1; m <= i - 1; m++)
                  {
                     if(b.discB[m][column] == 0)
                        incPosition++;
                     else 
                        break;
                  }  
               }
               if(incPosition != 0) 
                  score += calcScore(comPoint, incPosition);
               comPoint = 1;  
               empty = 0;
            }
                 
            if(j >= 3)
            {
               for(k = 1; k < 4; ++k)
               {
                  if(b.discB[i][j-k] == 1)
                     comPoint++;
                  else if(b.discB[i][j-k] == 2)
                  {
                     comPoint = 0; 
                     empty = 0;
                     break;
                  }
                  else empty ++;
               }
               incPosition = 0;
               if(empty>0) 
                  for(int c = 1; c < 4; ++c)
                  {
                     int column = j- c;
                     for(int m = i; m <= 5; m++)
                     {
                        if(b.discB[m][column] == 0)
                           incPosition++;
                        else 
                           break;
                     } 
                  } 
                    
                  if(incPosition != 0) 
                     score += calcScore(comPoint, incPosition);
                  comPoint = 1; 
                  empty = 0;
            }
                 
            if(j <= 3 && i >= 3)
            {
               for(k = 1; k < 4; ++k)
               {
                  if(b.discB[i-k][j+k] == 1)
                     comPoint++;
                  else if(b.discB[i-k][j+k] == 2)
                  {
                     comPoint=0;
                     empty=0;
                     break;
                  }
                  else 
                     empty++;                        
               }
               incPosition = 0;
               if(empty>0)
               {
                  for(int c = 1; c < 4; ++c)
                  {
                     int column = j + c, row = i - c;
                     for(int m = row; m <= 5; ++m)
                     {
                        if(b.discB[m][column] == 0)
                           incPosition++;
                        else if(b.discB[m][column] == 1);
                        else 
                           break;
                     }
                  } 
                  if(incPosition != 0) 
                     score += calcScore(comPoint, incPosition);
                  comPoint = 1;
                  empty = 0;
               }
            }
                 
            if(i >= 3 && j >= 3)
            {
               for(k = 1; k < 4; ++k)
               {
                  if(b.discB[i-k][j-k] == 1)
                     comPoint++;
                  else if(b.discB[i-k][j-k] == 2)
                  {
                     comPoint = 0;
                     empty = 0;
                     break;
                  }
                  else 
                     empty++;                        
               }
               incPosition = 0;
               if(empty > 0)
               {
                  for(int c = 1; c < 4; ++c)
                  {
                     int column = j-c, row = i-c;
                     for(int m = row; m <= 5; ++m)
                     {
                        if(b.discB[m][column] == 0)
                           incPosition++;
                        else if(b.discB[m][column] == 1);
                        else 
                           break;
                     }
                  } 
                  if(incPosition != 0) 
                     score += calcScore(comPoint, incPosition);
                  comPoint = 1;
                  empty = 0;
               }
            } 
         }
      }
      return score;
   } 
   
   // Get favorableness points for each position
   public int getFP(int a, int turn, int c, int d)
   {
      if(d <= c)
         {
            if(turn == 1) 
               return Integer.MAX_VALUE; 
            else 
               return Integer.MIN_VALUE; 
         }
      int calcResult = calcResult(b);
        
      if(calcResult == 1)
         return Integer.MAX_VALUE/2;
      else if(calcResult == 2)
         return Integer.MIN_VALUE/2;
      else if(calcResult == 0)
         return 0; 
        
      if(a == limitA)
         return getFavor(b);
        
      int maxP = Integer.MIN_VALUE, minScore = Integer.MAX_VALUE;
                
      for(int j = 0; j <= 6; ++j)
      { 
         int currentP = 0;
            
         if(!b.movCheck(j)) continue; 
            
         if(turn == 1)
         {
            b.placeMove(j, 1);
            currentP = getFP(a+1, 2, c, d);
                    
            if(a == 0)
            {
               if(currentP > maxP)
                  positionN = j; 
               if(currentP == Integer.MAX_VALUE/2)
               {
                  b.cancleM(j);
                  break;
               }
            }
                    
            maxP = Math.max(currentP, maxP);
                    
            c = Math.max(currentP, c);  
         } 
         else if(turn == 2)
         {
            b.placeMove(j, 2);
            currentP = getFP(a + 1, 1, c, d);
            minScore = Math.min(currentP, minScore);
                    
            d = Math.min(currentP, d); 
         }  
         b.cancleM(j); 
         
         if(currentP == Integer.MAX_VALUE || currentP == Integer.MIN_VALUE) 
            break; 
      }  
      return turn == 1?maxP:minScore;
   }
   
   // Get computer's decision
   public int getComD()
   {
      positionN = -1;
      getFP(0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
      return positionN;
   }
   
   // Generator
   public void playCF()
   {
      int playerM = -1;
      Scanner scan = new Scanner(System.in);
      
      System.out.println("Do you want to play first? (yes/no)");
      String answer = scan.next().trim();
      
      // Validate
      while(!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no"))
      {
         System.out.println("Should enter yes or no\n");
         System.out.println("Do you want to play first? (yes/no)");
         answer = scan.next().trim();
      }
      
      // Disc shape
      System.out.println("\nDisc shape - User: U, Computer: C");
      
      if(answer.equalsIgnoreCase("yes")) 
      {
         System.out.println("\n----Board----");
         b.displayDB();
         choosePosition();
         System.out.println("\nPlayer's choice");
         b.displayDB();
      }
      
      b.placeMove(3, 1);
      System.out.println("\nComputer's choice");
      b.displayDB();
      
      // Display result
      while(true)
      { 
         choosePosition();
         System.out.println("\nPlayer's choice");
         b.displayDB();
            
         int calcResult = calcResult(b);
         if(calcResult == 1)
         {
            System.out.println("Game end:\n");
            System.out.println("Computer Wins :(");
            break;
         }
         else if(calcResult == 2)
         {
            System.out.println("Game end:\n");
            System.out.println("User Win :)");
            break;
         }
         else if(calcResult == 0)
         {
            System.out.println("Game end:\n");
            System.out.println("Draw..");
            break;
         }
            
         b.placeMove(getComD(), 1);
         System.out.println("\nComputer's choice");
         b.displayDB();
         calcResult = calcResult(b);
         
         if(calcResult == 1)
         {
            System.out.println("Game end:\n");
            System.out.println("Computer Wins :(");
            break;
         }
         else if(calcResult == 2)
         {
            System.out.println("Game end:\n");
            System.out.println("User Win :)");
            break;
         }
         else if(calcResult == 0)
         {
            System.out.println("Game end:\n");
            System.out.println("Draw..");
            break;
         }
      }   
   }
}