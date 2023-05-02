package com.caja.ideal.utils;

public class AppConstant {
    public static final String[] SECURED_URLs = {"/books/**"};
    public static final String[] UN_SECURED_URLs = {"/usuario/**",  "/auth/**" , "/books/all", "/books/{id}"};
}
