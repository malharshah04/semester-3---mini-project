package travel.management.system;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ViewCustomers extends JFrame {
    private JPanel contentPane;
    private JTable table;

    public ViewCustomers() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(580, 220, 900, 680);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());  // Use BorderLayout for better layout management.

        // Load and display images
        ImageIcon image1 = new ImageIcon(ViewCustomers.class.getResource("/travel/management/system/icons/viewall.jpg"));
        Image scaledImage1 = image1.getImage().getScaledInstance(626, 201, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        JLabel label1 = new JLabel(scaledIcon1);
        contentPane.add(label1, BorderLayout.SOUTH);

        ImageIcon image2 = new ImageIcon(ViewCustomers.class.getResource("/travel/management/system/icons/viewall.jpg"));
        Image scaledImage2 = image2.getImage().getScaledInstance(626, 201, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        JLabel label2 = new JLabel(scaledIcon2);
        contentPane.add(label2, BorderLayout.EAST);

        table = new JTable();
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);  // Use a JScrollPane for large tables.

        try {
            Conn c = new Conn();
            String displayCustomersql = "select * from customer";
            ResultSet rs = c.s.executeQuery(displayCustomersql);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        JButton btnNewButton = new JButton("Back");
        btnNewButton.addActionListener(e -> setVisible(false));
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setForeground(Color.WHITE);
        contentPane.add(btnNewButton, BorderLayout.NORTH);

        // Create and add labels
        JPanel labelsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));  // Use FlowLayout for labels.
        contentPane.add(labelsPanel, BorderLayout.NORTH);

        String[] labelNames = { "Username", "       Id_Type", "             Number", "                Gender", "               Country", "                Address", "              Phone", "              Email" };
        for (String labelName : labelNames) {
            JLabel label = new JLabel(labelName);
            labelsPanel.add(label);
        }

        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ViewCustomers frame = new ViewCustomers();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
