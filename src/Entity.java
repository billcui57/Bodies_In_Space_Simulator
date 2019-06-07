
import javafx.scene.shape.Circle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author billc
 */
public abstract class Entity {
    double mass;
    double x;
    double y;
    double velx = 0;
    double vely = 0;
    double accx = 0;
    double accy = 0;
    int WIDTH ;
    int HEIGHT ;
    final int GFIELD = 20;
    Space world;
    

    public Entity(double x, double y, Space world) {
        this.x = x;
        this.y = y;
        this.world = world;
    }
    
    
    
    public abstract void update();
    public abstract void draw();
    
}
