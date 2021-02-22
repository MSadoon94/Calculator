package org.calculator.processing;
import org.calculator.common.Operations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class OperationFinderTest {

	private OperationFinder finder;

	@BeforeEach
	void setUp(){
		finder = new OperationFinder();
	}
	@Test
	void shouldPrioritizeDivisionAndMultiplicationOverAdditionAndSubtraction(){
		assertThat(finder.targetOperation("2-1+10/2"), is(Operations.DIVISION));
		assertThat(finder.targetOperation("2-1+10*2"), is(Operations.MULTIPLICATION));
	}
	@Test
	void shouldPrioritizeRightMostOperationsWhenOperationsAreEqualPriority(){
		assertThat(finder.targetOperation("2-1+10+2"), is(Operations.SUBTRACTION));
		assertThat(finder.targetOperation("2*1/10*2"), is(Operations.MULTIPLICATION));
	}
	@Test
	void shouldPrioritizeExponentOperationsOverDivisionAndMultiplication(){
		assertThat(finder.targetOperation("8/2^3"), is(Operations.EXPONENT));
		assertThat(finder.targetOperation("8*2^3"), is(Operations.EXPONENT));
	}

	@Test
	void shouldPrioritizeSquareRootOperationsOverDivisionAndMultiplication(){
		assertThat(finder.targetOperation("√4/2"), is(Operations.SQUARE_ROOT));
		assertThat(finder.targetOperation("√4*2"), is(Operations.SQUARE_ROOT));
	}
}
