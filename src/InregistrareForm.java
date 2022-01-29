import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InregistrareForm {
    private JPanel panelInregistrare;
    private JTextField txtNume;
    private JTextField txtPrenume;
    private JTextField txtUsername;
    private JPasswordField txtParola;
    private JButton btnCreezCont;

    public JPanel getPanelInregistrare() {
        return panelInregistrare;
    }

    public InregistrareForm(JFrame owner) {
        btnCreezCont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Application.getInstance().inregistrare(txtNume.getText(), txtPrenume.getText(), new String(txtParola.getPassword()), txtUsername.getText());
                    JOptionPane.showMessageDialog(null, "Te-ai inregistrat cu succes!");
                    panelInregistrare.setVisible(false);
                    owner.setContentPane(new LoginForm(owner).getMainPanel());
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    panelInregistrare.setVisible(false);
                    owner.setContentPane(new LoginForm(owner).getMainPanel());
                }
            }
        });
    }
}
