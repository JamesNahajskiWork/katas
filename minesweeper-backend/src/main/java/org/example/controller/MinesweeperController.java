package org.example.controller;

import org.example.service.MinesweeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin("http://localhost:3000")
public class MinesweeperController {

    @Autowired
    MinesweeperService minesweeperService;

    @PostMapping("/sweep")
    public int[][] saveAssignments(@RequestBody HashMap<String, Integer> xAndY) {
//        System.out.println("x:" + xAndY.get("x"));
//        System.out.println("y:" + xAndY.get("y"));
        minesweeperService.playMove(xAndY.get("x"),xAndY.get("y"));
        return minesweeperService.getGameState();
    }

    @GetMapping("/initial-state")
    public int[][] saveAssignments() {
        minesweeperService.resetBoard();
        return minesweeperService.getGameState();
    }
}
