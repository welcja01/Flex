package com.example.willflower.Flex;

import java.util.Random;

/**
 * Created by willflower on 11/28/17.
 */

public class Circle extends MainActivity{

    private int xCord = 0;
    private int yCord = 0;
    private int color = 0;

    Random ran = new Random();

    public Circle(int xCord, int yCord) {
            this.xCord = xCord;
            this.yCord = yCord;
            this.color = ran.nextInt(2);
    }

    public int getColor(){
        return this.color;
    }
    public int getxCord(){
        return this.xCord;
    }
    public int getyCord(){
        return  this.yCord;
    }
}




