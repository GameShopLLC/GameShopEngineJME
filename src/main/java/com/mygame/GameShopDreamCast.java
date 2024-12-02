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
public class GameShopDreamCast implements GameShopRunnable {
    
    SimpleApplication app;
    GameShopLine gsl;
    
    float time;
    Vector3f increase;
    public GameShopDreamCast(SimpleApplication app, GameShopLine gsl){
    
        this.app = app;
        this.gsl = gsl;
        increase = gsl.a.add(gsl.b);
       // app.getInputManager().addListener(analogListener, "pick target");
    }


    @Override
    public void update(float tpf) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
        
        
        if (time > 0.5f){
       
            gsl.a = new Vector3f(gsl.a.add(increase));
            gsl.b = new Vector3f(gsl.b.add(increase));
            System.out.println(gsl);
            time = 0f;
        }
        time += tpf;
        
    }
    

}
