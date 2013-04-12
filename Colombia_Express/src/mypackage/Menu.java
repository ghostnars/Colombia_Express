package mypackage;


import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.MessageArguments;
import net.rim.blackberry.api.mail.Address;
import net.rim.blackberry.api.mail.Message;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import promocion.Promocion;
import estilos.BitmapButtonField;
import estilos.Metodos;

public final class Menu extends Metodos implements FieldChangeListener
{
	private BitmapButtonField btn1,btn2,btn3;
	

	int tIcono = 160;
	int distancia = 0;
	int distanciaL = 0;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	int compensacion = 0;
	
	Bitmap Pedidos;
	Bitmap Pedidos1;
	Bitmap Perfil;
	Bitmap Perfil1;
	Bitmap Promocion;
	Bitmap Promocion1;
	
	
	public Menu()
	    { 
		
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");	
		//getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		getMainManager().setBackground(BackgroundFactory.createSolidBackground(0xaaaaaa));
		
		
		Pedidos 	=  Bitmap.getBitmapResource("pedidos.png");
		Pedidos1 	=  Bitmap.getBitmapResource("pedidos1.png");
		Perfil 		=  Bitmap.getBitmapResource("perfil.png");
		Perfil1	 	=  Bitmap.getBitmapResource("perfil1.png");
		Promocion	=  Bitmap.getBitmapResource("promo.png");
		Promocion1 	=  Bitmap.getBitmapResource("promo1.png");
		
		if (Display.getWidth() == 320)
		{
			tIcono = 85;
			distancia = 0;
			distanciaL = 0;
			AnchoImagen = 320;	
			AltoImagen = 39;
			compensacion = 0;
			
			Pedidos 	=  Bitmap.getBitmapResource("1pedidos.png");
			Pedidos1 	=  Bitmap.getBitmapResource("1pedidos1.png");
			Perfil 		=  Bitmap.getBitmapResource("1perfil.png");
			Perfil1	 	=  Bitmap.getBitmapResource("1perfil1.png");
			Promocion	=  Bitmap.getBitmapResource("1promo.png");
			Promocion1 	=  Bitmap.getBitmapResource("1promo1.png");
			
		}
		if (Display.getWidth() == 360)
		{
			compensacion = 50;
			tIcono = 110;
			distancia = 0;
			distanciaL = 0;
			AnchoImagen = 360;	
			AltoImagen = 44;
			
		}
		if (Display.getWidth() == 480)
		{
			tIcono = 120;
			AnchoImagen = 360;	
			AltoImagen = 44;
			distancia = 0;
			compensacion = 5;
		}	
		if (Display.getWidth() == 640)
		{
			compensacion = 10;
			
			AnchoImagen = 480;	
			AltoImagen = 58;
			
			Pedidos 	=  Bitmap.getBitmapResource("0pedidos.png");
			Pedidos1 	=  Bitmap.getBitmapResource("0pedidos1.png");
			Perfil 		=  Bitmap.getBitmapResource("0perfil.png");
			Perfil1	 	=  Bitmap.getBitmapResource("0perfil1.png");
			Promocion	=  Bitmap.getBitmapResource("0promo.png");
			Promocion1 	=  Bitmap.getBitmapResource("0promo1.png");
			
			
		}
		if (Display.getWidth() == 480 && Display.getHeight() == 640)
		{
			AnchoImagen = 480;	
			AltoImagen = 58;
			
			tIcono = 160;
			distancia = 30;
		}
		if (Display.getWidth() == 320 || Display.getWidth() == 480)
		{	
			//compensacion = 30;
		}
		//add(new LabelField(Display.getWidth() +" - "+  Display.getHeight()));
		Bitmap imagen 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( imagen, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		head.setMargin(0, 0, 0, 0);
		add(head);
		
		/*Bitmap Pedidos 	=  resizeBitmap(Bitmap.getBitmapResource("pedidos.png"), tIcono, tIcono);
		Bitmap Pedidos1 	=  resizeBitmap(Bitmap.getBitmapResource("pedidos1.png"), tIcono, tIcono);
		Bitmap Mapa 		=  resizeBitmap(Bitmap.getBitmapResource("mapa.png"), tIcono, tIcono);
		Bitmap Mapa1 		=  resizeBitmap(Bitmap.getBitmapResource("mapa1.png"), tIcono, tIcono);
		Bitmap Perfil 		=  resizeBitmap(Bitmap.getBitmapResource("perfil.png"), tIcono, tIcono);
		Bitmap Perfil1	 	=  resizeBitmap(Bitmap.getBitmapResource("perfil1.png"), tIcono, tIcono);
		Bitmap Promocion	=  resizeBitmap(Bitmap.getBitmapResource("promo.png"), tIcono, tIcono);
		Bitmap Promocion1 	=  resizeBitmap(Bitmap.getBitmapResource("promo1.png"), tIcono, tIcono);*/
		
		
		//Bitmap bordes = Bitmap.getBitmapResource("rounded-border1.png");
		VerticalFieldManager content = new VerticalFieldManager(Field.USE_ALL_WIDTH|Field.FIELD_HCENTER |Field.FIELD_VCENTER);
		//content.setBorder(BorderFactory.createBitmapBorder(new XYEdges(15,15,15,15), bordes));
		
		HorizontalFieldManager uno = new HorizontalFieldManager(Field.FIELD_HCENTER |Field.FIELD_VCENTER);
		
		btn1 = new BitmapButtonField(Promocion, Promocion1);
		btn1.setChangeListener(this);
		btn1.setMargin(distancia+compensacion, distanciaL, distancia, distanciaL);
		
		btn2 = new BitmapButtonField(Perfil,Perfil1);
		btn2.setChangeListener(this);
		btn2.setMargin(distancia+compensacion, distanciaL, distancia, distanciaL);
	
		HorizontalFieldManager dos = new HorizontalFieldManager(Field.FIELD_HCENTER |Field.FIELD_VCENTER);
		
		btn3 = new BitmapButtonField(Pedidos,Pedidos1);
		btn3.setChangeListener(this);
		btn3.setMargin(distancia, distanciaL, distancia, distanciaL);
		
	
		
		if (Display.getWidth() == 320 || Display.getWidth() == 480)
		{
			tIcono = 120;
			
			uno.add(btn1);
			uno.add(btn2);
			content.add(uno);
			dos.add(btn3);
			//dos.add(btn2);
			content.add(dos);	
			
			
		}else if (Display.getWidth() == 480 && Display.getHeight() >= 640)
		{
			tIcono = 120;
			
			uno.add(btn1);
			uno.add(btn2);
			content.add(uno);
			dos.add(btn3);
			//dos.add(btn2);
			content.add(dos);	
			
			
		}else if (Display.getWidth() == 360 && Display.getHeight() == 480)
		{
			
			uno.add(btn1);
			uno.add(btn2);
			content.add(uno);
			dos.add(btn3);
			//dos.add(btn2);
			content.add(dos);	

			
		}else{
			
			uno.add(btn1);
			uno.add(btn2);
			
			content.add(uno);
			dos.add(btn3);
			dos.add(btn2);
			content.add(dos);
		
		}
		add(content);
		    }
		public void fieldChanged(Field field, int arg1) {
			// TODO Auto-generated method stub
			
			if(btn1 == field){
				TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
		        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
		        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
		        UiEngineInstance engine = Ui.getUiEngineInstance();
		        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
		        openScreen(new Promocion(0));
		        
			}else if(btn2== field){
				TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
		        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
		        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
		        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
		        
		        UiEngineInstance engine = Ui.getUiEngineInstance();
		        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
		        openScreen(new Perfil());
			}else if(btn3== field){
				try {
					
					Message m = new Message();
					Address a = new Address("ghostnars@gmail.com", "Colombia Express");
					Address[] addresses = {a};
					m.addRecipients(net.rim.blackberry.api.mail.Message.RecipientType.TO, addresses);
					m.setContent("Telefono:\nPedido:");
					m.setSubject("Pedido Colombia Express ");
					Invoke.invokeApplication(Invoke.APP_TYPE_MESSAGES, new MessageArguments(m));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					openScreen(new ErrorPage());
				}
		    
			}
			 
			
		
			
		}

		public boolean onClose() {
			System.exit(0);
			return true;
		}
	

}
