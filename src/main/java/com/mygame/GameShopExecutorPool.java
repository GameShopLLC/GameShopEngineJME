/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author lynden
 */
public class GameShopExecutorPool extends BaseAppState {

    SimpleApplication app;
    GameShopRunnable[] gsr;
    
    public GameShopExecutorPool(SimpleApplication app){
    
         this.app = app;
        this.gsr = new GameShopRunnable[1];
    }
    @Override
    protected void initialize(Application app) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
       
        
    }

    @Override
    protected void cleanup(Application app) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void onEnable() {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void onDisable() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void addGameShopRunnables(int index, GameShopRunnable[] gsrs){
    
       // public void addDrawCalls(int index, String[] drawCalls){

        boolean start = this.gsr[0] != null;

        ArrayList<GameShopRunnable> newGSRs = new ArrayList<>(Arrays.asList(gsrs));

        ArrayList<GameShopRunnable> oldGSRs = null;

        if (start) {
            oldGSRs = new ArrayList<>(Arrays.asList(this.gsr));
        }

        ArrayList<GameShopRunnable> lowerGSRs = new ArrayList<>();

        ArrayList<GameShopRunnable> higherGSRs = new ArrayList<>();

        if (start) {
            for (int i = 0; i < index; i++) {
                lowerGSRs.add(oldGSRs.get(i));
            }

            for (int i = index; i < oldGSRs.size(); i++) {
                higherGSRs.add(oldGSRs.get(i));
            }
            this.gsr = new GameShopRunnable[(oldGSRs.size()) + (newGSRs.size())];

        } else {
            this.gsr = new GameShopRunnable[newGSRs.size()];
        }



        int i = 0;
        if (start) {
            for (GameShopRunnable dc : lowerGSRs) {


                this.gsr[i] = dc;
                i++;
            }
        }
//        System.out.println(Arrays.toString(oldDrawCalls.toArray()));
  //      System.out.println(Arrays.toString(newDrawCalls.toArray()));

        for (GameShopRunnable dc: newGSRs){

            this.gsr[i] = dc;
            i++;
        }

        if (start) {
            for (GameShopRunnable dc : higherGSRs) {

                this.gsr[i] = dc;
                i++;
            }
        }
        //newDrawCalls.addAll(index, drawCalls);
        //        String[] temp = new String[this.drawCalls.length];
//
//        int i = 0;
//        for (String dc: this.drawCalls){
//
//            temp[i] = dc;
//            i++;
//        }
//
//        this.drawCalls = new String[(this.drawCalls.length) + drawCalls.length];
//
//        System.out.println("dc " + this.drawCalls.length);
////        i = 0;
////        int j = 0;
//
//        for (i = 0; i < index; i++){
//
//            this.drawCalls[i] = temp[i];
//        }
//
//        for (i = index; i < index + drawCalls.length; i++){
//
//            this.drawCalls[i] = drawCalls[i - index];
//        }
//
//        for (i = index + drawCalls.length; i < this.drawCalls.length; i++){
//
//            this.drawCalls[i] = temp[i - (drawCalls.length)];
//        }

   // }
    }
    
    @Override
    public void update(float tpf) {
    
        if (this.gsr[0] != null){
        for (GameShopRunnable gsr: this.gsr){
        
            gsr.update(tpf);
        }
        }
    }
    
}
