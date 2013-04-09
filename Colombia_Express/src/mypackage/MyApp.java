package mypackage;

import net.rim.device.api.database.Database;
import net.rim.device.api.database.DatabaseFactory;
import net.rim.device.api.database.Statement;
import net.rim.device.api.io.URI;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import database.Config;

/**
 * This class extends the UiApplication class, providing a
 * graphical user interface.
 */
public class MyApp extends UiApplication
{
    /**
     * Entry point for application
     * @param args Command line arguments (not used)
     */ 
	static MainScreen splashScreen = null, homeScreen = null, homeScreen1 = null;
	static int	g_nScreenWidth = 0, g_nScreenHeight = 0;
	static Config path = new Config();
	static Config statement = new Config();
	
    public static void main(String[] args)
    {
        // Create a new instance of the application and make the currently
        // running thread the application's event dispatch thread.
        MyApp theApp = new MyApp();       
        theApp.enterEventDispatcher();
    }
    

    /**
     * Creates a new MyApp object
     */
    public MyApp()
    {    
    	
    	
    	try{
	     	URI uri = URI.create(path.Path());
	     //sqliteDB variable tipo Database
	     	Database sqliteDB = DatabaseFactory.create(uri);
	     		//Statement st utilizado para crear las sentencias SQL
	     	
		     	Statement st = sqliteDB.createStatement(statement.CreateUsuario());
		     	st.prepare();
				st.execute();
				st.close();
				
				Statement cn = sqliteDB.createStatement(statement.CreateNoticia());
				cn.prepare();
				cn.execute();
				cn.close();	
				
		     	Statement co = sqliteDB.createStatement(statement.CreateOferta());				
				co.prepare();
				co.execute();
				co.close();
				
			sqliteDB.close();
				
	     }catch (Exception e){
	    	 e.printStackTrace();
	     }finally{  	
	        splashScreen = new SplashScreen();
	        pushScreen( splashScreen );
	     }
    }    
}
