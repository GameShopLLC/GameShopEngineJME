/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import com.jme3.math.Vector3f;

/**
 *
 * @author lynden
 * 
 * This is a Dual Normal Vector
 * 
 * Nicknamed GameShopArrow
 */
public class GameShopArrow {
    
    public Vector3f a;
    public Vector3f b;
    
    public GameShopArrow(Vector3f a, Vector3f b){
    
        this.a = new Vector3f(a);
        this.b = new Vector3f(b);
        
    }
}
