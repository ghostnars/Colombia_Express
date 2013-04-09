package noticia;

import javax.microedition.io.file.FileConnection;

import mypackage.Menu;
import net.rim.device.api.browser.field2.BrowserField;
import net.rim.device.api.browser.field2.BrowserFieldConfig;
import net.rim.device.api.io.URI;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.BorderFactory;
import database.Config;
import estilos.BitmapButtonField;
import estilos.Metodos;

public class DetalleNoticia extends Metodos implements FieldChangeListener {
	
	Config path 	= new Config();
	Config statement= new Config();
	private Bitmap bitmapImage;
	Bitmap imagen1;
	URI uri;
	String idOfertas;
	LabelField lblDescripcion;
	LabelField lblFecha;
	LabelField lblCantidad;
	LabelField lblOferta;
	LabelField lblNombre;
	private Font fBold,fLite;
	private BitmapButtonField btnHome;
	FileConnection fconn;
	private BitmapButtonField btnUpdate;
	String Nombre, idOferta, Imagen, Descripcion, Fecha, Regular;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	String imgWeb;
	int izquierda = 60;
	int derecha = 60;
	int tFuente = 18;
	Bitmap facebook0;
	Bitmap facebook1;
	private int ancho = 250;
	private int alto = 49;
	
	public DetalleNoticia(String idNoticia, String titulo, String descripcion, String fecha, String imagen){
		Nombre 		= titulo;
		Imagen 		= imagen;
		Fecha 		= fecha;
		Descripcion = descripcion;
		
		
		//Regular 	= regular;
		//Dialog.alert(imagen);
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");
		//getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		getMainManager().setBackground(BackgroundFactory.createSolidBackground(0xcecdcd));
		//Barra Titulo
		
		if (Display.getWidth() == 320)
		{
			AnchoImagen = 320;	
			AltoImagen = 39;
			imgWeb = "<img width='234' height='220'  src='"+Imagen+"'>";
			izquierda = 15;
			derecha = 15;
			tFuente = 17;
			alto = 49;
			ancho = 250;
			
			facebook0 = Bitmap.getBitmapResource("facebook_290.png");
			facebook1 = Bitmap.getBitmapResource("facebook_290.png");
			
		}
		if (Display.getWidth() == 360)
		{
			
			AnchoImagen = 360;	
			AltoImagen = 44;
			imgWeb = "<img width='294' height='193'  src='"+Imagen+"'>";
			
			izquierda = 25;
			derecha = 25;
			alto = 59;
			ancho = 310;
		}
		if (Display.getWidth() == 480)
		{
			
	
		}	
		if (Display.getWidth() == 640)
		{
			
			imgWeb = "<img width='504' height='330'  src='"+Imagen+"'>";
			izquierda = 60;
			derecha = 60;
			tFuente = 27;
			alto = 97;
			ancho = 300;
		}
		
		try
		 {
		 	FontFamily ffFont = FontFamily.forName("Arial");
		 	fBold = ffFont.getFont(Font.BOLD, tFuente);
		 	
		 	FontFamily ffFont1 = FontFamily.forName("Arial");
		 	fLite = ffFont1.getFont(Font.ANTIALIAS_NONE, tFuente-2);
		 }catch (ClassNotFoundException e){
		 	   System.out.println(e.getMessage());
		 }
		
		Bitmap imagenT 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( imagenT, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		//head.setMargin(0, 0, -5, 0);
		setBanner(head);
		
		
		
		BrowserFieldConfig myBrowserFieldConfig = new BrowserFieldConfig();
        myBrowserFieldConfig.setProperty(BrowserFieldConfig.NAVIGATION_MODE,BrowserFieldConfig.NAVIGATION_MODE_NONE);
        //myBrowserFieldConfig.setProperty(BrowserFieldConfig.ENABLE_COOKIES, Boolean.TRUE);
        BrowserField browserField = new BrowserField(myBrowserFieldConfig);
        browserField.setMargin(10, izquierda, 0, derecha);
        add(browserField);
       // Dialog.alert(imgWeb);
        browserField.displayContent(imgWeb, "http://localhost");
				//bitmapImage = ImageUrl(Imagen);
				//bitmapImage = Bitmap.getBitmapResource("img1.png");;
				
				/*boolean lowRes = Display.getWidth() <= 360;
				if (lowRes)
				{
				    imagen1 = resizeBitmap(bitmapImage, 180, 180);
				}
				else
				{
				    imagen1 = resizeBitmap(bitmapImage, 500, 212);
				}	*/
				
				//BitmapField imagenBanner = new BitmapField(imagen1, Field.FIELD_HCENTER); 
				//imagenBanner.setMargin(10, 0, 10, 0);
				//add(imagenBanner);
				
				Bitmap bordes = Bitmap.getBitmapResource("rounded-border1.png");
				
				VerticalFieldManager dContent = new VerticalFieldManager(Field.FIELD_HCENTER | Field.USE_ALL_WIDTH);
				dContent.setBorder(BorderFactory.createBitmapBorder(new XYEdges(15,15,15,15), bordes));
				dContent.setMargin(10, izquierda-5, 0, izquierda-5);
				
				lblNombre		= new LabelField(Nombre,Field.FIELD_HCENTER);
				lblNombre.setMargin(0, 0, 5, 0);
				
				lblDescripcion 	= new LabelField(Descripcion){
			        public void paint(Graphics g){      
			            g.setColor(Color.GRAY);
			            super.paint(g);
			        }};
			    lblDescripcion.setMargin(5, 0, 5, 0); 
			    
				lblDescripcion.setFont(fLite);
				dContent.add(lblNombre);
				lblNombre.setFont(fBold);
				dContent.add(new SeparatorField());
				dContent.add(lblDescripcion);
				
				add(dContent);
				
				
				VerticalFieldManager btnContent = new VerticalFieldManager(Field.FIELD_HCENTER);
				
				BitmapButtonField btnFacebook = new BitmapButtonField(Bitmap.getBitmapResource("facebooka.png"), Bitmap.getBitmapResource("facebooka.png"));
				btnFacebook.setChangeListener(this);
				btnFacebook.setMargin(5, 15, 5, 15);
				btnContent.add(btnFacebook);
				
				BitmapButtonField btnTwitter = new BitmapButtonField(Bitmap.getBitmapResource("twitter.png"), Bitmap.getBitmapResource("twitter.png"));
				btnTwitter.setChangeListener(this);
				btnTwitter.setMargin(10, 15, 20, 15);
				btnContent.add(btnTwitter);
				add(btnContent);
			}
	
	
	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
		if(btnUpdate == field){
			System.exit(0);
		}
		if(btnHome== field){
			TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
	        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
	        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT);
	        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
	        UiEngineInstance engine = Ui.getUiEngineInstance();
	        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
			openScreen(new Menu());
		}
	} 
	
	public boolean onClose() {
		//force the app to quit

		 TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
	        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
	        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT);
	        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
	        
	        UiEngineInstance engine = Ui.getUiEngineInstance();
	        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
	        openScreen(new Noticia());
		return true;
	}

}
