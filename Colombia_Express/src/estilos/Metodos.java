package estilos;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import net.rim.device.api.browser.field2.BrowserField;
import net.rim.device.api.io.IOUtilities;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Characters;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.TouchEvent;
import net.rim.device.api.ui.Touchscreen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.MainScreen;

public abstract class Metodos extends MainScreen implements FieldChangeListener{
	
		Metodos prevScreen ;
	
	public Metodos(){
		
	}
	
	protected void openScreen(Metodos nextScreen) {
		nextScreen.prevScreen = this;
		UiApplication.getUiApplication().popScreen(this);
		UiApplication.getUiApplication().pushScreen(nextScreen);
	}
	
	public class WLabelField extends LabelField
	{
		public WLabelField(Object text)
		{
			super(text,Field.FIELD_VCENTER);
		}
		
		public void paint(Graphics g)
		{      
			g.setColor(Color.WHITE);
			super.paint(g);
		}
	}
	
	public class RLabelField extends LabelField
	{
		public RLabelField(Object text)
		{
			super(text,Field.FIELD_VCENTER);
		}
		
		public void paint(Graphics g)
		{      
			g.setColor(Color.RED);
			super.paint(g);
		}
	}
	
	public class WRichTextField extends RichTextField
	{
		public WRichTextField(String string)
		{
			super(string);
		}
		
		public void paint(Graphics g)
		{      
			g.setColor(Color.WHITE);
			super.paint(g);
		}
	}
	
	
	
	
	private static String HEADER_CONTENTTYPE = "content-type";
	private static String CONTENTTYPE_TEXTHTML = "text/html";

/**
* Fetches the content on the speicifed url.
* @param url The url of the content to fetch
*/

public static Bitmap getImageFromUrl(String url) {
Bitmap bitmap = null;

try
{
	String bitmapData = getDataFromUrl(url);
	bitmap = Bitmap.createBitmapFromBytes(bitmapData.getBytes(), 0, bitmapData.length(), 1);
	// Image.createImage(imageData.getBytes(), 0,imageData.length());
}catch(Exception e1) {
	e1.printStackTrace();
}

return bitmap;
}

/**
* Fetches the content on the speicifed url.
* @param url The url of the content to fetch
*/
private static String getDataFromUrl(String url){
StringBuffer b = new StringBuffer();
InputStream is = null;
HttpConnection c = null;

long len = 0 ;
int ch = 0;

try {
c = (HttpConnection)Connector.open(url);

is = c.openInputStream();
len = c.getLength();
if( len != -1) {
//Read exactly Content-Length bytes
	for(int i =0 ; i < len ; i++ )
		if((ch = is.read()) != -1) {
			b.append((char) ch);
		}
} else {
//Read until the connection is closed.
	while ((ch = is.read()) != -1) {
		len = is.available() ;
		b.append((char)ch);
	}
}

is.close();
c.close();

} catch (IOException e) {
//TODO Auto-generated catch block
	e.printStackTrace();
}

return b.toString();
}
	
	

	 public static Bitmap ImageUrl(String url) {
	  HttpConnection httpConnection = null;
	  DataOutputStream httpDataOutput = null;
	  InputStream httpInput = null;
	  int rc;
	  Bitmap bitmp = null;
	  try {
	   httpConnection = (HttpConnection) Connector.open(url);
	   rc = httpConnection.getResponseCode();
	   if (rc != HttpConnection.HTTP_OK) {
	    throw new IOException("HTTP response code: " + rc);
	   }
	   httpInput = httpConnection.openInputStream();
	   InputStream inp = httpInput;
	   byte[] b = IOUtilities.streamToBytes(inp);
	   EncodedImage hai = EncodedImage.createEncodedImage(b, 0, b.length);
	   //====================
	  
       //==============
	   return hai.getBitmap();
	  } catch (Exception ex) {
	   // System.out.println("URL Bitmap Error........" + ex.getMessage());
	  } finally {
	   try {
	    if (httpInput != null)
	     httpInput.close();
	    if (httpDataOutput != null)
	     httpDataOutput.close();
	    if (httpConnection != null)
	     httpConnection.close();
	    } catch (Exception e) {
	    e.printStackTrace();
	    }
	    }
	   return bitmp;
	  
	  }
	
	
	
