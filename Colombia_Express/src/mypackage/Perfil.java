package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import promocion.Promocion;
import estilos.BitmapButtonField;
import estilos.Metodos;

public class Perfil extends Metodos implements FieldChangeListener{
	
	private BitmapButtonField btnHome;
	String Imagen = "http://www.i-moves.com/demos/promosApp/IMG/1350596052_Oferta.jpg";
	Bitmap imagen1;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	private int imgAncho = 200;
	private int imgAlto = 170;
	Bitmap bordes;
	BitmapButtonField facebook;
	
	Bitmap facebook0;
	Bitmap facebook1;
	Bitmap twitter0;
	Bitmap twitter1;
	
	int lateral = 20;
	int tFuente = 25;
	public Perfil(){
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		//setTitle("Picture Scroll Field Demo");
		if (Display.getWidth() == 320)
		{
			AnchoImagen = 320;	
			AltoImagen = 39;
			
			facebook0 = Bitmap.getBitmapResource("facebook_290.png");
			facebook1 = Bitmap.getBitmapResource("facebook_290.png");
			
			twitter0 = Bitmap.getBitmapResource("twitter_290.png");
			twitter1 = Bitmap.getBitmapResource("twitter_290.png");

		}
		if (Display.getWidth() == 360)
		{
			
			AnchoImagen = 360;	
			AltoImagen = 44;
			
			imgAncho = 100;
			imgAlto = 100;
			
			tFuente = 30;
			
			lateral = 25;
			
			facebook0 = Bitmap.getBitmapResource("facebook_170.png");
			facebook1 = Bitmap.getBitmapResource("facebook_170.png");
			
			twitter0 = Bitmap.getBitmapResource("twitter_170.png");
			twitter1 = Bitmap.getBitmapResource("twitter_170.png");

		}
		if (Display.getWidth() == 480)
		{
			AnchoImagen = 480;	
			AltoImagen = 58;
			
			imgAncho = 350;
			imgAlto = 300;
	
			lateral = 65;
			
			tFuente = 25;
			
			facebook0 = Bitmap.getBitmapResource("facebook_170.png");
			facebook1 = Bitmap.getBitmapResource("facebook_170.png");
			
			twitter0 = Bitmap.getBitmapResource("twitter_170.png");
			twitter1 = Bitmap.getBitmapResource("twitter_170.png");
			
		}	
		if (Display.getWidth() == 640)
			
		{
			imgAncho = 540;
			imgAlto = 450;
			
			lateral = 50;
			
			tFuente = 35;
			
			facebook0 = Bitmap.getBitmapResource("facebook_170.png");
			facebook1 = Bitmap.getBitmapResource("facebook_170.png");
			
			twitter0 = Bitmap.getBitmapResource("twitter_170.png");
			twitter1 = Bitmap.getBitmapResource("twitter_170.png");

		}
		Bitmap imagenT 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( imagenT, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		//head.setMargin(0, 0, -5, 0);
		setBanner(head);
		
		HorizontalFieldManager Content = new HorizontalFieldManager(Field.USE_ALL_WIDTH);
		Content.setBackground(BackgroundFactory.createLinearGradientBackground(Color.BLACK, Color.BLACK,Color.BLACK,Color.BLACK));
		
		HorizontalFieldManager cImagen = new HorizontalFieldManager();
		VerticalFieldManager cTitulo = new VerticalFieldManager();
		
		BitmapField imgPerfil = new BitmapField(resizeBitmap(Bitmap.getBitmapResource("img4.jpg"), imgAncho, imgAlto ));
		cImagen.add(imgPerfil);
		cImagen.setMargin(5,5,5,5);
		
		VerticalFieldManager perfil = new VerticalFieldManager(Field.FIELD_HCENTER);
		
		RichTextField nombre = new RichTextField("Colombia Express",RichTextField.FIELD_HCENTER){
	        public void paint(Graphics g){      
	            g.setColor(Color.WHITE);
	            super.paint(g);
	        }};
		nombre.setMargin(15, 10, 10, 10);
		nombre.setFont(nombre.getFont().derive(Font.FANTASY_STYLE ,tFuente, Ui.UNITS_px));
		
		RichTextField Ubicacion = new RichTextField("Ubicacion del perfil"){
	        public void paint(Graphics g){      
	            g.setColor(Color.SILVER);
	            super.paint(g);
	        }};
		Ubicacion.setMargin(15, 10, 10, 10);
		
		
		
		HorizontalFieldManager btnContent = new HorizontalFieldManager(Field.FIELD_HCENTER);
		
		BitmapButtonField btnFacebook = new BitmapButtonField(facebook0, facebook1);
		btnFacebook.setChangeListener(this);
		btnFacebook.setMargin(5, 5, 10, 5);
		btnContent.add(btnFacebook);
		
		BitmapButtonField btnTwitter = new BitmapButtonField(twitter0, twitter1);
		btnTwitter.setChangeListener(this);
		btnTwitter.setMargin(5, 5, 10, 5);
		btnContent.add(btnTwitter);

		
		RichTextField tituloDetalle = new RichTextField("Colombia Express",RichTextField.FIELD_HCENTER){
	        public void paint(Graphics g){      
	            g.setColor(Color.DIMGRAY);
	            super.paint(g);
	        }};
	    tituloDetalle.setMargin(15, 10, 10, 10);
	    tituloDetalle.setFont(nombre.getFont().derive(Font.SANS_SERIF_STYLE ,tFuente, Ui.UNITS_px));
	    
		RichTextField descripcion = new RichTextField(
				"BUZZCOAPPS se centra en crear una aplicación en tiempo record sin complicaciones ni trabas para agilizar el proceso de tu negocio; " +
				"siempre dentro de un presupuesto accesible obteniendo para tu empresa calidad en tecnología.", DrawStyle.HALIGN_MASK
				){
	        public void paint(Graphics g){      
	            g.setColor(Color.GRAY);
	            super.paint(g);
	        }};
		descripcion.setMargin(0, 10, 20, 10);
		
		
		
		
		cTitulo.add(nombre);		
		cTitulo.add(Ubicacion);
		Content.add(cImagen);
		Content.add(cTitulo);
		add(Content);
		add(perfil);
		//add(btnContent);
		add(tituloDetalle);
		add(descripcion);
	}
	
	
	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
		if(facebook == field){
			UiApplication.getUiApplication().pushScreen(new Popimage());
		}
	}
	
	public boolean onClose() {
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
