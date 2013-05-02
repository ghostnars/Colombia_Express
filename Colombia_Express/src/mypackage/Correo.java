package mypackage;

import java.io.IOException;
import java.util.Vector;

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
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.BorderFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransport;
import org.xmlpull.v1.XmlPullParserException;

import database.Autenticacion;
import estilos.BitmapButtonField;
import estilos.Metodos;

public class Correo extends Metodos implements FieldChangeListener
{
	
	String tipoConexion = Autenticacion.getConnectionString()[0];
	String getTipo = Autenticacion.getConnectionString()[1];	
	String ServiceUrl = "http://www.buzzcoapp.com/admin2/webServices/servicioWeb_ColombiaExpress.php"+tipoConexion; 
	String ServiceNamespace = "http://www.buzzcoApp.com/admin2/webServices";
	String methodName = "enviarMail";
	int y,x,z;
	String res = "";
	int letraLength = 50;

	Font fTitulo, fDetalle;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	Bitmap headImage;
	int ancho;
	int top;
	int alto;
	int alto0 = 20;
	Font ffecha;
	BitmapField Logo;
	Bitmap imgEnviar = Bitmap.getBitmapResource("enviar.png");
	Bitmap imgEnviar1 = Bitmap.getBitmapResource("enviar1.png");
	private int bottom;
	BitmapButtonField btnEnviar;
	BasicEditField txtCorreo;
	BasicEditField txtTelefono;
	Vector array = new Vector();
	public Correo()
	{	
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");	
		//getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		getMainManager().setBackground(BackgroundFactory.createLinearGradientBackground(0xe9c32e, 0xe9c32e,0xebd155,0xebd155));
		Bitmap bordes = Bitmap.getBitmapResource("rounded-border1.png");
		Bitmap logo = Bitmap.getBitmapResource("logo.png");
		 Logo = new BitmapField(logo);
		//Dialog.alert(tipoConexion);
		if (Display.getWidth() == 320)
		{
			imgEnviar 	= Bitmap.getBitmapResource("0enviar.png");
			imgEnviar1 	= Bitmap.getBitmapResource("0enviar1.png");
			letraLength = 18;
			headImage = Bitmap.getBitmapResource("titulo_320.png");
			ancho = 280;
			alto = 60;
			top = 5;
			ancho = 270;
		}
		if (Display.getWidth() == 360)
		{
			imgEnviar 	= Bitmap.getBitmapResource("1enviar.png");
			imgEnviar1 	= Bitmap.getBitmapResource("1enviar1.png");
			headImage = Bitmap.getBitmapResource("titulo_360.png");
			letraLength = 24;
			ancho = 280;
			top = 15;
			bottom = 10;
			alto = 105;
		}
		if (Display.getWidth() == 480)
		{
			imgEnviar 	= Bitmap.getBitmapResource("2enviar.png");
			imgEnviar1 	= Bitmap.getBitmapResource("2enviar1.png");
			headImage = Bitmap.getBitmapResource("titulo_480.png");
			letraLength = 24;
			ancho = 300;
			alto = 100;
			top =5;
			bottom = 5;
		}	
		if (Display.getWidth() == 640)
		{
			imgEnviar 	= Bitmap.getBitmapResource("3enviar.png");
			imgEnviar1 	= Bitmap.getBitmapResource("3enviar1.png");
			headImage = Bitmap.getBitmapResource("titulo_640.png");
			letraLength = 35;
			ancho = 510;
			alto = 105;
			alto0 = 30;
			top = 7;
			bottom = 7;
			
		}
		
			
		if(getTipo.equals("error")){
			Status.show("Necesita internet para enviar un correo",2000 );
			
		}
		try
		{
		 	FontFamily ffFont = FontFamily.forName("Arial");
		 	ffecha = ffFont.getFont(Font.BOLD, letraLength);
		}catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
				
				BitmapField bitmapImg1 = new BitmapField( headImage, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
				HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
				head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.GOLD, Color.GOLD,Color.GOLD,Color.GOLD));
				head.add(bitmapImg1);
				//head.setMargin(0, 0, -5, 0);
				setBanner(head);
				
				
				
				
		        RichTextField Titulo = new RichTextField("Enviar Correo", RichTextField.TEXT_ALIGN_HCENTER){
		            public void paint(Graphics g){      
		                g.setColor(Color.ORANGERED);
		                super.paint(g);
		            }};
		        Titulo.setMargin(top, 0, bottom, 0);   
		        Titulo.setFont(ffecha);
		        add(Titulo);
		        
