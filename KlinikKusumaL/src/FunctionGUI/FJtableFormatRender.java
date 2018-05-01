package FunctionGUI;

import java.text.DateFormat;
import java.text.Format;
import javax.swing.table.DefaultTableCellRenderer;

public class FJtableFormatRender extends DefaultTableCellRenderer {

    public static FJtableFormatRender getDateTimeRenderer() {
        return new FJtableFormatRender(DateFormat.getDateTimeInstance());
    }

    public static FJtableFormatRender getTimeRenderer() {
        return new FJtableFormatRender(DateFormat.getTimeInstance());
    }

    private final Format formatter;

    public FJtableFormatRender(Format formatter) {
        this.formatter = formatter;
    }

    @Override
    public void setValue(Object value) {
        try {
            if (value != null) {
                value = formatter.format(value);
            }
        } catch (IllegalArgumentException e) {
            //System.out.println("IllegalArgumentException "+e);
        }
        super.setValue(value);
    }
}
