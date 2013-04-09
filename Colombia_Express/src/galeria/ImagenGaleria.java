package galeria;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.Dialog;
import estilos.Metodos;

public class ImagenGaleria extends Metodos
{

	Bitmap bitmap;
	int left = 0;
	int top = 0;
	int maxLeft = 0;
	int maxTop = 0;
     public ImagenGaleria(int index)
     {	
    	 //index = index -1;
			bitmap = Bitmap.getBitmapResource("img"+index+".jpg");
			
			if (bitmap == null) {
				UiApplication.getUiApplication().invokeLater(new Runnable() {
					public void run() {
						Dialog.alert("Failed to load image");
						System.exit(0);
					}
				});
				return;
			}
			
			if (bitmap.getWidth() > Graphics.getScreenWidth()) {
				maxLeft = bitmap.getWidth() - Graphics.getScreenWidth();
			}
			
			if (bitmap.getHeight() > Graphics.getScreenHeight()) {
				maxTop = bitmap.getHeight() - Graphics.getScreenHeight();
			}
		}
		
		protected void paint(Graphics graphics) {
			if (bitmap != null) {
				graphics.drawBitmap(0, 0, Graphics.getScreenWidth(), Graphics.getScreenHeight(),
						bitmap, left, top);
			}
		}

	public void fieldChanged(Field field, int context) {
		// TODO Auto-generated method stub
		
	}
	
	
	public boolean onClose() {
		 TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
	        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
	        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT);
	        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
	        UiEngineInstance engine = Ui.getUiEngineInstance();
	        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
	        openScreen(new Galeria());
		return true;
	}
}