		        RichTextField tituloTelefono = new RichTextField("Telefono: ", RichTextField.FIELD_LEFT){
		            public void paint(Graphics g){      
		                g.setColor(Color.ORANGERED);
		                super.paint(g);
		            }};
		            tituloTelefono.setMargin(top, 0, bottom, 15);
		        add(tituloTelefono);
		        
		       
		            VerticalFieldManager vfmTelefono = new VerticalFieldManager(VerticalFieldManager.FIELD_HCENTER) {
			            //define width
			            public int getPreferredWidth()
			            {
			                return ancho;
			            }
			            
			            //define height
			            public int getPreferredHeight()
			            {
			                return alto0;
			            }
			            
			            protected void sublayout( int maxWidth, int maxHeight )
			            {
			                super.sublayout(getPreferredWidth(), getPreferredHeight());
			                setExtent(getPreferredWidth(), getPreferredHeight());
			            }
			        };
			        vfmTelefono.setBorder(BorderFactory.createBitmapBorder(new XYEdges(12,12,12,12), bordes));
			        vfmTelefono.setMargin(top, 0, bottom, 0);
			        txtTelefono = new BasicEditField("", "503 ", 12,BasicEditField.FILTER_INTEGER | BasicEditField.NO_NEWLINE);
			        
			        vfmTelefono.add(txtTelefono);
			       	add(vfmTelefono);
			       	
			       	//-----------------------------------------------------------------------------------------//
			       
			       	
			       	RichTextField tituloCorreo = new RichTextField("Correo: ", RichTextField.FIELD_LEFT){
			            public void paint(Graphics g){      
			                g.setColor(Color.ORANGERED);
			                super.paint(g);
			            }};
			            tituloCorreo.setMargin(top, 0, bottom, 15);
			        add(tituloCorreo);
			        
			       
			            VerticalFieldManager vfmCorreo = new VerticalFieldManager(VerticalFieldManager.FIELD_HCENTER) {
				            //define width
				            public int getPreferredWidth()
				            {
				                return ancho;
				            }
				            
				            //define height
				            public int getPreferredHeight()
				            {
				                return alto;
				            }
				            
				            protected void sublayout( int maxWidth, int maxHeight )
				            {
				                super.sublayout(getPreferredWidth(), getPreferredHeight());
				                setExtent(getPreferredWidth(), getPreferredHeight());
				            }
				        };
				        vfmCorreo.setBorder(BorderFactory.createBitmapBorder(new XYEdges(12,12,12,12), bordes));
				        vfmCorreo.setMargin(top, 0, bottom, 0);
				        txtCorreo = new BasicEditField("", "", 100,BasicEditField.FILTER_DEFAULT);
				        
				        vfmCorreo.add(txtCorreo);
				       	add(vfmCorreo);
				       	
				       	btnEnviar = new BitmapButtonField(imgEnviar,imgEnviar1, Field.FIELD_HCENTER);
				       	btnEnviar.setChangeListener(this);
				       	btnEnviar.setMargin(top, 0, 10, 0);
			
			       	add(btnEnviar);
		}
	public void fieldChanged(Field field, int arg1) {
		 //TODO Auto-generated method stub
	if(btnEnviar == field){}
		if(!getTipo.equals("wifi")){
			Status.show("Necesita internet para enviar un correo",1000 );
		}else if(!getTipo.equals("BIBS")){		
			Status.show("Necesita internet para enviar un correo",1000 );
		}else{
			if(txtTelefono.getText().length() == 0){
				 Status.show("El campo Teléfono no puede ir vacío",2000);
			}else if(txtCorreo.getText().length() == 0){
				Status.show("El campo Correo no puede ir vacío",2000);
			}else{
				
				SoapObject rpc = new SoapObject(ServiceNamespace, methodName);
				rpc.addProperty("parametro", txtTelefono.getText() );
				rpc.addProperty("parametro1", txtCorreo.getText() );
				
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			 	envelope.bodyOut = rpc; 
		        envelope.dotNet = true;
		        envelope.encodingStyle = SoapSerializationEnvelope.ENC;
		        HttpTransport ht = new HttpTransport(ServiceUrl);
		        ht.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		        ht.debug = true;
		        
		        try {
					ht.call(ServiceUrl + "/" + methodName, envelope);   
					array = (Vector) envelope.getResponse();
					if(!(array.size()==0)){
						//Dialog.alert(array.toString());
						if(array.toString().equals("[mail{respuesta=1; }]")){
							Status.show("Mensaje enviado con exito.", 1000);
							txtTelefono.setText("");
							txtCorreo.setText("");
						}else{
							Status.show("Error al enviar el mensaje, intentelo mas tarde.", 1000);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (XmlPullParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	        openScreen(new Menu());
		return true;
	}
	
}
