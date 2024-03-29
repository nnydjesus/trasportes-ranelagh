package ar.com.nny.base.ui.swing.components;


import java.awt.Color;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import ar.com.nny.base.ui.swing.components.autocomplete.LimeTextField;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.adapter.ColorSelectionAdapter;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.formatter.EmptyDateFormatter;
import com.jgoodies.binding.formatter.EmptyNumberFormatter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ComponentValueModel;
import com.jgoodies.binding.value.ConverterFactory;
import com.jgoodies.binding.value.ValueModel;

/**
 * Consists only of static methods that create and vend frequently used
 * Swing components that are then bound to a given ValueModel.
 * This class is one of two helper classes that help you establish a binding:
 * 1) the Bindings class binds components that have been created before;
 * it wraps ValueModels with the adapters from package
 * <code>com.jgoodies.binding.adapter</code>.
 * 2) this BasicComponentFactory creates Swing components that are then
 * tied to ValueModels using the the different <code>#bind</code> methods
 * in the Bindings class.<p>
 *
 * If you have an existing factory that vends Swing components, you can use
 * Bindings to bind them to ValueModels. If you don't have such a factory, you
 * can use this BasicComponentFactory to create and bind Swing components.<p>
 *
 * This class is intended to be used or extended by custom ComponentFactory
 * classes. Such a factory can create a broader variety of component types,
 * may use different default configurations, and can use your favorite
 * Formatters, FormatterFactories, etc.
 *
 * @author  Karsten Lentzsch
 * @version $Revision: 1.16 $
 *
 * @see com.jgoodies.binding.value.ValueModel
 * @see com.jgoodies.binding.adapter.Bindings
 */
public class ComponentFactory {


    protected ComponentFactory() {
        // Reduce the visibility of the default constructor.
    }


    // Factory Methods ********************************************************

    /**
     * Creates and returns a check box with the specified text label
     * that is bound to the given ValueModel. The check box is selected
     * if and only if the model's value equals <code>Boolean.TRUE</code>.<p>
     *
     * The model is converted to the required ToggleButtonModel
     * using a ToggleButtonAdapter.
     *
     * @param valueModel     the model that provides a Boolean value
     * @param text      the check boxes' text label
     * @return a check box with the specified text bound to the given model,
     *     selected if the model's value equals Boolean.TRUE
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JCheckBox createCheckBox(ValueModel valueModel, String text) {
        JCheckBox checkBox = new JCheckBox(text);
        Bindings.bind(checkBox, valueModel);
        return checkBox;
    }


    /**
     * Creates and returns a JColorChooser that has the color selection bound
     * to the given ValueModel. The ValueModel must be of type Color and must
     * allow read-access to its value, and the initial value must not be
     * {@code null}.<p>
     *
     * It is strongly recommended (though not required)
     * that the underlying ValueModel provides only non-null values.
     * This is so because the ColorSelectionModel behavior is undefined
     * for {@code null} values and it may have unpredictable results.
     * To avoid these problems, you may create the ColorChooser with
     * a default color using {@link #createColorChooser(ValueModel, Color)}.
     *
     * @param valueModel  a Color-typed ValueModel
     * @return a color chooser with the selected color bound to the given model
     *
     * @throws NullPointerException   if the valueModel is {@code null},
     *     or if its initial value is {@code null}
     *
     * @see #createColorChooser(ValueModel, Color)
     *
     * @since 1.0.3
     */
    public static JColorChooser createColorChooser(ValueModel valueModel) {
        if (valueModel.getValue() == null)
            throw new NullPointerException("The initial value must not be null.");

        JColorChooser colorChooser = new JColorChooser(
                new ColorSelectionAdapter(valueModel));
        // Due to a bug in Java 1.4.2, Java 5 and Java 6, we don't use
        // the Bindings class, but provide a ColorSelectionModel at
        // instance creation time. The bug is in BasicColorChooserUI
        // that doesn't listen to color selection model changes.
        // This is required to update the color preview panel.
        // But the BasicColorChooserUI registers a preview listener
        // with the initial color selection model.
        //Bindings.bind(colorChooser, valueModel);
        return colorChooser;
    }


