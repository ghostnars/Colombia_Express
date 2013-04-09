package galeria;

import java.util.Vector;

import mypackage.Menu;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.TouchEvent;
import net.rim.device.api.ui.Touchscreen;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.extension.component.PictureScrollField;
import net.rim.device.api.ui.extension.component.PictureScrollField.HighlightStyle;
import net.rim.device.api.ui.extension.component.PictureScrollField.ScrollEntry;
import estilos.BitmapButtonField;
import estilos.Metodos;

public class CopyOfGaleria extends Metodos{

	private BitmapButtonField btnHome;
	PictureScrollField pictureScrollField;
	private BitmapButtonField btnUpdate;

    Vector vImg = new Vector();
    Vector vImages = new Vector();
    Vector vLabels = new Vector();
    Vector vTooltips = new Vector();
	//private Bitmap[] bitmaps;
	public CopyOfGaleria(){
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		 //setTitle("Picture Scroll Field Demo");
		
		
		//Barra Titulo
		Bitmap titleImage = Bitmap.getBitmapResource("fondoGris.png");	
		
		HorizontalFieldManager fmtitleBar 	= new HorizontalFieldManager();
		HorizontalFieldManager fmHome 		= new HorizontalFieldManager();
		fmHome.setMargin(0, 200, 0, 0);
		HorizontalFieldManager fmTitulo 	= new HorizontalFieldManager(Field.FIELD_VCENTER);
		fmTitulo.setMargin(0, 200, 0, 0);
		HorizontalFieldManager fmActualizar = new HorizontalFieldManager();
		
		btnHome = new BitmapButtonField(Bitmap.getBitmapResource("home1.png"), Bitmap.getBitmapResource("home1.png"));
		btnHome.setChangeListener(this);

		btnUpdate = new BitmapButtonField(Bitmap.getBitmapResource("sync.png"), Bitmap.getBitmapResource("sync.png"));
		btnUpdate.setChangeListener(this);
		
		LabelField Titulo = new LabelField("GALERIA");
		
		fmHome.add(btnHome);
		fmTitulo.add(Titulo);
		fmActualizar.add(btnUpdate);
		
		fmtitleBar.add(fmHome);
		fmtitleBar.add(fmTitulo);
		fmtitleBar.add(fmActualizar);
			
		fmtitleBar.setBackground(BackgroundFactory.createBitmapBackground(titleImage));
		setTitle(fmtitleBar);
		//Barra Titulo
		
	      for(int i=1; i<4;i++){
			vImg.addElement(Bitmap.getBitmapResource("img"+i+".jpg"));
	        vImages.addElement(resizeBitmap((Bitmap) vImg.elementAt(i), 150, 150));
	        vLabels.addElement("Descripcion de imgaen "+i);
	        vTooltips.addElement("Nombre de imgaen "+i);
	      }
	        
	        ScrollEntry[] entries = new ScrollEntry[4];

	        for (int i = 0; i < entries.length; i++) 
	        { 
	             entries[i] = new ScrollEntry((Bitmap) vImages.elementAt(i),(String) vLabels.elementAt(i),(String)vTooltips.elementAt(i));
	        }
	        
	        pictureScrollField = new PictureScrollField(200, 200);
	        pictureScrollField.setPadding(46,0,46,0);
	        pictureScrollField.setData(entries, 0);
	        pictureScrollField.setHighlightStyle(HighlightStyle.MAGNIFY_LENS);
	        pictureScrollField.setHighlightBorderColor(Color.BLUE);
	        //pictureScrollField.setLensShrink(0.4f);
	        pictureScrollField.setCenteredLens(!pictureScrollField.hasCenteredLens());
	        pictureScrollField.setBackground(BackgroundFactory.createSolidTransparentBackground(Color.GRAY, 60));
	        pictureScrollField.setChangeListener(this);
	        pictureScrollField.setLabelsVisible(true); 

	        add(pictureScrollField);
		
	}
    protected boolean navigationClick(int status, int time)
    {
        if(pictureScrollField.isFocus())
        {            
            if(Touchscreen.isSupported())
            {
                //openScreen(new PinchScreen(bitmaps[pictureScrollField.getCurrentImageIndex()]));
            	//Dialog.alert("imagen presionada "+ pictureScrollField.getCurrentImageIndex());
            	//openScreen(new PinchScreen(images[pictureScrollField.getCurrentImageIndex()].toString()));
            	for(int j=0;j<=vImages.size()-1;j++){
        			Dialog.alert("imagen presionada " + j +" / "+vImages.elementAt(j).toString());        								
        			}
        		
            }
            else
            {
                Dialog.inform("You selected item " + pictureScrollField.getCurrentImageIndex());
            }
            
            return true;
        }
        
        return super.navigationClick(status, time);
    }
    
    protected boolean touchEvent(TouchEvent message)
    {
        if(message.getEvent() == TouchEvent.CLICK)
        {
            if(pictureScrollField.isFocus())
            {                
                if(Touchscreen.isSupported())
                {
                    //UiApplication.getUiApplication().pushScreen(new PinchScreen(_bitmaps[_pictureScrollField.getCurrentImageIndex()]));
                	Dialog.alert("imagen presionada touch "+ pictureScrollField.getCurrentImageIndex());
                }
                else
                {
                    Dialog.inform("You selected item " + pictureScrollField.getCurrentImageIndex());
                }
                
                return true;            
            }                  
        }
        return super.touchEvent(message);         
    }

	public void fieldChanged(Field field, int arg1) {
		// TODO Auto-generated method stub
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
