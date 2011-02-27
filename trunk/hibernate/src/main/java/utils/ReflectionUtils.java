package utils;

import java.lang.reflect.InvocationTargetException;
 
/**
 * @author Ronny
 *
 */
public class ReflectionUtils {

	public static Object invokeMethod(Object model, String actionName) {
		try {
			return model.getClass().getMethod(actionName, new Class[]{}).invoke(model, new Object[]{});
		}
		catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
		catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void invokeMethod(Object model, String actionName, Class c, Object... args) {
		try {
			model.getClass().getMethod(actionName, c).invoke(model, args);
		}
		catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
		catch (SecurityException e) {
			throw new RuntimeException(e);
		}
		catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}
