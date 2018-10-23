package com.dgp52.bindjson2viewlib.interfaces;

import java.lang.reflect.Method;

public interface Convert {
    Object convert(String value, Method method);
}
