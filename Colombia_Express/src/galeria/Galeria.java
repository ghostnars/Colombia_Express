package galeria;

import mypackage.Menu;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.TouchEvent;
import net.rim.device.api.ui.Touchscreen;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.extension.component.PictureScrollField;
import net.rim.device.api.ui.extension.component.PictureScrollField.HighlightStyle;
import net.rim.device.api.ui.extension.component.PictureScrollField.ScrollEntry;
import estilos.BitmapButtonField;
import estilos.Metodos;

public class Galeria extends Metodos{
	  Bitmap[] images = new Bitmap[4];
      String[] labels = new String[4];
      String[] tooltips = new String[4];
	private BitmapButtonField boton2;
	private BitmapButtonField btnHome;
	PictureScrollField pictureScrollField;
	private Bitmap[] bImg = new Bitmap[4];
	private BitmapButtonField btnUpdate;
	int AnchoImagen = 640;
	int AltoImagen = 79;
	//private Bitmap[] bitmaps;
	public Galeria(){
		Bitmap bitmapImg = Bitmap.getBitmapResource("gray.jpg");
		getMainManager().setBackground(BackgroundFactory.createBitmapBackground(bitmapImg));
		 //setTitle("Picture Scroll Field Demo");
		if (Display.getWidth() == 320)
		{
			AnchoImagen = 320;	
			AltoImagen = 39;

		}
		if (Display.getWidth() == 360)
		{
			
			AnchoImagen = 360;	
			AltoImagen = 44;

		}
		if (Display.getWidth() == 480)
		{
			
	
		}	
		if (Display.getWidth() == 640)
		{
			

		}
		
		
		//Barra Titulo
		Bitmap imagenT 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( imagenT, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		setBanner(head);
		//Barra Titulo

	        bImg[0] = Bitmap.getBitmapResource("img1.jpg");
	        images[0] = resizeBitmap(bImg[0], 150, 150);
	        labels[0] = "Descripcion de imgaen 1";
	        tooltips[0] = "Nombre de imgaen 1";
	        
	        bImg[1] = Bitmap.getBitmapResource("img2.jpg");
	        images[1] = resizeBitmap(bImg[1], 150, 150);
	        labels[1] = "Descripcion de imgaen 2";
	        tooltips[1] = "Nombre de imgaen 2";

	        bImg[2] = Bitmap.getBitmapResource("img3.jpg");
	        images[2] = resizeBitmap(bImg[2], 150, 150);
	        labels[2] = "Descripcion de imgaen 2";
	        tooltips[2] = "Nombre de imgaen 2";

	        bImg[3] = Bitmap.getBitmapResource("img4.jpg");
	        images[3] = resizeBitmap(bImg[3], 150, 150);
	        labels[3] = "Descripcion de imgaen 3";
	        tooltips[3] = "Nombre de imgaen 3";
	        
	        ScrollEntry[] entries = new ScrollEntry[4];

	        for (int i = 0; i < entries.length; i++) 
	        { 
	             entries[i] = new ScrollEntry(images[i], labels[i],tooltips[i]);
	        }
	        
	        pictureScrollField = new PictureScrollField(200, 200);
	        pictureScrollField.setPadding(46,0,46,0);
	        pictureScrollField.setData(entries, 0);
	        pictureScrollField.setHighlightStyle(HighlightStyle.MAGNIFY_LENS);
	        pictureScrollField.setHighlightBorderColor(Color.BLUE);
	        pictureScrollField.setLensShrink(0.9f);
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
            	//Dialog.alert("imagen presionada "+ pictureScrollField.getCurrentImageIndex() +"  "+bImg[0].toString());
            	openScreen(new ImagenGaleria( pictureScrollField.getCurrentImageIndex()));
            	
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
                	openScreen(new ImagenGaleria( pictureScrollField.getCurrentImageIndex()));
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

	public void fieldChanged(Field arg0, int arg1) {
		// TODO Auto-generated method stub
		
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
