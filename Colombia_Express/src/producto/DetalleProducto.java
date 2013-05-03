package producto;

import javax.microedition.io.file.FileConnection;

import net.rim.blackberry.api.browser.Browser;
import net.rim.device.api.browser.field2.BrowserField;
import net.rim.device.api.browser.field2.BrowserFieldConfig;
import net.rim.device.api.io.URI;
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

public class DetalleProducto extends Metodos implements FieldChangeListener {
	String tipoConexion = Autenticacion.getConnectionString()[0];
	String getTipo = Autenticacion.getConnectionString()[1];
	Bitmap facebook;
	Bitmap facebook1;
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
	int tFuente = 24;
	int imgAlto = 220;
	int imgAncho = 364;
	int imgIzquierda = 50;
	int lblIzquierda = 50;
	int lblTop = 110;
	int descTop = 60;
	int descAncho = 200;
	int descAlto = 200;
	int descIzquierda = 40;
	String Nombre, Descripcion, Imagen, Regular;
	String imgWeb;
	int ancho;
	int alto;
	Bitmap imgDetalle = Bitmap.getBitmapResource("detalle_640.png");
	int btn_Alto = 56;
	int btn_Ancho = 290;
	int allContentH = 224;

	Bitmap headImage;
	
	int compensacion = 0;
	BitmapButtonField btnFacebook;
	
	public DetalleProducto(String titulo,String descripcion, String imagen,String precio){
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
			headImage = Bitmap.getBitmapResource("titulo3_320.png");
			imgWeb = "<img width='320' height='211'  src='"+Imagen+"' style='border:0px solid ORANGE;margin-left: -8px;margin-top: -8px;margin-right: -8px;margin-bottom: -8px;'>";
			allContentH = 112;
			descTop = 20;
			descIzquierda = -30;
			lblIzquierda = 20;
			lblTop = 60;
			imgIzquierda = 15;
			
			tFuente = 18;
			
			facebook = Bitmap.getBitmapResource("facebook_290.png");
			facebook1 = Bitmap.getBitmapResource("facebook1_290.png");
			imgDetalle = Bitmap.getBitmapResource("detalle_320.png");
		}
		
