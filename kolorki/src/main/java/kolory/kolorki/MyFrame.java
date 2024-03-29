package kolory.kolorki;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener{

	final static double PROBABILITY = 1;
	private MyPanel panel;
	private JTextField tfProbability;
	private JLabel lProbability;
	private JButton btnProbability;
	
	public MyPanel getPanel() {
		return panel;
	}

	public void setPanel(MyPanel panel) {
		this.panel = panel;
	}

	public MyFrame()
	{
		setSize(540,600);
		setLayout(null);
		setTitle("Kolorki");
		tfProbability = new JTextField();
		tfProbability.setBounds(350, 30, 30, 30);
		lProbability = new JLabel("Podaj Prawdopodobieńśtwo zmiany koloru ");
		lProbability.setBounds(30, 30, 380, 30);
		btnProbability = new JButton("OK");
		btnProbability.setBounds(400, 30, 60, 30);
		btnProbability.addActionListener(this);
		add(tfProbability);
		add(lProbability);
		add(btnProbability);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		
		if(panel!=null) changeProbability();
		else {
			
		initializePanel();
		
		Thread t[] = new Thread[400];
		int i = 0;
		for(RectRunnable r: getPanel().getRrun())
		{
			r.setG(getPanel().getGraphics());
			t[i] = new Thread(r);
			t[i].start();
			i++;
		}
		}
	}
	
	public void initializePanel()
	{
			
		try {
			double probability = Double.parseDouble(tfProbability.getText());
			if(probability<1 && probability>0)
			{
				panel = new MyPanel(probability);
			}
			else 
			{
				tfProbability.setText("1");
				panel = new MyPanel(PROBABILITY);
			}
		
		}
		catch(Exception exp)
		{
			tfProbability.setText("1");
			panel = new MyPanel(PROBABILITY);
		}
		panel.setSize(400,400);
		panel.setLocation(30,70);
		add(panel);
		getPanel().setVisible(true);
		getPanel().paintComponent(panel.getGraphics());
		getPanel().initialize();
	}
	
	public void changeProbability()
	{
		try {
			double probability = Double.parseDouble(tfProbability.getText());
			if(probability<1 && probability>0)
			{
				panel.setProbability(probability);
			}
			else 
			{
				tfProbability.setText("1");
				panel.setProbability(PROBABILITY);
			}
		
		}
		catch(Exception exp)
		{
			tfProbability.setText("1");
			panel.setProbability(PROBABILITY);
		}
	}
	
}
