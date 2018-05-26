/************************************************
*   File:         IntegerTextField.java
*
*   Project:      Project2
*
*   Author:       Desiredbean241
*
*   Description:  JTextField that throws an
*                 an exception when
*                 user enters something
*                 other than an Integer
*
*   Date:         05/20/2018
*
*   Comment:      Validates Input
*
************************************************/

import javax.swing.*;

public class IntegerTextField extends JTextField
{
   /*** Class Constants ***/

   /*** Class Variables ***/

   private int minimum = Integer.MIN_VALUE;
   private int maximum = Integer.MAX_VALUE;

   /*** Class Constructors ***/

   public IntegerTextField( int size )
   {
      super( size );
   }

   public IntegerTextField( int size, int minimum, int maximum )
   {
      super( size );

      this.maximum = maximum;
      this.minimum = minimum;
   }

   /*** Class Methods ***/

   /*** Accesor Methods ***/

   public int getInt() throws IntegerUserInputException
   {
      int intNumber = 0;

      /*** Extract User Input ***/

      try
      {
         intNumber = Integer.parseInt( getText().trim() );

         if( intNumber < minimum || intNumber > maximum )
            throw new IntegerUserInputException(minimum, maximum);
      }
      catch( NumberFormatException e )
      {
         throw new IntegerUserInputException("Not an Integer!: " + e.getMessage());
      }

      return intNumber;
   }
}
