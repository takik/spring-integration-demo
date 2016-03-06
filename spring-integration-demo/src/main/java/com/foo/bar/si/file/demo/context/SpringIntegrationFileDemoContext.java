package com.foo.bar.si.file.demo.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * SpringIntegrationFileDemoContext : Java Config Clazz
 *
 * @author ubuntu
 * @version 1.0
 *
 */
@Configuration
@ComponentScan(basePackages={"com.foo.bar.si.file.demo.service.activators",
							"com.foo.bar.si.file.demo.transformer"})
@ImportResource("classpath:META-INF/demo-integration-context.xml")
public class SpringIntegrationFileDemoContext {

}
