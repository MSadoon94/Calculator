package org.calculator.extraction;

public interface ExtractionBoundary {
	Extractor groupExtractor();
	Extractor multiOperatorExtractor();
	Extractor valueExtractor();
}