    /**
     * Creates and returns a JColorChooser that has the color selection bound
     * to the given ValueModel. The ValueModel must be of type Color and must
     * allow read-access to its value. If the valueModel returns
     * {@code null}, the given default color is used instead.
     * This avoids problems with the ColorSelectionModel that may have
     * unpredictable result for {@code null} values.
     *
     * @param valueModel  a Color-typed ValueModel
     * @param defaultColor  the color used if the valueModel returns null
     * @return a color chooser with the selected color bound to the given model
     *
     * @throws NullPointerException   if the valueModel or the default color
     *     is {@code null},
     *
     * @since 1.1
     */
    public static JColorChooser createColorChooser(ValueModel valueModel, Color defaultColor) {
        if (defaultColor == null)
            throw new NullPointerException("The default color must not be null.");

        JColorChooser colorChooser = new JColorChooser(
                new ColorSelectionAdapter(valueModel, defaultColor));
        // Due to a bug in Java 1.4.2, Java 5 and Java 6, we don't use
        // the Bindings class, but provide a ColorSelectionModel at
        // instance creation time. The bug is in BasicColorChooserUI
        // that doesn't listen to color selection model changes.
        // This is required to update the color preview panel.
        // But the BasicColorChooserUI registers a preview listener
        // with the initial color selection model.
        //Bindings.bind(colorChooser, valueModel);
        return colorChooser;
    }


    /**
     * Creates and returns a non-editable JComboBox that is bound
     * to the given SelectionInList. The SelectionInList's ListModel
     * is the list data provider and the selection index holder
     * is used for the combo box model's selected item.<p>
     *
     * If the selectionInList's selection holder is a {@link ComponentValueModel}
     * it is synchronized with the visible and enabled state of the returned
     * combo box.<p>
     *
     * There are a couple of other possibilities to bind a JComboBox.
     * See the constructors and the class comment of the
     * {@link ComboBoxAdapter}.
     *
     * @param selectionInList  provides the list and selection
     * @param <E>  the type of the combo box items and the selection
     *
     * @return a non-editable JComboBox that is bound to the SelectionInList
     *
     * @throws NullPointerException  if the selectionInList
     *     is {@code null}
     *
     * @see ComboBoxAdapter
     *
     * @since 1.0.1
     */
    public static <E> JComboBox createComboBox(SelectionInList<E> selectionInList) {
        return createComboBox(selectionInList, null);
    }


    /**
     * Creates and returns a non-editable JComboBox that is bound
     * to the given SelectionInList using the given cell renderer.
     * The SelectionInList provides the list data and the selection
     * index holder is used for the combo box model's selected item.<p>
     *
     * If the selectionInList's selection holder is a {@link ComponentValueModel}
     * it is synchronized with the visible and enabled state of the returned
     * combo box.<p>
     *
     * There are a couple of other possibilities to bind a JComboBox.
     * See the constructors and the class comment of the
     * {@link ComboBoxAdapter}.
     *
     * @param selectionInList  provides the list and selection
     * @param cellRenderer     an optional ListCellRenderer,
     *     can be {@code null}
     * @param <E>  the type of the combo box items and the selection
     *
     * @return a non-editable JComboBox that is bound to the SelectionInList
     *     and uses the given renderer - if non-{@code null}
     *
     * @throws NullPointerException  if the selectionInList
     *     is {@code null}
     *
     * @see ComboBoxAdapter
     *
     * @since 1.0.1
     */
    public static <E> JComboBox createComboBox(SelectionInList<E> selectionInList, ListCellRenderer cellRenderer) {
        JComboBox comboBox = new MyJComboBox<E>(selectionInList);
        Bindings.bind(comboBox, selectionInList);
        if (cellRenderer != null) {
            comboBox.setRenderer(cellRenderer);
        }
        return comboBox;
    }
    public static <E> JComboBox createComboBox(List<E> items, ValueModel model) {
        JComboBox comboBox = new MyJComboBox<E>(items, model);
        return comboBox;
    }


