package promocion;

import java.util.Vector;

import mypackage.Menu;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import net.rim.device.api.ui.component.Status;
import estilos.Metodos;

public class CopyOfPromocion extends Metodos implements ListFieldCallback {

// Two listFields, one to display the Girls, the other to display the Boys
private ListField _lfBoys;
private ListField _lfGirls;

private Person _selectedPerson = null; // Selected Person
private boolean _selectedPersonIsABoy = false;

// We display Data from these Vector
private Vector _listBoys = new Vector(5);
private Vector _listGirls = new Vector(5);
// In this case we populate the Vectors with
// our own data, normally of course this
// data would be supplied to us

private MenuItem _displayPersonMenu = new MenuItem("Display", 110, 10) {
public void run() {
if ( _selectedPerson != null ) {
String name = _selectedPerson._firstName + " " +
  _selectedPerson._initial + " " +
  _selectedPerson._lastName;
if ( _selectedPersonIsABoy ) {
name = name + " is a Boy";
} else {
name = name + " is a Girl";
}
Status.show(name);
}
}
};

public CopyOfPromocion() {
// Set up Dummy Data
_listBoys.addElement(new Person("Joe", "R", "Bloggs"));
_listBoys.addElement(new Person("Fred", "F", "Flintstone"));
_listBoys.addElement(new Person("Andy", "IS", "Boring"));
_listBoys.addElement(new Person("Peter", "IS", "Clever"));

_listGirls.addElement(new Person("Joesphine", "R", "Bloggs"));
_listGirls.addElement(new Person("Fredrica", "F", "Flintstone"));
_listGirls.addElement(new Person("Paticia", "IS", "Clever"));

setTitle("Team Players");

this.add(new LabelField("Boys"));
_lfBoys = new ListField(_listBoys.size());
_lfBoys.setCallback(this); // We manage the interaction!
this.add(_lfBoys);

this.add(new LabelField("Girls"));
_lfGirls = new ListField(_listGirls.size());
_lfGirls.setCallback(this); // We manage the interaction!
this.add(_lfGirls);

}

protected boolean onSavePrompt() {
return true;
}

protected void makeMenu( Menu menu, int instance ) {
// Which List item is selcted?
_selectedPerson = null; // Not sure which is focused atm.
Field focusingOn = this.getLeafFieldWithFocus();
if ( focusingOn == _lfBoys ) {
_selectedPersonIsABoy = true;
_selectedPerson = (Person) this.get(_lfBoys, _lfBoys.getSelectedIndex());
}
if ( focusingOn == _lfGirls ) {
_selectedPersonIsABoy = false;
_selectedPerson = (Person) this.get(_lfGirls, _lfGirls.getSelectedIndex());
}

}
//menu.add(_displayPersonMenu);
// Implemented Call Back Methods follow

// draw the curent row
public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
Person personToDraw = (Person) this.get(list, index);
int drawColor = Color.BLACK;
if ( list == _lfBoys ) {
// We have a boy!
drawColor = Color.BLUE;
} else {
drawColor = Color.RED;
}
g.setColor(drawColor);
g.drawText(personToDraw._firstName + " " + personToDraw._lastName, 0, y, 0, w);
}

// get the selected index from the correct Vector
public Object get(ListField list, int index) {
if ( list == _lfBoys ) {
return _listBoys.elementAt(index);
} else {
return _listGirls.elementAt(index);
}
}

public int getPreferredWidth(ListField list) {
return Display.getWidth();
}

public int indexOfList(ListField listField, String prefix, int start) {
// Not a correct implementation - this is really just commented out
return start;
}

// This is the class which describes the data we will be displaying
class Person {
// In this example, to save accessors, the names are public
public String _firstName = null;
public String _lastName = null;
public String _initial = null;
public Person(String firstName, String initial, String lastName) {
_firstName = firstName;
_initial = initial;
_lastName = lastName;
}
}


		
	
	public boolean onClose() {
		//force the app to quit
		
		 TransitionContext transition = new TransitionContext(TransitionContext.TRANSITION_SLIDE);
	        transition.setIntAttribute(TransitionContext.ATTR_DURATION, 200);
	        transition.setIntAttribute(TransitionContext.ATTR_DIRECTION, TransitionContext.DIRECTION_RIGHT);
	        transition.setIntAttribute(TransitionContext.ATTR_STYLE, TransitionContext.STYLE_PUSH);
	        
	        UiEngineInstance engine = Ui.getUiEngineInstance();
	        engine.setTransition(this, null, UiEngineInstance.TRIGGER_PUSH, transition);
	       // openScreen(new Menu());
		return true;
	}

	public void fieldChanged(Field arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
