package promocion;

import javax.microedition.io.file.FileConnection;

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
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.BorderFactory;
import database.Config;
import estilos.BitmapButtonField;
import estilos.Metodos;

public class DetallePromocion extends Metodos implements FieldChangeListener {
	
	Config path 	= new Config();
	Config statement= new Config();
	private Bitmap bitmapImage;
	Bitmap imagen1;
	URI uri;
	String idOfertas;
	RichTextField lblDescripcion;
	LabelField lblFecha;
	LabelField lblCantidad;
	LabelField lblOferta;
	LabelField lblNombre;
	private RichTextField lblPoferta;
	private RichTextField lblPregular;
	private Font fBold,fLite;
	private BitmapButtonField btnHome;
	FileConnection fconn;
	private BitmapButtonField btnUpdate;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	int tFuente = 24;
	int imgAlto = 220;
	int imgAncho = 364;
	int lblIzquierda = 50;
	String Nombre, idOferta, Imagen, Descripcion, Oferta, Regular;
	String imgWeb;
	int izquierda = 60;
	int derecha = 60;
	private int ancho=250;
	private int alto=49;
	
	int btn_Alto = 56;
	int btn_Ancho = 290;
	
	Bitmap facebook0;
	Bitmap facebook1;
	Bitmap twitter0;
	Bitmap twitter1;
	
	
	public DetallePromocion(String titulo, String idOferta, String imagen,String descripcion,String oferta,String regular){
		Nombre 		= titulo;
		Imagen 		= imagen;
		Descripcion = descripcion;
		Oferta 		= oferta;
		Regular 	= regular;
		
		//Dialog.alert(imagen);
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");
		//getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		getMainManager().setBackground(BackgroundFactory.createSolidBackground(0xcecdcd));
		//Barra Titulo
		
		if (Display.getWidth() == 320)
		{
			imgWeb = "<img width='274' height='180'  src='"+Imagen+"'>";
			
			AnchoImagen = 320;	
			AltoImagen = 39;
			tFuente = 17;
			
			izquierda = 15;
			derecha = 15;
			
			lblIzquierda = 10;
			
			alto = 49;
			ancho = 250;
			
			facebook0 = Bitmap.getBitmapResource("facebook_290.png");
			facebook1 = Bitmap.getBitmapResource("facebook_290.png");
			
			twitter0 = Bitmap.getBitmapResource("twitter_290.png");
			twitter1 = Bitmap.getBitmapResource("twitter_290.png");
			
		}
		
		if (Display.getWidth() == 360)
		{
			imgWeb = "<img width='294' height='193'  src='"+Imagen+"'>";
			
			AnchoImagen = 360;	
			AltoImagen = 44;
			
			izquierda = 25;
			derecha = 25;
			
			tFuente = 17;
			lblIzquierda = 20;
			
			alto = 59;
			ancho = 310;
			
			facebook0 = Bitmap.getBitmapResource("facebook_310.png");
			facebook1 = Bitmap.getBitmapResource("facebook_310.png");
			
			twitter0 = Bitmap.getBitmapResource("twitter_310.png");
			twitter1 = Bitmap.getBitmapResource("twitter_310.png");
		}
		if (Display.getWidth() == 480)
		{
			imgWeb = "<img width='384' height='252'  src='"+Imagen+"'>";
			
			AnchoImagen = 360;	
			AltoImagen = 44;
			
			izquierda = 40;
			derecha = 40;
			
			tFuente = 19;
			
			lblIzquierda = 35;
			
			facebook0 = Bitmap.getBitmapResource("facebook_350.png");
			facebook1 = Bitmap.getBitmapResource("facebook_350.png");
			
			twitter0 = Bitmap.getBitmapResource("twitter_350.png");
			twitter1 = Bitmap.getBitmapResource("twitter_350.png");
			
		}	
		if (Display.getWidth() == 640)
		{
			AnchoImagen = 480;	
			AltoImagen = 58;
			imgWeb = "<img width='524' height='343'  src='"+Imagen+"'>";
			izquierda = 50;
			derecha = 50;
			
			lblIzquierda = 45;
			
			tFuente = 30;
			
			facebook0 = Bitmap.getBitmapResource("facebook_540.png");
			facebook1 = Bitmap.getBitmapResource("facebook_540.png");
			
			twitter0 = Bitmap.getBitmapResource("twitter_540.png");
			twitter1 = Bitmap.getBitmapResource("twitter_540.png");
			
			//Dialog.alert(imgWeb);
			
		}
		 
		
		try
		 {
		 	FontFamily ffFont = FontFamily.forName("Arial");
		 	fBold = ffFont.getFont(Font.BOLD, tFuente );
		 	
		 	FontFamily ffFont1 = FontFamily.forName("Arial");
		 	fLite = ffFont1.getFont(Font.ANTIALIAS_NONE, tFuente - 2);
		 	
		 }catch (ClassNotFoundException e){
		 	   System.out.println(e.getMessage());
		 }
		
		Bitmap imagen1 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( imagen1, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
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
				
				Bitmap bordes = Bitmap.getBitmapResource("rounded-border1.png");
				
				HorizontalFieldManager pContent = new HorizontalFieldManager(Field.USE_ALL_WIDTH | HorizontalFieldManager.FIELD_HCENTER);
				pContent.setBorder(BorderFactory.createBitmapBorder(new XYEdges(15,15,15,15), bordes));
				pContent.setMargin(5,lblIzquierda , 0, lblIzquierda);
				VerticalFieldManager pLabel = new VerticalFieldManager();
				VerticalFieldManager Precio = new VerticalFieldManager(Field.FIELD_RIGHT);
				
				
				LabelField lblRegular = new LabelField("Precio Regular:");
				lblRegular.setMargin(20, 10, 10, lblIzquierda+(lblIzquierda/2));
				lblRegular.setFont(fBold);
				
				LabelField lblOferta = new LabelField("Precio Oferta: ");
				lblOferta.setMargin(20, 10, 10, lblIzquierda+(lblIzquierda/2));
				lblOferta.setFont(fBold);
				
				lblPoferta 		= new RichTextField("$ "+Oferta.toUpperCase(),Field.FIELD_RIGHT);
				lblPoferta.setMargin(20, 10, 20, lblIzquierda+lblIzquierda);
				lblOferta.setFont(fLite);
				
				lblPregular		= new RichTextField("$ "+Regular.toUpperCase(),Field.FIELD_RIGHT);
				lblPregular.setMargin(20, 10, 10, lblIzquierda+lblIzquierda);
				lblOferta.setFont(fLite);
				
				pLabel.add(lblRegular);
				pLabel.add(lblOferta);
				Precio.add(lblPregular);
				Precio.add(lblPoferta);
				pContent.add(pLabel);
				pContent.add(Precio);
				add(pContent);

				VerticalFieldManager dContent = new VerticalFieldManager(Field.FIELD_HCENTER | Field.USE_ALL_WIDTH);
				dContent.setBorder(BorderFactory.createBitmapBorder(new XYEdges(15,15,15,15), bordes));
				dContent.setMargin(0, lblIzquierda, 5, lblIzquierda);
				lblNombre		= new LabelField(Nombre,Field.FIELD_HCENTER);
				lblNombre.setMargin(5, 0, 5, 0);
				lblDescripcion 	= new RichTextField(Descripcion){
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
				
				BitmapButtonField btnFacebook = new BitmapButtonField(facebook0, facebook1);
				btnFacebook.setChangeListener(this);
				btnFacebook.setMargin(5, 15, 5, 15);
				btnContent.add(btnFacebook);
				
				BitmapButtonField btnTwitter = new BitmapButtonField(twitter0, twitter1);
				btnTwitter.setChangeListener(this);
				btnTwitter.setMargin(10, 15, 60, 15);
				btnContent.add(btnTwitter);
				//add(btnContent);
				
			}
	
	
	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
		if(btnUpdate == field){
			System.exit(0);
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
