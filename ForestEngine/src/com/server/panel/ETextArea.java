package com.server.panel;

/**
 * @author Buzzyboy
 * @date 03/20/2014
 */

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class ETextArea extends OutputStream {
	private JTextArea textControl;

	public ETextArea(JTextArea control) {
		textControl = control;
		javax.swing.text.DefaultCaret caret = (javax.swing.text.DefaultCaret) textControl
				.getCaret();
		caret.setUpdatePolicy(javax.swing.text.DefaultCaret.ALWAYS_UPDATE);
	}

	public void write(int b) throws IOException {
		textControl.append(String.valueOf((char) b));
	}
}