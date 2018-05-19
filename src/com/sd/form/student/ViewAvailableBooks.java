package com.sd.form.student;

import com.sd.form.librarian.*;
import com.sd.support.util.Util;
import com.sd.support.db.DB;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

public class ViewAvailableBooks extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewAvailableBooks frame = new ViewAvailableBooks();
                    frame.setVisible(true);
                    frame.setTitle("Books");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ViewAvailableBooks() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        Util.customizeFrame(this);

        String data[][] = null;
        String column[] = null;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select callno,name,author,publisher from books where quantity > 0", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            column = new String[cols];
            for (int i = 1; i <= cols; i++) {
                column[i - 1] = Util.capitalizeFirstLetter(rsmd.getColumnName(i));
            }

            rs.last();
            int rows = rs.getRow();
            rs.beforeFirst();

            data = new String[rows][cols];
            int count = 0;
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    data[count][i - 1] = rs.getString(i);
                }
                count++;
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        table = new JTable(data, column);
        JScrollPane sp = new JScrollPane(table);

        contentPane.add(sp, BorderLayout.CENTER);
    }

}