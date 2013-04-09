package noticia;

import java.util.Vector;

import mypackage.Menu;
import net.rim.device.api.database.Cursor;
import net.rim.device.api.database.Database;
import net.rim.device.api.database.DatabaseFactory;
import net.rim.device.api.database.Row;
import net.rim.device.api.database.Statement;
import net.rim.device.api.io.URI;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.BorderFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransport;

import database.Config;
import estilos.BitmapButtonField;
import estilos.ListStyleButtonField;
import estilos.Metodos;

public class CopyOfNoticia extends Metodos implements FieldChangeListener
{
	String ServiceUrl = "http://i-moves.com/demos/promosApp/webServices/servicioWeb_promo.php"; 
	String ServiceNamespace = "http://i-moves.com/demos/promoApp/webServices";
	String methodName = "getNoticias";
	
	String idAfiliado = "1";
	int y,x,z;
	int ax,bx;
	
	String id;
	String precioOfer="";
	String precioReg="";
	String fecha="";
	String nombre;
	String descripcion;
	String imagen;
	Vector a = new Vector();
	Vector bb = new Vector();
	Vector lista = new Vector();
	Vector idVector = new Vector();
	Vector listaVector = new Vector();
	String res = "";
	Config path 	= new Config();
	Config statement= new Config();
	Bitmap bitmapImage; 
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
	public CopyOfNoticia(){
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");	
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		
		//Barra Titulo
		Bitmap titleImage = Bitmap.getBitmapResource("barra.png");	
		
		HorizontalFieldManager fmtitleBar 	= new HorizontalFieldManager(Field.USE_ALL_WIDTH);
		HorizontalFieldManager fmHome 		= new HorizontalFieldManager();
		fmHome.setMargin(0, 210, 0, 0);
		HorizontalFieldManager fmTitulo 	= new HorizontalFieldManager(Field.FIELD_VCENTER);
		fmTitulo.setMargin(0, 210, 0, 0);
		HorizontalFieldManager fmActualizar = new HorizontalFieldManager();
		
		btnHome = new BitmapButtonField(Bitmap.getBitmapResource("home1.png"), Bitmap.getBitmapResource("home1.png"));
		btnHome.setChangeListener(this);

		btnUpdate = new BitmapButtonField(Bitmap.getBitmapResource("sync.png"), Bitmap.getBitmapResource("sync.png"));
		btnUpdate.setChangeListener(this);
		
		LabelField Titulo = new LabelField("NOTICIAS");
		
		fmHome.add(btnHome);
		fmTitulo.add(Titulo);
		fmActualizar.add(btnUpdate);
		
		fmtitleBar.add(fmHome);
		fmtitleBar.add(fmTitulo);
		fmtitleBar.add(fmActualizar);
			
		fmtitleBar.setBackground(BackgroundFactory.createBitmapBackground(titleImage));
		setTitle(fmtitleBar);
		
	validarDatos();
}

public void validarDatos(){
	try{
		uri = URI.create(path.Path());
		
		Database sqliteDB = DatabaseFactory.open(uri);     	
		Dialog.alert("paso1");
		Statement se = sqliteDB.createStatement(statement.SelectNoticia()+idAfiliado+";");
		Dialog.alert("paso2");
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
			Dialog.alert("con inter");		
			descargarDatos();
		}else if(incremento >= 1){
			c.close();
			se.close();
			sqliteDB.close();	
			Dialog.alert("sin inter");
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
	            a = (Vector) envelope.getResponse();
	            if(!(a.size()==0)){
	            	//Dialog.alert("tamaño "+a.size());
	            	for(int i=0;i<a.size();i++){
	            		//Dialog.alert(a.toString());
	            		res = a.elementAt(i).toString();
	            		
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
    		Database sqliteDB2 = DatabaseFactory.open(uri);  
            Statement slc = sqliteDB2.createStatement(statement.SelectNoticia()+idAfiliado);
                slc.prepare();
                Cursor sc = slc.getCursor();               	
                Row rc;                
                int k=0; 
                Bitmap bordes = Bitmap.getBitmapResource("rounded-border1.png");
            while(sc.next()){

                rc = sc.getRow(); 
                idVector.addElement(rc.getString(0));
                vectorNombre.addElement(rc.getString(2));
                vectorDesc.addElement(rc.getString(3));
                vectorFecha.addElement(rc.getString(4));
                vectorImage.addElement(rc.getString(5));
                try{
					
					String tituloNoticia =rc.getString(2);
					subNoticia = tituloNoticia.substring(0, 20);
				} catch (Exception e){
				//	Dialog.alert("error al entrar a la base"+e.getMessage());
				}
				VerticalFieldManager allContent = new VerticalFieldManager(Field.FIELD_HCENTER);
				allContent.setBorder(BorderFactory.createBitmapBorder(new XYEdges(12,12,12,12), bordes));
				allContent.setMargin(10, 100, 10, 100);	
				
				VerticalFieldManager content = new VerticalFieldManager(Field.FIELD_HCENTER);
				bitmapImage = ImageUrl(rc.getString(5));
				Bitmap imagen1 = resizeBitmap(bitmapImage, 300, 200);
				lista.addElement(new BitmapButtonField(imagen1, imagen1));
				content.add(((Field) lista.elementAt(k)));
				
				VerticalFieldManager info = new VerticalFieldManager();
				info.setBorder(BorderFactory.createBitmapBorder(new XYEdges(12,12,12,12), bordes));
				
				listaVector.addElement( new ListStyleButtonField(null,subNoticia+"...",null,0));
				RichTextField Fecha = new RichTextField(rc.getString(4),RichTextField.FIELD_RIGHT);
				Fecha.setMargin(0,0,0,250);
				((Field) lista.elementAt(k)).setChangeListener(this);
				((Field) listaVector.elementAt(k)).setChangeListener(this);
				info.add(((Field)listaVector.elementAt(k)));
				info.add(Fecha);
				allContent.add(content);
				allContent.add(info);
				add(allContent);   
				k++;
            }	
            slc.close();
            sc.close();
            sqliteDB2.close(); 
        }catch (Exception e){
        //	Dialog.alert("error al entrar a la base1111"+e.getMessage());
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
				/*openScreen(new NoticiaDescripcion(
					idVector.elementAt(j).toString(),
					vectorNombre.elementAt(j).toString(),
					vectorDesc.elementAt(j).toString(),
					vectorFecha.elementAt(j).toString(),
					vectorImage.elementAt(j).toString()
					));		*/	
			}
		}
		if(btnHome== field){
			TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
	        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
	        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT);
	        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
	        UiEngineInstance engine = Ui.getUiEngineInstance();
	        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
			//openScreen(new Menu());
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
