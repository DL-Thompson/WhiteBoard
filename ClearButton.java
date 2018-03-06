package WhiteBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;

public class ClearButton extends JButton
{
    private DrawingBoard board;
    
    public ClearButton(DrawingBoard _board)
    {
        super("Clear");
        board = _board;   
        
        this.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                board.clear();
                board.sendCanvas();
            }           
        });
    }
}
    
    