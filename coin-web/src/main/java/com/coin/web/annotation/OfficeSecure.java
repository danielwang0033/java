package com.coin.web.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OfficeSecure {

    boolean needLogin() default true;

    boolean doDownLoad() default false;
}
