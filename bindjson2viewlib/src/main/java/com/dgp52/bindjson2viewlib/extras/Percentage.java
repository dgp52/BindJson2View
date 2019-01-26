package com.dgp52.bindjson2viewlib.extras;

import com.dgp52.bindjson2viewlib.interfaces.Extra;

public class Percentage implements Extra {
    @Override
    public Object convert(String value) {
        return (int) ((Integer.parseInt(value)/100.0f));
    }
}
