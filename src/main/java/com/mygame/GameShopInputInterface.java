/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

/**
 *
 * @author lynden
 * 
 * An interface for Input
 */
public class GameShopInputInterface implements GameShopRunnable {
    
    SimpleApplication app;
    float time;
    boolean clickOn;
    public GameShopInputInterface(SimpleApplication app){
    
        this.app = app;
        clickOn = false;
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
        //CollisionResults results = new CollisionResults();
        // Convert screen click to 3d position
        Vector2f click2d =  app.getInputManager().getCursorPosition();
        Vector3f click3d = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
        //Vector3f dir = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0.1f).subtractLocal(click3d).normalizeLocal();
        Vector3f dir = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0.1f).clone();
        
        if (clickOn){
         app.getStateManager().getState(GameShopExecutorPool.class).addGameShopRunnables(0, new GameShopRunnable[] { new GameShopDreamCast(app, new GameShopLine(click3d, dir))});
        
         clickOn = false;
        }
      } // else if ...
    }
  };

    @Override
    public void update(float tpf) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
        if (time > 0.1f){
        
            clickOn = true;
            time = 0f;
        }
        time += tpf;
    }
}
