package com.foo.bar.si.file.demo.service.activators;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import au.com.bytecode.opencsv.CSVReader;

/**
 * CsvFileReader Service : Print Csv Content using the System.out Print Stream
 *
 * @author Tarak AKIK
 * @version 1.0
 *
 */
@Component
public class CsvFileReader {

	Logger logger = LoggerFactory.getLogger(CsvFileReader.class);

	public void printCsv(final Message<byte[]> message) throws IOException {

		String fileName = (String) message.getHeaders().get(
				FileHeaders.FILENAME);

		ByteArrayInputStream bais = new ByteArrayInputStream(
				message.getPayload());

		logger.info("Reading Csv Byte from file {} :", fileName);

		CSVReader reader = new CSVReader(new InputStreamReader(bais));

		String[] line = null;
		while ((line = reader.readNext()) != null) {
			for (String s : line)
				System.out.print(s+"\t");
			System.out.println();
		}

		reader.close();

	}

}
