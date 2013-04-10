package promocion;

import java.util.Vector;

import javax.microedition.io.file.FileConnection;

import mypackage.Menu;
import net.rim.device.api.browser.field2.BrowserField;
import net.rim.device.api.browser.field2.BrowserFieldConfig;
import net.rim.device.api.database.Cursor;
import net.rim.device.api.database.Database;
import net.rim.device.api.database.DatabaseFactory;
import net.rim.device.api.database.Row;
import net.rim.device.api.database.Statement;
import net.rim.device.api.io.URI;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
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

import database.Autenticacion;

import database.Config;
import estilos.BitmapButtonField;
import estilos.ListStyleButtonField;
import estilos.Metodos;

public class Promocion extends Metodos implements FieldChangeListener
{
	
	String tipoConexion = Autenticacion.getConnectionString()[0];
	String getTipo = Autenticacion.getConnectionString()[1];	
	String ServiceUrl = "http://www.buzzcoapp.com/admin2/webServices/servicioWeb_promo.php"+tipoConexion; 
	String ServiceNamespace = "http://i-moves.com/demos/promosApp/webServices";
	String methodName = "getOfertas";
	String idAfiliado = "31";
	int y,x,z;
	int ax,bx;
	
	String id;
	String precioOfer="";
	String precioReg="";
	String fecha="";
	String nombre;
	String descripcion;
	String imagen;
	
	Vector array 			= new Vector();
	Vector bb 				= new Vector();
	Vector lista 			= new Vector();
	Vector vectorId 		= new Vector();
	Vector vectorTitulo 	= new Vector();
	Vector vectorImagen 	= new Vector();
	Vector vectorDesc 		= new Vector();
	Vector vectorPoferta	= new Vector();
	Vector vectorPregular 	= new Vector();
	
