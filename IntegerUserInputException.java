/************************************************
*   File:         IntegerUserInputException.java
*
*   Project:      Project2
*
*   Author:       Desiredbean241
*
*   Description:  Exception that is thrown
*                 when user input is not
*                 an interger
*
*   Date:         05/20/2018
*
*   Comment:      Validates Input
*
************************************************/

public class IntegerUserInputException extends Exception
{
   /*** Constructors ***/

   public IntegerUserInputException()
   {
      super(" Error: User input invalid double!");
   }

   public IntegerUserInputException( String s )
   {
      super(s);
   }

   public IntegerUserInputException( int minimum, int maximum )
   {
      super(" User input out of range: [" + minimum + ", " + maximum + "]");
   }
}