		if (Display.getWidth() == 360)
		{
			headImage = Bitmap.getBitmapResource("titulo3_360.png");
			imgWeb = "<img width='360' height='215' src='"+Imagen+"' style='border:0px solid ORANGE;margin-left: -8px;margin-top: -8px;margin-right: -8px;margin-bottom: -8px;'>";
			allContentH = 126;
			descAncho = 230;
			descTop = 20;
			descIzquierda = 5;
			lblIzquierda = 25;
			lblTop = 65;
			imgIzquierda = 25;
			
			tFuente = 20;
			
			facebook = Bitmap.getBitmapResource("facebook_310.png");
			facebook1 = Bitmap.getBitmapResource("facebook1_310.png");
			imgDetalle = Bitmap.getBitmapResource("detalle_360.png");
		}
		if (Display.getWidth() == 480)
		{
			imgWeb = "<img width='480' height='317'  src='"+Imagen+"' style='border:0px solid ORANGE;margin-left: -8px;margin-top: -8px;margin-right: -8px;margin-bottom: -8px;'>";
			headImage = Bitmap.getBitmapResource("titulo3_480.png");
			allContentH = 169;
			descAncho = 300;
			descAlto = 150;
			descTop = 35;
			descIzquierda = 20;
			lblIzquierda = 40;
			lblTop = 90;
			
			tFuente = 27;
			imgIzquierda = 40;
					
			facebook = Bitmap.getBitmapResource("facebook_400.png");
			facebook1 = Bitmap.getBitmapResource("facebook1_400.png");
			imgDetalle = Bitmap.getBitmapResource("detalle_480.png");
		}	
		if (Display.getWidth() == 640)
		{
			headImage = Bitmap.getBitmapResource("titulo3_640.png");
			imgWeb = "<img width='640' height='422'  src='"+Imagen+"' style='border:0px solid ORANGE;margin-left: -8px;margin-top: -8px;margin-right: -8px;margin-bottom: -8px;'>";
			allContentH = 224;
			descAncho = 410;
			descAlto = 250;
			descTop =40;
			descIzquierda = 50;
			lblIzquierda = 60;
			lblTop = 120;
			
			tFuente = 38;
			imgIzquierda = 50;
			
			facebook = Bitmap.getBitmapResource("facebook_540.png");
			facebook1 = Bitmap.getBitmapResource("facebook1_540.png");
			imgDetalle = Bitmap.getBitmapResource("detalle_640.png");
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
        browserField.setMargin(0, 0, 0, 0);
       add(browserField);
        //Dialog.alert(imgWeb);
        browserField.displayContent(imgWeb, "http://localhost");
						
				
				HorizontalFieldManager allContent = new HorizontalFieldManager(){
		            //define width
		            public int getPreferredWidth()
		            {
		                return 640;
		            }
		            
		            //define height
		            public int getPreferredHeight()
		            {
		                return allContentH;
		            }
		            
		            protected void sublayout( int maxWidth, int maxHeight )
		            {
		                super.sublayout(getPreferredWidth(), getPreferredHeight());
		                setExtent(getPreferredWidth(), getPreferredHeight());
		            }
		        };
		        allContent.setBackground(BackgroundFactory.createBitmapBackground(imgDetalle));	
		        HorizontalFieldManager iContent = new HorizontalFieldManager();
		        VerticalFieldManager pContent = new VerticalFieldManager(){
		            //define width
		            public int getPreferredWidth()
		            {
		                return 70;
		            }
		            
		            //define height
		            public int getPreferredHeight()
		            {
		                return 50;
		            }
		            
		            protected void sublayout( int maxWidth, int maxHeight )
		            {
		                super.sublayout(getPreferredWidth(), getPreferredHeight());
		                setExtent(getPreferredWidth(), getPreferredHeight());
		            }
		        };
		        pContent.setMargin(lblTop, 0, 0, lblIzquierda);
		        VerticalFieldManager dContent = new VerticalFieldManager();
				dContent.setMargin(descTop, 10, 0, descIzquierda);

				lblPregular		= new RichTextField("$"+Regular, Field.FIELD_HCENTER | DrawStyle.ELLIPSIS){
			        public void paint(Graphics g){      
			            g.setColor(0x355298);
			            super.paint(g);
			        }};
				lblPregular.setMargin(0, 0, 0, 0);
				lblPregular.setFont(fBold);
				
				lblNombre		= new LabelField(Nombre){
			        public void paint(Graphics g){      
			            g.setColor(Color.WHITE);
			            super.paint(g);
			        }};
			    lblNombre.setMargin(0, 0, 0, 0);
			    lblNombre.setFont(fBold);
				lblDescripcion 	= new RichTextField(Descripcion, Field.FIELD_LEFT){
			        public void paint(Graphics g){      
			            g.setColor(Color.WHITE);
			            super.paint(g);
			        }
			        public int getPreferredWidth()
		            {
		                return descAncho;
		            }
		            //define height
		            public int getPreferredHeight()
		            {
		                return descAlto;
		            }
		            protected void layout( int maxWidth, int maxHeight )
		            {
		                super.layout(getPreferredWidth(), 
		                                getPreferredHeight());
		                setExtent(getPreferredWidth(), getPreferredHeight());
		            }};
			    lblDescripcion.setMargin(0, 0, 0, 0);
				//lblDescripcion.setFont(fLite);
				
			    //pContent.setBackground(BackgroundFactory.createLinearGradientBackground(Color.RED, Color.RED,Color.RED,Color.RED));
			    pContent.add(lblPregular);
			    
			   //dContent.setBackground(BackgroundFactory.createLinearGradientBackground(Color.ORANGE, Color.ORANGE,Color.ORANGE,Color.ORANGE));
				dContent.add(lblNombre);
				dContent.add(lblDescripcion);

				iContent.add(pContent);
				iContent.add(dContent);
				allContent.add(iContent);
				add(allContent);
				
				btnFacebook = new BitmapButtonField(facebook, facebook1);
				btnFacebook.setChangeListener(this);
				btnFacebook.setMargin(20,0,10,imgIzquierda);
				add(btnFacebook);
			}
	
	
	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
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
	
	public boolean onClose() {
		//force the app to quit

		 TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
	        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
	        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT);
	        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
	        
	        UiEngineInstance engine = Ui.getUiEngineInstance();
	        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
	        openScreen(new Producto(1));
		return true;
	}

}
