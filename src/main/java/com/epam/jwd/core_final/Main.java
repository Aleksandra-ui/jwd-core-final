package com.epam.jwd.core_final;

import java.awt.Toolkit;

import com.epam.jwd.core_final.context.Application;
import com.epam.jwd.core_final.exception.InvalidStateException;

public class Main {

    public static void main(String[] args) throws InvalidStateException {
        Application.start().printAvailableOptions().handleUserInput();;;
    }
}