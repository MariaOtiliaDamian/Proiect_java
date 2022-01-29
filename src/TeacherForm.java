import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherForm {
    public TeacherForm(JFrame owner) {
        btnCursuriListate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(1,0));
            }
        });
        btnStudentiListati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, Application.getInstance().currentUser.menuStrategy.getAccountMenuOptions(2,0));
            }
        });
        btnNoteazaStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
                Curs[] cursuri = dataManager.createCoursesData();
                int ok=0;
                String numeStudent = txtNumeStudent.getText();
                String prenumeStudent = txtPrenumeStudent.getText();
                int nota = Integer.parseInt(txtNota.getText());
                String[] impartNumeProfesor = Application.getInstance().currentUser.userName.split(" ");
                try{
                    for(Curs c:cursuri){
                        if(c.profu.nume.compareTo(impartNumeProfesor[0]) == 0 && c.profu.prenume.compareTo(impartNumeProfesor[1]) == 0){
                            for(Student s : c.studenti) {
                                if (s.nume.compareTo(numeStudent) == 0 && s.prenume.compareTo(prenumeStudent) == 0) {
                                    ok = 1;
                                    try {
                                        c.noteazaStudent(s, nota);
                                    } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(null, ex.getMessage());
                                    }
                                }
                            }
                        }
                    }
                }catch(Exception e1){
                    JOptionPane.showMessageDialog(null, "Studentul " + numeStudent + " " + prenumeStudent  + " nu este inscris la acest curs.");
                }
                if(ok == 0){
                    JOptionPane.showMessageDialog(null, "Studentul " + numeStudent + " " + prenumeStudent  + " nu este inscris la acest curs.");
                }
                IDisplayManager displayManager = Settings.displayHashMap.get(Settings.displayType);
                displayManager.displayCourses(cursuri);
                if(ok==1) {
                    JOptionPane.showMessageDialog(null, "Studentul a fost notat");
                }
            }
        });
        btnInapoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(false);
                owner.setContentPane(new LoginForm(owner).getMainPanel());
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    private JPanel panel1;
    private JButton btnCursuriListate;
    private JButton btnStudentiListati;
    private JButton btnNoteazaStudent;
    private JTextField txtNumeStudent;
    private JTextField txtPrenumeStudent;
    private JTextField txtNota;
    //private JButton btnNoteaza;
    private JButton btnInapoi;
}
