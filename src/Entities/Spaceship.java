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
public class Spaceship extends Entity {

    public Spaceship(double x, double y, double velx, double vely, double mass, boolean fixed, Space world) {
        super(x, y, velx, vely, mass, fixed, world);
        commandedTurnLeft = false;
        commandedTurnRight = false;
        commandedPropel = false;
        this.HEIGHT = 5;
        this.WIDTH = 5;
        orientAngle = 180;
    }

    double MAXTHRUST = 0.05;
    public boolean commandedTurnLeft;
    public boolean commandedTurnRight;
    public boolean commandedPropel;
    public boolean commandedFire;
    public double orientAngle;
    double MUZZLE_VEL=5;

    @Override
    public void update() {
        this.testCollide();
        this.accDueToGravity();

        if (this.commandedTurnLeft) {
            orientAngle -= 0.1;
        }

        if (this.commandedTurnRight) {
            orientAngle += 0.1;
        }

        if (this.commandedPropel) {
            this.accx += MAXTHRUST * Math.cos(orientAngle);
            this.accy += MAXTHRUST * Math.sin(orientAngle);
        }

        if (this.commandedFire) {
            double torpedoVelx;
            double torpedoVely;
            if((this.velx==0)&&(this.vely==0)){
                torpedoVelx=Math.abs(this.velx+MUZZLE_VEL)*Math.cos(orientAngle);
                torpedoVely=Math.abs(this.vely+MUZZLE_VEL)*Math.sin(orientAngle);
            }else{
                torpedoVelx=Math.abs(this.velx+Math.signum(velx)*MUZZLE_VEL)*Math.cos(orientAngle);
                torpedoVely=Math.abs(this.vely+Math.signum(vely)*MUZZLE_VEL)*Math.sin(orientAngle);
            }
            world.addEntity(new Torpedo(this.x+20*Math.cos(orientAngle),this.y+20*Math.sin(orientAngle) , torpedoVelx, torpedoVely, 10, false, this.world));
        }

        velx += accx;
        vely += accy;

        x += velx;
        y += vely;

    }

    public void startTurningLeft() {
        commandedTurnLeft = true;
    }

    public void stopTurningLeft() {
        commandedTurnLeft = false;
    }

    public void startTurningRight() {
        commandedTurnRight = true;
    }

    public void stopTurningRight() {
        commandedTurnRight = false;
    }

    public void startPropel() {
        commandedPropel = true;
    }

    public void stopPropel() {
        commandedPropel = false;
    }

    public void startFireTorpedo() {
        commandedFire = true;
    }

    public void stopFireTorpedo() {
        commandedFire = false;
    }

    @Override
    public void draw() {

        world.g.fillOval((int) x - WIDTH / 2, (int) y - HEIGHT / 2, WIDTH, HEIGHT);

        world.g.drawLine((int) x + WIDTH / 4, (int) y + HEIGHT / 4, (int) (x - WIDTH * (Math.cos(orientAngle)) + WIDTH / 4), (int) (y - WIDTH * (Math.sin(orientAngle)) + HEIGHT / 4));
    }

}
