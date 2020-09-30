package com.kxjl.base.datasource;

public class KDataSourceTypeManager {

	
	    private static final ThreadLocal<KDataSourceType> dataSourceTypes = new ThreadLocal<KDataSourceType>(){
	        @Override
	        protected KDataSourceType initialValue(){
	            return KDataSourceType.KB;
	        }
	    };
	    
	    public static KDataSourceType get(){
	        return dataSourceTypes.get();
	    }
	    
	    public static void set(KDataSourceType dataSourceType){
	        dataSourceTypes.set(dataSourceType);
	    }
	    
	    public static void reset(){
	        dataSourceTypes.set(KDataSourceType.KB);
	    }
	}