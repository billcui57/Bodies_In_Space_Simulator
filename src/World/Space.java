package World;

import Entities.Body;
import Entities.Entity;
import Entities.Spaceship;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author billc
 */
public class Space extends javax.swing.JPanel {

    /**
     * Creates new form Space
     */
    public Space() {
        initComponents();

        this.setBackground(Color.BLACK);
        entities.add(sun);
        entities.add(player1);
        entities.add(player2);

    }
    Body sun = new Body(400, 400, 0, 0, 20, true, this);
    Spaceship player1 = new Spaceship(200, 200, 0, 0, 100, false, this);
    Spaceship player2 = new Spaceship(600, 600, 0, 0, 100, false, this);

    ArrayList<Entity> entities = new ArrayList<Entity>();
    ArrayList<Point> coords = new ArrayList<Point>();
    boolean showLines = false;
    boolean tracePaths = false;
    boolean updateBodies = true;

    public Graphics g;

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity newEntity) {
        this.entities.add(newEntity);
    }

    public void rmEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public void rmEntity(int index) {
        this.entities.remove(index);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;

        this.requestFocus();

        g.setColor(Color.WHITE);

        if (pressing) {
            g.drawLine((int) beginx, (int) beginy, this.getMousePosition().x, this.getMousePosition().y);

        }

        try {
            for (int i = 0; i < entities.size(); i++) {
                if (updateBodies) {
                    this.entities.get(i).update();
                }
                this.entities.get(i).draw();

            }
        } catch (IndexOutOfBoundsException e) {

        }

        if (tracePaths) {
            for (int i = 0; i < entities.size(); i++) {
                coords.add(entities.get(i).getPoint());
            }

            for (int i = 0; i < coords.size(); i++) {
                g.drawOval(coords.get(i).x, coords.get(i).y, 1, 1);
            }
        } else {
            coords.clear();
        }

        if (showLines) {
            for (int i = 0; i < entities.size(); i++) {
                for (int j = i + 1; j < entities.size(); j++) {
                    g.drawLine((int) this.entities.get(i).getPoint().x, (int) this.entities.get(i).getPoint().y, (int) this.entities.get(j).getPoint().x, (int) this.entities.get(j).getPoint().y);

                    g.drawString(String.valueOf((int) this.entities.get(i).distanceFrom(this.entities.get(j))), (int) (this.entities.get(j).getPoint().x + (this.entities.get(i).getPoint().x - this.entities.get(j).getPoint().x) / 2), (int) (this.entities.get(j).getPoint().y + (this.entities.get(i).getPoint().y - this.entities.get(j).getPoint().y) / 2));
                }

            }
        }
        System.out.println(entities);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(800, 800));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    double beginx;
    double beginy;
    double endx;
    double endy;

    boolean pressing;


    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:

        pressing = true;
        beginx = evt.getX();
        beginy = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:

        pressing = false;
        endx = evt.getX();
        endy = evt.getY();
        double velx = (beginx - endx) / 5;
        double vely = (beginy - endy) / 5;

        double mass = -1000000;
        double vel = -1000000;
        try {
            mass = Double.parseDouble(JOptionPane.showInputDialog(this, "Specify Mass", 5));
            vel = Double.parseDouble(JOptionPane.showInputDialog(this, "Specify Positive Magnitude of Velocity", Math.sqrt(Math.pow(velx, 2) + Math.pow(vely, 2))));
        } catch (NullPointerException e) {

        }
        if ((vel != -1000000) && (mass != -1000000)) {
            if (vel != 0) {
                //remove ambiguity for first and third quadrant for arctan
                if (beginx < endx) {
                    velx = vel * Math.cos(Math.PI + Math.atan((beginy - endy) / (beginx - endx)));
                    vely = vel * Math.sin(Math.PI + Math.atan((beginy - endy) / (beginx - endx)));
                } else {
                    velx = vel * Math.cos(Math.atan((beginy - endy) / (beginx - endx)));
                    vely = vel * Math.sin(Math.atan((beginy - endy) / (beginx - endx)));
                }

            } else {
                velx = 0;
                vely = 0;
            }

            this.entities.add(new Body(beginx, beginy, velx, vely, mass, false, this));
        }


    }//GEN-LAST:event_formMouseReleased


    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        switch (evt.getKeyChar()) {
            case 'a':
                player1.startTurningLeft();
                break;
            case 'd':
                player1.startTurningRight();
                break;
            case 'w':
                player1.startPropel();
                break;
            case ' ':
                player1.startFireTorpedo();
                break;
            case 'j':
                player2.startTurningLeft();
                break;
            case 'l':
                player2.startTurningRight();
                break;
            case 'i':
                player2.startPropel();
                break;
        }

    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        switch (evt.getKeyChar()) {
            case 'a':
                player1.stopTurningLeft();
                break;
            case 'd':
                player1.stopTurningRight();
                break;
            case 'w':
                player1.stopPropel();
                break;
            case ' ':
                player1.stopFireTorpedo();
                break;
            case 'j':
                player2.stopTurningLeft();
                break;
            case 'l':
                player2.stopTurningRight();
                break;
            case 'i':
                player2.stopPropel();
                break;
        }
    }//GEN-LAST:event_formKeyReleased

    Timer t1;

    public void timer() {
        t1 = new Timer(20, new TimerListener());
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Space.super.repaint();

        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
