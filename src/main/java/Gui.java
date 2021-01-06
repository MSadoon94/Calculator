import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui implements ActionListener{

	private JPanel panel1;
	private JTextArea mainTextArea;
	private JButton button1;
	private RequestHandler requestHandler;

	public Gui() {
		button1.addActionListener(this);
		requestHandler = new RequestHandler(new RequestCache());
	}
	public Gui(JFrame frame, RequestHandler requestHandler){
		this.requestHandler = requestHandler;
		button1.addActionListener(this);
		frame.add(panel1);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Gui");
		frame.setContentPane(new Gui().panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1){
			requestHandler.sendRequest(mainTextArea.getText().trim());
		}
	}
}

