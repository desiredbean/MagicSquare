/************************************************
*   File:         MagicSquare.java
*
*   Project:      Project2
*
*   Author:       Desiredbean241
*
*   Description:  Class that checks for
*                 a MagicSquare
*
*   Date:         05/20/2018
*
*   Comment:      Validates Input
*
************************************************/

import java.util.*;

public class MagicSquare
{
   /*** Class Constants ***/

   public final static int n = 3;

   /*** Class Variables ***/

   /*** Class Constructors ***/

   private MagicSquare()
   {

   }

     /*** Throws an exception if intArray[][] is not a magic square ***/

     public static void isMagicSquare( int intArray[][] ) throws MagicSquareException
     {
        int sum = 0;
        int rowSum = 0;
        int colSum = 0;
        boolean isMagicSquare = true;


         /*** Check for repeated numbers ***/

         try
         {
            for ( int k = 0; k < intArray[0].length; k++ )
            {
               for ( int i = 0; i < intArray.length-1; i++ )
               {
                  for ( int j = i; j < intArray.length; j++ )
                  {
                     if (intArray[k][i] != intArray[k][j])
                     {
                        isMagicSquare = true;
                     }
                     else
                        isMagicSquare = false;
                   }
                }
             }

         /*** Calculate the sum of the diagonal ***/

         for ( int j = 0; j < n; j++ )
            sum = sum + intArray[j][j];

         /*** Sums of Rows ***/

         for ( int j = 0; j < n; j++ )
         {

            rowSum = 0;

            for ( int k = 0; k < n; k++ )
               rowSum += intArray[j][k];

            /*** Check if every row sum is equal to the diagonal sum ***/

            if ( rowSum != sum )
               isMagicSquare = false;
         }

         /*** Sums of Columns ***/

         for ( int j = 0; j < n; j++ )
         {

            colSum = 0;
            for ( int k = 0; k < n; k++ )
               colSum += intArray[j][k];

            /*** Check if every column sum is equal to the diagonal sum ***/
            if ( sum != colSum )
               isMagicSquare = false;
         }
      }
      catch( Exception e )
      {
         isMagicSquare = false;
      }

      if( !isMagicSquare )
         throw new MagicSquareException();
   }
}
