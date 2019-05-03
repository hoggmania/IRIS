package com.temenos.interaction.sdk.util;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

/**
 * This class helps in managing the indentation when building pretty printing
 * XML content.
 * 
 */
public class IndentationFormatter {
	private static final IndentationFormatter INSTANCE = new IndentationFormatter();
	private static final String INDENT_SIZE = "    ";

	private int currentIndent;

	/**
	 * Returns the singleton instance of the {@link IndentationFormatter}.
	 * 
	 * @return instance
	 */
	public static IndentationFormatter getInstance() {
		return INSTANCE;
	}

	private IndentationFormatter() {
		currentIndent = 0;
	}

	/**
	 * Increases indent.
	 */
	public void indent() {
		currentIndent++;
	}

	/**
	 * Decreases indent.
	 */
	public void outdent() {
		if (currentIndent > 0) {
			currentIndent--;
		}
	}

	/**
	 * Returns a string of spaces for the current indent.
	 * @return current indent
	 */
	public String currentIndent() {
		StringBuilder indentSpaces = new StringBuilder();
		for (int size = 0; size < currentIndent; size++) {
			indentSpaces.append(INDENT_SIZE);
		}
		return indentSpaces.toString();
	}
}
