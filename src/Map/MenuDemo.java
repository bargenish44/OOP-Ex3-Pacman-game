package Map;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class MenuDemo extends JFrame implements ActionListener
{
	JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("File");
	JMenu menu1 = new JMenu("Edit");
	JMenuItem item1 = new JMenuItem("New");
	JMenuItem item2 = new JMenuItem("Open");
	JButton myButton=new JButton("Click");
	public MenuDemo()
	{
		setJMenuBar(menuBar);
		setVisible(true);
		setSize(400,200);
		menuBar.add(menu);
		menuBar.add(menu1);
		item1.addActionListener(this);
		myButton.addActionListener(this);
		menu.add(item1);menu.add(item2);
		JPanel panel=new JPanel();
		setTitle("JLabel Font Change");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(panel);
		panel.setLayout(null);
		myButton.setBounds(95,55,90,50);
		panel.add(myButton);
	}
	public void actionPerformed(ActionEvent e){
		// TODO Auto-generated method stub
		try {
			if(e.getSource()==item1){
				System.out.println("\n");
				System.out.println("You have Clicked on JMenu item");
				System.out.println("Put Your Menu Item Action Condition here");
			}
			else if(e.getSource()==myButton){
				System.out.println("\n");
				System.out.println("You have Clicked on Button item");
				System.out.println("Put Your Button Action Condition here");
			}
		}
		catch(Exception exc){
			Exception newEx = new Exception("Error at:"+new java.util.Date()+"",exc);
			newEx.printStackTrace();
		}
	}
	public static void main(String[]args)
	{
		MenuDemo ex=new MenuDemo();
		ex.setVisible(true);
	}
}