package com.foo.bar.si.file.demo.transformer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Zip2FileTransformer: Unzip Zip Byte Array to File
 *
 * @author ubuntu
 * @version 1.0
 *
 */

@Component
public class Zip2FileTransformer {

	private final Logger LOGGER = LoggerFactory
			.getLogger(Zip2FileTransformer.class);
	private static final int BUFFER_SIZE = 4096;

	public Message<byte[]> unZip(final Message<byte[]> message)
			throws IOException {

		String extractedFileName = null;

		final ZipInputStream zipIn = new ZipInputStream(
				new ByteArrayInputStream(message.getPayload()));

		ZipEntry entry = zipIn.getNextEntry();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while (entry != null) {
			extractedFileName = entry.getName();
			if (!entry.isDirectory()) {
				byte[] bytesIn = new byte[BUFFER_SIZE];
				int read = 0;
				while ((read = zipIn.read(bytesIn)) != -1) {
					out.write(bytesIn, 0, read);
				}
				out.close();

				zipIn.closeEntry();
				break;
			}
		}
		zipIn.close();

		LOGGER.info("File Extracted {}", extractedFileName);

		final Message<byte[]> zipMessage = MessageBuilder
				.withPayload(out.toByteArray())
				.setHeader(FileHeaders.FILENAME, extractedFileName)
				.copyHeadersIfAbsent(message.getHeaders()).build();
		return zipMessage;
	}
}
