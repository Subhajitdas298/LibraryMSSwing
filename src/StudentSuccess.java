
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentSuccess extends JFrame {

    static StudentSuccess frame;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new StudentSuccess();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public StudentSuccess() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 433);
        contentPane = new JPanel();
        contentPane.setForeground(Color.GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblStudentSection = new JLabel("Student : " + Cache.get("student_name"));
        lblStudentSection.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JButton btnViewBooks = new JButton("View Available Books");
        btnViewBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ViewBooks.main(new String[]{});
            }
        });
        btnViewBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnViewIssuedBooks = new JButton("View My Issued Books");
        btnViewIssuedBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewStudentIssuedBooks.main(new String[]{});
            }
        });
        btnViewIssuedBooks.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cache.remove("student_id");
                Cache.remove("student_name");
                Library.main(new String[]{});
                frame.dispose();
            }
        });
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.CENTER, gl_contentPane.createSequentialGroup()
                                .addComponent(lblStudentSection))
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(132)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnViewIssuedBooks, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnViewBooks, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(101, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblStudentSection)
                                .addGap(18)
                                .addComponent(btnViewBooks, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnViewIssuedBooks, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }

}