	 public static Bitmap resizeBitmap(Bitmap image, int width, int height)
	  {
	      int imageWidth = image.getWidth();
	      int imageHeight = image.getHeight();
	    
	      // Need an array (for RGB, with the size of original image)
	      int rgb[] = new int[imageWidth * imageHeight];
	    
	      // Get the RGB array of image into "rgb"
	      image.getARGB(rgb, 0, imageWidth, 0, 0, imageWidth, imageHeight);
	    
	      // Call to our function and obtain rgb2
	      int rgb2[] = rescaleArray(rgb, imageWidth, imageHeight, width, height);
	    
	      // Create an image with that RGB array
	      Bitmap temp2 = new Bitmap(width, height);
	    
	      temp2.setARGB(rgb2, 0, width, 0, 0, width, height);
	    
	      return temp2;
	  }

	  private static int[] rescaleArray(int[] ini, int x, int y, int x2, int y2)
	  {
	      int out[] = new int[x2*y2];
	      for (int yy = 0; yy < y2; yy++)
	      {
	          int dy = yy * y / y2;
	          for (int xx = 0; xx < x2; xx++)
	          {
	              int dx = xx * x / x2;
	              out[(x2 * yy) + xx] = ini[(x * dy) + dx];
	          }
	      }
	      return out;
	  }
	
	
	  public static class StyleList extends Field
	  {
	      public static int DRAWPOSITION_TOP = 0;
	      public static int DRAWPOSITION_BOTTOM = 1;
	      public static int DRAWPOSITION_MIDDLE = 2;
	      public static int DRAWPOSITION_SINGLE = 3;
	     
	      private static final int CORNER_RADIUS = 18;
	     
	      private static final int HPADDING = Display.getWidth() <= 320 ? 6 : 8;
	      private static final int VPADDING = 4;
	     
	      private static final int COLOR_BACKGROUND = 0xFFFFFF;
	      private static final int COLOR_BORDER = 0xBBBBBB;
	      private static final int COLOR_BACKGROUND_FOCUS = 0x186DEF;
	     
	     
	      private MyLabelField _labelField;
	      private Bitmap _leftIcon;
	      private Bitmap _actionIcon;
	     
	      private int _targetHeight;
	      private int _rightOffset;
	      private int _leftOffset;
	      private int _labelHeight;
	     
	      private int _drawPosition = -1;

	      public StyleList( String label, long style )
	      {
	          this( label, null, style );
	      }
	     
	      public StyleList(BrowserField img, String label, Bitmap actionIcon )
	      {
	          this( null, label, actionIcon, 0 );
	      }
	     
	      public StyleList( String label, Bitmap actionIcon, long style )
	      {
	          this( null, label, actionIcon, style );
	      }
	     
	      public StyleList( Bitmap icon, String label, long style )
	      {
	          this( icon, label, null, style );
	      }
	     
	      public StyleList( Bitmap icon, String label, Bitmap actionIcon, long style )
	      {
	          super( USE_ALL_WIDTH | Field.FOCUSABLE );
	                
	          _labelField = new MyLabelField( label, style );
	          _actionIcon = actionIcon;
	          _leftIcon = icon;
	      }
	     
	      /**
	       * DRAWPOSITION_TOP | DRAWPOSITION_BOTTOM | DRAWPOSITION_MIDDLE
	       * Determins how the field is drawn (borders)
	       * If none is set, then no borders are drawn
	       */
	      public void setDrawPosition( int drawPosition )
	      {
	          _drawPosition = drawPosition;
	      }
	     
