package com.dgp52.bindjson2viewlib.extras;

import com.dgp52.bindjson2viewlib.interfaces.Extra;

public class Pixel implements Extra {
    @Override
    public Object convert(String value) {
        return Integer.parseInt(value);
    }
}
