package com.blackout;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;

public class MazeRestClient {
    
    static class ResourceUrlTemplate {
        static final String INIT = "http://www.epdeveloperchallenge.com/api/init";
        static final String MOVE = "http://www.epdeveloperchallenge.com/api/move?mazeGuid=%s&direction=%s";
        static final String JUMP = "http://www.epdeveloperchallenge.com/api/jump?mazeGuid=%s&x=%d&y=%d";
    }
    
    public static CurrentCell postInit() throws UnirestException {
        CurrentCell startCell = post(ResourceUrlTemplate.INIT);
        return startCell;
    }
    
    private final String mazeGuid;
   
    public MazeRestClient(String mazeGuid) {
        this.mazeGuid = mazeGuid;
    }

    public String getMazeGuid() {
        return mazeGuid;
    }
    
    private static CurrentCell post(String url) throws UnirestException {
        HttpRequestWithBody req = Unirest.post(url);
        HttpResponse<String> s = req.asString();
        String responseBodyJsonStr = s.getBody();
        CurrentCell cell = JsonUtil.parseMazeResponseJsonStr(responseBodyJsonStr);
        return cell;
    }
    
    public CurrentCell postMove(String direction) throws UnirestException {
        String url = String.format(ResourceUrlTemplate.MOVE, mazeGuid, direction);
        CurrentCell currentCell = post(url);
        return currentCell;
    }
    
    public CurrentCell postJump(int x, int y) throws UnirestException {
        String url = String.format(ResourceUrlTemplate.JUMP, mazeGuid, x, y);
        CurrentCell currentCell = post(url);
        return currentCell;
    }
}
