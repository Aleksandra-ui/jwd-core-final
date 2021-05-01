package com.epam.jwd.core_final.exception;

public class UnknownEntityException extends RuntimeException {

	private static final long serialVersionUID = -6196537801005288073L;
	private final String entityName;
    private final Object[] args;

    public UnknownEntityException(String entityName) {
        super();
        this.entityName = entityName;
        this.args = null;
    }

    public UnknownEntityException(String entityName, Object[] args) {
        super();
        this.entityName = entityName;
        this.args = args;
    }

    @Override
    public String getMessage() {
        // todo
        // you should use entityName, args (if necessary)
        return "Required " + entityName + " doesn't exist!";
    }
}
