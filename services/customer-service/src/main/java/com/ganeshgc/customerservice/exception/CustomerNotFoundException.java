package com.ganeshgc.customerservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true) //Extends the behavior of equals() and
// hashCode() by including fields from the superclass (RuntimeException in this case).
public class CustomerNotFoundException extends RuntimeException {
    private final String msg;

}
