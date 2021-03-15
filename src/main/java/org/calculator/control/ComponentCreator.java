package org.calculator.control;

import org.calculator.extraction.ExtractionBoundary;
import org.calculator.extraction.ExtractionController;
import org.calculator.processing.Invoker;
import org.calculator.processing.ProcessorBoundary;
import org.calculator.processing.ProcessorController;
import org.calculator.request.*;
import org.calculator.user.*;

import javax.swing.*;
import java.util.stream.Stream;

public class ComponentCreator {
	private RequestBoundary requestController = new RequestController();
	private ProcessorBoundary processorBoundary = new ProcessorController();
	private ExtractionBoundary extractionBoundary = new ExtractionController();
	private UserBoundary userBoundary = new UserController();
	private Observer reqObserver;


	public ComponentCreator(){
		Invoker invoker = processorBoundary.answerInvoker(extractionBoundary.groupExtractor());
		reqObserver = requestController.requestObserver(invoker);
		createGui();
	}

	private Gui createGui() {
		JFrame frame = new JFrame("Gui");
		Gui gui = new Gui(frame);
		addGuiDependencies(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(500,600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		return gui;
	}


	private void addGuiDependencies(Gui gui){
		HistoryCache historyCache = new HistoryCache();
		gui.addInputCache(historyCache);
		panelStream(historyCache, gui).forEach(gui::setPanels);
		gui.addPanelsToMainPanel();
	}

	private Stream<Panel> panelStream(HistoryCache historyCache, Gui gui){
		Panel inputHistory = userBoundary.historyPanel(new JLabel("Input History"), historyCache);
		Panel answerHistory = userBoundary.historyPanel(new JLabel("Answer History"), historyCache);
		Panel textAppending = userBoundary.textAppendingPanel();
		Panel textFunction = userBoundary.textFunctionPanel(gui, reqObserver);
		inputHistory.setName("InputHistory");
		answerHistory.setName("AnswerHistory");
		textAppending.setName("TextAppending");
		textFunction.setName("TextFunction");
		return Stream.of(inputHistory, answerHistory, textAppending, textFunction);
	}
}
