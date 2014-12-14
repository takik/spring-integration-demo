package com.foo.bar.si.file.demo.testing;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
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
	
	@Test
	public void textContextLoad(){
		Assert.assertNotNull(filesIn);
	}
	
	
	@Test
	public void testRooting() throws URISyntaxException{
		
		URL fileUrl = this.getClass().getResource("/FILES/users.pdf");
		File file = new File(fileUrl.toURI());
        Assert.assertTrue(file.exists());
		
		boolean isSent=filesIn.send(MessageBuilder.withPayload(file).build());
		
		Assert.assertNotNull(FileUtils.getFile("/output/others/users.pdf"));
	}
	
	
	@Test
	public void testIntegrate() throws URISyntaxException{
		
		URL fileUrl = this.getClass().getResource("/FILES/users.csv");
		File file = new File(fileUrl.toURI());
        Assert.assertTrue(file.exists());
		
		boolean isSent=filesIn.send(MessageBuilder.withPayload(file).build());
		
		Assert.assertNotNull(FileUtils.getFile("/output/archive/users.csv.zip"));
	}


}
