package WhiteBoard;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ToolButtonPanel extends JPanel
{
    private DrawingBoard board;
    
    //Creates a panel to hold buttons that link to the paint tools
    public ToolButtonPanel(DrawingBoard _board)
    {
        board = _board;
        
        //Sets the border
        Border tempBorder = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(tempBorder);
        
        //Sets the layout
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        this.setLayout(layout);
        
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 0.5;
        gc.weighty = 1.0;
        
        //Creates a button to select the paint brush tool
        PaintBrushTool paintBrushButton = new PaintBrushTool(board);
        gc.gridx = 0;
        gc.gridy = 0;
        this.add(paintBrushButton, gc);
        
        //Creates a button to select the rectangle drawer
        DrawRectangleTool rectangleButton = new DrawRectangleTool(board);
        gc.gridx = 1;
        gc.gridy = 0;
        this.add(rectangleButton, gc);
        
        //Creates a button to select the line drawer
        DrawLineTool lineButton = new DrawLineTool(board);
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(lineButton, gc);
        
        //Creates a button to select the circle drawer
        DrawCircleTool circleButton = new DrawCircleTool(board);
        gc.gridx = 1;
        gc.gridy = 1;
        this.add(circleButton, gc);
        
        //Creates a button to select the text inserter
        //DrawTextTool textButton = new DrawTextTool(board);
        //gc.gridx = 0;
        //gc.gridy = 2;
        //this.add(textButton, gc);
        
        EraseTool eraserButton = new EraseTool(board);
        gc.gridx = 0;
        gc.gridy = 2;
        this.add(eraserButton, gc);
        
        ClearButton clearButton = new ClearButton(board);
        gc.gridx = 1;
        gc.gridy = 2;
        this.add(clearButton, gc);
        
        //Blank Panel to fill space
        JPanel blank = new JPanel();
        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        this.add(blank, gc);
        
        //Creates the brush size selection panel
        BrushSizeSelector brushSizeButton = new BrushSizeSelector(board);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 2;
        this.add(brushSizeButton, gc);
    }

}
