package mypackage;


import net.rim.device.api.database.Cursor;
import net.rim.device.api.database.Database;
import net.rim.device.api.database.DatabaseFactory;
import net.rim.device.api.database.Row;
import net.rim.device.api.database.Statement;
import net.rim.device.api.io.URI;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import producto.Producto;
import database.Config;
import estilos.Utils;

public class SplashScreen extends MainScreen {
	String var = "vacio";
	Config path = new Config();
	Config statement = new Config();
	BitmapField bmp;
    public SplashScreen() {
    	
        super(SplashScreen.USE_ALL_HEIGHT | SplashScreen.NO_VERTICAL_SCROLL);
        
        try{
        	
        setTitle((LabelField)null) ; // hide screen title  
    	if (Display.getWidth() > Display.getHeight())
		{	
        bmp = new BitmapField(Utils.getFitBitmapImage("splash1.png",Display.getWidth(), Display.getHeight()),BitmapField.FIELD_HCENTER | BitmapField.FIELD_VCENTER);  
        }else if (Display.getWidth() == 360){
        bmp = new BitmapField(Utils.getFitBitmapImage("splash2.png",Display.getWidth(), Display.getHeight()),BitmapField.FIELD_HCENTER | BitmapField.FIELD_VCENTER);  
	
        }
        
        HorizontalFieldManager rowHolder = new HorizontalFieldManager(NO_HORIZONTAL_SCROLL | NO_VERTICAL_SCROLL| Field.FIELD_HCENTER | USE_ALL_HEIGHT );
        rowHolder.add(bmp);
        add(rowHolder);
        
    	
               
        }catch (Exception e){
        e.printStackTrace();
       // Dialog.alert("que ondas ahii "+e.getMessage());
        }finally{
        	//if (var.equals("no")){
		     //  MyApp.homeScreen = new Registro();
            //}else{
            	MyApp.homeScreen = new Menu();
           // }
		       UiApplication.getUiApplication().invokeLater(new Runnable() {			        	
				public void run() {					
					UiApplication.getUiApplication().pushScreen(MyApp.homeScreen);
				    UiApplication.getUiApplication().popScreen(MyApp.splashScreen);					    
				}  
		            //tiempo de duracion del splashscreen y repeticion en falso
		        }, 1500, false);
        }
    }
}
