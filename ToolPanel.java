
package WhiteBoard;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

//Creates the Panel to hold the colors and tools
public class ToolPanel extends JPanel
{
    
    private ColorSelectBoard colorSelectBoard;
    private DrawingBoard board; 
    private GridBagLayout layout;
    private GridBagConstraints gc;
    
    public ToolPanel(DrawingBoard _board)
    {
        board = _board;
        colorSelectBoard = new ColorSelectBoard(board, this);
        
        //Creates a GridBagLayout to manage all the tools that
        //will be added to the toolPanel.
        layout = new GridBagLayout();
        this.setLayout(layout);
        gc = new GridBagConstraints();
        
        //Places the color selection palette to the left of the 
        //toolPanel.
        gc.weightx = 0.01;
        gc.weighty = 1.0;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.WEST;
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border colorSelectBoardBorder = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
        colorSelectBoard.setBorder(colorSelectBoardBorder);
        this.add(colorSelectBoard, gc);
        
        //Panel to hold the various drawing tools
        ToolButtonPanel temp = new ToolButtonPanel(board);
        gc.weightx = 0.99;
        gc.weighty = 1.0;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.BOTH;
        this.add(temp, gc);
        
    }
}
