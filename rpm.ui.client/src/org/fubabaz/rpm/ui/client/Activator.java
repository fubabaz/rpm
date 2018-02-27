package org.fubabaz.rpm.ui.client;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.fubabaz.rpm.exception.SystemException;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		configureLogbackInBundle(bundleContext.getBundle());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
	}

	private void configureLogbackInBundle(Bundle bundle) {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		context.reset();

		JoranConfigurator jc = new JoranConfigurator();
		jc.setContext(context);

		URL logbackConfigFileUrl = FileLocator.find(bundle, new Path("config/logback.xml"), null);
		try {
			jc.doConfigure(logbackConfigFileUrl.openStream());
		} catch (JoranException e) {
			throw new SystemException(e);
		} catch (IOException e) {
			throw new SystemException(e);
		}
	}
}
