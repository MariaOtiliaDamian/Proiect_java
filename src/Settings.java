import java.io.*;
import java.util.HashMap;

public class Settings {
    static String STUDENTS_PATH = "studenti.csv";
    static String TEACHERS_PATH = "profesori.csv";
    static String COURSES_PATH= "cursuri.csv";
    static LOAD_TYPE loadType = LOAD_TYPE.HARDCODAT;
    static DISPLAY_TYPE displayType = DISPLAY_TYPE.GUI;
    static  HashMap<LOAD_TYPE, IDataLoader> dataLoaderHashMap = new HashMap<>() {{ put(LOAD_TYPE.HARDCODAT, new HardcodatDataManager()); put(LOAD_TYPE.FILE, new FileDataManager()); put(LOAD_TYPE.KEYBOARD, new KeyboardDataManager()); }  };
    static HashMap<DISPLAY_TYPE, IDisplayManager> displayHashMap = new HashMap<>() {{ put(DISPLAY_TYPE.CONSOLA, new ConsoleDisplay()); put(DISPLAY_TYPE.FISIER, new FileDisplay()); put(DISPLAY_TYPE.GUI, new GraphicUserInterfaceDisplay()); }  };

    public static void initApplication() {
        try{
            File citire_fisier=new File("settings.txt");
            BufferedReader br = new BufferedReader(new FileReader(citire_fisier));
            String line = new String();
            line= br.readLine();
            line= br.readLine();
            line= br.readLine();
            line= br.readLine();
            String[] vector= new String[3];
            vector=line.split("\"");
            loadType= LOAD_TYPE.valueOf(vector[1]);
            line=br.readLine();
            vector= line.split("\"");
            displayType= DISPLAY_TYPE.valueOf(vector[1]);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
