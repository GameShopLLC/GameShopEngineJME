/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author lynden
 * 
 * Will Contain All The CurrencyMeshes
 */
public class GameShopBank extends BaseAppState {

    SimpleApplication app;
    GameShopCurrencyMesh[] gscm;
    
    Vector2f screenDimensions;
    
    public GameShopBank (SimpleApplication app){
    
        this.app = app;
        this.gscm = new GameShopCurrencyMesh[1];
                GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
  DisplayMode[] modes = device.getDisplayModes();
  int i=0; // note: there are usually several, let's pick the first
  //settings.setResolution(modes[i].getWidth(),modes[i].getHeight());
  
        screenDimensions = new Vector2f(modes[i].getWidth(),modes[i].getHeight());
        
    }
    
    public void onClick(Vector3f collision, int index){
    
        Vector2f size = new Vector2f(gscm[index].atms.layer.width, gscm[index].atms.layer.height);
        Vector3f startPoint = new Vector3f(gscm[index].currencyLines[0].points[0]);
        Vector3f endPoint = new Vector3f(gscm[index].currencyLines[3].points[3]);
        
        Vector3f ratio = new Vector3f((startPoint.x + collision.x) / endPoint.x, (startPoint.y + collision.y) /endPoint.y, (startPoint.z + collision.z) /endPoint.z);
        
        System.out.println("ratio " + ratio);
        System.out.println(size);
//        Vector2f viewPort2d = new Vector2f(app.getCamera().getScreenCoordinates(ratio).x, app.getCamera().getScreenCoordinates(ratio).y);
//        System.out.println("ViewPort2d " + viewPort2d);
//        Vector
        gscm[index].atms.addDrawCalls(gscm[index].atms.drawCalls.length - 1, new String[]{"drawCircle " + (short)((startPoint.x + ratio.x) * size.x)/2   + " " +  (short)((startPoint.y + ratio.y) * size.y)/2 + " " + "25 Color 0 255 0 255"});
    
        System.out.println(Arrays.toString(gscm[index].atms.drawCalls));
        gscm[index].atms.process();
        gscm[index].mat.setTexture("ColorMap", gscm[index].atms.makeTexture());
        
    }
    
    public void addGSCMs(int index, GameShopCurrencyMesh[] gscm){

        boolean start = this.gscm[0] != null;

        ArrayList<GameShopCurrencyMesh> newGSCMs = new ArrayList<>(Arrays.asList(gscm));

        ArrayList<GameShopCurrencyMesh> oldGSCMs = null;

        if (start) {
            oldGSCMs = new ArrayList<>(Arrays.asList(this.gscm));
        }

        ArrayList<GameShopCurrencyMesh> lowerGSCMs = new ArrayList<>();

        ArrayList<GameShopCurrencyMesh> higherGSCMs = new ArrayList<>();

        if (start) {
            for (int i = 0; i < index; i++) {
                lowerGSCMs.add(oldGSCMs.get(i));
            }

            for (int i = index; i < oldGSCMs.size(); i++) {
                higherGSCMs.add(oldGSCMs.get(i));
            }
            this.gscm = new GameShopCurrencyMesh[(oldGSCMs.size()) + (newGSCMs.size())];

        } else {
            this.gscm = new GameShopCurrencyMesh[newGSCMs.size()];
        }



        int i = 0;
        if (start) {
            for (GameShopCurrencyMesh dc : lowerGSCMs) {


                this.gscm[i] = dc;
                i++;
            }
        }
//        System.out.println(Arrays.toGameShopCurrencyMesh(oldGSCMs.toArray()));
  //      System.out.println(Arrays.toGameShopCurrencyMesh(newGSCMs.toArray()));

        for (GameShopCurrencyMesh dc: newGSCMs){

            this.gscm[i] = dc;
            i++;
        }

        if (start) {
            for (GameShopCurrencyMesh dc : higherGSCMs) {

                this.gscm[i] = dc;
                i++;
            }
        }
    
    }
    @Override
    protected void initialize(Application aplctn) {
      //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void cleanup(Application aplctn) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void onEnable() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void onDisable() {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
