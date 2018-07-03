package FunctionGUI;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.table.DefaultTableCellRenderer;

public class FormatRenderer extends DefaultTableCellRenderer {

    public static FormatRenderer getDateRenderer() {
        return new FormatRenderer(new SimpleDateFormat("dd-MM-yyyy"));
    }

    public static FormatRenderer getTimeRenderer() {
        return new FormatRenderer(DateFormat.getTimeInstance());
    }
    private final Format formatter;

    public FormatRenderer(Format formatter) {
        this.formatter = formatter;
        setHorizontalAlignment(CENTER);
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
