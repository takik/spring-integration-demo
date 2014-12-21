package com.foo.bar.si.file.demo.main;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.foo.bar.si.file.demo.context.SpringIntegrationFileDemoContext;

public class DemoRunner {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(DemoRunner.class);

	public static void main(String[] args) {

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("\n========================================================="
					+ "\n "
					+ "\n Welcome to Spring File Demo Integration! "
					+ "\n "
					+ "\n For more information please visit: "
					+ "\n http://www.tarakakik.wordpress/spring-integration "
					+ "\n "
					+ "\n=========================================================");
		}
		final AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				SpringIntegrationFileDemoContext.class);
		context.registerShutdownHook();
		final Scanner scanner = new Scanner(System.in);
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("\n========================================================="
					+ "\n "
					+ "\n Please press 'q + Enter' to quit the application. "
					+ "\n "
					+ "\n=========================================================");
		}
		while (!scanner.hasNext("q")) {
			// Do nothing unless user presses 'q' to quit.
		}
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Exiting application...bye.");
		}
		System.exit(0);
	}

}
