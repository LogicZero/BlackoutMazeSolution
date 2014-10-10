package com.blackout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtil {
    
    public static CurrentCell parseMazeResponseJsonStr(String mazeResponseJsonStr) {
        JsonElement jsonElement = new JsonParser().parse(mazeResponseJsonStr);
        JsonObject containerJsonObj = jsonElement.getAsJsonObject();
        JsonObject currentCellJsonObj = containerJsonObj.getAsJsonObject("currentCell");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        CurrentCell currentCellPojo = gson.fromJson(currentCellJsonObj, CurrentCell.class);
        return currentCellPojo;
    }
}
