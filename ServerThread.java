package WhiteBoard;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import sun.awt.image.ToolkitImage;

public class ServerThread implements Runnable
{
    private DrawingBoard board;
    private MessagePanel messagePanel;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    
    public ServerThread(DrawingBoard _board, MessagePanel _messagePanel)
    {
        board = _board;
        messagePanel = _messagePanel;   
    }
    
    @Override
    public void run() 
    {
        messagePanel.linkServer(this);
        board.linkServer(this);
        
        try
        {
            server = new ServerSocket(6789, 100);
            
            //Infinite loop to keep the server checking for connections and updating
            while(true)
            {
                try
                {
                    waitForConnection();
                    setupStreams();
                    updateProgram();
                }
                catch (EOFException ex)
                {
                    sendMessage("\nHost has closed the connection.");
                }
                finally
                {
                    closeServer();
                }
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }       
    }
    
    private void updateProgram()
    {
        Object temp;
        
        do
        {
            try 
            {
                //Receiving Information
                //Receives the object from the connection
                temp = input.readObject();
                
                //Checks to see if the object is a message or an image and tries to display it
                if (temp instanceof String)
                {
                    String message = (String) temp;
                    receiveMessage(message);
                }
                else if (temp instanceof ImageIcon)
                {
                    //If the object received is an image, remove the canvas deom its wrapper and
                    //send it to be displayed on the board
                    ImageIcon canvas = (ImageIcon) temp;
                    BufferedImage canvas2 = ((ToolkitImage)canvas.getImage()).getBufferedImage();
                    postImage(canvas2);
                }
            } 
            catch (IOException | ClassNotFoundException ex) 
            {
                Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(true);
    }
    
    //Waits for a client to connect and sets up the connection
    private void waitForConnection() throws IOException
    {
        showMessage("Waiting for someone to connect...\n");
        connection = server.accept();
        sendMessage(connection.getInetAddress().getHostName() + " has connected.\n");
    }
    
    //Sets up the input and output streams to send a receive data
    private void setupStreams() throws IOException
    {
        //Sets up the output connection
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        
        //Sets up the input connection
        input = new ObjectInputStream(connection.getInputStream());
    }
    
    private void closeServer()
    {
        showMessage("\n" + connection.getInetAddress().getHostName() + " has disconnected.\n");
        try
        {
            output.close();
            input.close();
            connection.close();
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }
    
    //Function to send a message to the chatWindow
    public void sendMessage(final String message)
    {  
        //Posts a message to the chat window and sends it through the connection
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    @Override
                    public void run() 
                    {
                        try
                        {
                            //Posts the message to the server chat window
                            messagePanel.postMessage("Server: " + message);
                            //Sends the message to the client
                            output.writeObject("Server: " + message);
                            output.flush();
                        }
                        catch (IOException ioException)
                        {
                            messagePanel.postMessage("Server: Error. Message could not be sent.");
                        }
                    }                   
                }
        );          
    }
    
    public void showMessage(final String message)
    {   
        //Sends the message received to the chatWindow
        //Used to display non chat related messages
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    @Override
                    public void run() 
                    {                        
                            //Posts the message to the server chat window
                            messagePanel.postMessage("Server: " + message);                       
                    }                   
                }
        );          
    }
    
    private void receiveMessage(final String message)
    {
        //Sends the client message to the chatWindow
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    @Override
                    public void run() 
                    {
                        messagePanel.postMessage(message);
                    }                   
                }
        );          
    }
    
    private void postImage(final BufferedImage image)
    {
        //Replaces the old image with the new image and calls repaint on the panel
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() 
                    {                       
                            board.updateCanvas(image);                       
                    }
                });    
    }
    
    public void sendImage() 
    {
        //Sends the image through the socket
        SwingUtilities.invokeLater(
                new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        try 
                        {
                            output.writeObject(new ImageIcon(board.getCanvas()));
                            output.flush();
                        } 
                        catch (IOException ex) 
                        {
                            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
    }
   
}
