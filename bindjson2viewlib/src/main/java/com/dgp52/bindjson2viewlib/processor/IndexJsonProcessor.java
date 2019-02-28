package com.dgp52.bindjson2viewlib.processor;

import com.dgp52.bindjson2viewlib.app.JSONMethod;
import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.util.Keyword;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexJsonProcessor {

    private static Map<String, ArrayList<Integer>> mapTagIndex = new HashMap<>();
    private static Map<Integer, JSONMethod> mapIndexMethod = new HashMap<>();

    public static boolean Index(String jsonString) {
        int methodCounter = 0;
        boolean isComplete = true;
        mapTagIndex.clear();
        mapIndexMethod.clear();
        try {
            JSONArray binders = new JSONObject(jsonString).getJSONArray(Keyword.JSONProperty.BINDERS.getValue());
            for(int i=0;i<binders.length();i++){
                JSONArray methods = binders.getJSONObject(i).getJSONArray(Keyword.JSONProperty.METHODS.getValue());
                List<Integer> methodIndices = new ArrayList<>();
                for(int j=0;j<methods.length();j++){
                    JSONObject jsonMethod = methods.getJSONObject(j);
                    //Switch
                    boolean switchFlag = jsonMethod.has(Keyword.JSONProperty.SWITCH.getValue()) ?  jsonMethod.getBoolean(Keyword.JSONProperty.SWITCH.getValue()) : true;
                    if(!switchFlag)
                        continue;
                    JSONArray jsonParams = jsonMethod.getJSONArray(Keyword.JSONProperty.PARAMS.getValue());
                    //Name
                    String methodName = jsonMethod.getString(Keyword.JSONProperty.NAME.getValue());
                    //Params
                    ArrayList<String> params = new ArrayList<>();
                    for(int p=0; p<jsonParams.length();p++){
                        params.add(jsonParams.getString(p));
                    }
                    //Arguments
                    JSONArray jsonArguments = jsonMethod.getJSONArray(Keyword.JSONProperty.ARGUMENTS.getValue());
                    ArrayList<JSONMethod.Argument> arguments = new ArrayList<>();
                    for(int a=0; a < jsonArguments.length();a++) {
                        String name = jsonArguments.getJSONObject(a).getString(Keyword.JSONProperty.NAME.getValue());
                        JSONArray jsonValues = jsonArguments.getJSONObject(a).getJSONArray(Keyword.JSONProperty.VALUES.getValue());
                        ArrayList<String> values = new ArrayList<>();
                        for(int v=0; v < jsonValues.length(); v++){
                            values.add(jsonValues.getString(v));
                        }
                        arguments.add(new JSONMethod().new Argument(name,values));
                    }
                    JSONMethod method = new JSONMethod(methodName, switchFlag, params, arguments);
                    mapIndexMethod.put(methodCounter,method);
                    methodIndices.add(methodCounter);
                    methodCounter++;
                }
                JSONArray tags = binders.getJSONObject(i).getJSONArray(Keyword.JSONProperty.TAGS.getValue());
                for(int t=0;t<tags.length();t++){
                    if(mapTagIndex.containsKey(tags.getString(t))){
                        mapTagIndex.get(tags.getString(t)).addAll(methodIndices);
                    } else {
                        mapTagIndex.put(tags.getString(t), new ArrayList<>(methodIndices));
                    }
                }
            }
        } catch (Exception e) {
            isComplete = false;
            ServiceException.logE(e);
        }
        if(isComplete)
            ServiceException.logI("Indexing successful");
        return isComplete;
    }

    public static ArrayList<JSONMethod> getMethods (String tag) {
        ArrayList<JSONMethod> methods = new ArrayList<>();
        ArrayList<Integer> indices = mapTagIndex.get(tag);
        if(indices==null)
            return null;
        for(int i=0; i<indices.size(); i++) {
            methods.add(mapIndexMethod.get(indices.get(i)));
        }
        return methods;
    }
}
