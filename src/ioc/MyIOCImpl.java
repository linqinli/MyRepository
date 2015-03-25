package ioc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

import org.jdom.*;
import org.jdom.input.SAXBuilder;

public class MyIOCImpl {
	private static HashMap<String, Object> objMap = new HashMap<String, Object>();
	private static HashMap<String, Bean> beans = new HashMap<String, Bean>();
	
	private static class Bean {
		String name;
		String className;
		List<Property> properties;
		boolean initialized;
		
		Bean(String name, String className) {
			this.name = name;
			this.className = className;
			initialized = false;
			properties = new ArrayList<Property>();
		}
		void addProperty(Property property) {
			properties.add(property);
		}
	}
	private static class Property {
		String name;
		String ref;
		Property(String name, String ref) {
			this.name = name;
			this.ref = ref;
		}
	}
	public static void main(String[] args) throws Exception {
		loadXML("src\\ioc\\beans.xml");
		Student s = (Student)objMap.get("student");
		System.out.println(s.getMajor().getName());
	}
	
	public Object getBean(String name) {
		return objMap.get(name);
	}
	public static void loadXML(String path) throws JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(path);
		Element root = doc.getRootElement();
		List<?> list = root.getChildren("bean");
		for (int i = 0; i < list.size(); i++) {
			Element bean = (Element) list.get(i);
			String name = bean.getAttributeValue("name");
			String className = bean.getAttributeValue("class");
			Bean b = new Bean(name, className);
			List<?> propertyList = bean.getChildren("property");
			for(int j = 0; j < propertyList.size(); j++) {
				Element pro = (Element) propertyList.get(i);
				b.addProperty(new Property(pro.getAttributeValue("name"), pro.getAttributeValue("ref")));
			}
			beans.put(name, b);
		}
		
		for(String name : beans.keySet()) {
			Bean bean = beans.get(name);
			if(!bean.initialized) {
				initObject(bean);
			}
		}
	}
	
	private static void initObject(Bean bean) {
		try {
			String name = bean.name;
			String className = bean.className;
			Class c = Class.forName(className);
			Object obj = c.newInstance();
			initProperties(c, obj, bean.properties);
			objMap.put(name, obj);
			bean.initialized = true;
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void initProperties(Class c, Object obj, List<Property> propertyList) throws Exception {
		if(propertyList == null || propertyList.isEmpty()) {
			return ;
		}
		for(Property pro : propertyList) {
			Object args = objMap.get(pro.ref);
			if(args == null) {
				initObject(beans.get(pro.ref));
				args = objMap.get(pro.ref);
			}
			String methodStr = "set" + pro.name.substring(0, 1).toUpperCase() + pro.name.substring(1);
			Method method = c.getMethod(methodStr, args.getClass());
			method.invoke(obj, args);
		}
	}

}
