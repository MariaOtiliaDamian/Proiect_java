import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private JPanel mainPanel;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnInregistrare;
    private JFrame owner;
    public LoginForm(JFrame owner) {
        this.owner = owner;
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == btnLogin) {
                    System.out.println("Am apasat butonul de login");
                    try {
                        Application.getInstance().login(new User(txtUsername.getText(), new String(txtPassword.getPassword())));
                        JOptionPane.showMessageDialog(null, "Te-ai logat cu succes!");
                        mainPanel.setVisible(false);
                        if(Application.getInstance().currentUser.menuStrategy.getAccountType().equals(UserAccountType.STUDENT)){
                            owner.setContentPane(new StudentForm(owner).getPanelStudent());
                        }else {
                            owner.setContentPane(new TeacherForm(owner).getPanel1());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });
        btnInregistrare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == btnInregistrare) {
                    System.out.println("Am apasat butonul de inregistrare");
                    try {
                        mainPanel.setVisible(false);
                        owner.setContentPane(new InregistrareForm(owner).getPanelInregistrare());

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());


                    }
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
