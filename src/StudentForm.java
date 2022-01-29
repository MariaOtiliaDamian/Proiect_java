import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentForm {
    private JPanel panelStudent;
    private JButton btnCursurileMele;
    private JButton btnNoteleMele;
    private JButton btnRestante;
    private JButton btnMediaAnuala;
    private JTextField txtAn;
    private JButton btnInapoi;

    public StudentForm(JFrame owner) {
        btnCursurileMele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int an = Integer.parseInt(txtAn.getText());
                    JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(1, an));
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Nu ai introdus anul.");
                }
            }
        });
        btnMediaAnuala.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int an = Integer.parseInt(txtAn.getText());
                    JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(2, an));
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Nu ai introdus anul.");
                }
            }
        });
        btnRestante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int an = Integer.parseInt(txtAn.getText());
                    JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(3, an));
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Nu ai introdus anul.");
                }
            }
        });
        btnNoteleMele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int an = Integer.parseInt(txtAn.getText());
                    JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(4, an));
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Nu ai introdus anul.");
                }
            }
        });
        btnInapoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelStudent.setVisible(false);
                owner.setContentPane(new LoginForm(owner).getMainPanel());
            }
        });
    }

    public JPanel getPanelStudent() {
        return panelStudent;
    }
}
