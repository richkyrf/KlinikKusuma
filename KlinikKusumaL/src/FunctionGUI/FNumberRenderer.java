package FunctionGUI;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.SwingConstants;

public class FNumberRenderer extends FJtableFormatRender {

    public static FNumberRenderer getCurrencyRenderer() {
        return new FNumberRenderer(NumberFormat.getCurrencyInstance());
    }

    public static FNumberRenderer getIntegerRenderer() {
        return new FNumberRenderer(NumberFormat.getIntegerInstance());
    }

    public static FNumberRenderer getPercentRenderer() {
        return new FNumberRenderer(NumberFormat.getPercentInstance());
    }

    public static FNumberRenderer getumberrender() {
        DecimalFormat df = new DecimalFormat("#,###,###,###");
        return new FNumberRenderer(df);
    }

    public static FNumberRenderer getdecimalrender() {
        DecimalFormat df = new DecimalFormat("#,###,###,###.00");
        return new FNumberRenderer(df);
    }

    public FNumberRenderer(NumberFormat formatter) {
        super(formatter);
        setHorizontalAlignment(SwingConstants.RIGHT);
    }
}
