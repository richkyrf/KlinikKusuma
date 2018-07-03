/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import java.util.Date;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author richk
 */
public class ColumnsSpanTest extends BaseDjReportTest {

    public DynamicReport buildReport() throws Exception {
        FastReportBuilder frb = new FastReportBuilder();
        frb.addColumn("State", "state", String.class.getName(), 30)
                .addColumn("Branch", "branch", String.class.getName(), 30)
                .addColumn("Item", "item", String.class.getName(), 50)
                .addColumn("Amount", "amount", Float.class.getName(), 60, true)
                .addGroups(2)
                .setTitle("November 2018 sales report")
                .setSubtitle("This report was generated at " + new Date())
                .setColumnsPerPage(1, 10)
                .setUseFullPageWidth(true)
                .setColspan(1, 2, "Estimated");
        DynamicReport dynamicReport = frb.build();
        dynamicReport.getOptions().getDefaultHeaderStyle().setBorder(Border.PEN_1_POINT());
        return dynamicReport;
    }

    public static void main(String[] args) throws Exception {
        ColumnsSpanTest test = new ColumnsSpanTest();
//        JasperViewer.viewReport(test.);
    }
}
