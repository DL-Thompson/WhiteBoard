
package WhiteBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

//Creates the panel where the paint color can be selected
public final class ColorSelectBoard extends JPanel
{
    
    private DrawingBoard board;
    private JPanel toolPanel;
    private GridBagLayout layout;
    private GridBagConstraints gc;
    
    public ColorSelectBoard(DrawingBoard _board, JPanel _toolPanel)
    {
        board = _board;
        toolPanel = _toolPanel;
        
        //Creates a GridBagLayout for arranging the colors
        layout = new GridBagLayout();
        this.setLayout(layout);
        gc = new GridBagConstraints();
        
        //Sets some GridBagConstraints
        gc.insets = new Insets(3,3,3,3);
        gc.weightx = 0;
        gc.weighty = 0;
        
        //Adds the color select buttons
        setGC(0,0);
        this.add(makeColorButton(Color.BLACK),gc);
        
        setGC(1,0);
        this.add(makeColorButton(Color.DARK_GRAY),gc);
        
        setGC(2,0);
        this.add(makeColorButton(Color.GRAY),gc);
        
        setGC(3,0);
        this.add(makeColorButton(Color.LIGHT_GRAY),gc);
        
        setGC(4,0);
        this.add(makeColorButton(new Color(128,0,0)),gc); //Maroon
        
        setGC(5,0);
        this.add(makeColorButton(new Color(175,0,0)),gc); //Medium Red
        
        setGC(6,0);
        this.add(makeColorButton(Color.RED),gc);
        
        setGC(7,0);
        this.add(makeColorButton(new Color(255,99,71)),gc); //Tomato
        
        setGC(0,1);
        this.add(makeColorButton(new Color(0,0,139)),gc); //Dark Blue
        
        setGC(1,1);
        this.add(makeColorButton(Color.BLUE),gc);
        
        setGC(2,1);
        this.add(makeColorButton(Color.CYAN),gc);
        
        setGC(3,1);
        this.add(makeColorButton(new Color(135,206,235)),gc); //Sky Blue
        
        setGC(4,1);
        this.add(makeColorButton(new Color(128,0,128)),gc); //Purple
        
        setGC(5,1);
        this.add(makeColorButton(Color.MAGENTA),gc);
        
        setGC(6,1);
        this.add(makeColorButton(new Color(238,130,238)),gc); //Violet
        
        setGC(7,1);
        this.add(makeColorButton(Color.PINK),gc);
        
        setGC(0,2);
        this.add(makeColorButton(new Color(0,100,0)),gc); //Dark Green
        
        setGC(1,2);
        this.add(makeColorButton(new Color(0,128,0)),gc); //Green
        
        setGC(2,2);
        this.add(makeColorButton(Color.GREEN),gc);
        
        setGC(3,2);
        this.add(makeColorButton(new Color(144,238,144)),gc); //Light Green
               
        setGC(4,2);
        this.add(makeColorButton(new Color(160,82,45)),gc); //Sienna Brown
        
        setGC(5,2);
        this.add(makeColorButton(new Color(255,165,0)),gc); //Orange
        
        setGC(6,2);
        this.add(makeColorButton(Color.YELLOW),gc);
        
        setGC(7,2);
        this.add(makeColorButton(Color.WHITE),gc);
    }
    
   //Function to create the paint color select buttons
    public JButton makeColorButton(final Color color)
    {
        JButton tempButton = new JButton();
        tempButton.setBackground(color);
        tempButton.setPreferredSize(new Dimension(30, 30));
        
        tempButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                board.changeColor(color);
            }
        });
        
        return tempButton;
    }
    
    //Sets the grid location for each button;
    private void setGC(int gx, int gy)
    {
        gc.gridx = gx;
        gc.gridy = gy;
    }
    
}
