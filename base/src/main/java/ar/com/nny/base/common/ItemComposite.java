package ar.com.nny.base.common;

import java.awt.Component;
import java.util.Vector;

@SuppressWarnings("serial")
abstract public class ItemComposite extends Vector<Component> implements Item {

	abstract public String toString() ;

}
