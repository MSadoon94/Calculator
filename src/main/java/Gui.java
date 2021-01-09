import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener{

	private JPanel panel1;
	private JTextArea mainTextArea;
	private JButton button1;
	private RequestServices requestServices;

	public Gui(JFrame frame, RequestServices services){
		requestServices = services;
		button1.addActionListener(this);
		frame.add(panel1);
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Gui");
		Gui gui = new Gui(frame, new RequestHandler());
		frame.setContentPane(gui.panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1){
			requestServices.sendRequest(mainTextArea.getText().trim());

		}
	}
}

