package com.foo.bar.si.file.demo.testing;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foo.bar.si.file.demo.context.SpringIntegrationFileDemoContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringIntegrationFileDemoContext.class)
public class FileDemoTest {

	@Autowired
	private MessageChannel filesIn;

	private static final String TARGET_PATH = FileDemoTest.class
			.getProtectionDomain().getCodeSource().getLocation().getFile()
			.replaceAll("test-classes", "");

	@Test
	public void textContextLoad() {
		Assert.assertNotNull(filesIn);
	}
	
	@Test
	public void testFilter() throws InterruptedException, URISyntaxException {

		File file = null;
		file = getFile("/FILES/bigFile.csv");

		Assert.assertTrue(file.exists());
		Assert.assertTrue(file.length()>1000);

		boolean isSent = filesIn.send(MessageBuilder.withPayload(file).build());
		Assert.assertTrue(isSent);

		Thread.sleep(2000);

		File bigFile = FileUtils
				.getFile(TARGET_PATH + "/big/bigFile.csv");

		Assert.assertNotNull(bigFile.exists());

	}

	@Test
	public void testRooting() throws URISyntaxException, InterruptedException {

		File file =getFile("/FILES/users.pdf");
		Assert.assertTrue(file.exists());

		boolean isSent = filesIn.send(MessageBuilder.withPayload(file).build());
		Assert.assertTrue(isSent);

		Thread.sleep(2000);

		File otherFile = FileUtils.getFile(TARGET_PATH + "/others/users.pdf");

		Assert.assertTrue(otherFile.exists());
	}

	@Test
	public void testIntegrate() throws InterruptedException, URISyntaxException {

		File file = null;
		file = getFile("/FILES/users.csv");

		Assert.assertTrue(file.exists());

		boolean isSent = filesIn.send(MessageBuilder.withPayload(file).build());
		Assert.assertTrue(isSent);

		Thread.sleep(2000);

		File zipFile = FileUtils
				.getFile(TARGET_PATH + "/archive/users.csv.zip");

		Assert.assertNotNull(zipFile.exists());

	}

	private File getFile(String path) throws URISyntaxException {
		URL fileUrl = this.getClass().getResource(path);
		return new File(fileUrl.toURI());
	}

}
