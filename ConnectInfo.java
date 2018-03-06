package WhiteBoard;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectInfo extends JOptionPane
{
    private JOptionPane optionPane;
    private JTextField nameField;
    private JTextField ipField;
    private String name, ip;
    
    public ConnectInfo()
    {
        System.out.println("Connect Box created.");
        
        nameField = new JTextField(15);
        ipField = new JTextField(15);
        
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name: "));
        inputPanel.add(nameField);
        inputPanel.add(Box.createHorizontalStrut(15));
        inputPanel.add(new JLabel("IP Address: "));
        inputPanel.add(ipField);
        int result = this.showConfirmDialog(null, inputPanel, "Enter a name and IP.", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION)
        {
            name = nameField.getText();
            ip = ipField.getText();
        }
        
        this.setVisible(true);
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getIP()
    {
        return ip;
    }
}
