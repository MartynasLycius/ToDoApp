/*****************************************************************************************************************
 *
 *	 File			 : Status.java
 *
  *****************************************************************************************************************/


package com.opt.common.enumeration;

public enum Status {

    ACTIVE("A", "Active"), INACTIVE("I", "Inactive"), CLOSED("C", "Closed");

    public static Status find(final String value) {
        for (final Status status : values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        return null;
    }

    private final String value;
    private final String label;

    private Status(final String value, final String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
