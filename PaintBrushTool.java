package WhiteBoard;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

public class PaintBrushTool extends JButton implements MouseListener, MouseMotionListener
{
    private DrawingBoard board;
    private BufferedImage canvas;
    private int x, y, prevX, prevY;
    private Graphics2D g2;
    
    public PaintBrushTool(DrawingBoard _board)
    {
        //Gets the current drawing board and sets this button to be the default listener
        super("Brush");
        board = _board;
        setListeners();
        
        //Switches the drawing board tool from its current tool to this tool
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
        //Disconnects the current mouse listeners, then, reconnects this listener
        board.removeListeners();
        board.setListeners(this, this);
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        prevX = e.getX();
        prevY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        //Gets the image to be drawn on and gets the color to be used
        canvas = board.getCanvas();
        g2 = (Graphics2D) canvas.getGraphics();       
        g2.setColor(board.getColor());
        g2.setStroke(board.getStroke());
        
        
        x = e.getX();
        y = e.getY();

        //Draws a line from where the mouse was clicked to
        //where it was dragged to.
        g2.drawLine(prevX, prevY, x, y);
        

        //Set the current x y coordinates to the previous coordinates
        prevX = x;
        prevY = y;
        
        //Updates the program with the new drawing
        board.updateCanvas(canvas);
        board.sendCanvas();
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        //Does nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        //Does nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        //Does nothing
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        //Does nothing
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        //Does nothing
    }
}