    /**
     * Creates and returns a formatted text field that is bound
     * to the Date value of the given ValueModel.
     * The JFormattedTextField is configured with an AbstractFormatter
     * that uses two different DateFormats to edit and display the Date.
     * A <code>SHORT</code> DateFormat with strict checking is used to edit
     * (parse) a date; the DateFormatter's default DateFormat is used to
     * display (format) a date. In both cases {@code null} Dates are
     * mapped to the empty String.
     *
     * @param valueModel  the model that holds the value to be edited
     * @return a formatted text field for Date instances that is bound
     *     to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JFormattedTextField createDateField(
        ValueModel valueModel) {
        DateFormat shortFormat = DateFormat.getDateInstance(DateFormat.SHORT);
        shortFormat.setLenient(false);

        JFormattedTextField.AbstractFormatter defaultFormatter =
            new EmptyDateFormatter(shortFormat);
        JFormattedTextField.AbstractFormatter displayFormatter =
            new EmptyDateFormatter();
        DefaultFormatterFactory formatterFactory =
            new DefaultFormatterFactory(defaultFormatter, displayFormatter);

        return createFormattedTextField(valueModel, formatterFactory);
    }


    /**
     * Creates and returns a formatted text field that binds its value
     * to the given model and converts Strings to values using
     * the given Format.
     *
     * @param valueModel  the model that provides the value
     * @param format      the <code>Format</code> used to convert values
     *     into a text representation and vice versa via <code>#format</code>
     *     and <code>#parse</code>
     * @return a formatted text field that is bound to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JFormattedTextField createFormattedTextField(
        ValueModel valueModel,
        Format format) {
        JFormattedTextField textField = new JFormattedTextField(format);
        Bindings.bind(textField, valueModel);
        return textField;
    }


    /**
     * Creates and returns a formatted text field that binds its value
     * to the given model and converts Strings to values using
     * the given Formatter.
     *
     * @param valueModel  the model that provides the value
     * @param formatter   the Formatter used to convert values to
     *     a text representation and vice versa via <code>#valueToString</code>
     *     and <code>#stringToValue</code>
     * @return a formatted text field that is bound to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JFormattedTextField createFormattedTextField(
        ValueModel valueModel,
        JFormattedTextField.AbstractFormatter formatter) {
        JFormattedTextField textField = new JFormattedTextField(formatter);
        Bindings.bind(textField, valueModel);
        return textField;
    }


    /**
     * Creates and returns a formatted text field that binds its value
     * to the given model and converts Strings to values using
     * Formatters provided by the given AbstractFormatterFactory.
     *
     * @param valueModel  the model that provides the value
     * @param formatterFactory   provides formatters for different field states
     *     that in turn are used to convert values to a text representation and
     *     vice versa via <code>#valueToString</code>
     *     and <code>#stringToValue</code>
     * @return a formatted text field that is bound to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JFormattedTextField createFormattedTextField(
        ValueModel valueModel,
        JFormattedTextField.AbstractFormatterFactory formatterFactory) {
        JFormattedTextField textField = new JFormattedTextField(formatterFactory);
        Bindings.bind(textField, valueModel);
        return textField;
    }


    /**
     * Creates and returns a formatted text field that binds its value
     * to the given model and converts Strings to values using
     * a MaskFormatter that is based on the given mask.
     *
     * @param valueModel  the model that provides the value
     * @param mask        the mask pattern used to create an instance of
     *   <code>MaskFormatter</code> that in turn converts values to Strings
     *   and vice versa
     * @return a bound formatted text field using a MaskFormatter
     *
     * @throws NullPointerException if the valueModel is {@code null}
     * @throws IllegalArgumentException if the mask is invalid
     */
    public static JFormattedTextField createFormattedTextField(
        ValueModel valueModel,
        String mask) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(mask);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid mask '" + mask + "'.");
        }
        JFormattedTextField textField = new JFormattedTextField(formatter);
        Bindings.bind(textField, valueModel);
        return textField;
    }


    // Integer Fields *********************************************************

    /**
     * Creates and returns a formatted text field that is bound
     * to the Integer value of the given ValueModel.
     * Empty strings are converted to {@code null} and vice versa.<p>
     *
     * The Format used to convert numbers to strings and vice versa
     * is <code>NumberFormat.getIntegerInstance()</code>.
     *
     * @param valueModel  the model that holds the value to be edited
     * @return a formatted text field for Integer instances that is bound
     *     to the specified valueModel
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JFormattedTextField createIntegerField(
            ValueModel valueModel) {
        return createIntegerField(
                valueModel,
                NumberFormat.getIntegerInstance(),
                null);
    }


    /**
     * Creates and returns a formatted text field that is bound
     * to the Integer value of the given ValueModel.
     * Empty strings are converted to the specified empty number.<p>
     *
     * The Format used to convert numbers to strings and vice versa
     * is <code>NumberFormat.getIntegerInstance()</code>.
     *
     * @param valueModel  the model that holds the value to be edited
     * @param emptyNumber an Integer that represents the empty string
     * @return a formatted text field for Integer instances that is bound
     *     to the specified valueModel
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JFormattedTextField createIntegerField(
            ValueModel valueModel,
            int emptyNumber) {
        return createIntegerField(
                valueModel,
                NumberFormat.getIntegerInstance(),
                emptyNumber);
    }


    /**
     * Creates and returns a formatted text field that is bound
     * to the Integer value of the given ValueModel.
     * Empty strings are converted to {@code null} and vice versa.
     *
     * @param valueModel    the model that holds the value to be edited
     * @param numberFormat  used to convert numbers to strings and vice versa
     * @return a formatted text field for Integer instances that is bound
     *     to the specified valueModel
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JFormattedTextField createIntegerField(
            ValueModel valueModel,
            NumberFormat numberFormat) {
        return createIntegerField(
                valueModel,
                numberFormat,
                null);
    }


    /**
     * Creates and returns a formatted text field that is bound
     * to the Integer value of the given ValueModel.
     * Empty strings are converted to the specified empty number.
     *
     * @param valueModel    the model that holds the value to be edited
     * @param numberFormat  used to convert numbers to strings and vice versa
     * @param emptyNumber   an Integer that represents the empty string
     * @return a formatted text field for Integer instances that is bound
     *     to the specified valueModel
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JFormattedTextField createIntegerField(
            ValueModel valueModel,
            NumberFormat numberFormat,
            int emptyNumber) {
        return createIntegerField(
                valueModel,
                numberFormat,
                Integer.valueOf(emptyNumber));
    }


    /**
     * Creates and returns a formatted text field that is bound
     * to the Integer value of the given ValueModel.
     * Empty strings are converted to the specified empty number.
     *
     * @param valueModel    the model that holds the value to be edited
     * @param numberFormat  used to convert numbers to strings and vice versa
     * @param emptyNumber   an Integer that represents the empty string
     * @return a formatted text field for Integer instances that is bound
     *     to the specified valueModel
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JFormattedTextField createIntegerField(
            ValueModel valueModel,
            NumberFormat numberFormat,
            Integer emptyNumber) {
        NumberFormatter numberFormatter =
            new EmptyNumberFormatter(numberFormat, emptyNumber);
        numberFormatter.setValueClass(Integer.class);

        return createFormattedTextField(valueModel, numberFormatter);
    }


    // Long Fields ************************************************************

    /**
     * Creates and returns a formatted text field that is bound
     * to the Long value of the given ValueModel.
     * Empty strings are converted to {@code null} and vice versa.<p>
     *
     * The Format used to convert numbers to strings and vice versa is
     * <code>NumberFormat.getIntegerInstance()</code>.
     *
     * @param valueModel  the model that holds the value to be edited
     * @return a formatted text field for Long instances that is bound to the
     *         specified valueModel
     *
     * @throws NullPointerException  if the model is {@code null}
     */
    public static JFormattedTextField createLongField(ValueModel valueModel) {
        return createLongField(valueModel, NumberFormat.getIntegerInstance(),
                null);
    }

    /**
     * Creates and returns a formatted text field that is bound to the
     * Long value of the given ValueModel. Empty strings are converted
     * to the specified empty number.<p>
     *
     * The Format used to convert numbers to strings and vice versa is
     * <code>NumberFormat.getIntegerInstance()</code>.
     *
     * @param valueModel    the model that holds the value to be edited
     * @param emptyNumber   a Long that represents the empty string
     * @return a formatted text field for Long instances that is bound to the
     *         specified valueModel
     *
     * @throws NullPointerException  if the model is {@code null}
     */
    public static JFormattedTextField createLongField(ValueModel valueModel,
            long emptyNumber) {
        return createLongField(valueModel, NumberFormat.getIntegerInstance(),
                emptyNumber);
    }

    /**
     * Creates and returns a formatted text field that is bound to the
     * Long value of the given ValueModel. Empty strings are converted
     * to {@code null} and vice versa.
     *
     * @param valueModel   the model that holds the value to be edited
     * @param numberFormat used to convert numbers to strings and vice versa
     * @return a formatted text field for Long instances that is bound to the
     *         specified valueModel
     *
     * @throws NullPointerException  if the model is {@code null}
     */
    public static JFormattedTextField createLongField(ValueModel valueModel,
            NumberFormat numberFormat) {
        return createLongField(valueModel, numberFormat, null);
    }

    /**
     * Creates and returns a formatted text field that is bound to the
     * Long value of the given ValueModel. Empty strings are converted
     * to the specified empty number.
     *
     * @param valueModel   the model that holds the value to be edited
     * @param numberFormat used to convert numbers to strings and vice versa
     * @param emptyNumber  a Long that represents the empty string
     * @return a formatted text field for Long instances that is bound to the
     *         specified valueModel
     *
     * @throws NullPointerException  if the model is {@code null}
     */
    public static JFormattedTextField createLongField(ValueModel valueModel,
            NumberFormat numberFormat, long emptyNumber) {
        return createLongField(valueModel, numberFormat, Long.valueOf(emptyNumber));
    }

    /**
     * Creates and returns a formatted text field that is bound to the
     * Long value of the given ValueModel. Empty strings are converted
     * to the specified empty number.
     *
     * @param valueModel    the model that holds the value to be edited
     * @param numberFormat  used to convert numbers to strings and vice versa
     * @param emptyNumber   a Long that represents the empty string
     * @return a formatted text field for Long instances that is bound to the
     *         specified valueModel
     *
     * @throws NullPointerException   if the model is {@code null}
     */
    public static JFormattedTextField createLongField(ValueModel valueModel,
            NumberFormat numberFormat, Long emptyNumber) {
        NumberFormatter numberFormatter =
            new EmptyNumberFormatter(numberFormat, emptyNumber);
        numberFormatter.setValueClass(Long.class);

        return createFormattedTextField(valueModel, numberFormatter);
    }


    // ************************************************************************

    /**
     * Creates and returns a text label that is bound to the given ValueModel.
     *
     * @param valueModel  the model that provides the value
     * @return a text label that is bound to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JLabel createLabel(ValueModel valueModel) {
        JLabel label = new JLabel();
        Bindings.bind(label, valueModel);
        return label;
    }


    /**
     * Creates and returns a text label that is bound to the
     * given ValueModel that is wrapped by a <code>StringConverter</code>.
     * The conversion to Strings uses the specified Format.
     *
     * @param valueModel  the model that provides the value
     * @param format      the format used to create the StringConverter
     * @return a text label that is bound to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     *
     * @see ConverterFactory
     */
    public static JLabel createLabel(ValueModel valueModel, Format format) {
        return createLabel(ConverterFactory.createStringConverter(valueModel, format));
    }


    /**
     * Creates and returns a JList for the given SelectionInList.<p>
     *
     * If the selectionInList's selection holder is a {@link ComponentValueModel}
     * it is synchronized with the visible and enabled state of the returned
     * list.
     *
     * @param selectionInList  provides the list and selection
     * @param <E>  the type of the list items and the selection
     * @return a JList bound to the given SelectionInList
     *
     * @throws NullPointerException  if selectionInList is {@code null}
     */
    public static <E> JList createList(SelectionInList<E> selectionInList) {
        return createList(selectionInList, null);
    }


    /**
     * Creates and returns a JList for the given SelectionInList using
     * the specified optional ListCellRenderer to render cells.<p>
     *
     * If the selectionInList's selection holder is a {@link ComponentValueModel}
     * it is synchronized with the visible and enabled state of the returned
     * list.
     *
     * @param selectionInList  provides the list and selection
     * @param cellRenderer     an optional ListCellRenderer,
     *     can be {@code null}
     * @param <E>  the type of the list items and the selection
     * @return a JList bound to the given SelectionInList
     *
     * @throws NullPointerException  if selectionInList is {@code null}
     */
    public static <E> JList createList(SelectionInList<E> selectionInList, ListCellRenderer cellRenderer) {
        JList list = new JList();
        Bindings.bind(list, selectionInList);
        if (cellRenderer != null) {
            list.setCellRenderer(cellRenderer);
        }
        return list;
    }


    /**
     * Creates and returns a JPasswordField with the content bound
     * to the given ValueModel. Text changes are committed to the model
     * on focus lost.<p>
     *
     * <strong>Security Note: </strong> The binding created by this method
     * uses Strings as values of the given ValueModel. The String-typed
     * passwords could potentially be observed in a security fraud.
     * For stronger security it is recommended to request a character array
     * from the JPasswordField and clear the array after use by setting
     * each character to zero. Method {@link JPasswordField#getPassword()}
     * return's the field's password as a character array.
     *
     * @param valueModel  the model that provides the value
     * @return a text field that is bound to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     *
     * @see #createPasswordField(ValueModel, boolean)
     * @see JPasswordField#getPassword()
     */
    public static JPasswordField createPasswordField(ValueModel valueModel) {
        return createPasswordField(valueModel, true);
    }


    /**
     * Creates and returns a JPasswordField with the content bound
     * to the given ValueModel. Text changes can be committed to the model
     * on focus lost or on every character typed.<p>
     *
     * <strong>Security Note: </strong> The binding created by this method
     * uses Strings as values of the given ValueModel. The String-typed
     * passwords could potentially be observed in a security fraud.
     * For stronger security it is recommended to request a character array
     * from the JPasswordField and clear the array after use by setting
     * each character to zero. Method {@link JPasswordField#getPassword()}
     * return's the field's password as a character array.
     *
     * @param valueModel  the model that provides the value
     * @param commitOnFocusLost  true to commit text changes on focus lost,
     *     false to commit text changes on every character typed
     * @return a text field that is bound to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     *
     * @see #createPasswordField(ValueModel)
     * @see JPasswordField#getPassword()
     */
    public static JPasswordField createPasswordField(
            ValueModel valueModel,
            boolean commitOnFocusLost) {
        JPasswordField textField = new JPasswordField();
        Bindings.bind(textField, valueModel, commitOnFocusLost);
        return textField;
    }


    /**
     * Creates and returns a radio button with the specified text label
     * that is bound to the given ValueModel. The radio button is selected
     * if and only if the model's value equals the specified choice.<p>
     *
     * The model is converted to the required ToggleButton
     * using a RadioButtonAdapter.
     *
     * @param model     the model that provides the current choice
     * @param choice    this button's value
     * @param text      the radio buttons' text label
     * @return a radio button with the specified text bound to the given model,
     *     selected if the model's value equals the specified choice
     *
     * @throws NullPointerException if the valueModel is {@code null}
     */
    public static JRadioButton createRadioButton(ValueModel model, Object choice, String text) {
        JRadioButton radioButton = new JRadioButton(text);
        Bindings.bind(radioButton, model, choice);
        return radioButton;
    }


    /**
     * Creates and returns a text area with the content bound to the given
     * ValueModel. Text changes are committed to the model on focus lost.
     *
     * @param valueModel  the model that provides the value
     * @return a text area that is bound to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     *
     * @see #createTextArea(ValueModel, boolean)
     */
    public static JTextArea createTextArea(ValueModel valueModel) {
        return createTextArea(valueModel, true);
    }


    /**
     * Creates and returns a text area with the content bound to the given
     * ValueModel. Text changes can be committed to the model on focus lost
     * or on every character typed.
     *
     * @param valueModel         the model that provides the text value
     * @param commitOnFocusLost  true to commit text changes on focus lost,
     *     false to commit text changes on every character typed
     * @return a text area that is bound to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     *
     * @see #createTextArea(ValueModel)
     */
    public static JTextArea createTextArea(
            ValueModel valueModel,
            boolean commitOnFocusLost) {
        JTextArea textArea = new JTextArea();
        Bindings.bind(textArea, valueModel, commitOnFocusLost);
        return textArea;
    }


    /**
     * Creates and returns a text field with the content bound
     * to the given ValueModel. Text changes are committed to the model
     * on focus lost.
     *
     * @param valueModel  the model that provides the value
     * @return a text field that is bound to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     *
     * @see #createTextField(ValueModel, boolean)
     */
    public static JTextField createTextField(ValueModel valueModel) {
        return createTextField(valueModel, true);
    }


    /**
     * Creates and returns a text field with the content bound
     * to the given ValueModel. Text changes can be committed to the model
     * on focus lost or on every character typed.
     *
     * @param valueModel         the model that provides the text value
     * @param commitOnFocusLost  true to commit text changes on focus lost,
     *     false to commit text changes on every character typed
     * @return a text field that is bound to the given value model
     *
     * @throws NullPointerException if the valueModel is {@code null}
     *
     * @see #createTextField(ValueModel)
     */
    public static JTextField createTextField(
            ValueModel valueModel, boolean commitOnFocusLost) {
        JTextField textField = new LimeTextField();
        Bindings.bind(textField, valueModel, commitOnFocusLost);
        return textField;
    }


}
