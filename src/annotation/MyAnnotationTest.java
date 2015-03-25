package annotation;

@MyAnnotation(id=3, value="Hi", name="myAnnotationTest")
public class MyAnnotationTest {

	public static void main(String[] args) {
		if(MyAnnotationTest.class.isAnnotationPresent(MyAnnotation.class)) {
			MyAnnotation myAnnotation = MyAnnotationTest.class.getAnnotation(MyAnnotation.class);
			System.out.println("id: " + myAnnotation.id());
			System.out.println("name: " + myAnnotation.name());
			System.out.println("value: " + myAnnotation.value());
		}
	}

}
