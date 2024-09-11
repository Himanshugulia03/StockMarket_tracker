import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Main2 {
	public static void main(String[] args){
		JFrame framee = new JFrame("Stock Market Tracker");
		framee.setSize(400,400);
		framee.setIconImage(new ImageIcon("C:/Users/ashug/Downloads/WhatsApp Image 2024-09-10 at 8.14.10 PM.jpeg").getImage());
		framee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon imageB = new ImageIcon("C:/Users/ashug/Downloads/WhatsApp Image 2024-09-10 at 8.58.36 PM.jpeg");
		BackgroundPanel panell = new BackgroundPanel(imageB.getImage());
		panell.setLayout(null);
		Font f2 = new Font("Arial", Font.BOLD, 15);

		JLabel l1 = new JLabel("Enter symbol:");
		l1.setForeground(Color.BLACK);
		l1.setFont(f2);
		l1.setBounds(100,20,120,30);

		JTextField f1 = new JTextField();
		f1.setBounds(220,20,80,30);
		f1.setFont(f2);
		f1.setBackground(Color.white);
		f1.setForeground(Color.black);
		f1.setBorder(new BevelBorder(BevelBorder.LOWERED));
		f1.setMargin(new Insets(1,10,1,10));

		JButton button = new JButton("Fetch Data");
		Font ft = new Font("Arial", Font.BOLD, 15);
		button.setFont(ft);
		Color co = new Color(0,128,0);
		button.setBackground(co);
		button.setForeground(Color.WHITE);
		button.setBorder(new BevelBorder(BevelBorder.RAISED));
		button.setBounds(150,100,100,35);

		JTextArea display = new JTextArea();
		display.setBounds(120,150,160,200);
		Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
		display.setBorder(blackBorder);
		display.setOpaque(false);

		panell.add(l1);
		panell.add(f1);
		panell.add(button);
		panell.add(display);

		framee.add(panell);

		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String symbol = f1.getText();

				if(symbol.isEmpty()){
					System.out.println("please enter symbol");
				}
				display.setOpaque(true);
				String response = Main.getdata(symbol);
				String result = Main.parsedata(response);

				display.setText(result);
			}
		});

		framee.setVisible(true);
	}
}

class BackgroundPanel extends JPanel{
	private Image backgroundIamge;

	public BackgroundPanel(Image imageB){
		this.backgroundIamge = imageB;
		setLayout(null);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (backgroundIamge != null) {
			g.drawImage(backgroundIamge, 0, 0, this);
		}
	}
	}
