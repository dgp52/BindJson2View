package com.dgp52.bindjson2viewlib.app;

import java.util.ArrayList;

public class JSONMethod {
    private String name;
    private ArrayList<String> params;
    private ArrayList<Argument> arguments;
    private boolean switchFlag;

    public JSONMethod(){};

    public JSONMethod(String name, boolean switchFlag, ArrayList<String> params, ArrayList<Argument> arguments){
        this.name = name;
        this.switchFlag = switchFlag;
        this.params = params;
        this.arguments = arguments;
    }

    public ArrayList<Argument> getArguments(){
        return arguments;
    }

    public String getName(){
        return name;
    }

    public boolean getSwitchFlag() {
        return switchFlag;
    }

    public ArrayList<String> getParams(){
        return params;
    }

    public class Argument {
        private String name;
        private ArrayList<String> values;

        public Argument(String name, ArrayList<String> values){
            this.name = name;
            this.values = values;
        }

        public String getName(){
            return name;
        }

        public ArrayList<String> getValues(){
            return values;
        }
    }
}
