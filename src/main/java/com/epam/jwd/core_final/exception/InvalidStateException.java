package com.epam.jwd.core_final.exception;

public class InvalidStateException extends Exception {

	private static final long serialVersionUID = 4975922770060137565L;

	private final String entityName;
    private final Object[] args;

    public InvalidStateException(String entityName) {
        super();
        this.entityName = entityName;
        this.args = null;
    }

    public InvalidStateException(String entityName, Object[] args) {
        super();
        this.entityName = entityName;
        this.args = args;
    }

    @Override
    public String getMessage() {
        // todo
        // you should use entityName, args (if necessary)
        return "Can not create a " + entityName;
    }
}
