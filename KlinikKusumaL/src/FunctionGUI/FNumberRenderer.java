package FunctionGUI;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.SwingConstants;

public class FNumberRenderer extends FJtableFormatRender {

    public static FNumberRenderer getCurrencyRenderer() {
        return new FNumberRenderer(NumberFormat.getCurrencyInstance(), SwingConstants.RIGHT);
    }

    public static FNumberRenderer getIntegerRenderer() {
        return new FNumberRenderer(NumberFormat.getIntegerInstance(), SwingConstants.RIGHT);
    }

    public static FNumberRenderer getPercentRenderer() {
        return new FNumberRenderer(NumberFormat.getPercentInstance(), SwingConstants.RIGHT);
    }

    public static FNumberRenderer getumberrender() {
        DecimalFormat df = new DecimalFormat("#,###,###,###");
        return new FNumberRenderer(df, SwingConstants.RIGHT);
    }
    
    public static FNumberRenderer getcenteredumberrender() {
        DecimalFormat df = new DecimalFormat("#,###,###,###");
        return new FNumberRenderer(df, SwingConstants.CENTER);
    }

    public static FNumberRenderer getdecimalrender() {
        DecimalFormat df = new DecimalFormat("#,###,###,###.00");
        return new FNumberRenderer(df, SwingConstants.RIGHT);
    }

    public FNumberRenderer(NumberFormat formatter, int alignment) {
        super(formatter);
        setHorizontalAlignment(alignment);
    }
}
