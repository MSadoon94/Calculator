package org.calculator.control;

import org.calculator.extraction.ExtractionBoundary;
import org.calculator.extraction.ExtractionController;
import org.calculator.processing.Invoker;
import org.calculator.processing.ProcessorBoundary;
import org.calculator.processing.ProcessorController;
import org.calculator.user.*;

import javax.swing.*;
import java.util.stream.Stream;

public class ComponentCreator {
	private ProcessorBoundary processorBoundary = new ProcessorController();
	private ExtractionBoundary extractionBoundary = new ExtractionController();
	private UserBoundary userBoundary = new UserController();
	private Invoker answerInvoker;
	private AccessoryPanel inputHistory, answerHistory;
	private Panel appender, functions;
	private Panel2 entryPanel;


	public ComponentCreator(){
		answerInvoker = processorBoundary.answerInvoker(extractionBoundary.groupExtractor());
		createGui();
	}

	private Ui createGui() {
		JFrame frame = new JFrame("Gui");
		Ui gui = userBoundary.gui(frame);
		addGuiDependencies(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(500,600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		return gui;
	}

	private JFrame frame(){
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(500,600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		return frame;
	}

	private void addGuiDependencies(Ui gui){
		panelStream( gui).forEach(gui::setPanels);
		gui.addPanelsToMainPanel();
	}

	private Stream<Panel> panelStream(Ui gui){
		inputHistory = historyPanel(new JLabel("Input History"));
		answerHistory = historyPanel(new JLabel("Answer History"));

		Observer historyObserver = userBoundary.historyObserver(inputHistory, answerHistory);

		entryPanel = userBoundary.entryPanel(gui, answerInvoker, historyObserver);
		frame().add(entryPanel.panel());
		appender = userBoundary.textAppendingPanel(gui);
		functions = userBoundary.textFunctionPanel(gui, answerInvoker, historyObserver);

		return namedPanels();
	}

	private AccessoryPanel historyPanel(JLabel label){
		return userBoundary.historyPanel(label, userBoundary.userCache());
	}

	private Stream<Panel> namedPanels(){
		inputHistory.setName("InputHistory");
		answerHistory.setName("AnswerHistory");
		appender.setName("Appender");
		functions.setName("Functions");

		return Stream.of(inputHistory, answerHistory, appender, functions);
	}
}
