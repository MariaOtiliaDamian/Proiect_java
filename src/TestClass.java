import javax.swing.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.io.File;

enum LOAD_TYPE {
	HARDCODAT, KEYBOARD, FILE
}

enum DISPLAY_TYPE  {
	CONSOLA, FISIER, GUI
}

public class TestClass {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Graphic user interface");
		LoginForm loginForm = new LoginForm(frame);
		frame.setContentPane(loginForm.getMainPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(1100,800);
		frame.setVisible(true);
		Settings.initApplication();
		IDataLoader dataManager = Settings.dataLoaderHashMap.get(Settings.loadType);
		//IDisplayManager displayManager = Settings.displayHashMap.get(Settings.displayType);
		//displayManager.displayCourses(dataManager.createCoursesData());
		/* Scanner sc = new Scanner(System.in);
		System.out.println("Username = ");
		var username = sc.next();
		System.out.println("Password = ");
		var password = sc.next();

		try {
			Application.getInstance().login(new User(username, password));
			System.out.println(Application.getInstance().currentUser);
			System.out.println(Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation());
			System.out.println(Application.getInstance().currentUser.menuStrategy.getAccountType());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
		int index = 0;
		Scanner sc = new Scanner(System.in);
		String username = new String();
		String password = new String();
		Curs[] cursuri = dataManager.createCoursesData();
		while (index != 3) {
			System.out.println("Introduceti optiunea dorita:\n1. Logheaza-te!\n2. Inregistreaza-te!\n3. Iesi!");
			index = sc.nextInt();
			if (index == 1) {
				int ok = 0;
				while (ok == 0) {
					ok = 1;
					System.out.println("Username = ");
					username = sc.nextLine();
					username = sc.nextLine();
					System.out.println("Parola = ");
					password = sc.next();
					try {
						Application.getInstance().login(new User(username, password));
					} catch (Exception e) {
						ok = 0;
						System.out.println(e.getMessage());
					}
				}
				if (Application.getInstance().currentUser.menuStrategy.getAccountType() == UserAccountType.STUDENT) {
					while (index != 5) {
						System.out.print("1. Cursurile mele\n2. Media anuala\n3. Restante\n4. Notele mele\n5. Inapoi \nIntroduceti optiunea dorita: ");
						index = sc.nextInt();
						String[] splitNume = Application.getInstance().currentUser.userName.split(" ");
						String nume = splitNume[0];
						String prenume = splitNume[1];
						if (index == 1) {
							String cursurileStudentului = "";
							System.out.println("Introduceti anul:");
							int an = sc.nextInt();
							for (Curs c : cursuri) {
								if (c.an == an) {
									for (Student s : c.studenti) {
										if (s.nume.compareTo(nume) == 0 && s.prenume.compareTo(prenume) == 0) {
											cursurileStudentului = cursurileStudentului + c.nume + "\n";
										}
									}
								}
							}
							System.out.println(cursurileStudentului);
						} else if (index == 2) {
							String afis = "Media este:\n";
							int suma = 0, nr = 0;
							System.out.println("Introduceti anul:");
							int an = sc.nextInt();
							for (Curs c : cursuri) {
								for (Student s : c.studenti) {
									if (s.nume.compareTo(nume) == 0 && s.prenume.compareTo(prenume) == 0 && c.an == an) {
										if (c.nota.get(s) != null) {
											suma = suma + c.nota.get(s);
											nr++;
										}
									}
								}
							}
							float media = 0;
							try {
								media = suma / nr;
							} catch (Exception e) {
								System.out.println("Studentul nu are note la curs.");
							}
							afis = afis + media;
							if (afis.length() == 0)
								afis = "Nu sunteti inscris la niciun curs in acest an.";
							System.out.println(afis);
						} else if (index == 3) {
							String afis = "";
							System.out.println("Introduceti anul:");
							int an = sc.nextInt();
							for (Curs c : cursuri) {
								for (Student s : c.studenti) {
									if (s.nume.compareTo(nume) == 0 && s.prenume.compareTo(prenume) == 0 && c.an == an) {
										if (c.nota.get(s) != null && c.nota.get(s) < 5) {
											afis = afis + c.nume + "\n";
										}
									}
								}
							}
							if (afis.length() == 0)
								afis = "Nu aveti restante.";
							System.out.println(afis);
						} else if (index == 4) {
							String afis = "";
							System.out.println("Introduceti anul:");
							int an = sc.nextInt();
							for (Curs c : cursuri) {
								for (Student s : c.studenti) {
									if (s.nume.compareTo(nume) == 0 && s.prenume.compareTo(prenume) == 0 && c.an == an) {
										String nota = c.nota.get(s) != null ? c.nota.get(s).toString() : "Studentul nu a fost notat";
										afis = afis + "\n" + c.nume + " " + nota + "\n";
									}
								}
							}
							if (afis.length() == 0)
								afis = "Nu sunteti inscris la niciun curs in acest an.";
							System.out.println(afis);
						}
						if (index == 5) {
							break;
						}
					}
				} else if (Application.getInstance().currentUser.menuStrategy.getAccountType() == UserAccountType.TEACHER) {
					while (index != 4) {
						System.out.print("1. Cursuri listate\n2. Studenti listati\n3. Noteaza student:\n4. Inapoi\nIntroduceti optiunea dorita:");
						index = sc.nextInt();
						String[] splitNume = Application.getInstance().currentUser.userName.split(" ");
						String nume = splitNume[0];
						String prenume = splitNume[1];
						if (index == 1) {
							String afis = new String();
							for (Curs c : cursuri) {
								if (c.profu.nume.compareTo(nume) == 0 && c.profu.prenume.compareTo(prenume) == 0) {
									afis = afis + "\n" + c.nume + "\n";
								}
							}
							if (afis.length() == 0)
								System.out.println("Nu sunteti profesor la niciun curs.");
							System.out.println(afis);
						} else if (index == 2) {
							String afis = new String();
							for (Curs c : cursuri) {
								if (c.profu.nume.compareTo(nume) == 0 && c.profu.prenume.compareTo(prenume) == 0) {
									for (Student s : c.studenti) {
										afis = afis + "\n" + s.nume + " " + s.prenume;
									}
								}
							}
							if (afis.length() == 0)
								System.out.println("Nu aveti studenti la curs.");
							System.out.println(afis);
						} else if (index == 3) {
							System.out.println("Introduceti numele studentului:");
							String numeStudent = sc.next();
							System.out.println("Introduceti prenumele studentului:");
							String prenumeStudent = sc.next();
							System.out.println("Introduceti nota studentului:");
							ok=0;
							int nota = sc.nextInt();
							try {
								for (Curs c : cursuri) {
									if (c.profu.nume.compareTo(nume) == 0 && c.profu.prenume.compareTo(prenume) == 0) {
										for (Student s : c.studenti) {
											if (s.nume.compareTo(numeStudent) == 0 && s.prenume.compareTo(prenumeStudent) == 0) {
												ok = 1;
												try {
													c.noteazaStudent(s, nota);
												} catch (Exception ex) {
													System.out.println(ex.getMessage());
												}
											}
										}
									}
								}
							} catch (Exception e1) {
								System.out.println( "Studentul " + numeStudent + " " + prenumeStudent + " nu este inscris la curs.");
							}
							if (ok == 0) {
								System.out.println( "Studentul " + numeStudent + " " + prenumeStudent + " nu este inscris la curs.");
							}
							IDisplayManager displayManager = Settings.displayHashMap.get(Settings.displayType);
							displayManager.displayCourses(cursuri);
							if(ok==1) {
								System.out.println("Studentul a fost notat");
							}
						}
						if (index == 4) {
							break;
						}
					}
				}
			} else if (index == 2) {
				System.out.println("Nume:");
				String name = sc.next();
				System.out.println("Prenume:");
				String prenume = sc.next();
				System.out.println("Username:");
				String userName = sc.nextLine();
				userName = sc.nextLine();
				System.out.println("Parola:");
				password = sc.next();
				try {
					Application.getInstance().inregistrare(name, prenume, password, userName);
					System.out.println("Inregistrare reusita!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				break;
			}
		}
	}
}
