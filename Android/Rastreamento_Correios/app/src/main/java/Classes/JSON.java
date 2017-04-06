package Classes;



import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;




public class JSON {
	
	public static String ObjToJson(Object obj)
	{
		Gson gson = new Gson();
		return gson.toJson(obj);

	}
	
	@SuppressWarnings("unchecked")
	public static Object JsonToObject(String sJson, Class classe)
	{
		Gson gson = new Gson();
		return gson.fromJson(sJson, classe);
	
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<?> JsonToListObject(String sJson,  Class clazz)
	{
		Gson gson = new Gson();
		return gson.fromJson(sJson, new ListParameterizedType(clazz));
		 
	
	}
	private static class ListParameterizedType implements ParameterizedType {

	    private Type type;

	    private ListParameterizedType(Type type) {
	        this.type = type;
	    }

	    @Override
	    public Type[] getActualTypeArguments() {
	        return new Type[] {type};
	    }

	    @Override
	    public Type getRawType() {
	        return ArrayList.class;
	    }

	    @Override
	    public Type getOwnerType() {
	        return null;
	    }

	    // implement equals method too! (as per javadoc)
	}
}
