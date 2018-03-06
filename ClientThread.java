package WhiteBoard;

import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import sun.awt.image.ToolkitImage;

public class ClientThread implements Runnable
{
    private DrawingBoard board;
    private MessagePanel messagePanel;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket connection;
    private String serverIP, name;
    
    public ClientThread(DrawingBoard _board, MessagePanel _messagePanel, String host, String _name) 
    {
        board = _board;
        messagePanel = _messagePanel;
        serverIP = host;
        name = _name;
        
    }
    
    @Override
    public void run() 
    {
        messagePanel.linkClient(this);
        board.linkClient(this);
        
        //Sets up the server
        try 
        {
            connectToServer();
            setupStreams();
            updateProgram();
        } 
        catch (EOFException eofExcepion) 
        {
            showMessage("\n" + name + " terminated connection.");
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        } 
        finally 
        {
            closeClient();
        }
    }
    
    private void updateProgram() throws IOException
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
            catch (ClassNotFoundException ex) 
            {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(true);
    }
    
    //Tries to connect to a server
    private void connectToServer() throws IOException 
    {
        showMessage("Attempting connection...\n");
        connection = new Socket(InetAddress.getByName(serverIP), 6789);
        showMessage("Connected to " + connection.getInetAddress().getHostName());
    }
    
    //Sets up the input and output
    private void setupStreams() throws IOException 
    {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("You are now connected.\n");
    }
    
    //Closes the connection
    private void closeClient() 
    {
        showMessage("Closing connectons...");
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
    
    public void sendMessage(final String message) 
    {
        //Posts a message to the chat window and sends it through the connection
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() 
                    {
                        try 
                        {
                            //Posts the message to the server chat window
                            messagePanel.postMessage(name + ": " + message);
                            //Sends the message to the client
                            output.writeObject(name + ": " + message);
                            output.flush();
                        } 
                        catch (IOException ioException) 
                        {
                            messagePanel.postMessage(name + ": " + "Error. Message could not be sent.");
                        }
                    }
                });
    }
    
    public void showMessage(final String message) 
    {   
        //Sends the message to the chatWindow
        //Used to display non chat related messages
        SwingUtilities.invokeLater(
                new Runnable() 
                {
                    @Override
                    public void run() 
                    {
                        //Posts the message to the server chat window
                        messagePanel.postMessage(name + ": " + message);
                    }
                });
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
                });
    }
    
    private void postImage(final BufferedImage image)
    {
        //Replaces the old image with the new image and calls repaint on the panel
        SwingUtilities.invokeLater(
                new Runnable() 
                {
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
