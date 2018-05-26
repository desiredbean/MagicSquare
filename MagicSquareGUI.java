/************************************************
*   File:         MagicSquareGUI.java
*
*   Project:      Project2
*
*   Author:       Desiredbean241
*
*   Description:  GUI for MagicSquare
*                 order program
*
*   Date:         05/20/2018
*
*   Comment:      Validates Input
*
************************************************/

import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class MagicSquareGUI implements ActionListener
{

   /*** Class Constants ***/

   public final int MAX_SIZE = 3;
   public final int MIN_SIZE = 1;

   /*** Class Variables ***/

   private JFrame myFrame;

   private IntegerTextField[][] itfArray = new IntegerTextField[MAX_SIZE][MAX_SIZE];
   private int[][] intArray = new int[MAX_SIZE][MAX_SIZE];

   private JTextField jtfDocument;

   private JLabel jlaDocument;

   private JButton jbuDocument;
   private JButton jbuExit;
   private JButton jbuClear;
   private JButton jbuMagicSquare;
   private JButton jbuRead;

   private JTextArea jtaOutput;

   private int lineCount = MIN_SIZE ;
   private int totalLineCount = MIN_SIZE;

   /*** Constructors ***/

   public MagicSquareGUI()
   {
      /*** Local Variables ***/

      Container c;

      /*** Instatiates JFrame (Outer Container -- window) ***/

      myFrame = new JFrame( "Magic Square" );

      c = myFrame.getContentPane();

      buildGUI( c );

      setWindowAttributes( myFrame );
   }

   /*** Methods  ***/

   private void buildGUI( Container c )
   {
      /*** JPanels ***/

      JPanel jpaInput;
      JPanel jpaProcess;
      JPanel jpaOutput;

      /*** Set layout of panels ***/

      c.setLayout( new BorderLayout() );

      /*** Create panels ***/

      jpaInput = createInputPanel();
      jpaProcess = createProcessPanel();
      jpaOutput = createOutputPanel();

      /*** Add panels to outer container ***/

      c.add( "North", jpaInput );
      c.add( "Center", jpaProcess );
      c.add( "South", jpaOutput );
   }

   private JButton createJButton( String title )
   {
      JButton newButton = new JButton( title );

      newButton.addActionListener( this );

      return newButton;
   }

   private JLabel createJLabel(String title)
   {
      JLabel newLabel = new JLabel( title, SwingConstants.RIGHT );

      return newLabel;
   }

   private JTextField createJTextField(int size)
   {
      JTextField newTextField = new JTextField( size );

      return newTextField;
   }

   private IntegerTextField createIntegerTextField()
   {
      IntegerTextField newTextField = new IntegerTextField( MAX_SIZE, MIN_SIZE, ( MAX_SIZE * MAX_SIZE ) );

      return newTextField;
   }

   private JTextArea createJTextArea(  int rows, int columns )
   {
      JTextArea newTextArea = new JTextArea( rows, columns );

      /*** Set JTextArea Attributes ***/

      newTextArea.setEditable( false );

      return newTextArea;
   }

   public JPanel createNorthInputPanel()
   {
      /*** Local Variables ***/

      JPanel northInputPanel = new JPanel();

      /*** Set Layout ***/

      northInputPanel.setLayout( new BorderLayout() );

      /*** Create JTextArea with info for user ***/

      JTextArea info = createJTextArea( 3, 10 );

      /*** Set Attributes ***/

      info.setEditable( false );

      /*** Set Text Area ***/

      info.setText(("                        This Application allows you to enter a \n" +
                    "                        an attempt at a magic square either by \n" +
                    "                    entering with the keyboard or reading a text file. \n"));

      /*** Add components to panel ***/

      northInputPanel.add( "Center", info );

      /*** Return Panel ***/

      return northInputPanel;
   }

   public JPanel createSouthInputPanel()
   {
      /*** Local Variables ***/

      JPanel southInputPanel = new JPanel();

      /*** Create JPanel objects ***/

      for( int i = 0; i < itfArray[0].length; i++ )
      {
         for( int j = 0; j < itfArray.length; j++ )
         {
            itfArray[i][j] = createIntegerTextField();
         }
      }

      /*** Create document label, textfield, and buttons ***/

      jlaDocument = createJLabel( "Document Name: " );
      jtfDocument = createJTextField( MIN_SIZE * MIN_SIZE );
      jbuDocument = createJButton( "Browse..." );


      /*** Set inputPanel layout ***/

      southInputPanel.setLayout( new GridLayout( 4, 3 ) );

      /*** Add components to panel ***/

      for( int i = 0; i < itfArray[0].length; i++ )
      {
         for( int j = 0; j < itfArray.length; j++ )
         {
            southInputPanel.add( itfArray[i][j]) ;
         }
      }

      southInputPanel.add( jlaDocument );
      southInputPanel.add( jtfDocument );
      southInputPanel.add( jbuDocument );


      /*** Return JPanel ***/

      return southInputPanel;
   }

   public JPanel createInputPanel()
   {
      /*** Local Variables ***/

      JPanel inputPanel = new JPanel();

      JPanel northInputPanel = createNorthInputPanel();
      JPanel southInputPanel = createSouthInputPanel();

      /*** Set input panel layout ***/

      inputPanel.setLayout( new BorderLayout() );

      /*** Add panels to panel ***/

      inputPanel.add( "North", northInputPanel );
      inputPanel.add( "South", southInputPanel );

      /*** Return panel ***/

      return inputPanel;
   }

   public JPanel createProcessPanel()
   {
      JPanel processPanel = new JPanel();

      /*** Create button objects ***/

      jbuClear = createJButton( "Clear" );
      jbuExit = createJButton( "Exit" );
      jbuMagicSquare = createJButton( "Magic Square" );
      jbuRead = createJButton( "Read Text File" );

      /*** Set panel layout ***/

      processPanel.setLayout( new GridLayout( MIN_SIZE,4 ) );

      /*** Add components to panel ***/

      processPanel.add( jbuClear );
      processPanel.add( jbuExit );
      processPanel.add( jbuMagicSquare );
      processPanel.add( jbuRead );

      /*** Return JPanel ***/

      return processPanel;
   }

   public JPanel createOutputPanel()
   {
      JPanel outputPanel = new JPanel();

      return outputPanel;
   }

   public void setWindowAttributes( Frame frame )
   {
      /*** Set JFrame Attributes***/

      frame.setSize( 450, 240 );
      frame.setLocation( 200, 100 );
      frame.setVisible( true );
   }

   /*** Button action methods ***/

   private void processDocumentButton()
   {
      try
      {
         /*** Instatiate JFileChooser ***/

         JFileChooser jfcChooser = new JFileChooser();

         /*** Set Current Directory to current directory ***/

         jfcChooser.setCurrentDirectory( new File(".") );

         /*** Show open dialog ***/

         int returnValue = jfcChooser.showOpenDialog( null );

         /*** Get file if user approves selection ***/

         if( returnValue == jfcChooser.APPROVE_OPTION );
         {
            File file = jfcChooser.getSelectedFile();

            jtfDocument.setText( file.getName() );
         }
      }
      catch( NullPointerException e )
      {
          /*** Print to console if user exits without choosing file ***/

          System.out.println( "User Exited without selecting file" );
      }
   }

   public void processExitButton()
   {
      /*** Exit Application ***/

      System.exit( 0 );
   }

   public void processClearButton()
   {
      /*** Clear All Text Fields ***/

      for( int i = 0; i < itfArray[0].length; i++ )
      {
         for( int j = 0; j < itfArray.length; j++ )
         {
            /*** Set IntegerTextFields to default ***/

            itfArray[i][j].setText( "" );
            itfArray[i][j].setBackground( Color.WHITE );
         }
      }

      /*** Set Document textfield to default ***/

      jtfDocument.setText( "" );
      lineCount = MIN_SIZE;
   }

   public void processMagicSquare()
   {
      /*** Local Variables ***/

      int number;

      /*** Loop through IntegerTextField Array ***/

      for( int i = 0; i < itfArray[0].length; i++ )
      {
         for( int j = 0; j < itfArray.length; j++ )
         {
            try
            {
               /*** Put contents of itfArray into intArray ***/

               number = itfArray[i][j].getInt();
               intArray[i][j] = number;

               /*** If successful set background to white ***/

               itfArray[i][j].setBackground( Color.WHITE );
            }
            catch(IntegerUserInputException e)
            {
               /*** Set background to red for invalid fields ***/

               itfArray[i][j].setBackground( Color.RED );

               /*** Popup to let user know invalid input ***/

               JOptionPane.showMessageDialog( itfArray[i][j], "Error: " + e.getMessage() +
                                             "\n at (" + ( i + MIN_SIZE ) + ", " + ( j + MIN_SIZE ) + ")" );
            }
         }
      }

      try
      {
         MagicSquare.isMagicSquare( intArray );
         JOptionPane.showMessageDialog( null, "This is a Magic Square!" );
      }
      catch( MagicSquareException e )
      {
         JOptionPane.showMessageDialog( null, e.getMessage() );
      }
   }

   public void processReadButton( String fileName )
   {
      /*** Local Variables ***/

      Scanner inStream = null;

      Boolean fileFound = true;
      String record, text;
      String fields[] = null;
      int number;
      IntegerTextField tempField;
      int count = 0;

      /*** Attempt to scan file for fields ***/

      try
      {
         /*** Instatiate Scanner to open textfile ***/

         inStream = new Scanner( new File( fileName ) );

         /*** Verify that it is not the EOF ***/

         if( inStream.hasNextLine() )
         {

           /*** Extract record from text file ***/

           for( int h = 0; h < lineCount; h++ )
            {
              /*** Get record ***/

              record = inStream.nextLine();

              /*** Create an array of fields ***/

              fields = record.trim().split( " " );
            }

            /*** Attempt to parse for integers in field array ***/

            for( int i = 0; i < fields.length; i++ )
            {
               if( fields.length > 0 )
               {
                  try
                  {
                     number = Integer.parseInt( fields[i] );
                  }
                  catch( NumberFormatException e )
                  {

                  }
               }
               else
                  System.out.println( "No data for field" );
            }
         }

         for( int k = 0; k < MAX_SIZE; k++ )
         {
            for( int j = 0; j < MAX_SIZE; j++ )
            {
               /*** Set IntegerTextFields to contents of field array ***/

               itfArray[k][j].setText( fields[count] );

               /*** Increment count ***/

               count++;
            }
         }
      }
      catch( FileNotFoundException e )
      {
         /*** Display popup if file is not found ***/

         JOptionPane.showMessageDialog( null, "File not found" );

         fileFound = false;
      }
      catch( Exception e )
      {}

      /*** Increment lineCount ***/

      lineCount++;

      if( fileFound == false )
      {
         lineCount = MIN_SIZE;
      }

      /*** Process MagicSquare from file ***/

      if( fileName.length() >= MIN_SIZE && fileFound )
         processMagicSquare();

      /*** Loop back to beggining of file if user keeps clicking button ***/

      try
      {
         if(!inStream.hasNextLine())
         {
           /*** Inform user they have reached end of file ***/

            JOptionPane.showMessageDialog( null, " End of file\n press again to restart" );
            totalLineCount = MIN_SIZE;
            lineCount = MIN_SIZE;
         }
         else if(inStream.hasNextLine())
         {
            totalLineCount++;
         }

      }
      catch(Exception e)
      {}
   }

   /*** Action Performed -- Handle User Clicks ***/

   public void actionPerformed( ActionEvent e )
   {
      if( e.getSource() == jbuDocument )
         processDocumentButton();
      else if( e.getSource() == jbuClear )
         processClearButton();
      else if( e.getSource() == jbuExit )
         processExitButton();
      else if( e.getSource() == jbuMagicSquare )
         processMagicSquare();
      else if( e.getSource() == jbuRead )
         processReadButton( jtfDocument.getText() );

   }

   /*** Application ***/

   public static void main( String[] args )
   {
      MagicSquareGUI myGUI = new MagicSquareGUI();
   }
}
