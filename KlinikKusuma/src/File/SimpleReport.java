package File;

import KomponenGUI.FDateF;
import java.io.FileOutputStream;
import java.util.Date;
import javafx.print.PageOrientation;
import javax.swing.table.TableModel;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.type.OrientationEnum;

public class SimpleReport {

    TableModel model;
    String judul;

    public SimpleReport(TableModel model, String JudulLaporan, String[] JudulKolom) {
        judul = JudulLaporan;
        this.model = model;
        build(JudulKolom);
    }

    private void build(String[] JudulKolom) {
        StyleBuilder titleStyle = stl.style().bold().setHorizontalTextAlignment(HorizontalTextAlignment.CENTER).setFontSize(14).setFontName("Verdana").setPadding(5).setBorder(stl.pen1Point().setLineWidth(1f));
        StyleBuilder columnTitleStyle = stl.style().bold().setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE).setPadding(2).setBorder(stl.pen1Point().setLineWidth(0.5f));
        StyleBuilder colStyle = stl.style().setPadding(2).setBorder(stl.pen1Point().setLineWidth(0.5f));

        try {
            JasperReportBuilder report = DynamicReports.report();
            report.setColumnTitleStyle(columnTitleStyle);
            for (int i = 0; i < JudulKolom.length; i++) {
                if (i == 0) {
                    report.columns(col.column(JudulKolom[i], JudulKolom[i], type.stringType()).setStyle(colStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER).setWidth(120));
                } else if (i == 1) {
                    report.columns(col.column(JudulKolom[i], JudulKolom[i], type.stringType()).setStyle(colStyle).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT).setWidth(200));
                } else if (i == JudulKolom.length - 2) {
                    report.columns(col.column(JudulKolom[i], JudulKolom[i], type.integerType()).setStyle(colStyle).setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT).setWidth(100));
                } else if (i == JudulKolom.length - 1) {
                    report.columns(col.column(JudulKolom[i], JudulKolom[i], type.stringType()).setStyle(colStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER).setWidth(200));
                } else {
                    report.columns(col.column(JudulKolom[i], JudulKolom[i], type.stringType()).setStyle(colStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER));
                }
            }
            report.setPageFormat(PageType.A3, net.sf.dynamicreports.report.constant.PageOrientation.LANDSCAPE);
            report.setPageMargin(margin().setLeft(20).setRight(20).setBottom(20).setTop(20));
            report.pageFooter(cmp.text("Di Print Oleh " + GlobalVar.VarL.username + " Pada " + FDateF.datetostr(new Date(), "dd/MM/yyyy HH:mm")));
            report.ignorePagination();
            report.title(cmp.horizontalList().add(cmp.text(judul).setStyle(titleStyle)).newRow().add(cmp.filler().setFixedHeight(10)));
            report.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL);
            report.setDataSource(createDataSource(JudulKolom));
            report.show(false);
            //report.toPdf(new FileOutputStream("E:/report.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JRDataSource createDataSource(String[] JudulKolom) {
        DRDataSource dataSource = new DRDataSource(JudulKolom);
        for (int i = 0; i < model.getRowCount(); i++) {
            Object data[] = new Object[JudulKolom.length];
            for (int j = 0; j < JudulKolom.length; j++) {
                if (j == JudulKolom.length - 2) {
                    data[j] = new Integer(model.getValueAt(i, j).toString());
                } else {
                    data[j] = model.getValueAt(i, j).toString();
                }
            }
            dataSource.add(data);
        }
        return dataSource;
    }

}
