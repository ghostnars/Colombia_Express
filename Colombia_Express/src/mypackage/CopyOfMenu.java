package mypackage;


import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.BorderFactory;
import promocion.Promocion;

import com.blackberry.facebook.Facebook;

import estilos.BitmapButtonField;
import estilos.Metodos;

public final class CopyOfMenu extends Metodos implements FieldChangeListener
{
	private BitmapButtonField btn1,btn2,btn3,btn4,btn5,btn6;
	

	int tIcono = 160;
	int distancia = 25;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	public CopyOfMenu()
	    { 
		
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");	
		//getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		getMainManager().setBackground(BackgroundFactory.createSolidBackground(0xcecdcd));
		
		if (Display.getWidth() == 320)
		{
			tIcono = 85;
			distancia = 10;
			AnchoImagen = 320;	
			AltoImagen = 39;
			
		}
		if (Display.getWidth() == 360)
		{
			
			tIcono = 110;
			distancia = 20;
			AnchoImagen = 360;	
			AltoImagen = 44;
			
		}
		if (Display.getWidth() == 480)
		{
			tIcono = 120;
			AnchoImagen = 360;	
			AltoImagen = 44;
			distancia = 10;
		}	
		if (Display.getWidth() == 640)
		{
			AnchoImagen = 480;	
			AltoImagen = 58;
		}
		if (Display.getWidth() == 480 && Display.getHeight() == 640)
		{
			AnchoImagen = 480;	
			AltoImagen = 58;
			
			tIcono = 160;
			distancia = 30;
		}
		//add(new LabelField(Display.getWidth() +" - "+  Display.getHeight()));
		Bitmap imagen 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( imagen, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		head.setMargin(0, 0, 0, 0);
		add(head);
		
		Bitmap Facebook 	=  resizeBitmap(Bitmap.getBitmapResource("facebook.png"), tIcono, tIcono);
		Bitmap Facebook1 	=  resizeBitmap(Bitmap.getBitmapResource("facebook1.png"), tIcono, tIcono);
		Bitmap Galeria 		=  resizeBitmap(Bitmap.getBitmapResource("galeria.png"), tIcono, tIcono);
		Bitmap Galeria1 	=  resizeBitmap(Bitmap.getBitmapResource("galeria1.png"), tIcono, tIcono);
		Bitmap Mapa 		=  resizeBitmap(Bitmap.getBitmapResource("mapa.png"), tIcono, tIcono);
		Bitmap Mapa1 		=  resizeBitmap(Bitmap.getBitmapResource("mapa1.png"), tIcono, tIcono);
		Bitmap Noticia 		=  resizeBitmap(Bitmap.getBitmapResource("noticia.png"), tIcono, tIcono);
		Bitmap Noticia1 	=  resizeBitmap(Bitmap.getBitmapResource("noticia1.png"), tIcono, tIcono);
		Bitmap Perfil 		=  resizeBitmap(Bitmap.getBitmapResource("perfil.png"), tIcono, tIcono);
		Bitmap Perfil1	 	=  resizeBitmap(Bitmap.getBitmapResource("perfil1.png"), tIcono, tIcono);
		Bitmap Promocion	=  resizeBitmap(Bitmap.getBitmapResource("promocion.png"), tIcono, tIcono);
		Bitmap Promocion1 	=  resizeBitmap(Bitmap.getBitmapResource("promocion1.png"), tIcono, tIcono);
		
		Bitmap bordes = Bitmap.getBitmapResource("rounded-border1.png");
		VerticalFieldManager content = new VerticalFieldManager(Field.USE_ALL_WIDTH|Field.FIELD_HCENTER |Field.FIELD_VCENTER);
		content.setBorder(BorderFactory.createBitmapBorder(new XYEdges(15,15,15,15), bordes));
		//content.setMargin(65, 0, 0, 0);
		HorizontalFieldManager uno = new HorizontalFieldManager(Field.FIELD_HCENTER |Field.FIELD_VCENTER);
		
		btn1 = new BitmapButtonField(Promocion, Promocion1);
		btn1.setChangeListener(this);
		btn1.setMargin(0, distancia, distancia, distancia);
		
		btn2 = new BitmapButtonField(Noticia,Noticia1);
		btn2.setChangeListener(this);
		btn2.setMargin(0, distancia, distancia, distancia);
		
		btn3 = new BitmapButtonField(Galeria,Galeria1);
		btn3.setChangeListener(this);
		btn3.setMargin(0, distancia, distancia, distancia);
	
		HorizontalFieldManager dos = new HorizontalFieldManager(Field.FIELD_HCENTER |Field.FIELD_VCENTER);
		
	    btn4 = new BitmapButtonField(Mapa,Mapa1);
		btn4.setChangeListener(this);
		btn4.setMargin(0, distancia, distancia, distancia);
		
		btn5 = new BitmapButtonField(Facebook,Facebook1);
		btn5.setChangeListener(this);
		btn5.setMargin(0, distancia, distancia, distancia);
		
		btn6 = new BitmapButtonField(Perfil,Perfil1);
		btn6.setChangeListener(this);
		btn6.setMargin(0, distancia, distancia, distancia);
		
		HorizontalFieldManager tres = new HorizontalFieldManager(Field.FIELD_HCENTER |Field.FIELD_VCENTER);
		
		if (Display.getWidth() == 480 && Display.getHeight() >= 640)
		{
			tIcono = 120;
			
			uno.add(btn1);
			uno.add(btn2);
			content.add(uno);
			dos.add(btn3);
			dos.add(btn4);
			content.add(dos);	
			tres.add(btn5);
			tres.add(btn6);
			content.add(tres);
			
		}else if (Display.getWidth() == 360 && Display.getHeight() == 480)
		{
			
			uno.add(btn1);
			uno.add(btn2);
			content.add(uno);
			dos.add(btn3);
			dos.add(btn4);
			content.add(dos);	
			tres.add(btn5);
			tres.add(btn6);
			content.add(tres);
			
		}else{
			
			uno.add(btn1);
			uno.add(btn2);
			uno.add(btn3);
			content.add(uno);
			dos.add(btn4);
			dos.add(btn5);
			dos.add(btn6);
			content.add(dos);
		
		}
		add(content);
		    }
		public void fieldChanged(Field field, int arg1) {
			// TODO Auto-generated method stub
			
			if(btn1 == field){
				//Status.show("Cargando Promociones...",1000 );
				
				TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
		        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
		        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
		        UiEngineInstance engine = Ui.getUiEngineInstance();
		        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
		        openScreen(new Promocion(0));
		        
			}else if(btn2== field){
				Status.show("Cargando Noticias...",1000 );
				TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
		        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
		        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
		        UiEngineInstance engine = Ui.getUiEngineInstance();
		        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
				
		      //  openScreen(new Noticia());
		        //openScreen(new CopyOfNoticia());
			}else if(btn3== field){
				Status.show("Cargando Galeria...",1000 );
				TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
		        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
		        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
		        UiEngineInstance engine = Ui.getUiEngineInstance();
		        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
		        //openScreen(new GaleriaWeb());
		    
			}else if(btn4== field){
				Status.show("Cargando Mapa...",1000 );
				TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
		        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
		        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
		        
		        UiEngineInstance engine = Ui.getUiEngineInstance();
		        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
		        
		       // openScreen(new Mapas());
			}else if(btn5== field){
					Status.show("Cargando Facebook...");
	    			TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
	    	        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
	    	        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
	    	        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
	    	        
	    	        UiEngineInstance engine = Ui.getUiEngineInstance();
	    	        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
	            	
	    	      //	openScreen(new Facebook());
		    
			}else if(btn6== field){
				TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
		        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
		        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
		        
		        UiEngineInstance engine = Ui.getUiEngineInstance();
		        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
		        openScreen(new Perfil());
			
			}
			 
			
		
			
		}

		public boolean onClose() {
			System.exit(0);
			return true;
		}
	

}
