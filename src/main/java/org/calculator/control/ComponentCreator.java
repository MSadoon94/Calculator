package org.calculator.control;

import org.calculator.extraction.ExtractionBoundary;
import org.calculator.extraction.ExtractionController;
import org.calculator.processing.Invoker;
import org.calculator.processing.ProcessorBoundary;
import org.calculator.processing.ProcessorController;
import org.calculator.user.*;
import org.calculator.verification.VerificationBoundary;
import org.calculator.verification.VerificationController;
import org.calculator.verification.Verifier;

import javax.swing.*;

public class ComponentCreator {
	private ProcessorBoundary processorBoundary = new ProcessorController();
	private ExtractionBoundary extractionBoundary = new ExtractionController();
	private VerificationBoundary verificationBoundary = new VerificationController();
	private UserBoundary userBoundary = new UserController();
	private Verifier verifier;
	private Invoker answerInvoker;
	private AccessoryPanel inputHistory, answerHistory;
	private CompositePanel entryPanel;
	private JFrame frame;


	public ComponentCreator() {
		answerInvoker = processorBoundary.answerInvoker(extractionBoundary.groupExtractor());
		frame = new JFrame("Calculator");
		Ui gui = userBoundary.gui(frame);
		verifier = verificationBoundary.verifier(gui);
		addGuiDependencies(gui);
		frame();
	}

	private JFrame frame(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(500, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		return frame;
	}

	private void addGuiDependencies(Ui gui) {

		createPanels();
		gui.addPanels(inputHistory, answerHistory, entryPanel);
	}

	private void createPanels() {
		inputHistory = historyPanel(new JLabel("Input History"));
		answerHistory = historyPanel(new JLabel("Answer History"));

		Observer historyObserver = userBoundary.historyObserver(inputHistory, answerHistory);

		entryPanel = userBoundary.entryPanel(
				verifier,
				answerInvoker,
				historyObserver);

	}

	private AccessoryPanel historyPanel(JLabel label) {
		AccessoryPanel panel = userBoundary.historyPanel(label, userBoundary.userCache());
		panel.setName(label.getText());
		return panel;
	}
}
