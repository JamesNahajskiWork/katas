package org.example.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin("http://localhost:3000")
public class MinesweeperController {


    @PostMapping("/sweep")
    public int[][] saveAssignments(@RequestBody HashMap<String, Integer> xAndY) {
        System.out.println(xAndY.get("x"));
        System.out.println(xAndY.get("y"));
        return new int[][] {{-2,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1}};
    }

    @GetMapping("/initial-state")
    public int[][] saveAssignments() {
        return new int[][] {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1}};
    }
}
