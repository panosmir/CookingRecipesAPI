package com.panos.mir.rootnames;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Panos on 3/22/2017.
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CustomJsonRootName {
    String users();
    String recipes();
}
