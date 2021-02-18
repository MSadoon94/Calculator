package org.calculator.processing;
import org.calculator.common.Operations;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class OperationFinderTest {
	@Test
	void shouldPrioritizeDivisionAndMultiplicationOverAdditionAndSubtraction(){
		OperationFinder finder = new OperationFinder();

		assertThat(finder.targetOperation("2-1+10/2"), is(Operations.DIVISION));
		assertThat(finder.targetOperation("2-1+10*2"), is(Operations.MULTIPLICATION));
	}
	@Test
	void shouldPrioritizeRightMostOperationsWhenOperationsAreEqualPriority(){
		OperationFinder finder = new OperationFinder();

		assertThat(finder.targetOperation("2-1+10+2"), is(Operations.SUBTRACTION));
		assertThat(finder.targetOperation("2*1/10*2"), is(Operations.MULTIPLICATION));
	}
	@Test
	void shouldPrioritizeExponentOperationsOverDivisionAndMultiplication(){
		OperationFinder finder = new OperationFinder();

		assertThat(finder.targetOperation("8/2^3"), is(Operations.EXPONENT));
		assertThat(finder.targetOperation("8*2^3"), is(Operations.EXPONENT));
	}
}
