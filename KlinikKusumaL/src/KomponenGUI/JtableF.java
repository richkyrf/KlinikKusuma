package KomponenGUI;

import Eror.LogEror;
import FunctionGUI.FNumberRenderer;
import FunctionGUI.FormatRenderer;
import FunctionGUI.JOptionPaneF;
import LSubProces.Koneksi;
import java.awt.Color;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class JtableF extends JTable {

    boolean usecolor = false;
    String SQL = "";
    Connection con;
    boolean bf = false;
    int booleanfield = -1;
    int booleanfield2 = -1;
    Class[] types = new Class[]{null};
    java.sql.ResultSetMetaData md;
    int columnCount;
    Vector data;
    Vector columnNames;
    Vector row;
    String jcomSQL;

    public JtableF() {
        setFont(new java.awt.Font("Tahoma", 0, 18));
        setRowHeight(20);
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
        setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
    }

    public void useColor(boolean bool) {
        usecolor = bool;
    }

    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
        Component comp = super.prepareRenderer(renderer, row, col);
        if (usecolor) {
            int modelRow = convertRowIndexToModel(row);
            if (getModel().getValueAt(modelRow, getColumnCount() - 1).equals("Antri")) {
                comp.setBackground(Color.pink);
            } else if (isRowSelected(row)) {
                comp.setBackground(Color.gray);
                comp.setForeground(Color.white);
            } else {
                comp.setForeground(Color.black);
                comp.setBackground(row % 2 == 0 ? Color.white : new Color(240, 240, 240));
            }
        } else {
            if (isRowSelected(row)) {
                comp.setBackground(Color.gray);
                comp.setForeground(Color.white);
            } else {
                comp.setForeground(Color.black);
                comp.setBackground(row % 2 == 0 ? Color.white : new Color(225, 225, 225));
            }
        }
        return comp;
    }

    public void setQuery(String Query) {
        SQL = Query;
    }

    public String getQuery() {
        return SQL;
    }

    public void setrender(int[] x, String[] Type) {
        try {
            TableColumnModel m = this.getColumnModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
            leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
            for (int i = 0; i < x.length; i++) {
                if ("Date".equals(Type[i])) {
                    m.getColumn(x[i]).setCellRenderer(FormatRenderer.getDateTimeRenderer());
                }
                if ("Time".equals(Type[i])) {
                    m.getColumn(x[i]).setCellRenderer(FormatRenderer.getTimeRenderer());
                }
                if ("Percent".equals(Type[i])) {
                    m.getColumn(x[i]).setCellRenderer(FNumberRenderer.getPercentRenderer());
                }
                if ("Curency".equals(Type[i])) {
                    m.getColumn(x[i]).setCellRenderer(FNumberRenderer.getCurrencyRenderer());
                }
                if ("Number".equals(Type[i])) {
                    m.getColumn(x[i]).setCellRenderer(FNumberRenderer.getumberrender());
                }
                if ("CenteredNumber".equals(Type[i])) {
                    m.getColumn(x[i]).setCellRenderer(FNumberRenderer.getcenteredumberrender());
                }
                if ("Center".equals(Type[i])) {
                    m.getColumn(x[i]).setCellRenderer(centerRenderer);
                }
                if ("Left".equals(Type[i])) {
                    m.getColumn(x[i]).setCellRenderer(leftRenderer);
                }
                if ("Right".equals(Type[i])) {
                    m.getColumn(x[i]).setCellRenderer(rightRenderer);
                }
                if ("Decimal".equals(Type[i])) {
                    m.getColumn(x[i]).setCellRenderer(FNumberRenderer.getdecimalrender());
                }
            }
        } catch (Exception e) {
            LogEror.SaveEror(e);
        }
    }

    public void addNewRow() {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        Vector row = new Vector();
        row.add(null);
        row.add(null);
        row.add(null);
        model.addRow(row);
    }

    public void useboolean(boolean f) {
        bf = f;
    }

    public void setbooleanfield(int i) {
        booleanfield = i;
    }

    public void setbooleanfield2(int i) {
        booleanfield2 = i;
    }

    public void setclass(Class[] cv) {
        types = cv;
    }

    public void tampilkan() {
        Koneksi koneksi = new Koneksi();
        con = koneksi.getConnection();
        setAutoCreateRowSorter(true);
        PreparedStatement pstmt = null;
        ResultSet rs;
        try {
            pstmt = con.prepareStatement(SQL);
            //System.out.println(pstmt);
            pstmt.executeQuery();
            rs = pstmt.getResultSet();
            md = rs.getMetaData();
            columnCount = md.getColumnCount();
            data = new Vector(columnCount);
            columnNames = new Vector(columnCount);
            row = new Vector(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                columnNames.addElement(md.getColumnLabel(i));
            }
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    if (i == booleanfield + 1) {
                        row.addElement(rs.getBoolean(booleanfield + 1));
                    } else if (i == booleanfield2 + 1) {
                        row.addElement(rs.getBoolean(booleanfield2 + 1));
                    } else {
                        row.addElement(rs.getObject(i));
                    }
                }
                data.addElement(row);
                row = new Vector(columnCount); // Create a new row Vector
            }
            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnIndex == booleanfield || columnIndex == booleanfield2;
                }
            };
            if (bf) {
                model = new DefaultTableModel(data, columnNames) {
                    @Override
                    public Class<?> getColumnClass(int column) {
                        if (column == booleanfield || column == booleanfield2) {
                            return java.lang.Boolean.class;
                        }
                        return java.lang.Object.class;
                    }

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnIndex == booleanfield || columnIndex == booleanfield2;
                    }
                };
            }
            setModel(model);
            for (int column = 0; column < getColumnCount(); column++) {
                TableColumn tableColumn = getColumnModel().getColumn(column);
                int preferredWidth = 50;
                int maxWidth = 200;

                for (int row = 0; row < getRowCount(); row++) {
                    TableCellRenderer cellRenderer = getCellRenderer(row, column);
                    Component c = prepareRenderer(cellRenderer, row, column);
                    int width = c.getPreferredSize().width + getIntercellSpacing().width;
                    preferredWidth = Math.max(preferredWidth, width);

                    //  We've exceeded the maximum width, no need to check other rows
                    if (preferredWidth >= maxWidth) {
                        preferredWidth = maxWidth;
                    }
                }
                tableColumn.setPreferredWidth(preferredWidth + 15);
            }
            setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            getTableHeader().setReorderingAllowed(false);
        } catch (SQLException e) {
            LogEror.SaveEror(e);
            JOptionPaneF.showMessageDialog(null, "Gagal Menampilkan Data " + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
//                    con.close();
                }
            } catch (SQLException ex) {
                LogEror.SaveEror(ex);
            }
        }
    }

    public Vector getcoloumname() {
        return columnNames;
    }

    public Vector loaddata(Vector row, ResultSet rs, int i) throws SQLException {
        row.addElement(rs.getObject(i));
        return row;
    }

    public int getint(int x, int y) {
        int h = 0;
        if (getSelectedRow() != -1) {
            h = Integer.parseInt(getValueAt(x, y).toString().replace(",", "").replace(".", ""));
        }
        return h;
    }

    public long getLong(int x, int y) {
        long h = 0;
        if (getSelectedRow() != -1) {
            h = Long.parseLong(getValueAt(x, y).toString().replace(",", "").replace(".", ""));
        }
        return h;
    }

    public float getFloat(int x, int y) {
        float h = 0;
        if (getSelectedRow() != -1) {
            h = Float.parseFloat(getValueAt(x, y).toString().replace(",", ""));
        }
        return h;
    }

    public String getstring(int x, int y) {
        String a = null;
        try {
            if (getSelectedRow() != -1) {
                a = getValueAt(x, y).toString();
            }
        } catch (Exception e) {
            LogEror.SaveEror(e);
        }
        return a;
    }

    public Date getdate(int x, int y, String Format) {
        Date startDate = new Date();
        try {
            String tgl = getValueAt(x, y).toString();
            DateFormat df = new SimpleDateFormat(Format);
            startDate = df.parse(tgl);
        } catch (ParseException ex) {
            LogEror.SaveEror(ex);
        }
        return startDate;
    }

    public String getTime(int x, int y, String Format) {
        Date startDate;
        String Waktu = "00:00";
        try {
            String tgl = getValueAt(x, y).toString();
            DateFormat df = new SimpleDateFormat(Format);
            startDate = df.parse(tgl);
            Waktu = FDateF.datetostr(startDate, "HH:mm");
        } catch (ParseException ex) {
            LogEror.SaveEror(ex);
        }
        return Waktu;
    }

    public int getintC(int x, int y) {
        int h = 0;
        if (getSelectedRow() != -1) {
            h = Integer.parseInt(getValueAt(x, y).toString().substring(0, (getValueAt(x, y).toString().replace(",", "").length() - 2)).replace(",", "").replace(".", ""));
        }
        return h;
    }

    public int getintcoma(int x, int y) {
        int h = 0;
        if (getSelectedRow() != -1) {
            h = Integer.parseInt(getValueAt(x, y).toString().substring((getValueAt(x, y).toString().replace(",", "").length() - 2), (getValueAt(x, y).toString().replace(",", "").length())).replace(",", "").replace(".", ""));
        }
        return h;
    }
}
