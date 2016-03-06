package com.foo.bar.si.file.demo.main;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.foo.bar.si.file.demo.context.SpringIntegrationFileDemoContext;


/**
 * DemoRunner : Start Demo
 *
 * @author ubuntu
 * @version 1.0
 *
 */
public class DemoRunner {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(DemoRunner.class);

	public static void main(String[] args) {

		final AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				SpringIntegrationFileDemoContext.class);
		context.registerShutdownHook();
		final Scanner scanner = new Scanner(System.in);
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("\n========================================================="
					+ "\n "
					+ "\n Welcome to Spring File Demo Integration! "
					+ "\n For more information please visit: "
					+ "\n "			
					+ "\n http://www.tarakakik.wordpress.com/"
					+ "\n "
					+ "\n Please press 'q + Enter' to quit the application. "
					+ "\n===========================================================");
		}
		while (!scanner.hasNext("q")) {
			// Do nothing unless user presses 'q' to quit.
		}
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Exiting application...bye.");
		}
		scanner.close();
		System.exit(0);
	}

}
