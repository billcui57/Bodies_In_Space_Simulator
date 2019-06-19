package Entities;


import World.Space;
import Entities.Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author billc
 */
public class Spaceship extends Entity{

    public Spaceship(double x, double y, Space world) {
        super(x, y, world);
        commandedTurnLeft=false;
        commandedTurnRight=false;
        commandedPropel=false;
    }
    
    boolean commandedTurnLeft;
    boolean commandedTurnRight;
    boolean commandedPropel;

    @Override
    public void update() {
        this.testCollide();
         this.accDueToGravity();
         
         
    }

    @Override
    public void draw() {
        
    }
    
}
