
import java.awt.Color;



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
boolean fixed;

    public Body(double x, double y, double velx, double vely, double mass, boolean fixed, Space world) {
        super(x, y, world);
        this.velx = velx;
        this.vely = vely;
        this.WIDTH = 10;
        this.HEIGHT = 10;
        this.mass = mass;
        this.fixed = fixed;

    }

    public double distanceFrom(Body other) {
        //System.out.println(x + "\t" +other.x + "\t" +other.y+ "\t"+Math.pow(x-other.x, 2)+ "\t"+Math.pow(y-other.y, 2));
        return (Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2)));

    }

    public void update() {
        accx = 0;
        accy = 0;
        if (!fixed) {
            for (int i = 0; i < world.bodies.size(); i++) {
                if (!world.bodies.get(i).equals(this)) {
                    Body otherBody = world.bodies.get(i);
                    double GMm = (GFIELD * mass * otherBody.mass);
                    double force = GMm / Math.pow(this.distanceFrom(otherBody), 2);

                    double forcex = force * ((otherBody.x - this.x) / distanceFrom(otherBody));
                    double forcey = force * ((otherBody.y - this.y) / distanceFrom(otherBody));

                    accx += forcex / mass;
                    accy += forcey / mass;

                }
            }
            //System.out.println(x+"\t"+y+"\t"+accx + "\t" +accy);

            velx += accx;
            vely += accy;

            x += velx;
            y += vely;
        }
    }

    public void draw() {
    
        world.g.fillOval((int) x - WIDTH / 2, (int) y - HEIGHT / 2, WIDTH, HEIGHT);
       
      
    }

}
