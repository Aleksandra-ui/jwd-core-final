package com.epam.jwd.core_final.context;

import java.util.Scanner;
import java.util.function.Supplier;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    default ApplicationMenu printAvailableOptions() throws InvalidStateException {
        System.out.println("you'll do the task.");
        final NassaContext nassaContext = NassaContext.newInstance();
        final Supplier<ApplicationContext> applicationContextSupplier = () -> nassaContext; // todo

        nassaContext.init();
        return applicationContextSupplier::get;
       
    }

    default void handleUserInput() {
    	Scanner sc = new Scanner(System.in);
        System.out.print(sc.next());
    }
}
