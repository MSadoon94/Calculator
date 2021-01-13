import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui implements ActionListener, UiBoundary{

	private RequestServices reqServices;
	private Observer reqObserver;
	private JPanel panel1;
	private JTextArea mainTextArea;
	private JButton button1;



	public Gui(JFrame frame, RequestServices services){
		reqServices = services;
		button1.addActionListener(this);
		frame.add(panel1);
		frame.setContentPane(panel1);
	}

	public void attachObserver(Observer observer) {
		reqObserver = observer;
	}

	public Request getRequest() {
		return reqServices.getRequest();
	}

	public void setResponse(String response) {
		mainTextArea.setText(response);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1){
			addRequest();
			sendRequest(mainTextArea.getText().trim());
		}
	}

	private void addRequest() {
		reqServices.addRequest(mainTextArea.getText().trim());
	}

	private void sendRequest(String in){
		reqObserver.update(in);
	}



}

