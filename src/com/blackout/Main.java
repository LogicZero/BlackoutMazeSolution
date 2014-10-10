package com.blackout;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;

import com.mashape.unirest.http.exceptions.UnirestException;


public class Main {

    static CurrentCell startMaze() throws UnirestException {
        CurrentCell startCell = MazeRestClient.postInit();
        return startCell;
    }
    
    public static void main(String[] args) throws JSONException, UnirestException, FileNotFoundException, UnsupportedEncodingException {
        
        for(int i=0; true; i++) {
            String fileName = String.format("/Users/bgurung/git/BlackMaze/infos/run_%d_info.txt", i); 
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            DummyMazeRunner dummy = new DummyMazeRunner(startMaze(), writer);
            dummy.run();
            writer.flush();
            writer.close();
        }
    }

}
