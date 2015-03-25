package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

public class MyAOP implements InvocationHandler {
	private Object target;
	
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),  
                target.getClass().getInterfaces(), this); 
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		try {
			ConnectionFactory.setAutoCommit(false);
			System.out.println("Transaction Begin.");
			method.invoke(target, args);
			ConnectionFactory.commit();
			System.out.println("Commited.");
		} catch (Exception e) {
			ConnectionFactory.rollBack();
			System.out.println("Roll Back.");
			e.printStackTrace();
		}
		return null;
	}
	
	public static IStuDAO getStuDAO() {
		MyAOP my = new MyAOP();
		IStuDAO stuDAO = (IStuDAO)my.bind(new StuDAO());
		return stuDAO;
	}
	
	public static void main(String[] args) throws SQLException {
		
		getStuDAO().delStudent(19);
	}
}
