package mypackage;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import estilos.Metodos;

public class Perfil extends Metodos implements FieldChangeListener{
	
	String Imagen = "http://www.i-moves.com/demos/promosApp/IMG/1350596052_Oferta.jpg";
	Bitmap imagen1;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	private int imgAncho = 200;
	private int imgAlto = 170;
	Bitmap bordes;
	private Font fBold,fLite;
	Bitmap headImage;
	Bitmap headImage2;
	int lateral = 20;
	int tFuente = 25;
	public Perfil(){
		//Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");
		//getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		//setTitle("Picture Scroll Field Demo");
		getMainManager().setBackground(BackgroundFactory.createLinearGradientBackground(0xe9c32e, 0xe9c32e,0xebd155,0xebd155));
		if (Display.getWidth() == 320)
		{
			headImage = Bitmap.getBitmapResource("titulo_320.png");
			headImage2 = Bitmap.getBitmapResource("perfil_320.png");
			AnchoImagen = 320;	
			AltoImagen = 39;
			
			tFuente = 20;
			
			imgAncho = 78;
			imgAlto = 78;
			
			

		}
		if (Display.getWidth() == 360)
		{
			headImage = Bitmap.getBitmapResource("titulo_360.png");
			headImage2 = Bitmap.getBitmapResource("perfil_360.png");
			AnchoImagen = 360;	
			AltoImagen = 44;
			
			imgAncho = 88;
			imgAlto = 88;
			
			tFuente = 25;
			
			lateral = 25;
			

		}
		if (Display.getWidth() == 480)
		{
			headImage = Bitmap.getBitmapResource("titulo_480.png");
			headImage2 = Bitmap.getBitmapResource("perfil_480.png");
			
			AnchoImagen = 360;	
			AltoImagen = 44;
			
			//AnchoImagen = 480;	
			//AltoImagen = 58;
			
			imgAncho = 95;
			imgAlto = 95;
	
			lateral = 65;
			
			tFuente = 28;
			
			
		}	
		if (Display.getWidth() == 640)
			
		{
			headImage = Bitmap.getBitmapResource("titulo2_640.png");
			headImage2 = Bitmap.getBitmapResource("perfil_640.png");
			AnchoImagen = 480;	
			AltoImagen = 58;
			
			lateral = 50;
			
			imgAncho = 118;
			imgAlto = 118;
			
			tFuente = 40;
	

		}
		
		try
		 {
		 	FontFamily ffFont = FontFamily.forName("Arial");
		 	fBold = ffFont.getFont(Font.BOLD, tFuente );
		 	
		 	FontFamily ffFont1 = FontFamily.forName("Arial");
		 	fLite = ffFont1.getFont(Font.SANS_SERIF_STYLE, tFuente - 2);
		 	
		 }catch (ClassNotFoundException e){
		 	   System.out.println(e.getMessage());
		 }
		
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(headImage2));

		/*BitmapField bitmapImg1 = new BitmapField( headImage, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		//head.setMargin(0, 0, -5, 0);
		setBanner(head);
		

		
		HorizontalFieldManager cImagen = new HorizontalFieldManager();
		
		
		BitmapField imgPerfil = new BitmapField(headImage2);
		cImagen.add(imgPerfil);
		//cImagen.setMargin(5,5,5,5);
		
		VerticalFieldManager perfil = new VerticalFieldManager(Field.FIELD_HCENTER);
		
		//-----------------------------------------------0xe3b32f//
		
		HorizontalFieldManager Content = new HorizontalFieldManager(Field.USE_ALL_WIDTH);
		//Content.setBackground(BackgroundFactory.createBitmapBackground(headImage2));
		/*Content.setBackground(BackgroundFactory.createLinearGradientBackground(Color.ORANGERED, Color.ORANGERED,Color.ORANGERED,Color.ORANGERED));
		
		VerticalFieldManager cTitulo = new VerticalFieldManager();
		cTitulo.setBackground(BackgroundFactory.createLinearGradientBackground(0xe9c633, 0xe9c633,0xe9c633,0xe9c633));
		cTitulo.setMargin(5, 5, 0, 0);
		RichTextField nombre = new RichTextField("Colombia Express",RichTextField.FIELD_HCENTER){
	        public void paint(Graphics g){      
	            g.setColor(Color.WHITE);
	            super.paint(g);
	        }};
		nombre.setMargin(15, 10, 10, 10);
		nombre.setFont(fBold);
		
		RichTextField Ubicacion = new RichTextField("Ubicacion del perfil"){
	        public void paint(Graphics g){      
	            g.setColor(Color.WHITE);
	            super.paint(g);
	        }};
		Ubicacion.setMargin(15, 10, 10, 10);
		Ubicacion.setFont(fLite);*/
		
		

/*
		
		RichTextField tituloDetalle = new RichTextField("Colombia Express",RichTextField.FIELD_HCENTER){
	        public void paint(Graphics g){      
	            g.setColor(Color.ORANGERED);
	            super.paint(g);
	        }};
	    tituloDetalle.setMargin(15, 10, 10, 10);
	   tituloDetalle.setFont(fBold);
	    
	    HorizontalFieldManager cTitulo2 = new HorizontalFieldManager( HorizontalFieldManager.FIELD_HCENTER);
		cTitulo2.setBackground(BackgroundFactory.createLinearGradientBackground(Color.ORANGERED, Color.ORANGERED,Color.ORANGERED,Color.ORANGERED));
		cTitulo2.setMargin(10, 10, 10, 10);
	    cTitulo2.setPadding(10, 5, 10, 5);
	    
	    RichTextField tituloDetalle2 = new RichTextField("Lorem impsun"){
	        public void paint(Graphics g){      
	            g.setColor(Color.WHITE);
	            super.paint(g);
	        }};
	    tituloDetalle2.setFont(fBold);
	    
		RichTextField descripcion = new RichTextField(
				"BUZZCOAPPS se centra en crear una aplicación en tiempo record sin complicaciones ni trabas para agilizar el proceso de tu negocio; " +
				"siempre dentro de un presupuesto accesible obteniendo para tu empresa calidad en tecnología.", DrawStyle.HALIGN_MASK
				){
	        public void paint(Graphics g){      
	            g.setColor(Color.ORANGERED);
	            super.paint(g);
	        }};
		descripcion.setMargin(20, 10, 20, 10);
		
		
	    HorizontalFieldManager cTelefono = new HorizontalFieldManager( HorizontalFieldManager.FIELD_HCENTER);
	    cTelefono.setBackground(BackgroundFactory.createLinearGradientBackground(Color.ORANGERED, Color.ORANGERED,Color.ORANGERED,Color.ORANGERED));
	    cTelefono.setMargin(10, 10, 10, 10);
	    cTelefono.setPadding(10, 5, 10, 5);
	    
	    RichTextField telefono = new RichTextField("Tel. 22962276"){
	        public void paint(Graphics g){      
	            g.setColor(Color.WHITE);
	            super.paint(g);
	        }};
	    //telefono.setFont(fLite);
		
		
		//cTitulo.add(nombre);		
		//cTitulo.add(Ubicacion);
		Content.add(cImagen);
		//Content.add(cTitulo);
		
		add(Content);
		add(perfil);
		//add(btnContent);
		add(tituloDetalle);
		cTitulo2.add(tituloDetalle2);
		add(cTitulo2);
		
		add(descripcion);
		cTelefono.add(telefono);
		add(cTelefono);

		*/
	}
	
	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
		
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
