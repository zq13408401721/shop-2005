package com.sprout.interfaces;

public interface Callback<T> {

    void fail(String msg);

    void success(T t);

}
