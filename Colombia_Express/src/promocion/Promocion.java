package Promocion;

import java.util.Vector;

import javax.microedition.io.file.FileConnection;

import mypackage.ErrorPage;
import mypackage.Menu;
import net.rim.device.api.database.Cursor;
import net.rim.device.api.database.Database;
import net.rim.device.api.database.DatabaseFactory;
import net.rim.device.api.database.Row;
import net.rim.device.api.database.Statement;
import net.rim.device.api.io.URI;
import net.rim.device.api.system.Application;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.image.Image;
import net.rim.device.api.ui.image.ImageFactory;

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
	String ServiceUrl = "http://www.buzzcoapp.com/admin2/webServices/servicioWeb_ColombiaExpress.php"+tipoConexion; 
	String ServiceNamespace = "http://www.buzzcoApp.com/admin2/webServices";
	String methodName = "getPromociones";
	String idAfiliado = "33";
	int y,x,z;
	int ax,bx;
	
	String id;
	String precio="";
	String precioReg="";
	String fecha="";
	String nombre;
	String descripcion;
	String imagen;
	String inicio;
	String fin;
	String estado;
	
	
	
	
	
	
	
	Vector array 			= new Vector();
	Vector bb 				= new Vector();
	Vector lista 			= new Vector();
	Vector vectorId 		= new Vector();
	Vector vectorTitulo 	= new Vector();
	Vector vectorImagen 	= new Vector();
	Vector vectorDesc 		= new Vector();

	Vector vectorPrecio 	= new Vector();
	
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
	int flag = 0;
	Bitmap headImage;
	 private int tLista =60;
	Bitmap flecha = Bitmap.getBitmapResource("flecha.png");
	public Promocion(int bandera)
	{	
		flag = bandera;
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");	
		//getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		getMainManager().setBackground(BackgroundFactory.createLinearGradientBackground(Color.ORANGE, 0xebd359,0xebd359,Color.ORANGE));
		
		//Dialog.alert(tipoConexion);
		if (Display.getWidth() == 320)
		{
			headImage = Bitmap.getBitmapResource("titulo_320.png");
			AnchoImagen = 320;	
			AltoImagen = 39;
			tLista = 50;
		}
		if (Display.getWidth() == 360)
		{
			headImage = Bitmap.getBitmapResource("titulo_360.png");
			iconoWeb = 60;
			letraLength = 29;
			AnchoImagen = 360;	
			AltoImagen = 44;
			tLista = 70;
		}
		if (Display.getWidth() == 480)
		{
			headImage = Bitmap.getBitmapResource("titulo_480.png");
			letraLength = 35;
			AnchoImagen = 360;	
			AltoImagen = 44;
			tLista = 70;
		}	
		if (Display.getWidth() == 640)
		{
			headImage = Bitmap.getBitmapResource("titulo_640.png");
			tLista = 110;
			AnchoImagen = 480;	
			AltoImagen = 58;
		}
		 
				
				
				BitmapField bitmapImg1 = new BitmapField( headImage, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
				HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
				head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.GOLD, Color.GOLD,Color.GOLD,Color.GOLD));
				head.add(bitmapImg1);
				//head.setMargin(0, 0, -5, 0);
				setBanner(head);
				
			//Status.show(getTipo, 2000);
				//validarDatos();
				descargarDatos();
				MenuItem _viewItem1 = new MenuItem(" Actualizar lista", 110, 1){
					
					public void run(){
						
						UiApplication.getUiApplication().invokeLater(new Runnable(){
							
				    		public void run(){
								Object[] choices = new Object[] {"Aceptar", "Cancelar"};
								int result = Dialog.ask("Seguro que desea Actualizar", choices, 1);
								switch (result) {
								case 0:
									try {
										actualizarDatos();
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
										cambiar();
									}
									
									break;
								case 1:
									
									break;
								}				
							}
				    	});    	 
						   	 
						
			        }
			  	};

			Image menuIcon = ImageFactory.createImage(Bitmap.getBitmapResource("sync.png"));
		    _viewItem1.setIcon(menuIcon);
		    addMenuItem(_viewItem1);
	
    }
	public void cambiar(){
		//openScreen(new ErrorPage());
	
	Application.getApplication().invokeLater(
		    new Runnable() {
		        public void run() {
		            Ui.getUiEngine().pushScreen(new ErrorPage());
		        }
		    }
		);
	}
	public void eliminarDatos(){
		try
		{
			URI uri1 = URI.create(path.Path());
			Database sqliteDB1 = DatabaseFactory.open(uri1); 
			Statement in = sqliteDB1.createStatement("DELETE FROM OFERTA");
			in.prepare();
			in.execute();
			in.close();
			sqliteDB1.close();		
			
		}
		catch(Exception e)
		{	
			Dialog.alert(e.toString()+"error en el insert");
		}
	};
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
				
				
				if(getTipo.equals("wifi")){
					Status.show("Descargando Datos...");
					descargarDatos();
					
				}else if(getTipo.equals("BIBS")){
					
					Status.show("Parece que está en una conexion lenta, puede tardar un momento");
					descargarDatos();
					
				}else if(getTipo.equals("error")){
					//Status.show("Error de red",1000 );
					cambiar();
				}
				
			}else if(incremento >= 1){
				c.close();
				se.close();
				sqliteDB.close();	
				//Dialog.alert("sin inter");
				//Status.show("Cargando Datos...",1000 );
				if(flag == 0){
					
					if(getTipo.equals("wifi")){
						Status.show("Descargando Datos...");
						eliminarDatos();
						descargarDatos();
					}else if(getTipo.equals("BIBS")){
						//Status.show("Parece que esta en una conexion lenta");
						//eliminarDatos();
						//Status.show("Cargando Datos...",1000 );
						cargarDatos();
					}else if(getTipo.equals("error")){
						//Status.show("Cargando Datos sin conexión...",1000 );
						cargarDatos();
					}else{
						cargarDatos();
					}
				}else if(flag == 1 ){
					cargarDatos();
				}
			}else{
				c.close();
				se.close();
				sqliteDB.close();
				//Dialog.alert("problemas");
				cambiar();
			}
			
		}catch(Exception e){
			//add(new LabelField("error al validar si hay datos: "+e.getMessage()));
			validarDatos();
		
		}
	}
	
	 public void descargarDatos(){
		try{
			
			
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
	        	//Dialog.alert("tamaño "+array.size());
	        	for(int i=0;i<array.size();i++){
	        		
	        		res = array.elementAt(i).toString();
	        		Dialog.alert(" "+res);
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					id=res.substring(y+1, z);
					
					//Dialog.alert(" "+id);
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					nombre=res.substring(y+1, z);
					//Dialog.alert(" "+nombre);
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					descripcion=res.substring(y+1, z);
					//Dialog.alert(" "+descripcion);
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					imagen=res.substring(y+1, z);
					//Dialog.alert(" "+imagen);
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					inicio=res.substring(y+1, z);
					Dialog.alert(" "+inicio);
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					fin=res.substring(y+1, z);
					Dialog.alert(" "+fin);
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					precio=res.substring(y+1, z);
					Dialog.alert(" "+precio);
					res=res.substring(z+1, res.length());
					y=res.indexOf("=");z=0;z=res.indexOf(";");
					estado=res.substring(y+1, z);
					Dialog.alert(" "+estado);
					res=res.substring(z+1, res.length());
					
					/*try
					{
						URI uri3 = URI.create(path.Path());
						//Dialog.alert(""+path.Path());
						Database sqliteDB3 = DatabaseFactory.open(uri3); 
						//Dialog.alert(statement.InsertOferta()+"'"+idAfiliado+"','"+id+"','"+nombre+"','"+descripcion+"','"+imagen+"','"+precio+"');");
						Statement in = sqliteDB3.createStatement(statement.InsertOferta()+"'"+idAfiliado+"','"+id+"','"+nombre+"','"+descripcion+"','"+imagen+"','"+precio+"');");
						in.prepare();
						in.execute();
						in.close();
						sqliteDB3.close();		
						
					}
					catch(Exception e)
					{	
					Dialog.alert(e.getMessage()+"error en el insert");
						cambiar();
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
			cambiar();
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
                vectorId.addElement(rc.getString(1));
                vectorTitulo.addElement(rc.getString(2));
                vectorDesc.addElement(rc.getString(3));
				vectorImagen.addElement(rc.getString(4));
				vectorPrecio.addElement(rc.getString(5));
				
				
				
				VerticalFieldManager vfmLabel = new VerticalFieldManager(Field.FIELD_VCENTER);
				vfmLabel.setBackground(BackgroundFactory.createLinearGradientBackground(0xebcc41, 0xebcc41,0xebd359,0xebd359));
				vfmLabel.setMargin(3, 3, 3, 3);
				
				

		        
		    		   lista.addElement(new ListStyleButtonField( null, vectorTitulo.elementAt(j).toString(), flecha, DrawStyle.ELLIPSIS){
		    	           
							//define width
		    	            public int getPreferredWidth()
		    	            {
		    	                return Display.getWidth();
		    	            }
		    	            //define height
		    	            public int getPreferredHeight()
		    	            {
		    	                return tLista;
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
        	//Dialog.alert("error al entrar a la base "+e.getMessage());
        	cambiar();
        }
    	
	}
	public void actualizarDatos(){
		String getTipo1 = Autenticacion.getConnectionString()[1];
		
		if(getTipo1.equals("wifi")){
			//Status.show("Actualizando Datos..." );
			eliminarDatos();
			openScreen(new Promocion(0));
			
		}else if(getTipo1.equals("BIBS")){
			//Status.show("Parece que está en una conexion lenta, puede tardar un momento");
			eliminarDatos();
			openScreen(new Promocion(0));
			
		}/*else if(getTipo1.equals("error")){
			//Status.show("Error de red",1000 );
			cambiar();
		}*/
		else{
			//Status.show("Error de red",1000 );
			cambiar();
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

				openScreen(new DetallePromocion(
						vectorTitulo.elementAt(j).toString(),
						vectorDesc.elementAt(j).toString(),					
						vectorImagen.elementAt(j).toString(),
						vectorPrecio.elementAt(j).toString()
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