	String res = "";
	Config path 		= new Config();
	Config statement	= new Config();
	Bitmap bitmapImage; 
	URI uri;
	int incremento = 0;
	int letraLength = 50;
	private BitmapButtonField btnHome;
	int iconoWeb = 92;
	FileConnection fc;
	FileConnection fconn;
	FileConnection fconn1;
	private BitmapButtonField btnUpdate;
	Font fTitulo, fDetalle;
	private String Desc;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	Bitmap flecha = Bitmap.getBitmapResource("flecha.png");
	public Promocion()
	{	
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");	
		//getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		getMainManager().setBackground(BackgroundFactory.createSolidBackground(0xcecdcd));
		
		//Dialog.alert(tipoConexion);
		if (Display.getWidth() == 320)
		{
			AnchoImagen = 320;	
			AltoImagen = 39;
		}
		if (Display.getWidth() == 360)
		{
			iconoWeb = 60;
			letraLength = 29;
			AnchoImagen = 360;	
			AltoImagen = 44;
		}
		if (Display.getWidth() == 480)
		{
			letraLength = 35;
			AnchoImagen = 480;	
			AltoImagen = 59;
		}	
		if (Display.getWidth() == 640)
		{
	
		}
		 

		//Barra Titulo
				Bitmap titleImage = Bitmap.getBitmapResource("barra.png");	
				
				/*HorizontalFieldManager fmtitleBar 	= new HorizontalFieldManager(Field.USE_ALL_WIDTH);
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
				setTitle(fmtitleBar);*/
				
				
				Bitmap imagen 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
				BitmapField bitmapImg1 = new BitmapField( imagen, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
				HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
				head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
				head.add(bitmapImg1);
				//head.setMargin(0, 0, -5, 0);
				setBanner(head);
				
				Dialog.alert(getTipo);
				validarDatos();
				
				
    }
	
	public void validarDatos(){
		try{
			uri = URI.create(path.Path());
			Database sqliteDB = DatabaseFactory.open(uri);     	
			Statement se = sqliteDB.createStatement(statement.SelectOferta()+idAfiliado+";");
			se.prepare();
			Cursor c = se.getCursor();               	
			Row r;                	
			while(c.next()){
				r = c.getRow(); 
				incremento ++;
			}
			
			if(incremento == 0){
				c.close();
				se.close();
				sqliteDB.close();	
				//Dialog.alert("con inter");
				Status.show("Descargando Datos...",1000 );
				descargarDatos();
			}else if(incremento >= 1){
				c.close();
				se.close();
				sqliteDB.close();	
				//Dialog.alert("sin inter");
				Status.show("Cargando Datos...",1000 );
				cargarDatos();
			}else{
				c.close();
				se.close();
				sqliteDB.close();
				//Dialog.alert("problemas");
				descargarDatos();
			}
			
		}catch(Exception e){
			add(new LabelField("error al validar si hay datos: "+e.getMessage()));
			validarDatos();
		
		}
	}
	
	 public void descargarDatos(){
		try{
			Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");
			getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
			
			SoapObject rpc = new SoapObject(ServiceNamespace, methodName);
			rpc.addProperty("idAfiliado", idAfiliado );
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		 	envelope.bodyOut = rpc; 
	        envelope.dotNet = true;
	        envelope.encodingStyle = SoapSerializationEnvelope.ENC;
	        HttpTransport ht = new HttpTransport(ServiceUrl);
	        ht.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
	        ht.debug = true;
	        
	        ht.call(ServiceUrl + "/" + methodName, envelope);         
	        array = (Vector) envelope.getResponse();
	       if(!(array.size()==0)){
	        	//Dialog.alert("tamaño "+a.size());
	        	for(int i=0;i<array.size();i++){
	        		
	        		res = array.elementAt(i).toString();
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					id=res.substring(y+1, z);
					
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					nombre=res.substring(y+1, z);
					
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					imagen=res.substring(y+1, z);
					
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					descripcion=res.substring(y+1, z);
					
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					fecha=res.substring(y+1, z);
					
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					precioOfer=res.substring(y+1, z);
					
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					precioReg=res.substring(y+1, z);
					
					res=res.substring(z+1, res.length());
					
					try
					{
						URI uri1 = URI.create(path.Path());
						//Dialog.alert(""+path.Path());
						Database sqliteDB1 = DatabaseFactory.open(uri1); 
						//Dialog.alert(statement.InsertOferta()+"'"+id+"','"+idAfiliado+"','"+nombre+"','"+imagen+"','"+descripcion+"','"+fecha+"','"+precioOfer+"','"+precioReg+"');");
						Statement in = sqliteDB1.createStatement(statement.InsertOferta()+"'"+id+"','"+idAfiliado+"','"+nombre+"','"+imagen+"','"+descripcion+"','"+fecha+"','"+precioOfer+"','"+precioReg+"');");
						in.prepare();
						in.execute();
						in.close();
						sqliteDB1.close();		
						
					}
					catch(Exception e)
					{	
						Dialog.alert(e.toString()+"error en el insert");
					}
					/*id = "";
					nombre ="";
					imagen ="";
					descripcion="";
					fecha ="";
					precioOfer = "";
					precioReg ="";*/
					
	        	}
            }
	        
	        cargarDatos();
		}catch(Exception e){
			Dialog.alert(e.getMessage() + "error en ofertas");
		} 
		
	}
	
	public void cargarDatos(){
		//Dialog.alert("Entro a la base");
		
    	try{  
			uri = URI.create(path.Path());
			Database sqliteDB = DatabaseFactory.open(uri);  
			Statement slc = sqliteDB.createStatement(statement.SelectOferta()+idAfiliado);
            slc.prepare();
            Cursor sc = slc.getCursor();               	
            Row rc;                
            int j = 0;
            while(sc.next()){
                rc = sc.getRow(); 
                vectorId.addElement(rc.getString(0));
                vectorTitulo.addElement(rc.getString(2));
				vectorImagen.addElement(rc.getString(3));
                vectorDesc.addElement(rc.getString(4));
                vectorPoferta.addElement(rc.getString(6));
				vectorPregular.addElement(rc.getString(7));
				
				
				
				VerticalFieldManager vfmLabel = new VerticalFieldManager(Field.FIELD_VCENTER);
				vfmLabel.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.WHITE,Color.WHITE));
				vfmLabel.setMargin(2, 2, 2, 2);
				/*HorizontalFieldManager campo = new HorizontalFieldManager(Field.NON_FOCUSABLE) {
		            protected void sublayout(int width, int height) {
	                    super.sublayout(iconoWeb, iconoWeb);
	                }
	            };
				
				String url = "<img width='"+iconoWeb+"' height='"+iconoWeb+"' style='background:transparent;border:none;margin-left: -8px;margin-top: -8px;padding-right: -118px;margin-bottom: -8px;' src='"+vectorImagen.elementAt(j).toString()+"'/>";
				
				BrowserFieldConfig myBrowserFieldConfig = new BrowserFieldConfig();
		        myBrowserFieldConfig.setProperty(BrowserFieldConfig.NAVIGATION_MODE,BrowserFieldConfig.NAVIGATION_MODE_NONE );
		        myBrowserFieldConfig.setProperty(BrowserFieldConfig.ENABLE_COOKIES,Boolean.TRUE);
		        myBrowserFieldConfig.setProperty(BrowserFieldConfig.ENABLE_GEARS,Boolean.TRUE);
		        
		        BrowserField browserField = new BrowserField(myBrowserFieldConfig);
		        campo.add(browserField);
		        browserField.displayContent(url, "http://localhost");*/
				

		        
		    		   lista.addElement(new ListStyleButtonField( null, vectorTitulo.elementAt(j).toString(), flecha, DrawStyle.ELLIPSIS){
		    	            //define width
		    	            public int getPreferredWidth()
		    	            {
		    	                return Display.getWidth();
		    	            }
		    	            //define height
		    	            public int getPreferredHeight()
		    	            {
		    	                return 70;
		    	            }
		    	            public void layout( int maxWidth, int maxHeight )
		    	            {
		    	                super.layout(getPreferredWidth(), 
		    	                                getPreferredHeight());
		    	                setExtent(getPreferredWidth(), getPreferredHeight());
		    	            }
		    	        });
		 
		    	((Field) lista.elementAt(j)).setChangeListener(this);  	
				vfmLabel.add((Field) lista.elementAt(j));
				//hfmContent.add(campo);
				add(vfmLabel);
	
				
				j++;
            }
            slc.close(); 
            sc.close();
			sqliteDB.close(); 
        }catch (Exception e){
        	Dialog.alert("error al entrar a la base "+e.getMessage());
        }
    	
	}
	public void fieldChanged(Field field, int arg1) {
		 //TODO Auto-generated method stub
		for(int j=0;j<=lista.size()-1;j++){
			if( lista.elementAt(j)== field ){
				TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
				transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
				transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
				transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
				UiEngineInstance engine = Ui.getUiEngineInstance();
				engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
				
				//Dialog.alert(vectorId.elementAt(j).toString());

				openScreen(new DetallePromocion(
						vectorTitulo.elementAt(j).toString(),
						vectorId.elementAt(j).toString(),
						vectorImagen.elementAt(j).toString(),
						vectorDesc.elementAt(j).toString(),
						vectorPoferta.elementAt(j).toString(),
						vectorPregular.elementAt(j).toString()
						));
			}
			
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
		/*if(boton2== field){
			TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
			transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
			transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
			transition.setIntAttribute(TransitionCont-Instance();
			engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
			openScreen(new Noticia());
		}*/	
		
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
