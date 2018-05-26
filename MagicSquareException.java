/************************************************
*   File:         MagicSquareException.java
*
*   Project:      Project2
*
*   Author:       Desiredbean241
*
*   Description:  Exception that is thrown when
*                 magic square isn't detected
*
*   Date:         05/20/2018
*
*   Comment:      Validates Input
*
************************************************/

public class MagicSquareException extends Exception
{
  public MagicSquareException()
  {
     super( " Not a Magic Square!" );
  }
}
