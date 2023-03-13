import Dao.EtudiantDao;
import Dao.IEtudiantDao;
import Metier.EtudiantMetier;
import Metier.IEtudiantMetier;
import Model.Etudiant;
import Presentation.EtudiantControleur;
import Presentation.IEtudiantControleur;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner;

public class SimulationDeNotes_App {
    static Scanner clavier = new Scanner(System.in);
    static IEtudiantControleur etudiantControleur;
    public static void test1() throws Exception {
        System.out.println("[ Test 1 ]");
        var dao = new EtudiantDao();
        var metier = new EtudiantMetier(dao);
        var controleur = new EtudiantControleur(metier);
        //Injections de dépendances
        metier.setEtudiantDao(dao);
        controleur.setEtudiantMetier(metier);
        //Test
        String rep = "";
        do {
            System.out.println("********** Simulation de notes **********");
           try {
               String choix = "";
               while (true) {
                   System.out.print("Entrer l'ID de l'étudiant: ");
                   int id = Integer.parseInt(clavier.nextLine());
                   controleur.afficherMoyenneEtudiant(id);
                   System.out.print("Voulez-vous continuer? (O/N): ");
                   rep = clavier.nextLine();
                   if (rep.equalsIgnoreCase("N")) break;
               }
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
        } while (rep.equalsIgnoreCase("O"));
    }
    public static void test2() throws Exception{
        System.out.println("[ Test 2 ]");
        String daoClass ;
        String metierClass ;
        String controleurClass ;

        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("config.properties");
        if (propertiesFile == null)throw new Exception("Properties file not found");
        else {
                try {
                    properties.load(propertiesFile);
                    daoClass = properties.getProperty("DAO");
                    metierClass = properties.getProperty("METIER");
                    controleurClass = properties.getProperty("CONTROLEUR");
                    propertiesFile.close();
                } catch (Exception e) {
                    throw new Exception("Error while loading properties file");
                }finally {
                    properties.clear();
                }
            }
            try {
                Class cDao = Class.forName(daoClass);
                Class cMetier = Class.forName(metierClass);
                Class cControleur = Class.forName(controleurClass);

                var dao = (IEtudiantDao<Etudiant,Integer>) cDao.getDeclaredConstructor().newInstance();
                var metier = (IEtudiantMetier) cMetier.getDeclaredConstructor().newInstance();
                var controleur = (IEtudiantControleur) cControleur.getDeclaredConstructor().newInstance();

                Method setDao = cMetier.getMethod("setEtudiantDao", IEtudiantDao.class);
                setDao.invoke(metier, dao);

                Method setMetier = cControleur.getMethod("setEtudiantMetier", IEtudiantMetier.class);
                setMetier.invoke(controleur, metier);
               controleur.afficherMoyenneEtudiant(1);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    public static void test3() throws Exception{
        System.out.println("[ Test 3 ]");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc.xml");
        etudiantControleur = (IEtudiantControleur) context.getBean("controleur");
        etudiantControleur.afficherMoyenneEtudiant(1);
    }
    public static void test4() throws Exception{
        System.out.println("[ Test 4 ]");
        ApplicationContext context = new AnnotationConfigApplicationContext("Dao","Metier","Presentation");
        etudiantControleur = (IEtudiantControleur) context.getBean(IEtudiantControleur.class);
        etudiantControleur.afficherMoyenneEtudiant(1);
    }
    public static void main(String[] args) throws Exception {
        test1();
        System.out.println("=========================================");
        test2();
        System.out.println("=========================================");
        test3();
        System.out.println("=========================================");
        test4();
    }
}
