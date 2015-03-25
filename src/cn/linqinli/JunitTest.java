package cn.linqinli;

import junit.framework.*;
class Creature {
	private boolean isFrog = true;
	public void greet() {
		if(isFrog)
			System.out.println("Ribbet");
		else
			System.out.println("Darling");
	}
	public void kiss() {
		isFrog = false;
	}
}

public class JunitTest extends TestCase {
	Creature creature = new Creature();
	public void test() {
		creature.greet();
		creature.kiss();
		creature.greet();
	}
	public static void main(String args[]) {
		junit.textui.TestRunner.run(JunitTest.class);
	}
}
