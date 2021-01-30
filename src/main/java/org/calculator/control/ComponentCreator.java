package org.calculator.control;

import org.calculator.answer.AnswerHandler;
import org.calculator.answer.AnswerServices;
import org.calculator.processing.ProcessorActions;
import org.calculator.processing.ProcessorBoundary;
import org.calculator.processing.ProcessorController;
import org.calculator.request.*;
import org.calculator.user.Gui;
import org.calculator.user.UiActions;

import javax.swing.*;

public class ComponentCreator {
	private AnswerServices ansServices;
	private RequestServices reqServices;
	private ProcessorActions processor;
	private RequestBoundary requestController = new RequestController();
	private ProcessorBoundary processorBoundary = new ProcessorController();

	public ComponentCreator(){
		createServices();
		UiActions ui = createGui();
		processor = processorBoundary.processorActions(ui,ansServices);
		Analyzer analyzer = requestController.requestAnalyzer(processor, requestController.requestBuilder());
		Observer reqObserver = requestController.requestObserver(analyzer);
		ui.attachObserver(reqObserver);
	}

	private Gui createGui() {
		JFrame frame = new JFrame("Gui");
		Gui gui = new Gui(frame, reqServices);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(300,400);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		return gui;
	}

	private void createServices(){
		ansServices = new AnswerHandler();
		reqServices = requestController.requestServices();
	}
}
