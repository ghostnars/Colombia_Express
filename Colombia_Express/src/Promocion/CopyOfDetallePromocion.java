package Promocion;

import javax.microedition.io.file.FileConnection;

import net.rim.blackberry.api.browser.Browser;
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
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.BorderFactory;
import database.Autenticacion;
import database.Config;
import estilos.BitmapButtonField;
import estilos.Metodos;

public class CopyOfDetallePromocion extends Metodos implements FieldChangeListener {
	

	Bitmap imagen1;
	URI uri;
	String idOfertas;
	RichTextField lblDescripcion;
	LabelField lblCantidad;
	LabelField lblOferta;
	LabelField lblNombre;
	RichTextField lblPregular;
	Font fBold,fLite;
	FileConnection fconn;
	private BitmapButtonField btnUpdate;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	int tFuente = 24;
	int imgAlto = 220;
	int imgAncho = 364;
	int lblIzquierda = 50;
	String Nombre, Descripcion, Imagen, Regular;
	String imgWeb;
	int izquierda = 60;
	int derecha = 60;
	int ancho;
	int alto;
	Bitmap facebook = Bitmap.getBitmapResource("facebook_290.png");
	Bitmap facebook1 = Bitmap.getBitmapResource("facebook_290.png");
	int btn_Alto = 56;
	int btn_Ancho = 290;
	String tipoConexion = Autenticacion.getConnectionString()[0];
	String getTipo = Autenticacion.getConnectionString()[1];
	Bitmap headImage;
	BitmapButtonField btnFacebook;
	int compensacion = 0;
	public CopyOfDetallePromocion(String titulo,String descripcion, String imagen,String precio){
		Nombre 		= titulo;
		Imagen 		= imagen;
		Descripcion = descripcion;
		Regular 	= precio;
		
		//Dialog.alert(imagen);
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");
		//getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		getMainManager().setBackground(BackgroundFactory.createLinearGradientBackground(0xe9c32e, 0xe9c32e,0xebd155,0xebd155));
		//Barra Titulo
		
		if (Display.getWidth() == 320)
		{
			headImage = Bitmap.getBitmapResource("titulo_320.png");
			imgWeb = "<img width='274' height='180'  src='"+Imagen+"' style='border:8px solid ORANGE;margin-left: -8px;margin-top: -8px;margin-right: -8px;margin-bottom: -8px;'>";
			
			AnchoImagen = 320;	
			AltoImagen = 39;
			tFuente = 18;
			
			izquierda = 15;
			derecha = 15;
			
			lblIzquierda = 10;
			
			alto = 49;
			ancho = 250;
			compensacion = 30;
			facebook = Bitmap.getBitmapResource("facebook_290.png");
			facebook1 = Bitmap.getBitmapResource("facebook1_290.png");
			
		}
		
		if (Display.getWidth() == 360)
		{
			headImage = Bitmap.getBitmapResource("titulo_360.png");
			imgWeb = "<img width='294' height='193' src='"+Imagen+"' style='border:8px solid ORANGE;margin-left: -8px;margin-top: -8px;margin-right: -8px;margin-bottom: -8px;'>";
			
			AnchoImagen = 360;	
			AltoImagen = 44;
			
			izquierda = 25;
			derecha = 25;
			
			tFuente = 19;
			lblIzquierda = 20;
			
			alto = 59;
			ancho = 310;
			facebook = Bitmap.getBitmapResource("facebook_310.png");
			facebook1 = Bitmap.getBitmapResource("facebook1_310.png");
			
		}
		if (Display.getWidth() == 480)
		{
			imgWeb = "<img width='384' height='252'  src='"+Imagen+"' style='border:8px solid ORANGE;margin-left: -8px;margin-top: -8px;margin-right: -8px;margin-bottom: -8px;'>";
			headImage = Bitmap.getBitmapResource("titulo_480.png");
			AnchoImagen = 360;	
			AltoImagen = 44;
			

			
			izquierda = 40;
			derecha = 40;
			
			tFuente = 19;
			
			lblIzquierda = 35;
			facebook = Bitmap.getBitmapResource("facebook_400.png");
			facebook1 = Bitmap.getBitmapResource("facebook1_400.png");
			
		}	
		if (Display.getWidth() == 640)
		{
			headImage = Bitmap.getBitmapResource("titulo_640.png");
			AnchoImagen = 480;	
			AltoImagen = 58;
			imgWeb = "<img width='524' height='343'  src='"+Imagen+"' style='border:8px solid ORANGE;margin-left: -8px;margin-top: -8px;margin-right: -8px;margin-bottom: -8px;'>";
			izquierda = 50;
			derecha = 50;
			
			lblIzquierda = 45;
			
			tFuente = 35;
			facebook = Bitmap.getBitmapResource("facebook_540.png");
			facebook1 = Bitmap.getBitmapResource("facebook1_540.png");
			
		}
		 
		
		try
		 {
		 	FontFamily ffFont = FontFamily.forName("Arial");
		 	fBold = ffFont.getFont(Font.BOLD, tFuente );
		 	
		 	FontFamily ffFont1 = FontFamily.forName("Arial");
		 	fLite = ffFont1.getFont(Font.ANTIALIAS_NONE, tFuente - 4);
		 	
		 }catch (ClassNotFoundException e){
		 	   System.out.println(e.getMessage());
		 }
		
		//Bitmap imagen1 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( headImage, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		//head.setMargin(0, 0, -5, 0);
		setBanner(head);
		
		

		BrowserFieldConfig myBrowserFieldConfig = new BrowserFieldConfig();
        myBrowserFieldConfig.setProperty(BrowserFieldConfig.NAVIGATION_MODE,BrowserFieldConfig.NAVIGATION_MODE_NONE);
        myBrowserFieldConfig.setProperty(BrowserFieldConfig.ENABLE_COOKIES, Boolean.TRUE);
        BrowserField browserField = new BrowserField(myBrowserFieldConfig);
        browserField.setMargin(10, derecha, 0, izquierda);
        add(browserField);
        //Dialog.alert(imgWeb);
        browserField.displayContent(imgWeb, "http://localhost");
				
				Bitmap bordes = Bitmap.getBitmapResource("bg_shadowAnaranjado.png");
				
				HorizontalFieldManager pContent = new HorizontalFieldManager(Field.USE_ALL_WIDTH | HorizontalFieldManager.FIELD_HCENTER);
				pContent.setBorder(BorderFactory.createBitmapBorder(new XYEdges(25,25,25,25), bordes));
				pContent.setMargin(5,lblIzquierda , 0, lblIzquierda);
				VerticalFieldManager pLabel = new VerticalFieldManager();
				VerticalFieldManager Precio = new VerticalFieldManager(Field.FIELD_RIGHT);
				
				
				LabelField lblRegular = new LabelField("Precio Regular:"){
			        public void paint(Graphics g){      
			            g.setColor(Color.WHITE);
			            super.paint(g);
			        }};
				lblRegular.setMargin(15, 10, 10, lblIzquierda+(lblIzquierda/3));
				lblRegular.setFont(fBold);
				

				
				lblPregular		= new RichTextField("$ "+Regular.toUpperCase(),Field.FIELD_RIGHT){
			        public void paint(Graphics g){      
			            g.setColor(Color.WHITE);
			            super.paint(g);
			        }};
				lblPregular.setMargin(15, 10, 10, lblIzquierda);
				
				
				pLabel.add(lblRegular);
				Precio.add(lblPregular);
				pContent.add(pLabel);
				pContent.add(Precio);
				add(pContent);

				VerticalFieldManager dContent = new VerticalFieldManager(Field.FIELD_HCENTER | Field.USE_ALL_WIDTH);
				dContent.setBorder(BorderFactory.createBitmapBorder(new XYEdges(25,25,25,25), bordes));
				dContent.setMargin(0, lblIzquierda, 0, lblIzquierda);
				lblNombre		= new LabelField(Nombre,Field.FIELD_HCENTER){
			        public void paint(Graphics g){      
			            g.setColor(Color.WHITE);
			            super.paint(g);
			        }};
				lblNombre.setMargin(5, 0, 5, 0);
				lblDescripcion 	= new RichTextField(Descripcion){
			        public void paint(Graphics g){      
			            g.setColor(Color.WHITE);
			            super.paint(g);
			        }};
			    lblDescripcion.setMargin(5, 0, 5, 0);
				lblDescripcion.setFont(fLite);
				dContent.add(lblNombre);
				lblNombre.setFont(fBold);
				SeparatorField separator = new SeparatorField(){
			        public void paint(Graphics g){      
			            g.setColor(Color.WHITE);
			            super.paint(g);
			        }};
				dContent.add(separator);
				dContent.add(lblDescripcion);
				
				add(dContent);
				
				
				btnFacebook = new BitmapButtonField(facebook, facebook1, Field.FIELD_HCENTER);
				btnFacebook.setChangeListener(this);
				btnFacebook.setMargin(6,0,50,0);
				add(btnFacebook);
			}
	
	
	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
		if(btnFacebook == field){

			if(btnFacebook == field){
				if(getTipo.equals("wifi")){
					Browser.getDefaultSession().displayPage("http://i-moves.com/colombiaExpress/?nombre="+Nombre+"&descripcion="+Descripcion+"&enlace=http://buzzcoapp.com/&imagen="+Imagen);						
				}else if(getTipo.equals("BIBS")){		
					Browser.getDefaultSession().displayPage("http://i-moves.com/colombiaExpress/?nombre="+Nombre+"&descripcion="+Descripcion+"&enlace=http://buzzcoapp.com/&imagen="+Imagen);				
				}else{
					Status.show("Necesita internet para enviar un correo",1000 );
				}
			}
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
	        openScreen(new Promocion(1));
		return true;
	}

}