	      public String toString()
	      {
	          return _labelField.toString();
	      }
	     
	      public void layout( int width, int height )
	      {

	          _targetHeight = getFont().getHeight() / 2 * 3 + 2 * VPADDING;
	  //#ifndef VER_4.6.1 | VER_4.6.0 | VER_4.5.0 | VER_4.2.1 | VER_4.2.0
	          if( Touchscreen.isSupported() ) {
	              _targetHeight = getFont().getHeight() * 2 + 2 * VPADDING;
	          }
	  //#endif
	         
	          _leftOffset = HPADDING;
	          if( _leftIcon != null ) {
	              _leftOffset += _leftIcon.getWidth() + HPADDING;
	          }
	         
	          _rightOffset = HPADDING;
	          if( _actionIcon != null ) {
	              _rightOffset += _actionIcon.getWidth() + HPADDING;
	          }
	         
	          _labelField.layout( width - _leftOffset - _rightOffset, height );
	          _labelHeight = _labelField.getHeight();
	          int labelWidth = _labelField.getWidth();
	         
	          if( _labelField.isStyle( DrawStyle.HCENTER ) ) {
	              _leftOffset = ( width - labelWidth ) / 2;
	          } else if ( _labelField.isStyle( DrawStyle.RIGHT ) ) {
	              _leftOffset = width - labelWidth - HPADDING - _rightOffset;
	          }
	         
	          int extraVPaddingNeeded = 0;
	          if( _labelHeight < _targetHeight ) {
	              // Make sure that they are at least 1.5 times font height
	              extraVPaddingNeeded =  ( _targetHeight - _labelHeight ) / 2;
	          }
	         
	          setExtent( width, _labelHeight + 2 * extraVPaddingNeeded );
	      }
	     
	      public void setText( String text )
	      {
	          _labelField.setText( text );
	          updateLayout();
	      }
	     
	      protected void paint( Graphics g )
	      {
	          // Left Bitmap
	          if( _leftIcon != null ) {
	              g.drawBitmap( HPADDING, VPADDING, _leftIcon.getWidth(), _leftIcon.getHeight(), _leftIcon, 0, 0 );
	          }
	         
	          // Text
	          try {
	              g.pushRegion( _leftOffset, ( getHeight() - _labelHeight ) / 2, getWidth() - _leftOffset - _rightOffset, _labelHeight, 0, 0 );
	              _labelField.paint( g );
	          } finally {
	              g.popContext();
	          }
	         
	          // Right (Action) Bitmap
	          if( _actionIcon != null ) {
	              g.drawBitmap( getWidth() - HPADDING - _actionIcon.getWidth(), ( getHeight() - _actionIcon.getHeight() ) / 2, _actionIcon.getWidth(), _actionIcon.getHeight(), _actionIcon, 0, 0 );
	          }
	      }
	     
