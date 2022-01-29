import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Application {
    private static Application single_instance = null;
    public User currentUser = null;
    private List<User> userList = new ArrayList<>();

    private Application() {
        /*IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
        Curs[] preiaCurs = dataManager.createCoursesData();
        Random r = new Random();
        var studenti = dataManager.createStudentsData();
        var profesori = dataManager.createProfesorData();
        this.userList.add(new User(studenti[12].nume+ " "+studenti[12].prenume, studenti[12].prenume, new StudentStrategy( studenti[12])));
        this.userList.add(new User(profesori[1].nume+ " "+profesori[1].prenume, profesori[1].prenume, new TeacherStrategy( profesori[1])));
        this.userList.add(new User(studenti[1].nume+ " "+studenti[1].prenume, studenti[1].prenume, new StudentStrategy( studenti[1])));
        this.userList.add(new User(studenti[11].nume+ " "+studenti[11].prenume, studenti[11].prenume, new StudentStrategy( studenti[11])));
        try {
            FileOutputStream fos = new FileOutputStream("users.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
            });
            encoder.writeObject(userList);
            encoder.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        this.initUsers();
    }

    static Application getInstance() {
        if ( single_instance == null) {
            single_instance = new Application();
        }
        return  single_instance;
    }

    private void initUsers() {
        try (FileInputStream fis = new FileInputStream("users.xml")) {
            XMLDecoder decoder = new XMLDecoder(fis);
            this.userList = (ArrayList<User>)decoder.readObject();
            decoder.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(User user) throws Exception {
        int index = userList.indexOf(user);
        if ( index != -1 ) {
            Application.getInstance().currentUser = userList.get(index);
        } else {
            throw new Exception("Username sau parola este gresita!");
        }
    }

    public void inregistrare(String nume, String prenume, String parola, String username)throws Exception{
        int ok=0,var=0,v=0;
        Profesor p= new Profesor();
        Student st=new Student();
        for(User u: userList){
            if(u.menuStrategy.getAccountHolderInformation().get(nume)!=null){
                ok++;
            }
        }
        if(ok==0){
            IDataLoader informatii= Settings.dataLoaderHashMap.get(Settings.loadType);
            Curs[] cursuri = informatii.createCoursesData();
            for(Curs c: cursuri){
                if((c.profu.nume.compareTo(nume)==0)&&(c.profu.nume.compareTo(nume)==0)) {
                    var=1;
                    p= c.profu;
                }
                for(Student s: c.studenti){
                    if((s.nume.compareTo(nume)==0)&&(s.prenume.compareTo(prenume)==0)){
                        v=1;
                        st=s;
                    }
                }

            }
            if(var==1){
                this.initUsers();
                userList.add(new User(username,parola,new TeacherStrategy(p)));
                try {
                    FileOutputStream fos = new FileOutputStream("users.xml");
                    XMLEncoder encoder = new XMLEncoder(fos);
                    encoder.setExceptionListener(new ExceptionListener() {
                        @Override
                        public void exceptionThrown(Exception e) {
                            System.out.println("Exception:" + e.toString());
                        }
                    });
                    encoder.writeObject(userList);
                    encoder.close();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.initUsers();
            }
            else if(v==1){
                this.initUsers();
                userList.add(new User(username,parola,new StudentStrategy(st)));
                try {
                    FileOutputStream fos = new FileOutputStream("users.xml");
                    XMLEncoder encoder = new XMLEncoder(fos);
                    encoder.setExceptionListener(new ExceptionListener() {
                        @Override
                        public void exceptionThrown(Exception e) {
                            System.out.println("Exception:" + e.toString());
                        }
                    });
                    encoder.writeObject(userList);
                    encoder.close();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.initUsers();
            } else{
                throw new Exception("Nu sunteti inregistrat la niciun curs");
            }
        }
        else {
            throw new Exception("Aveti deja cont!");
        }
    }
}
