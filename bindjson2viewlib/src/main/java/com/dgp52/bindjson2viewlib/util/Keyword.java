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
        PARAMS("params"),
        VALUES("values"),
        SWITCH("switch"),
        ARGUMENTS("arguments");

        private final String value;
        JSONProperty(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    public enum Convert {
        INT("int"),
        BOOLEAN("boolean"),
        BYTE("byte"),
        CHAR("char"),
        SHORT("short"),
        LONG("long"),
        FLOAT("float"),
        DOUBLE("double"),
        CHARSEQUENCE("charSequence"),
        STRING("string"),
        CHARARRAY("char[]"),
        LAYOUTPARAMS("layoutParams"),
        TOCHARARRAY("toCharArray"),
        PERCENTAGEWIDTH("percentageWidth"),
        PERCENTAGEHEIGHT("percentageHeight"),
        LAYOUTPARAMSWIDTHHEIGHTPIXEL("layoutParamsWidthHeightPixel"),
        LAYOUTPARAMSWIDTHHEIGHTPERCENTAGE("layoutParamsWidthHeightPercentage"),
        RESOURCEID("resourceId"),
        PARSECOLOR("parseColor");

        private final String value;
        Convert(String value) {
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }
}
