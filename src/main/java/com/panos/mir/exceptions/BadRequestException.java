package com.panos.mir.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Panos on 4/5/2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseBody
public class BadRequestException extends RuntimeException{
}
