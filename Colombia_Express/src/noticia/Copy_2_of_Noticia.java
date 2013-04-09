package noticia;

import java.util.Vector;

import mypackage.Menu;
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
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;
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

public class Copy_2_of_Noticia extends Metodos implements FieldChangeListener
{
	String tipoConexion = tipoConexion = Autenticacion.getConnectionString();
	String ServiceUrl = "http://www.buzzcoapp.com/admin2/webServices/servicioWeb_promo.php"+tipoConexion; 
	String ServiceNamespace = "http://i-moves.com/demos/promosApp/webServices";
	String methodName = "getNoticias";
	
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
	String Title;
	Vector array = new Vector();
	Vector bb = new Vector();
	Vector lista = new Vector();
	Vector idVector = new Vector();
	Vector listaVector = new Vector();
	String res = "";
	Config path 	= new Config();
	Config statement= new Config();
	Bitmap bitmapImage;
	//Bitmap bitmapImage; 
	URI uri;
	int incremento = 0;
	private BitmapButtonField boton,boton1,boton2,boton3,boton4;
	private BitmapButtonField btnHome;
	private String subNoticia;
	private Statement in;
	private Database sqliteDB1;
	private BitmapButtonField btnUpdate;
	private Vector vectorNombre = new Vector();
	private Vector vectorDesc = new Vector();
	private Vector vectorFecha = new Vector();
	private Vector vectorImage = new Vector();
	private int letraLength =25;
	private Font fBold;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	int altoWeb = 92;
	int anchoWeb = 92;
	BrowserFieldConfig myBrowserFieldConfig;
	private int imgAncho=280;
	private int imgAlto=130;
	private int imgLateral = 40;
	public Copy_2_of_Noticia(){
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");	
		getMainManager().setBackground(BackgroundFactory.createSolidBackground(0xcecdcd));
		if (Display.getWidth() == 320)
		{
			AnchoImagen = 320;	
			AltoImagen = 39;
			imgAncho = 250;
			imgAlto = 120;
			imgLateral = 30;
			Dialog.alert("paso1");
		}
		if (Display.getWidth() == 360)
		{
			
			AnchoImagen = 360;	
			AltoImagen = 44;
			anchoWeb = 225;
			altoWeb = 130;
			letraLength =20;
			imgAncho = 250;
			imgAlto = 120;
		}
		if (Display.getWidth() == 480)
		{
			
	
		}	
		if (Display.getWidth() == 640)
		{
			//AnchoImagen = 320;	
			//AltoImagen = 130;
			anchoWeb = 450;
			altoWeb = 200;
			letraLength = 40;
			imgAncho = 250;
			imgAlto = 120;
		}
		try
		 {
		 	FontFamily ffFont = FontFamily.forName("Arial");
		 	fBold = ffFont.getFont(Font.BOLD, 18);
		 	
		 }catch (ClassNotFoundException e){
		 	   System.out.println(e.getMessage());
		 }
		//Barra Titulo
		Bitmap imagenT 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( imagenT, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		//head.setMargin(0, 0, -5, 0);
		setBanner(head);
		
		validarDatos();
		//descargarDatos();
}

public void validarDatos(){
	try{
		uri = URI.create(path.Path());
		
		Database sqliteDB = DatabaseFactory.open(uri);     	
		//Dialog.alert("paso1");
		Statement se = sqliteDB.createStatement(statement.SelectNoticia()+idAfiliado+";");
		//Dialog.alert("paso2");
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
			//Status.show("con inter", 1000);
			descargarDatos();
		}else if(incremento >= 1){
			c.close();
			se.close();
			sqliteDB.close();	
			//Dialog.alert("sin inter");
			Status.show("sin inter", 1000);
			cargarDatos();
		}else{
			c.close();
			se.close();
			sqliteDB.close();
			Dialog.alert("problemas");
			descargarDatos();
		}
		
	}catch(Exception e){
		add(new LabelField("error al validar si hay datos: "+e.getMessage()));
		validarDatos();
	
	}
}
	public void descargarDatos(){
		 try{
			 SoapObject rpc = new SoapObject(ServiceNamespace, methodName);
				rpc.addProperty("idAfiliado", idAfiliado);
			
			 	SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			 	envelope.bodyOut = rpc; 
		        envelope.dotNet = true;
		        envelope.encodingStyle = SoapSerializationEnvelope.ENC;
		        HttpTransport ht = new HttpTransport(ServiceUrl);
		        ht.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		        ht.debug = true;
		        String res = "";
		        
	            ht.call(ServiceUrl + "/" + methodName, envelope);         
	            array = (Vector) envelope.getResponse();
	            if(!(array.size()==0)){
	            	//Dialog.alert("tamaño "+a.size());
	            	for(int i=0;i<array.size();i++){
	            		//Dialog.alert(a.toString());
	            		res = array.elementAt(i).toString();
	            		
	    				y=res.indexOf("=");z=0;z=res.indexOf(";");
	    				id=res.substring(y+1, z);  				
	    				res=res.substring(z+1, res.length());
	    				
	    				y=res.indexOf("=");z=0;z=res.indexOf(";");
	    				nombre=res.substring(y+1, z);
	    				res=res.substring(z+1, res.length());

	    				y=res.indexOf("=");z=0;z=res.indexOf(";");
	    				descripcion=res.substring(y+1, z);
	    				res=res.substring(z+1, res.length());
	    				
	    				y=res.indexOf("=");z=0;z=res.indexOf(";");
	    				fecha=res.substring(y+1, z);
	    				res=res.substring(z+1, res.length());
	    				
	    				y=res.indexOf("=");z=0;z=res.indexOf(";");
	    				imagen = res.substring(y+1, z);
	    				res=res.substring(z+1, res.length());
	    				//Dialog.alert(array.toString());
						try{
						sqliteDB1 = DatabaseFactory.open(uri);
						in = sqliteDB1.createStatement(statement.InsertNoticia()+"'"+id+"','"+idAfiliado+"','"+nombre+"','"+descripcion+"','"+fecha+"','"+imagen+"');");

						in.prepare();
						in.execute();
						in.close();
						sqliteDB1.close();
						}catch(Exception e){	
							Dialog.alert(e.toString()+"error en el insert");    
						}
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
    		Database sqliteDB2 = DatabaseFactory.open(uri);  
            Statement slc = sqliteDB2.createStatement(statement.SelectNoticia()+idAfiliado);
                slc.prepare();
                Cursor sc = slc.getCursor();               	
                Row rc;                
                int k=0; 
                
              
            while(sc.next()){
            	
                rc = sc.getRow(); 
                idVector.addElement(rc.getString(0));
                vectorNombre.addElement(rc.getString(2));
                vectorDesc.addElement(rc.getString(3));
                vectorFecha.addElement(rc.getString(4));
                vectorImage.addElement(rc.getString(5));
                
               
				Bitmap bordes = Bitmap.getBitmapResource("rounded-border1.png");	
				VerticalFieldManager allContent = new VerticalFieldManager(Field.FIELD_HCENTER);
				allContent.setBorder(BorderFactory.createBitmapBorder(new XYEdges(12,12,12,12), bordes));
				allContent.setMargin(0, imgLateral, 0, 40);	
				
				
				VerticalFieldManager content = new VerticalFieldManager(Field.FIELD_HCENTER | Field.NON_FOCUSABLE);
				
				
				String imgCut =  rc.getString(5);
				int cut = rc.getString(5).length();
				String limite = imgCut.substring(cut-3, cut);
				//Status.show(imgCut.substring(cut-3, cut) + " ",1000);
				
				if(!limite.equals("png")){
					//Status.show( " ENTRO  ",1000);
					//bitmapImage = ImageUrl("http://www.i-moves.com/demos/promosApp/IMG/1363297105_Noticia.gif");	
					bitmapImage = Bitmap.getBitmapResource("rounded-border1.png");
				}else{
					bitmapImage = Bitmap.getBitmapResource("rounded-border1.png");
					
				}
			
				Bitmap imagen1 = resizeBitmap(bitmapImage, imgAncho, imgAlto );				
				lista.addElement(new BitmapButtonField(imagen1, imagen1));				
				
				VerticalFieldManager info = new VerticalFieldManager();
				info.setBorder(BorderFactory.createBitmapBorder(new XYEdges(12,12,12,12), bordes));
				
				if(vectorNombre.elementAt(k).toString().length() > letraLength){
	    		 	String Title1 = vectorNombre.elementAt(k).toString().substring(0, letraLength)+"..";
	    		 	listaVector.addElement( new ListStyleButtonField(null,Title1,null,0));
	    	   }else{
	    		   String Title1 = vectorNombre.elementAt(k).toString();
	    		   listaVector.addElement( new ListStyleButtonField(null,Title1,null,0));
	    	   }
				
				
				RichTextField Fecha = new RichTextField(vectorFecha.elementAt(k).toString(),RichTextField.FIELD_RIGHT);
				Fecha.setMargin(0,0,5,430);
				Fecha.setFont(fBold);
				((Field) lista.elementAt(k)).setChangeListener(this);
				((Field) listaVector.elementAt(k)).setChangeListener(this);
				content.add(((Field) lista.elementAt(k)));
				info.add(((Field) listaVector.elementAt(k)));
				info.add(Fecha);
				allContent.add(content);
				allContent.add(new SeparatorField());
				allContent.add(info);
				add(allContent);  
				k++;
				
            }	
            slc.close();
            sc.close();
            sqliteDB2.close(); 
        }catch (Exception e){
        	Dialog.alert("error al entrar a la base1111"+e.getMessage());
        }
	}
	
	public void fieldChanged(Field field, int arg1) {
		// TODO Auto-generated method stub
		for(int j=0;j<=lista.size()-1;j++){
			if( lista.elementAt(j)== field ||  listaVector.elementAt(j)== field ){
				TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
				transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
				transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_LEFT);
				transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
				UiEngineInstance engine = Ui.getUiEngineInstance();
				engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
				openScreen(new DetalleNoticia(
						idVector.elementAt(j).toString(),
						vectorNombre.elementAt(j).toString(),
						vectorDesc.elementAt(j).toString(),
						vectorFecha.elementAt(j).toString(),
						vectorImage.elementAt(j).toString()
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
