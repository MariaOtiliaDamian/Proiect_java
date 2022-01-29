import java.util.HashMap;

public class StudentStrategy implements MenuStrategy{
    public Student student;
    public StudentStrategy() { }
    StudentStrategy(Student student) {
        this.student = student;
    }

    @Override
    public UserAccountType getAccountType() {
        return UserAccountType.STUDENT;
    }

    @Override
    public HashMap<String, String> getAccountHolderInformation() {
        return new HashMap<>() {{
            put(student.nume, student.prenume);
        }};
    }

    @Override
    public String getAccountMenuOptions(int index, int an) {
        IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
        Curs[] cursuri = dataManager.createCoursesData();
        if(index == 1){
            String cursurileStudentului = "";
            for(Curs c:cursuri){
                if(c.an == an) {
                    for (Student s : c.studenti) {
                        if (s.nume.compareTo(student.nume) == 0 && s.prenume.compareTo(student.prenume) == 0) {
                            cursurileStudentului = cursurileStudentului + c.nume + "\n";
                        }
                    }
                }
            }
            return cursurileStudentului;
        }else if(index == 2){
            String afis = "Media este:\n";
            int suma = 0, nr = 0;
            for(Curs c:cursuri){
                for(Student s:c.studenti){
                    if(s.compareTo(student)==0 && c.an == an) {
                        if(c.nota.get(s) != null) {
                            suma = suma + c.nota.get(s);
                            nr++;
                        }
                    }
                }
            }
            float media = 0;
            try {
                media = suma / nr;
            }catch (Exception e){return "Studentul nu are note la curs.";}
            afis = afis + media;
            if(afis.length() == 0)
                afis = "Nu sunteti inscris la niciun curs in acest an.";
            return afis;
        } else if (index == 3) {
            String afis ="";
            for(Curs c:cursuri){
                for(Student s:c.studenti){
                    if(s.compareTo(student)==0 && c.an == an) {
                        if(c.nota.get(s) != null && c.nota.get(s) < 5){
                            afis = afis + c.nume + "\n";
                        }
                    }
                }
            }
            if(afis.length()==0)
                afis = "Nu aveti restante.";
            return afis;
        }else if(index == 4){
            String afis = "";
            for(Curs c:cursuri){
                for(Student s:c.studenti){
                    if(s.compareTo(student)==0 && c.an == an) {
                        String nota = c.nota.get(s) != null ? c.nota.get(s).toString() : "Studentul nu a fost notat";
                        afis = afis + "\n" + c.nume+ " "  + nota + "\n";
                    }
                }
            }
            if(afis.length()==0)
                afis = "Nu sunteti inscris la niciun curs in acest an.";
            return afis;
        }
        return "";
    }

    @Override
    public void nextMenuOption() {

    }

    @Override
    public void previousMenuOption() {

    }
}
