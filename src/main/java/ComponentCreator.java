import javax.swing.*;

public class ComponentCreator {
	private AnswerServices ansServices;
	private RequestServices reqServices;
	private Processor processor;
	private RequestAnalyzer analyzer;

	public ComponentCreator(){
		createServices();
		UiBoundary ui = createGui();
		processor = new Processor(ui, ansServices);
		analyzer = new RequestAnalyzer(processor, new RequestBuilder());
		Observer reqObserver = new RequestObserver(analyzer);
		ui.attachObserver(reqObserver);
	}

	private Gui createGui() {
		JFrame frame = new JFrame("Gui");
		Gui gui = new Gui(frame, reqServices);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		return gui;
	}

	private void createServices(){
		ansServices = new AnswerHandler();
		reqServices = new RequestHandler();
	}
}
