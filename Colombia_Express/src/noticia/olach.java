package noticia;

import org.w3c.dom.Document;

import estilos.Metodos;

import net.rim.device.api.browser.field.ContentReadEvent;
import net.rim.device.api.browser.field2.BrowserField;
import net.rim.device.api.browser.field2.BrowserFieldListener;
import net.rim.device.api.script.ScriptEngine;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.Status;

/**
 * @description Demonstration of using the BrowserFieldListener class 
 * 				added to version 5.0 of the BlackBerry Device Software
 * @version 1.0
 * @author Adam Stanley (astanley@rim.com), Developer Relations
 * @category BlackBerry BrowserField Development: BrowserFieldListener API
 * @since April 30, 2010
 * @see http://www.blackberry.com/developers/docs/5.0.0api/net/rim/device/api/browser/field2/BrowserFieldListener.html
 */

/**
 * This class is to be extended for the purpose of 
 *	listening for events pertaining to a BrowserField component. 
 */
public class olach extends Metodos implements FieldChangeListener
{
	public olach(){
		
		Bitmap bitmap = Bitmap.getPredefinedBitmap(Bitmap.HOURGLASS );
		
		for(int i = 0; i<15; i++){
			Status.show("Cargando...",bitmap,100);
			
		BrowserField myBrowserField = new BrowserField(); 
		add(myBrowserField);
		myBrowserField.requestContent("local:///index.html");
		
		}
	}

	public void fieldChanged(Field arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}