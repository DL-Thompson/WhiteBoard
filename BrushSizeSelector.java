package WhiteBoard;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class BrushSizeSelector extends JPanel
{
    private DrawingBoard board;
    
    public BrushSizeSelector(DrawingBoard _board)
    {
        board = _board;
        
        //Creates a layout and border for the brush size panel
        TitledBorder title = BorderFactory.createTitledBorder("Brush Size");
        this.setBorder(title);
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        this.setLayout(layout);
        
        //Creates the large brush stroke button
        JButton largeButton = new JButton("Large");
        largeButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                board.setStroke(new BasicStroke(8));
                
            }           
        });     
        
        //Creates the medium brush stroke button
        JButton mediumButton = new JButton("Medium");
        mediumButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                board.setStroke(new BasicStroke(4));
                
            }           
        });
        
        //Creates the small brush stroke button
        JButton smallButton= new JButton("Small");
        smallButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                board.setStroke(new BasicStroke(2));
                
            }           
        });
        
        //Creates the thin brush stroke button
        JButton thinButton= new JButton("Thin");
        thinButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) 
            {
                board.setStroke(new BasicStroke(1));
                
            }           
        });
        
        //Adds the buttons to the panel
        gc.gridy = 0;
        
        gc.gridx = 0;
        this.add(thinButton, gc);
        
        gc.gridx = 1;
        this.add(smallButton, gc);
        
        gc.gridx = 2;
        this.add(mediumButton, gc);
        
        gc.gridx = 3;
        this.add(largeButton, gc);
    }    
}
