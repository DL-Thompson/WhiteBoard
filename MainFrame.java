
package WhiteBoard;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

public final class MainFrame extends JFrame{
    
    private ToolPanel toolPanel;
    private MessagePanel messagePanel;
    private DrawingBoard board;
    private TopMenuBar menuBar;
    
    //Creates the main GUI layout for the program
    public MainFrame()
    {
        super("WhiteBoard");
        //Create the 3 components to be added to the layout
        messagePanel = new MessagePanel();
        board = new DrawingBoard();
        toolPanel = new ToolPanel(board);
        
        
        //Adds the menu bar to the frame
        menuBar = new TopMenuBar(board, messagePanel, this);
        this.setJMenuBar(menuBar);
          
        //Creates a new gridbaglayout
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        //Sets the drawing panel to take up 80% of the x direction
        //and 90% of the y direction putting it in the top left.
        gc.weightx = 0.7;
        gc.weighty = 0.925;
        gc.gridx = 0;
        gc.gridy = 0; 
        gc.fill = GridBagConstraints.BOTH;
        board.setPreferredSize(new Dimension(600,800));
        content.add(board, gc);
        
        //Sets the message panel to take up 20% of the x direction
        //and 90% of the y direction putting it in the top right.
        gc.weightx = 0.3;
        gc.weighty = 0.925;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.BOTH;
        Border messageBorder = BorderFactory.createRaisedBevelBorder();
        messagePanel.setBorder(messageBorder);
        messagePanel.setPreferredSize(new Dimension(100,800));
        content.add(messagePanel, gc);
        
        //Sets the toolbox panel to take up 100% of the x direction
        //and 10% of the y direction putting it along the bottom      
        gc.weightx = 1;
        gc.weighty = 0.075;
        gc.gridx = 0;
        gc.gridy = 1;  
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.BOTH;
        Border toolBorder = BorderFactory.createLineBorder(Color.BLACK);
        toolPanel.setBorder(toolBorder);
        toolPanel.setPreferredSize(new Dimension(100,800));
        content.add(toolPanel, gc);
    }
   
    public static void main(String[] args) 
    {
        
        MainFrame window = new MainFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setPreferredSize(new Dimension(900,900));
        window.setMinimumSize(new Dimension(900,900));
        window.setVisible(true);   
    }
  
}
    

