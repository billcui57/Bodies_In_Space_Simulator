package Entities;

import World.Space;
import Entities.Entity;
import java.awt.Color;
import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author billc
 */
public class Body extends Entity {

    public Body(double x, double y, double velx, double vely, double mass, boolean fixed, Space world) {
        super(x, y, velx, vely, mass, fixed, world);
        this.WIDTH = 10;
        this.HEIGHT = 10;
    }

    @Override
    public void update() {
        this.testCollide();
        this.accDueToGravity();

        velx += accx;
        vely += accy;

        x += velx;
        y += vely;
    }

    @Override
    public void draw() {
        world.g.fillOval((int) x - WIDTH / 2, (int) y - HEIGHT / 2, WIDTH, HEIGHT);

    }

}
