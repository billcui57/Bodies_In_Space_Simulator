package Entities;


import World.Space;
import Entities.Entity;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

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

    public Spaceship(double x, double y, double velx, double vely, double mass, boolean fixed, Space world) {
        super(x, y, world);
        commandedTurnLeft=false;
        commandedTurnRight=false;
        commandedPropel=false;
        this.HEIGHT=5;
        this.WIDTH=5;
        orientAngle=180;
        this.velx = velx;
        this.vely = vely;
        this.mass = mass;
        this.fixed = fixed;
    }
    
    double MAXTHRUST=0.01;
    public boolean commandedTurnLeft;
    public boolean commandedTurnRight;
    public boolean commandedPropel;
    public double orientAngle;
  

    
    public void update() {
        this.testCollide();
        this.accDueToGravity();
        
        if(this.commandedTurnLeft){
            orientAngle-=0.1;
        }
        
        if(this.commandedTurnRight){
            orientAngle+=0.1;
        }
        
        if(this.commandedPropel){
            this.accx+=MAXTHRUST*Math.cos(orientAngle);
            this.accy+=MAXTHRUST*Math.sin(orientAngle);
        }
        
        
            velx += accx;
            vely += accy;

            x += velx;
            y += vely;
        
    }
    

    public void draw() {
        world.g.fillOval((int) x - WIDTH / 2, (int) y - HEIGHT / 2, WIDTH, HEIGHT);
       
        world.g.drawLine((int) x+ WIDTH / 4, (int) y + HEIGHT / 4, (int)(x - WIDTH*(Math.cos(orientAngle))+WIDTH / 4),  (int)(y - WIDTH*(Math.sin(orientAngle))+ HEIGHT / 4));
    }
    
}
