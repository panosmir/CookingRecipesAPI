package com.panos.mir.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Panos on 4/3/2017.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
@ResponseBody
public class NotFoundException extends RuntimeException{
}
