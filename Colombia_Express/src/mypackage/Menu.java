package mypackage;


import net.rim.blackberry.api.invoke.Invoke;
import net.rim.blackberry.api.invoke.MessageArguments;
import net.rim.blackberry.api.invoke.PhoneArguments;
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
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import promocion.Promocion;
import estilos.BitmapButtonField;
import estilos.Metodos;

public final class Menu extends Metodos implements FieldChangeListener
{
	private BitmapButtonField btn1,btn2,btn3,btn4;
	

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
	Bitmap Llamar;
	Bitmap Llamar1;

	Bitmap headImage;
	Bitmap bitmapImg;
	
	public Menu()
	    { 
		if (Display.getWidth() == 320)	
		{
			bitmapImg = Bitmap.getBitmapResource("fondo_320.png");
			
		}
		else if (Display.getWidth() == 360)	
		{
			bitmapImg = Bitmap.getBitmapResource("fondo_360.png");
			
		}
		else if (Display.getWidth() == 480)	
		{
			bitmapImg = Bitmap.getBitmapResource("fondo_480.png");
			
		}
		else if (Display.getWidth() == 640)	
		{

			bitmapImg = Bitmap.getBitmapResource("fondo_640.png");
		}
		//getMainManager().setBackground(BackgroundFactory.createSolidBackground(0xaaaaaa));
			
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		
		
		Pedidos 	=  Bitmap.getBitmapResource("pedidos.png");
		Pedidos1 	=  Bitmap.getBitmapResource("pedidos1.png");
		Perfil 		=  Bitmap.getBitmapResource("perfil.png");
		Perfil1	 	=  Bitmap.getBitmapResource("perfil1.png");
		Promocion	=  Bitmap.getBitmapResource("promo.png");
		Promocion1 	=  Bitmap.getBitmapResource("promo1.png");
		
		
		
		
		
		
		if (Display.getWidth() == 320)
		{
			headImage = Bitmap.getBitmapResource("titulo_320.png");
			distancia = 5;
			distanciaL = 5;
			AnchoImagen = 320;	
			AltoImagen = 39;
			compensacion = 0;
			
			Pedidos 	=  Bitmap.getBitmapResource("1pedidos.png");
			Pedidos1 	=  Bitmap.getBitmapResource("1pedidos1.png");
			Perfil 		=  Bitmap.getBitmapResource("1perfil.png");
			Perfil1	 	=  Bitmap.getBitmapResource("1perfil1.png");
			Promocion	=  Bitmap.getBitmapResource("1promo.png");
			Promocion1 	=  Bitmap.getBitmapResource("1promo1.png");
			Llamar		=  Bitmap.getBitmapResource("1llamar.png");
			Llamar1 	=  Bitmap.getBitmapResource("1llamar1.png");
		}
		if (Display.getWidth() == 360)
		{
			headImage = Bitmap.getBitmapResource("titulo_360.png");
			distancia = 7;
			distanciaL = 7;
			AnchoImagen = 360;	
			AltoImagen = 44;
			
			Pedidos 	=  Bitmap.getBitmapResource("2pedidos.png");
			Pedidos1 	=  Bitmap.getBitmapResource("2pedidos1.png");
			Perfil 		=  Bitmap.getBitmapResource("2perfil.png");
			Perfil1	 	=  Bitmap.getBitmapResource("2perfil1.png");
			Promocion	=  Bitmap.getBitmapResource("2promo.png");
			Promocion1 	=  Bitmap.getBitmapResource("2promo1.png");
			Llamar		=  Bitmap.getBitmapResource("2llamar.png");
			Llamar1 	=  Bitmap.getBitmapResource("2llamar1.png");
			
		}
		if (Display.getWidth() == 480)
		{
			headImage = Bitmap.getBitmapResource("titulo_480.png");
			AnchoImagen = 360;	
			AltoImagen = 44;
			distancia = 7;
			distanciaL = 7;
			
			Pedidos 	=  Bitmap.getBitmapResource("pedidos.png");
			Pedidos1 	=  Bitmap.getBitmapResource("pedidos1.png");
			Perfil 		=  Bitmap.getBitmapResource("perfil.png");
			Perfil1	 	=  Bitmap.getBitmapResource("perfil1.png");
			Promocion	=  Bitmap.getBitmapResource("promo.png");
			Promocion1 	=  Bitmap.getBitmapResource("promo1.png");
			Llamar		=  Bitmap.getBitmapResource("llamar.png");
			Llamar1 	=  Bitmap.getBitmapResource("llamar1.png");
						
		}	
		if (Display.getWidth() == 640)
		{
			headImage = Bitmap.getBitmapResource("titulo_640.png");
			distancia = 10;
			distanciaL = 10;
			AnchoImagen = 480;	
			AltoImagen = 58;
			
			Pedidos 	=  Bitmap.getBitmapResource("0pedidos.png");
			Pedidos1 	=  Bitmap.getBitmapResource("0pedidos1.png");
			Perfil 		=  Bitmap.getBitmapResource("0perfil.png");
			Perfil1	 	=  Bitmap.getBitmapResource("0perfil1.png");
			Promocion	=  Bitmap.getBitmapResource("0promo.png");
			Promocion1 	=  Bitmap.getBitmapResource("0promo1.png");
			Llamar		=  Bitmap.getBitmapResource("0llamar.png");
			Llamar1 	=  Bitmap.getBitmapResource("0llamar1.png");
			
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
		//Bitmap imagen 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( headImage, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		head.setMargin(0, 0, 0, 0);
		add(head);
		
		
		//Bitmap bordes = Bitmap.getBitmapResource("rounded-border1.png");
		VerticalFieldManager content = new VerticalFieldManager(Field.USE_ALL_WIDTH|Field.FIELD_HCENTER |Field.FIELD_VCENTER);
		//content.setBorder(BorderFactory.createBitmapBorder(new XYEdges(15,15,15,15), bordes));
		
		HorizontalFieldManager uno = new HorizontalFieldManager(Field.FIELD_HCENTER |Field.FIELD_VCENTER);
		uno.setBackground(BackgroundFactory.createLinearGradientBackground(Color.ORANGE, Color.ORANGE,Color.ORANGE,Color.ORANGE));
		
		btn1 = new BitmapButtonField(Promocion, Promocion1);
		btn1.setChangeListener(this);
		btn1.setMargin(distancia, distanciaL, distancia, distanciaL);
		
		btn2 = new BitmapButtonField(Perfil,Perfil1);
		btn2.setChangeListener(this);
		btn2.setMargin(distancia, distanciaL, distancia, distanciaL);
	
		HorizontalFieldManager dos = new HorizontalFieldManager(Field.FIELD_HCENTER |Field.FIELD_VCENTER);
		dos.setBackground(BackgroundFactory.createLinearGradientBackground(Color.ORANGE, Color.ORANGE,Color.ORANGE,Color.ORANGE));
		btn3 = new BitmapButtonField(Pedidos,Pedidos1);
		btn3.setChangeListener(this);
		btn3.setMargin(0, distanciaL, distancia, distanciaL);
		
		btn4 = new BitmapButtonField(Llamar,Llamar1);
		btn4.setChangeListener(this);
		btn4.setMargin(0, distanciaL, distancia, distanciaL);
	
		
		if (Display.getWidth() == 320)
		{
			content.setMargin(20, 0, 0, 0);
		}
		else if (Display.getWidth() == 480)
		{
			content.setMargin(45, 0, 0, 0);
		}
		else if (Display.getWidth() == 480 && Display.getHeight() >= 640)
		{
			content.setMargin(25, 0, 0, 0);
		}
		else if (Display.getWidth() == 360 && Display.getHeight() == 480)
		{
			content.setMargin(60, 0, 0, 0);
		}
		else if (Display.getWidth() == 640)
		{
			content.setMargin(55, 0, 0, 0);
		
		}
		uno.add(btn1);
		uno.add(btn2);
		content.add(uno);
		dos.add(btn3);
		dos.add(btn4);
		content.add(dos);	
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
		    
			}else if(btn4== field){
				 try {
						String phoneNumber = "77879532";
						if ( phoneNumber.length() == 0 ){
						   Dialog.alert("No se puede realizar la llamada,\nIntentelo mas tarde. Gracias");
						}
						else {
						    PhoneArguments call = new PhoneArguments (PhoneArguments.ARG_CALL,phoneNumber);
						    Invoke.invokeApplication(Invoke.APP_TYPE_PHONE, call);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Dialog.alert("No se puede realizar la llamada,\nIntentelo mas tarde. Gracias");
					}  
		    
			}
			 
			
		
			
		}

		public boolean onClose() {
			System.exit(0);
			return true;
		}
	

}
