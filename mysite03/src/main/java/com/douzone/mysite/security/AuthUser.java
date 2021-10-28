package com.douzone.mysite.security;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//1. 어노테이션정의
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface AuthUser {
}
