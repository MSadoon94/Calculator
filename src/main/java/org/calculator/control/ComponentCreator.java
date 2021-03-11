package org.calculator.control;

import org.calculator.extraction.ExtractionBoundary;
import org.calculator.extraction.ExtractionController;
import org.calculator.processing.Invoker;
import org.calculator.processing.ProcessorBoundary;
import org.calculator.processing.ProcessorController;
import org.calculator.request.*;
import org.calculator.user.*;

import javax.swing.*;

public class ComponentCreator {
	private RequestBoundary requestController = new RequestController();
	private ProcessorBoundary processorBoundary = new ProcessorController();
	private ExtractionBoundary extractionBoundary = new ExtractionController();
	private UserBoundary userBoundary = new UserController();

	public ComponentCreator(){
		UiActions ui = createGui();

		Invoker invoker = processorBoundary.answerInvoker(extractionBoundary.groupExtractor());
		Observer reqObserver = requestController.requestObserver(invoker);
		ui.attachObserver(reqObserver);
	}

	private Gui createGui() {
		JFrame frame = new JFrame("Gui");
		Gui gui = new Gui(frame);
		addGuiDependencies(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(400,500);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		return gui;
	}

	private void addGuiDependencies(Gui gui){
		HistoryCache historyCache = new HistoryCache();
		gui.addInputCache(new HistoryCache());
		gui.addInputHistoryPanel(
				userBoundary.historyPanel(new JLabel("Input History"), historyCache));
		gui.addAnswerHistoryPanel(
				userBoundary.historyPanel(new JLabel("Answer History"), historyCache));
		gui.addTextAppendingPanel(
				userBoundary.textAppendingPanel(new JTextArea()));

	}

}
