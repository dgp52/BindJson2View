package com.dgp52.bindjson2viewlib.util;

public final class Keyword {

    public enum App {
        FILENAME("bindjson2view"),
        VIEWPROCESSORTHREAD("viewProcessorThread"),
        JSONPROCESSORTHREAD("jsonProcessorThread"),
        USENETWORK("network"),
        USELOCAL("local");

        private final String value;
        App(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    public enum JSONProperty {
        BINDERS("binders"),
        TAGS("tags"),
        METHODS("methods"),
        NAME("name"),
        CONVERTS("converts"),
        PARAMS("params"),
        VALUES("values"),
        UNIT("unit"),
        SWITCH("switch");

        private final String value;
        JSONProperty(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    public enum ConvertType {
        BOOLEAN("boolean"),
        BYTE("byte"),
        CHAR("char"),
        SHORT("short"),
        INT("int"),
        LONG("long"),
        FLOAT("float"),
        DOUBLE("double"),
        CHARSEQUENCE("charSequence"),
        STRING("string"),
        CHARARRAY("char[]"),
        COLOR("color"),
        IMAGE("image"),
        WIDTH("width"),
        HEIGHT("height"),
        LAYOUTPARAMS("layoutParams"),
        LAYOUTPARAMSWIDTHHEIGHT("layoutParamsWidthHeight");

        private final String value;
        ConvertType(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    public enum Unit {
        HEX("hex"),
        PERCENTAGE("percentage"),
        PIXEL("pixel"),
        RES("res"),
        ASSETS("assets");

        private final String value;
        Unit(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    public enum Switch {
        ON("on"),
        OFF("off");

        private final String value;
        Switch(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }
}
