/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

/**
 *
 * @author lynden
 * 
 * An interface for Input
 */
public class GameShopInputInterface {
    
    SimpleApplication app;
    
    public GameShopInputInterface(SimpleApplication app){
    
        this.app = app;
        
        registerListener(dreamCastAnalogListener, "pick target");
        
    }
    
    public final void registerListener(AnalogListener al, String name){
    
        app.getInputManager().addListener(al, name);
    }
    
    private final AnalogListener dreamCastAnalogListener = new AnalogListener() {
    @Override
    public void onAnalog(String name, float intensity, float tpf) {
        
        
        if (name.equals("pick target")) {
         // Reset results list.
         CollisionResults results = new CollisionResults();
         // Aim the ray from camera location in camera direction
         // (assuming crosshairs in center of screen).
         Ray ray = new Ray(app.getCamera().getLocation(), app.getCamera().getDirection());
         // Collect intersections between ray and all nodes in results list.
         app.getRootNode().collideWith(ray, results);
         // Print the results so we see what is going on
         for (int i = 0; i < results.size(); i++) {
           // For each "hit", we know distance, impact point, geometry.
           float dist = results.getCollision(i).getDistance();
           Vector3f pt = results.getCollision(i).getContactPoint();
           String target = results.getCollision(i).getGeometry().getName();
           System.out.println("Selection #" + i + ": " + target + " at " + pt + ", " + dist + " WU away.");
         }
         // 5. Use the results -- we rotate the selected geometry.
         if (results.size() > 0) {
           // The closest result is the target that the player picked:
           Geometry target = results.getClosestCollision().getGeometry();
           // Here comes the action:
           if(target.getName().equals("OurMesh"))
             target.rotate(0, - intensity * 30, 0);
//           else if(target.getName().equals("OurMesh"))
//             target.rotate(0, intensity, 0);
         }
        } // else if ...
    }
  };
}
