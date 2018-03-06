
package WhiteBoard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

//Creates the board to be painted on
public class DrawingBoard extends JComponent
{
    private int x, y, prevX, prevY;
    private Graphics2D g2;
    private BufferedImage canvasBack;
    private ServerThread server;
    private ClientThread client;
    private Color color;
    private Boolean firstCanvas;
    private MouseListener ml;
    private MouseMotionListener mml;
    private Stroke stroke;
        
    public DrawingBoard()
    {
        //Sets some default values
        setDoubleBuffered(false);
        color = Color.BLACK;
        firstCanvas = true;  
        stroke = new BasicStroke(1);
    }
    
    @Override
    public void paintComponent(final Graphics g)
    {
        //Gets the current image 
        canvasBack = getCanvas();
        
        //Paints to the current image
        if (canvasBack != null)
        {
            g2 = (Graphics2D) canvasBack.getGraphics();  
            g.drawImage(canvasBack, 0, 0, this);
            if (firstCanvas == true)
            {
                //If this is the first painting of the canvas, clear it
                clear();
                firstCanvas = false;
            }
        }
    }
    
    public void clear()
    {
        //Clears the image.
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(getColor());
        g2.setStroke(stroke);
        repaint();
    }
    
    public void changeColor(Color _color)
    {
        //Changes the color of the paint brush
        color = _color;
        repaint();
    }
    
    public BufferedImage getCanvas()
    {
        //Returns a new image that is a copy of the current canvas
        BufferedImage copyImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) copyImg.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(canvasBack, 0, 0, this);
        g.dispose();
        return copyImg;
    }
    
    public synchronized void updateCanvas(BufferedImage _canvas)
    {
        //Updates the current canvas with any canvas received
        this.canvasBack = _canvas;
        repaint();
        
    }
    
    public void linkServer(ServerThread _server)
    {
        //Links the server object to the server thread so updates can be sent
        server = _server;
    }
    
    public void linkClient(ClientThread _client)
    {
        //Links the client object to the client thread so updates can be sent
        client = _client;
    }
    
    public void sendCanvas()
    {
        //Sends the canvas image across the network connection
        if (client == null && server != null)
                {
                    server.sendImage();
                }
                else if (server == null && client != null)
                {
                    client.sendImage();
                }
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public void setStroke(Stroke _stroke)
    {
        stroke = _stroke;
    }
    
    public Stroke getStroke()
    {
        return stroke;
    }
    
    public void removeListeners()
    {
        //Removes the current mouse listeners
        this.removeMouseListener(ml);
        this.removeMouseMotionListener(mml);
    }
    
    public void setListeners(MouseListener _ml, MouseMotionListener _mml)
    {
        //Sets the new mouse listeners
        ml = _ml;
        mml = _mml;
        this.addMouseListener(ml);
        this.addMouseMotionListener(mml);
    }
}
    
   

