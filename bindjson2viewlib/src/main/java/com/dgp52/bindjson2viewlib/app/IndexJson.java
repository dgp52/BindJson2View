package com.dgp52.bindjson2viewlib.app;

import com.dgp52.bindjson2viewlib.logexception.ServiceException;
import com.dgp52.bindjson2viewlib.util.Keyword;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexJson {

    private static Map<String, ArrayList<Integer>> mapTagIndex = new HashMap<>();
    private static Map<Integer, JSONObject> mapIndexMethod = new HashMap<>();

    public static boolean Index(String jsonString) {
        int methodCounter = 0;
        boolean isComplete = true;
        mapTagIndex.clear();
        mapIndexMethod.clear();
        try {
            JSONArray binders = new JSONObject(jsonString).getJSONArray(Keyword.BINDERS);
            for(int i=0;i<binders.length();i++){
                JSONArray methods = binders.getJSONObject(i).getJSONArray(Keyword.METHODS);
                List<Integer> methodIndices = new ArrayList<>();
                for(int j=0;j<methods.length();j++){
                    mapIndexMethod.put(methodCounter,methods.getJSONObject(j));
                    methodIndices.add(methodCounter);
                    methodCounter++;
                }
                JSONArray tags = binders.getJSONObject(i).getJSONArray(Keyword.TAGS);
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
        return isComplete;
    }

    public static JSONArray getMethods (String tag) {
        JSONArray methods = new JSONArray();
        ArrayList<Integer> indices = mapTagIndex.get(tag);
        if(indices==null)
            return null;
        for(int i=0; i<indices.size(); i++) {
            methods.put(mapIndexMethod.get(indices.get(i)));
        }
        return methods;
    }
}
