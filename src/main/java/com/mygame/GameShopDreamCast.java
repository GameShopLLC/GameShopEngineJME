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
 */
public class GameShopDreamCast implements Runnable {
    
    SimpleApplication app;
    GameShopLine gsl;
    
    public GameShopDreamCast(SimpleApplication app, GameShopLine gsl){
    
        this.app = app;
        this.gsl = gsl;
        
       // app.getInputManager().addListener(analogListener, "pick target");
    }

    @Override
    public void run() {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
       while (true){
       
           
       }
    }
    

}
