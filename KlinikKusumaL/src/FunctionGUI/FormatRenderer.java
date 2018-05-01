package FunctionGUI;

import java.text.DateFormat;
import java.text.Format;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatRenderer extends DefaultTableCellRenderer {

    public static FormatRenderer getDateTimeRenderer() {
        return new FormatRenderer(DateFormat.getDateTimeInstance());
    }

    public static FormatRenderer getTimeRenderer() {
        return new FormatRenderer(DateFormat.getTimeInstance());
    }
    private final Format formatter;

    public FormatRenderer(Format formatter) {
        this.formatter = formatter;
    }

    public void setValue(Object value) {
        try {
            if (value != null) {
                value = formatter.format(value);
            }
        } catch (IllegalArgumentException e) {
        }

        super.setValue(value);
    }
}
