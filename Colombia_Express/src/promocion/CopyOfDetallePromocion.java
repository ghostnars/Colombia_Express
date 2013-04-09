package promocion;

import javax.microedition.io.file.FileConnection;

import mypackage.Menu;
import net.rim.device.api.io.URI;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import database.Config;
import estilos.BitmapButtonField;
import estilos.Metodos;

public class CopyOfDetallePromocion extends Metodos implements FieldChangeListener {
	
	Config path 	= new Config();
	Config statement= new Config();
	private Bitmap bitmapImage;
	Bitmap imagen1;
	URI uri;
	String idOfertas;
	LabelField lblDescripcion;
	LabelField lblFecha;
	LabelField lblCantidad;
	private LabelField lblPoferta;
	private LabelField lblPregular;
	private Font ffOferta;
	private BitmapButtonField btnHome;
	FileConnection fconn;
	private BitmapButtonField btnUpdate;
	String idOferta, Imagen, Descripcion, Oferta, Regular;
	public CopyOfDetallePromocion(String idOferta, String imagen,String descripcion,String oferta,String regular){
		Imagen 		= imagen;
		Descripcion = descripcion;
		Oferta 		= oferta;
		Regular 	= regular;
		//Dialog.alert(imagen);
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		//Barra Titulo
		Bitmap titleImage = Bitmap.getBitmapResource("barra.png");	
		
		HorizontalFieldManager fmtitleBar 	= new HorizontalFieldManager(Field.USE_ALL_WIDTH);
		
		HorizontalFieldManager fmHome 		= new HorizontalFieldManager();
		fmHome.setMargin(0, 200, 0, 0);
		HorizontalFieldManager fmTitulo 	= new HorizontalFieldManager(Field.FIELD_VCENTER);
		fmTitulo.setMargin(0, 200, 0, 0);
		HorizontalFieldManager fmActualizar = new HorizontalFieldManager();
		
		btnHome = new BitmapButtonField(Bitmap.getBitmapResource("home1.png"), Bitmap.getBitmapResource("home1.png"));
		btnHome.setChangeListener(this);

		btnUpdate = new BitmapButtonField(Bitmap.getBitmapResource("sync.png"), Bitmap.getBitmapResource("sync.png"));
		btnUpdate.setChangeListener(this);
		
		LabelField Titulo = new LabelField("OFERTAS");
		
		fmHome.add(btnHome);
		fmTitulo.add(Titulo);
		fmActualizar.add(btnUpdate);
		
		fmtitleBar.add(fmHome);
		fmtitleBar.add(fmTitulo);
		fmtitleBar.add(fmActualizar);
			
		fmtitleBar.setBackground(BackgroundFactory.createBitmapBackground(titleImage));
		setTitle(fmtitleBar);
		//Barra Titulo
		
		idOfertas = idOferta;
		

				bitmapImage = ImageUrl(Imagen);
				//bitmapImage = Bitmap.getBitmapResource("img1.png");;
				boolean lowRes = Display.getWidth() <= 360;
				if (lowRes)
				{
				    imagen1 = resizeBitmap(bitmapImage, 300, 127);
				}
				else
				{
				    imagen1 = resizeBitmap(bitmapImage, 500, 212);
				}	
				BitmapField imagenBanner = new BitmapField(imagen1, Field.FIELD_HCENTER);  
				lblDescripcion 	= new LabelField(Descripcion);
				lblPoferta 		= new LabelField("Oferta: $"+Oferta.toUpperCase()){
			        public void paint(Graphics g){      
			            g.setColor(Color.RED);
			            super.paint(g);
			        }};
				lblPregular		= new LabelField("Regular: $"+Regular.toUpperCase());
				
				lblPoferta.setFont(ffOferta);
				lblDescripcion.setMargin(10, 0, 10, 10);
				lblPoferta.setMargin(20, 10, 20, 10);
				lblPregular.setMargin(20, 10, 10, 10);
				imagenBanner.setMargin(10, 0, 15, 0);
				add(imagenBanner);
				add(new SeparatorField());
				//usedefender
				HorizontalFieldManager content = new HorizontalFieldManager(Field.USE_ALL_WIDTH | HorizontalFieldManager.NO_VERTICAL_SCROLL);
				VerticalFieldManager Descripcion = new VerticalFieldManager(){
		            //define width2
		            public int getPreferredWidth()
		            {
		                return ((Display.getWidth()/2) + (Display.getWidth()/12));
		            }
		            //define height
		            public int getPreferredHeight()
		            {
		                return Display.getHeight()/2;
		            }
		            protected void sublayout( int maxWidth, int maxHeight )
		            {
		                super.sublayout(getPreferredWidth(), 
		                                getPreferredHeight());
		                setExtent(getPreferredWidth(), getPreferredHeight());
		            }
		        };
				Descripcion.add(lblDescripcion);

				content.add(Descripcion);
				VerticalFieldManager Precio = new VerticalFieldManager();
				Precio.add(lblPregular);
				Precio.add(lblPoferta);
				content.add(Precio);
				add(content);
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
	        openScreen(new Promocion());
		return true;
	}

}
