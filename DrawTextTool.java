package WhiteBoard;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.border.Border;

public class DrawTextTool extends JButton implements MouseListener, MouseMotionListener
{
    private DrawingBoard board;
    private BufferedImage canvas;
    private int x, y, prevX, prevY;
    private Graphics2D g2;
    
    public DrawTextTool(DrawingBoard _board)
    {
        super("Text");
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
        //Disconnects the current mouse listeners, then, reconnects this listener
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
        prevX = e.getX();
        prevY = e.getY();
        
        System.out.println("Box added.");
        
        JWindow textBoxPanel = new JWindow();
        //JFrame textBoxPanel = new JFrame();
        //JDialog textBoxPanel = new JDialog();
        //textBoxPanel.setBackground(new Color(0,0,0,0));
        //AWTUtilities.setWindowOpacity(textBoxPanel, 0);
        JTextArea textBox = new JTextArea();
        //JScrollPane scroll = new JScrollPane(textBox);
        textBoxPanel.add(textBox);
        //textBoxPanel.setUndecorated(true);
        textBoxPanel.setVisible(true);
        textBox.requestFocus();
        //textBox.setOpaque(true);
        Border line = BorderFactory.createLineBorder(Color.black);
        //textBoxPanel.setBorder(line);
        textBoxPanel.setBounds(prevX, prevY, 150, 150);
        //textBoxPanel.setUndecorated(true);
        //board.add(textBoxPanel);
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        //Does Nothing
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
        //Does Nothing
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        //Does Nothing
    }

}
