
package WhiteBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

//Creates the menu bar to go at the top of the frame
public class TopMenuBar extends JMenuBar
{
    
    private DrawingBoard board;
    private MessagePanel messagePanel;
    private JMenu menu;
    private JMenuItem item;
    private MainFrame frame;
    
    public TopMenuBar(DrawingBoard _board, MessagePanel _messagePanel, MainFrame _frame)
    {
        board = _board;
        messagePanel = _messagePanel;
        frame = _frame;
        
        //Creates the File drop down menu
        menu = new JMenu("File");
        
        //Adds the exit item and function to the file menu
        item = new JMenuItem("Exit");
        item.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                        System.exit(0);
                    }
                }
        );
        menu.add(item);
        this.add(menu);
        
        
        //Creates the Connection drop down menu
        menu = new JMenu("Connection");
        
        //Adds the connect to a session to the Connection menu
        item = new JMenuItem("Connect");
        item.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                       ConnectInfo info = new ConnectInfo();
                       frame.setTitle("WhiteBoard: Client");
                       Thread clientThread = new Thread(new ClientThread(board, messagePanel, info.getIP(), info.getName()));
                       clientThread.start();       
                    }
                }
        );
        menu.add(item);
        menu.addSeparator();
        
        //Adds the host a session to the Connection menu
        item = new JMenuItem("Host");
        item.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent event)
                    {
                       frame.setTitle("WhiteBoard: Server");
                       Thread serverThread = new Thread(new ServerThread(board, messagePanel));
                       serverThread.start();       
                    }
                }
        );
        menu.add(item);
        this.add(menu);       
    }  
}
