package com.model.enums;

import java.util.HashMap;
import java.util.Map;

import com.model.enums.UserRoleEnumType.UserRoleEnum;
import com.model.enums.base.AbstractIntEnumUserType;

public class UserRoleEnumType extends AbstractIntEnumUserType<UserRoleEnum> {

    private static final Map<Integer, UserRoleEnum> MAP;
    public static final int COLUMN_LENGTH = 1;

    static {
        MAP = new HashMap<Integer, UserRoleEnum>();
        MAP.put(UserRoleEnum.convertToVal(UserRoleEnum.ROLE_USER), UserRoleEnum.ROLE_USER);
        MAP.put(UserRoleEnum.convertToVal(UserRoleEnum.ROLE_ADMIN), UserRoleEnum.ROLE_ADMIN);
        MAP.put(UserRoleEnum.convertToVal(UserRoleEnum.ROLE_SUPERUSER), UserRoleEnum.ROLE_SUPERUSER);
    }

    public UserRoleEnumType() {
        super(UserRoleEnum.class);
    }

    @Override
    protected Map<Integer, UserRoleEnum> getConversionMap() {
        return MAP;
    }
    

    public enum UserRoleEnum {
    	
    	ROLE_USER(1),    
    	ROLE_ADMIN(2),
    	ROLE_SUPERUSER(3);    
      
        private int role;    

        UserRoleEnum(int role){    
           this.role = role;    
        }
        
        public static UserRoleEnum convertFromDB(int role){
        	UserRoleEnum retVal = null;
        	for(UserRoleEnum val: UserRoleEnum.values()){
        		if(val.role == role){
        			retVal = val;
        		}
        	}
        	return retVal;
        }
        
        public static int convertToVal(UserRoleEnum valToConvert){
        	int retVal = 0;
        	for(UserRoleEnum val: UserRoleEnum.values()){
        		if(val == valToConvert){
        			retVal = valToConvert.role;
        		}
        	}
        	return retVal;
        }
    }  
}