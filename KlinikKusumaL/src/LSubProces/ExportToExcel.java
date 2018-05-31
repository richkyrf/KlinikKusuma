package LSubProces;

import Eror.LogEror;
import static Function.Jfilechoiser.GetSaveLocationFile;
import FunctionGUI.JOptionPaneF;
import java.awt.HeadlessException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportToExcel {

    private Connection connection = null;

    public ExportToExcel() {
        Koneksi koneksi = new Koneksi();
        connection = koneksi.getConnection();
    }

    public void generateXls(String SQL) {
        PreparedStatement stmt = null;
        try {
            String FileLocation;
            FileLocation = GetSaveLocationFile("Excel", "xls");
            if (FileLocation != null) {
                HSSFWorkbook xlsWorkbook = new HSSFWorkbook();
                HSSFSheet xlsSheet = xlsWorkbook.createSheet();
                short rowIndex = 0;

                stmt = this.connection.prepareStatement(SQL);
                ResultSet rs = stmt.executeQuery();
                ResultSetMetaData colInfo = rs.getMetaData();
                List<String> colNames = new ArrayList();
                rowIndex = (short) (rowIndex + 1);
                HSSFRow titleRow = xlsSheet.createRow(rowIndex);
                for (int i = 1; i <= colInfo.getColumnCount(); i++) {
                    colNames.add(colInfo.getColumnName(i));
                    titleRow.createCell((i - 1)).setCellValue(new HSSFRichTextString(colInfo
                            .getColumnName(i)));
                    xlsSheet.setColumnWidth((i - 1), (short) 4000);
                }
                HSSFRow dataRow;
                int colIndex;
                while (rs.next()) {
                    rowIndex = (short) (rowIndex + 1);
                    dataRow = xlsSheet.createRow(rowIndex);
                    colIndex = 0;
                    for (String colName : colNames) {
                        colIndex = (colIndex + 1);
                        dataRow.createCell(colIndex).setCellValue(rs.getString(colIndex));
                    }
                }
                try (FileOutputStream f2 = new FileOutputStream(FileLocation)) {
                    xlsWorkbook.write(f2);
                }
                JOptionPaneF.showMessageDialog(null, "Berhasil Export");
            } else {
                JOptionPaneF.showMessageDialog(null, "Batal Export");
            }
        } catch (HeadlessException | IOException | SQLException e) {
            LogEror.SaveString(stmt.toString());
            LogEror.SaveEror(e);
            JOptionPaneF.showMessageDialog(null, "Gagal EXport Data !!!" + e);
        } finally {
            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    LogEror.SaveEror(ex);
//                }
            }
        }
    }
}
