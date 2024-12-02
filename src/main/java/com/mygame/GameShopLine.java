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
 * The Shortest Path Between Two Points
 * 
 */
public class GameShopLine {
    
    public Vector3f a;
    public Vector3f b;
    
    public GameShopLine(Vector3f a, Vector3f b){
    
        this.a = new Vector3f(a);
        this.b = new Vector3f(b);
        
    }
}
