package com.panos.mir.rootnames;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface CustomJsonRootName {
    String users();
    String recipes();
    String ingredients();
    String categories();
    String category();
}
