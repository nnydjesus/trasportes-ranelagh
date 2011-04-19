package ar.com.nny.base.ui.swing.components;

import java.text.Format;

import javax.swing.text.NumberFormatter;

import ar.com.nny.base.ui.swing.components.autocomplete.LimeTextField;

public class JFormattedDecimalTextField extends LimeTextField {

    private static final long serialVersionUID = 1L;
    private static Format decimalFormat;
    static{
        NumberFormatter decimalFormat = new NumberFormatter();
        decimalFormat.setValueClass(Double.class);
    }

    public JFormattedDecimalTextField() {
        super(decimalFormat);
    }
}
