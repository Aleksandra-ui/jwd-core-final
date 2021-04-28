package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.function.Supplier;

public interface Application {

	static ApplicationMenu start() throws InvalidStateException, FileNotFoundException, UnsupportedEncodingException {

		final NassaContext nassaContext = NassaContext.newInstance();
		final Supplier<ApplicationContext> applicationContextSupplier = () -> nassaContext; // todo

		nassaContext.init();
		return applicationContextSupplier::get;
	}
}
