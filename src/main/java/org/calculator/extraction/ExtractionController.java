package org.calculator.extraction;

public class ExtractionController implements ExtractionBoundary{

	public ExtractorUtilities groupExtractor() {
		return new GroupExtractor();
	}

	public Extractor multiOperatorExtractor() {
		return new MultiOperatorExtractor();
	}
}
