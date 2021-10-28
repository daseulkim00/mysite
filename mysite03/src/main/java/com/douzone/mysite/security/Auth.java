package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Target: 어노테이션의 타겟을 지정( FIELD, METHOD, PARAMETER, TYPE)
// @Retention: 어노테이션의 지속 (보존) 기간을 지정(  RUNTIME, SOURCE ) 
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
	// public String value() default "USER";
	public String role() default "USER";
	// public boolean test() default false;
}
