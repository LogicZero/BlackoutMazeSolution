package com.blackout;

import java.io.PrintWriter;
import java.util.Stack;

import org.json.JSONException;

import com.mashape.unirest.http.exceptions.UnirestException;

public class DummyMazeRunner {
    
    private final MazeRestClient mazeRestClient;
    private final CurrentCell startCell;
    private final Stack<Location> jumpbackLocations;
    private int moves;
    private int jumpbacks;
    private CurrentCell endCell;
    
    private PrintWriter logger;

    public DummyMazeRunner(CurrentCell startCell, PrintWriter logger) throws JSONException, UnirestException {
        this.startCell = startCell;
        mazeRestClient = new MazeRestClient(startCell.getMazeGuid());
        jumpbackLocations = new Stack<>();
        moves = 0;
        jumpbacks = 0;
        this.logger = logger;
    }
    
    private void log(String msg) {
        logger.println(msg);
        System.out.println(msg);
    }

    public void run() throws UnirestException {
        CurrentCell currentCell = startCell;
        log("String run from cell - " + currentCell);
        while(true) {
            Direction direction = decideMove(currentCell);
            if(direction != null) {
                currentCell = move(direction);
                if(arrivedAtEnd(currentCell)) {
                    return;
                }
             } else {
                currentCell = jumpback();
            }
        }
    }
    
    private boolean arrivedAtEnd(CurrentCell currentCell) {
        boolean atEnd = currentCell.isAtEnd();
        if(atEnd) {
            endCell = currentCell;
            log("Arrived at the end!!! End Cell - " + currentCell);
        }
        return atEnd;
    }
    
    private CurrentCell move(Direction direction) throws UnirestException {
        CurrentCell currentCell = mazeRestClient.postMove(direction.name());
        moves++;
        log(String.format("Move %d: Moved towards %s", moves, direction));
        return currentCell;
    }
    
    private CurrentCell jumpback() throws UnirestException {
        Location loc = jumpbackLocations.pop();
        jumpbacks++;
        log(String.format("Jumpback %d: Jumped back to location - %s", moves, loc));
        return mazeRestClient.postJump(loc.getX(), loc.getY());
    }
    
    private void pushJumpbackLocation(CurrentCell cell) {
        jumpbackLocations.push(new Location(cell.getX(), cell.getY()));
    }
    
    private Direction decideMove(CurrentCell cell) {
        log(String.format("(%d, %d) [E:%s, W:%s, N:%s, S:%s]", cell.getX(), cell.getY(), cell.getEast(), cell.getWest(), cell.getNorth(), cell.getSouth()));
        
        RouteState eRouteState = RouteState.valueOf(cell.getEast());
        RouteState wRouteState = RouteState.valueOf(cell.getWest());
        RouteState nRouteState = RouteState.valueOf(cell.getNorth());
        RouteState sRouteState = RouteState.valueOf(cell.getSouth());
        
        Direction direction = null;
        
        if (eRouteState == RouteState.UNEXPLORED) {
            direction = Direction.EAST;
        }
        
        if (wRouteState == RouteState.UNEXPLORED) {
            if (direction != null) {
                pushJumpbackLocation(cell);
                return direction;
            }
            direction = Direction.WEST;
        }
        
        if (nRouteState == RouteState.UNEXPLORED) {
            if (direction != null) {
                pushJumpbackLocation(cell);
                return direction;
            }
            direction = Direction.NORTH;
        }
        
        if (sRouteState == RouteState.UNEXPLORED) {
            if (direction != null) {
                pushJumpbackLocation(cell);
                return direction;
            }
            direction = Direction.SOUTH;
        }
        return direction;
    }
    
    public String runDetail() {
        return new StringBuilder("Start Cell: " + startCell)
                    .append("End Location: " + endCell)
                    .append("Moves: " + moves)
                    .append("Jumpbacks: " + jumpbacks)
                    .toString();
    }
}
