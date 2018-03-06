
package WhiteBoard;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MessagePanel extends JPanel
{
    private JTextArea userText;
    private JTextArea chatWindow;
    private GridBagLayout layout;
    private GridBagConstraints gc;
    private ServerThread server;
    private ClientThread client;
    
    //Creates the chat window
    public MessagePanel()
    {
        server = null;
        client = null;
        userText = new JTextArea();
        chatWindow = new JTextArea(2, 30);
        JScrollPane scrollPane = new JScrollPane(chatWindow);
        
        //Creates a GridBagLayout to hold the various chat components
        layout = new GridBagLayout();
        this.setLayout(layout);
        gc = new GridBagConstraints();
        
        //Sets the inset border spacing
        gc.insets = new Insets(5,5,5,5);
        
        //Places the chat window in the panel
        gc.weightx = 0;
        gc.weighty = 0.9;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.BOTH;
        chatWindow.setLineWrap(true);
        chatWindow.setWrapStyleWord(true);
        Font font = new Font("Calibri", Font.PLAIN, 18);
        chatWindow.setFont(font);
        this.add(new JScrollPane(chatWindow), gc);
        
        //places the box to enter text into the panel
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.fill = GridBagConstraints.BOTH;
        userText.setLineWrap(true);
        userText.setWrapStyleWord(true);
        userText.setFont(font);
        userText.addKeyListener(
                //When the enter key is pressed, the message in the text
                //area is sent to the chatWindow
                new KeyListener()
                {

                    @Override
                    public void keyTyped(KeyEvent e) 
                    {
                        //Does Nothing
                    }

                    @Override
                    public void keyPressed(KeyEvent e) 
                    {
                        //Checks to see if the key pressed is the enter key
                        int keyCode = e.getKeyCode();
                        if (keyCode == KeyEvent.VK_ENTER)
                        {
                            //Sends the entered message to the chat window
                            String userTextMessage = userText.getText();                           
                            userText.setText("");
                           
                           if (client == null)
                           {
                            server.sendMessage(userTextMessage);
                           }
                           else
                           {
                               client.sendMessage(userTextMessage);
                           }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) 
                    {
                        //Checks to see if the key released is the enter key
                        int keyCode = e.getKeyCode();
                        if (keyCode == KeyEvent.VK_ENTER)
                        {
                            //Reset the cursor position to the top of the box
                            userText.setCaretPosition(0);
                        }
                    }                  
                }
        );
        this.add(new JScrollPane(userText), gc);
    }
    
    public synchronized void postMessage(String message)
    {
        //Prints the message to the current program window
        message = message.replace("\n","");
        chatWindow.append(message + "\n");
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
}