	      protected void paintBackground( Graphics g )
	      {
	          if( _drawPosition < 0 ) {
	              // it's like a list field, let the default background be drawn
	              super.paintBackground( g );
	              return;
	          }
	         
	          int oldColour = g.getColor();
	         
	          int background = g.isDrawingStyleSet( Graphics.DRAWSTYLE_FOCUS ) ? COLOR_BACKGROUND_FOCUS : COLOR_BACKGROUND;
	          try {
	              if( _drawPosition == 0 ) {
	                  // Top
	                  g.setColor( background );
	                  g.fillRoundRect( 0, 0, getWidth(), getHeight() + CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS );
	                  g.setColor( COLOR_BORDER );
	                  g.drawRoundRect( 0, 0, getWidth(), getHeight() + CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS );
	                  g.drawLine( 0, getHeight() - 1, getWidth(), getHeight() - 1 );
	              } else if( _drawPosition == 1 ) {
	                  // Bottom
	                  g.setColor( background );
	                  g.fillRoundRect( 0, -CORNER_RADIUS, getWidth(), getHeight() + CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS );
	                  g.setColor( COLOR_BORDER );
	                  g.drawRoundRect( 0, -CORNER_RADIUS, getWidth(), getHeight() + CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS );
	              } else if( _drawPosition == 2 ) {
	                  // Middle
	                  g.setColor( background );
	                  g.fillRoundRect( 0, -CORNER_RADIUS, getWidth(), getHeight() + 2 * CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS );
	                  g.setColor( COLOR_BORDER );
	                  g.drawRoundRect( 0, -CORNER_RADIUS, getWidth(), getHeight() + 2 * CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS );
	                  g.drawLine( 0, getHeight() - 1, getWidth(), getHeight() - 1 );
	              } else {
	                  // Single
	                  g.setColor( background );
	                  g.fillRoundRect( 0, 0, getWidth(), getHeight(), CORNER_RADIUS, CORNER_RADIUS );
	                  g.setColor( COLOR_BORDER );
	                  g.drawRoundRect( 0, 0, getWidth(), getHeight(), CORNER_RADIUS, CORNER_RADIUS );
	              }
	          } finally {
	              g.setColor( oldColour );
	          }
	      }
	     
	      protected void drawFocus( Graphics g, boolean on )
	      {
	          if( _drawPosition < 0 ) {
	              super.drawFocus( g, on );
	          } else {
	              boolean oldDrawStyleFocus = g.isDrawingStyleSet( Graphics.DRAWSTYLE_FOCUS );
	              try {
	                  if( on ) {
	                      g.setDrawingStyle( Graphics.DRAWSTYLE_FOCUS, true );
	                  }
	                  paintBackground( g );
	                  paint( g );
	              } finally {
	                  g.setDrawingStyle( Graphics.DRAWSTYLE_FOCUS, oldDrawStyleFocus );
	              }
	          }
	      }
	     
	      protected boolean keyChar( char character, int status, int time )
	      {
	          if( character == Characters.ENTER ) {
	              clickButton();
	              return true;
	          }
	          return super.keyChar( character, status, time );
	      }
	     
	      protected boolean navigationClick( int status, int time )
	      {
	          clickButton();
	          return true;   
	      }
	     
	      protected boolean trackwheelClick( int status, int time )
	      {       
	          clickButton();   
	          return true;
	      }
	     
	      //#ifndef VER_4.1.0 | 4.0.0
	          protected boolean invokeAction( int action )
	          {
	              switch( action ) {
	                  case ACTION_INVOKE: {
	                      clickButton();
	                      return true;
	                  }
	              }
	              return super.invokeAction( action );
	          }
	      //#endif       
	          
	      /**
	       * A public way to click this button
	       */
	      public void clickButton()
	      {
	          fieldChangeNotify( 0 );
	      }
	        
	  //#ifndef VER_4.6.1 | VER_4.6.0 | VER_4.5.0 | VER_4.2.1 | VER_4.2.0
	      protected boolean touchEvent( TouchEvent message )
	      {
	          int x = message.getX( 1 );
	          int y = message.getY( 1 );
	          if( x < 0 || y < 0 || x > getExtent().width || y > getExtent().height ) {
	              // Outside the field
	              return false;
	          }
	          switch( message.getEvent() ) {
	        
	              case TouchEvent.UNCLICK:
	                  clickButton();
	                  return true;
	          }
	          return super.touchEvent( message );
	      }
	  //#endif

	      public void setDirty( boolean dirty ) {}
	      public void setMuddy( boolean muddy ) {}
	     
	     
	      private static class MyLabelField extends LabelField
	      {
	         
	          public MyLabelField( String text, long style )
	          {
	              super( text, style );
	          }
	     
	          public void layout( int width, int height )
	          {  
	              super.layout( width, height );
	          }  
	         
	          public void paint( Graphics g )
	          {
	              super.paint( g );
	          }
	      }
	  }
	
	
	
	
}
