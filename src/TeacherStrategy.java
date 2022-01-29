import java.util.HashMap;

public class TeacherStrategy implements MenuStrategy {
    public Profesor profesor;
    public TeacherStrategy() { }

    TeacherStrategy(Profesor p) {
        this.profesor = p;
    }
    @Override
    public UserAccountType getAccountType() {
        return UserAccountType.TEACHER;
    }

    @Override
    public HashMap<String, String> getAccountHolderInformation() {
        return new HashMap<>() {{
            put(profesor.nume, profesor.prenume);
        }};
    }

    @Override
    public String getAccountMenuOptions(int index, int an) {
        IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
        Curs[] cursuri = dataManager.createCoursesData();
        if(index == 1){
            String afis = new String();
            for(Curs c:cursuri){
                if(c.profu.nume.compareTo(profesor.nume) == 0 && c.profu.prenume.compareTo(profesor.prenume) == 0){
                    afis = afis + "\n" + c.nume + "\n";
                }
            }
            if(afis.length()==0)
                return "Nu sunteti profesor la niciun curs.";
            return afis;
        }else if(index == 2){
            String afis = new String();
            for(Curs c:cursuri){
                if(c.profu.nume.compareTo(profesor.nume) == 0 && c.profu.prenume.compareTo(profesor.prenume) == 0){
                    for(Student s : c.studenti) {
                        afis = afis + "\n" + s.nume + " " + s.prenume;
                    }
                }
            }
            if(afis.length() == 0)
                return "Nu aveti studenti la curs.";
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
