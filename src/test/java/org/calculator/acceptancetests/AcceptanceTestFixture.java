package org.calculator.acceptancetests;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextAreaOperator;

public abstract class AcceptanceTestFixture {
	protected abstract void startInputtingRequest();

	protected JFrameOperator jFrameOperator(){
		return new JFrameOperator("Gui");
	}
	protected JTextAreaOperator jTextAreaOperator(JFrameOperator frameOperator){
		return new JTextAreaOperator(frameOperator, 0);
	}
	protected JButtonOperator jButtonOperator(JFrameOperator frameOperator, String buttonText){
		return new JButtonOperator(frameOperator, buttonText);
	}
}
