package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FormUtils {
	
	public static void copyFormFieldsUsingReflection(Object inObj,Object outObj){
		if(null != inObj){
			Field[] fields = inObj.getClass().getDeclaredFields();
			Field outField = null;
			for(Field fld : fields){
				try {					
					outField = outObj.getClass().getDeclaredField(fld.getName());
					if( (null != outField) && 
						(false == Modifier.isTransient(outField.getModifiers())) &&
						(false == Modifier.isFinal(outField.getModifiers()))){
						fld.setAccessible(true);
						outField.setAccessible(true);
						outField.set(outObj, fld.get(inObj));
						fld.setAccessible(false);
						outField.setAccessible(false);
					}
				} catch(NoSuchFieldException fe){
					;//do nothing, expected
				}catch (Exception e) {				
					e.printStackTrace();
				}
			}
		}
	}
}
