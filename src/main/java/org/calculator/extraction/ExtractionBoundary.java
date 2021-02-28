package org.calculator.extraction;

public interface ExtractionBoundary {
	ExtractorUtilities groupExtractor();
	Extractor multiOperatorExtractor();
	Extractor valueExtractor();
}
