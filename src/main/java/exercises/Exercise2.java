package exercises;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.JButton;
import javax.swing.text.DateFormatter;

import org.junit.Test;

public class Exercise2 {

	// Exercise 1
	/**
	 * What kind of lambda expressions might you use the Function<T, R> functional interface 
	 * for if you were writing a software calculator?
	 */
	@Test
	public void exercise2_1() {
		Function<Integer, Integer> squareFunction = x -> x * x;
		Function<Double, Double> squareRootFunction = x -> Math.sqrt(x);
		Function<Double, Double> sinFunction = x -> Math.sin(x);
		
		assertEquals(new Integer(25), squareFunction.apply(5));
		assertEquals(new Double(5.0), squareRootFunction.apply(25.0));
		assertTrue(0.499 < sinFunction.apply(Math.toRadians(30.0)) && 
				sinFunction.apply(Math.toRadians(30.0))< 0.5);
	}
	
	// Exercise 2
	/**
	 * ThreadLocal class has a new factory method that takes a lambda expression.
	 * The Java DateFormatter class isn’t thread-safe. Use the constructor to create a 
	 * thread-safe DateFormatter instance that prints dates like this: “01-Jan-1970”.
	 */
	private static final ThreadLocal<DateFormatter> threadId = 
			ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));
	
	@Test
	public void exercise2_2() {
		try {
			Date date = new Date();
			// 07-Apr-2018
			assertEquals(new SimpleDateFormat("dd-MMM-yyyy").format(date), threadId.get().valueToString(date));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Exercise 3
	// Would check(x -> x > 5) be inferred, given the following overloads for check?
	interface IntPred {
	    boolean test(Integer value);
	}
	boolean check1(Predicate<Integer> predicate, int num) {
		return predicate.test(num);
	}
	boolean check2(IntPred predicate, int num) {
		return predicate.test(num);
	}
	
	@Test
	public void exercise2_3() {
		Runnable helloWorld = () -> System.out.println("hello world");
		
		JButton button = new JButton();
		button.addActionListener(event ->
		  System.out.println(event.getActionCommand()));
		
		assertEquals(Boolean.FALSE, check1(x -> x > 5, 4));
		assertEquals(Boolean.FALSE, check2(x -> x > 5, 4));
	}

}
