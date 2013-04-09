package galeria;

import net.rim.device.api.browser.field2.BrowserField;
import net.rim.device.api.browser.field2.BrowserFieldConfig;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import estilos.Metodos;

public class GaleriaWeb extends Metodos{
	int AnchoImagen = 640;
	int AltoImagen = 79;
	public GaleriaWeb(){
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
			AnchoImagen = 480;	
			AltoImagen = 59;
	
		}	
		if (Display.getWidth() == 640)
		{
			
		}
		
		Bitmap imagenT 	=  resizeBitmap(Bitmap.getBitmapResource("headImage.png"), AnchoImagen, AltoImagen);
		BitmapField bitmapImg1 = new BitmapField( imagenT, Field.FIELD_HCENTER | Field.FIELD_VCENTER );
		HorizontalFieldManager head = new HorizontalFieldManager(Field.USE_ALL_WIDTH | Field.FIELD_HCENTER | Field.FIELD_VCENTER);
		head.setBackground(BackgroundFactory.createLinearGradientBackground(Color.WHITE, Color.WHITE,Color.SILVER,Color.SILVER));
		head.add(bitmapImg1);
		setBanner(head);
		
		BrowserFieldConfig myBrowserFieldConfig = new BrowserFieldConfig();
	    myBrowserFieldConfig.setProperty(BrowserFieldConfig.NAVIGATION_MODE, BrowserFieldConfig.NAVIGATION_MODE_CARET);
	    myBrowserFieldConfig.setProperty(BrowserFieldConfig.ENABLE_COOKIES,Boolean.TRUE);
	    BrowserField browserField = new BrowserField(myBrowserFieldConfig);
	    browserField.requestContent("local:///pageHeber/index.html");
	    add(browserField);
		
	}
	public void fieldChanged(Field arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
