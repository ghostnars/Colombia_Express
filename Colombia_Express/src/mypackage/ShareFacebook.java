package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.decor.BackgroundFactory;

import com.blackberry.facebook.ApplicationSettings;
import com.blackberry.facebook.Facebook;
import com.blackberry.facebook.FacebookException;
import com.blackberry.facebook.inf.User;

import estilos.Metodos;

public class ShareFacebook extends Metodos implements FieldChangeListener{
	Bitmap logo;
	BitmapField profile;
	ButtonField btnConectar;
	ButtonField btnDesconectar;
	ButtonField btnPublicar;
	
	String Nombre = "";
	int alto, ancho;


	String NEXT_URL = "http://www.facebook.com/connect/login_success.html"; 
	String APPLICATION_ID = "215868425204765"; 
	String APPLICATION_SECRET = "3c02c4630fa73493d55e129750e41066"; 
	String[] PERMISSIONS = Facebook.Permissions.USER_DATA_PERMISSIONS; 
	   
	ApplicationSettings as = new ApplicationSettings(NEXT_URL, APPLICATION_ID, APPLICATION_SECRET, PERMISSIONS); 
	Facebook fb = Facebook.getInstance(as);
	User user;
	
	public ShareFacebook(){
		
		
		getMainManager().setBackground(BackgroundFactory.createLinearGradientBackground(Color.BLACK, Color.BLACK,Color.ORANGERED,Color.ORANGERED));
		
		if (Display.getWidth() == 320)
		{

		}
		if (Display.getWidth() == 360)
		{

		}
		if (Display.getWidth() == 480)
		{


		}	
		if (Display.getWidth() == 640)
		{

		}
		
		BitmapField Logo = new BitmapField(logo);
		setTitle(Logo);
		
		btnDesconectar = new ButtonField("Desconectar", Field.FIELD_HCENTER);
		btnDesconectar.setChangeListener(this);
		btnDesconectar.setMargin(10, 0, 10, 0);
		btnDesconectar.setPadding(5, 5, 5, 5);
		
		btnConectar = new ButtonField("Conectarse a Facebook", Field.FIELD_HCENTER);
		btnConectar.setChangeListener(this);
		btnConectar.setMargin(10, 0, 10, 0);
		btnConectar.setPadding(5, 5, 5, 5);
		
		//PROFILE FB
		try {
			user = fb.getCurrentUser();
			
			
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Negocio
		String Titulo = "";
		RichTextField lblTitulo = new RichTextField(Titulo, Field.FIELD_HCENTER){
	        public void paint(Graphics g){      
	            g.setColor(Color.WHITE);
	            super.paint(g);
	        }
	    };
		
		//Descripcion
		String Descripcion = "";
		RichTextField lblDescripcion = new RichTextField(Descripcion, Field.FIELD_HCENTER){
	        public void paint(Graphics g){      
	            g.setColor(Color.WHITE);
	            super.paint(g);
	        }
	    };
	    lblTitulo.setMargin(10, 5, 10, 5);
	    lblDescripcion.setMargin(10, 5, 10, 5);
	    
	    if (user == null) {
			add(btnConectar);
			profile = new BitmapField(Bitmap.getBitmapResource("perfilFacebook.png"), Field.FIELD_HCENTER);
			profile.setMargin(10, 5, 10, 5);
			add(profile);
		}else{
			add(btnDesconectar);
			profile = new BitmapField(user.getPicture(0),Field.FIELD_HCENTER);
			profile.setMargin(10, 5, 10, 5);
			Nombre = user.getFirstName() + " " + user.getLastName();
			add(profile);
		}
	    
		RichTextField lblNombre = new RichTextField(Nombre,Field.FIELD_HCENTER){
	        public void paint(Graphics g){      
	            g.setColor(Color.WHITE);
	            super.paint(g);
	        }
	    };
	    
	    lblNombre.setMargin(10, 5, 10, 5);
	    add(lblNombre);
	    add(lblTitulo);
	    add(lblDescripcion);
		
		btnPublicar = new ButtonField("Publicar", Field.FIELD_HCENTER);
		if (user != null) {
			btnPublicar.setChangeListener(this);
		}
		btnPublicar.setMargin(10, 0, 10, 0);
		btnPublicar.setPadding(5, 5, 5, 5);
		add(btnPublicar);
		
	}
	
	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
		if (btnPublicar == field) {
			/*String link    = "http://"+rsPromociones.getPromociones()[posicion].getWeb();
			String urlPic  = "http://www.checkngo.rokapps.com.rokauser.arvixevps.com/" + rsPromociones.getPromociones()[posicion].getArte().toString();
			String nNombre = rsPromociones.getPromociones()[posicion].getNegocio() + " - " + rsPromociones.getPromociones()[posicion].getFechafinal();
			String Descrip = rsPromociones.getPromociones()[posicion].getPromocion();
			
			String result = user.publishPost("", link, urlPic, nNombre, nNombre, Descrip, urlPic);
			if ((result != null) && !result.trim().equals("")) {
			    Dialog.inform("Su publicación ha sido realizada exitosamente.");
			} else {
			    Dialog.inform("Error en publicacion, intentelo mas tarde.");
			}*/
		}
		
		
	}
	
	public boolean onClose() {
		//fuerza la app a cerrar o envia a la page que se desee con tranciciones y direccion
		TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
	    transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
	    transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT);
	    transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
	    UiEngineInstance engine = Ui.getUiEngineInstance();
	    engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
	        	openScreen(new Menu());	
 
		return true;
	}

}
