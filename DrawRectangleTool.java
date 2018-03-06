package WhiteBoard;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

public class DrawRectangleTool extends JButton implements MouseListener, MouseMotionListener
{
    private DrawingBoard board;
    private BufferedImage canvas, oldCanvas;
    private int x, y, prevX, prevY;
    private Graphics2D g2;
 
    
    public DrawRectangleTool(DrawingBoard _board)
    {
        super("Rectangle");
        board = _board;
              
        this.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                setListeners();
            }           
        });     
    }
    
    private void setListeners()
    {
        board.removeListeners();
        board.setListeners(this, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        //Does Nothing
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        //Sets the start coordinates and gets a copy of the canvas to be saved
        //as a backup
        prevX = e.getX();
        prevY = e.getY();
        oldCanvas = board.getCanvas();
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        //Sends the drawn rectangle through the network
        board.sendCanvas();
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        //Does Nothing
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        //Does Nothing
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        //Gets the canvas and graphics
        canvas = board.getCanvas();    
        g2 = (Graphics2D) canvas.getGraphics();
        g2.setColor(board.getColor());
        g2.setStroke(board.getStroke());
        
        x = e.getX();
        y = e.getY();
        
        //Clears the image by drawing the old saved copy, then draws a new rectangle
        //outline for each diagonal drag of the mouse.
        g2.drawImage(oldCanvas, 0, 0, this);
        if (x >= prevX && y >= prevY)
        {
            g2.drawRect(prevX, prevY, x - prevX, y - prevY);
        }
        else if (x <= prevX && y <= prevY)
        {
            g2.drawRect(x, y, prevX-x, prevY-y);
        }
        else if (x >= prevX && y <= prevY)
        {
            g2.drawRect(prevX, y, x-prevX, prevY-y);
        }
        else if(x <= prevX && y >= prevY)
        {
            g2.drawRect(x, prevY, prevX-x, y-prevY);
        }
        board.updateCanvas(canvas);
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        //Does Nothing
    }
    
    
}
