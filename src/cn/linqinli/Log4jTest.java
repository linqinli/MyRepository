package cn.linqinli;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;

public class Log4jTest {

	public static void main(String[] args) {
		ConsoleAppender appender = new ConsoleAppender(new PatternLayout());
		Logger logger = Logger.getLogger(Log4jTest.class);
		logger.addAppender(appender);
        logger.setLevel(Level.INFO);
        logger.info("info.");
        logger.debug("debug.");
        logger.warn("warning.");
	}

}
