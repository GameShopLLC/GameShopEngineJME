/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.FastMath;
import com.jme3.math.Ray;
import com.jme3.math.Triangle;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

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
    
    Vector2f screenDimensions;
    public GameShopInputInterface(SimpleApplication app){
    
        this.app = app;
        clickOn = false;
           GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
  DisplayMode[] modes = device.getDisplayModes();
  int i=0; // note: there are usually several, let's pick the first
  //settings.setResolution(modes[i].getWidth(),modes[i].getHeight());
  
        screenDimensions = new Vector2f(modes[i].getWidth(),modes[i].getHeight());
        
        //registerListener(dreamCastAnalogListener, "pick target");
        app.getInputManager().addListener(actionListener, "Shoot");
    }
    
    public final void registerListener(AnalogListener al, String name){
    
        app.getInputManager().addListener(al, name);
    }
    
    /** Defining the "Shoot" action: Determine what was hit and how to respond. */
  final private ActionListener actionListener = new ActionListener() {
    @Override
    public void onAction(String name, boolean keyPressed, float tpf) {
      if (name.equals("Shoot") && !keyPressed) {
        // 1. Reset results list.
        CollisionResults results = new CollisionResults();
        
         Vector2f click2d =  app.getInputManager().getCursorPosition();
        
        //Vector2f viewPort2d = new Vector2f(((click2d.x / screenDimensions.x) - 0.5f) * 2 , ((click2d.y/ screenDimensions.y) - 0.5f) * 2);
      //  Vector3f worldCoordinates = app.getCamera().getWorldCoordinates(new Vector2f(viewPort2d.x, viewPort2d.y), 0f).clone();// .getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).normalizeLocal().clone();
       
        Vector3f start = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f);
        Vector3f end = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtract(start).normalizeLocal();
        
        // 2. Aim the ray from cam loc to cam direction.
        Ray ray = new Ray(start, end);
        // 3. Collect intersections between Ray and Shootables in results list.
        app.getRootNode().getChild("baseNode").collideWith(ray, results);
        // 4. Print results.
        System.out.println("----- Collisions? " + results.size() + "-----");
        for (int i = 0; i < results.size(); i++) {
          // For each hit, we know distance, impact point, name of geometry.
          float dist = results.getCollision(i).getDistance();
          Vector3f pt = results.getCollision(i).getContactPoint();
          String hit = results.getCollision(i).getGeometry().getName();
          Triangle triangle = results.getCollision(i).getTriangle(null);
          System.out.println("* Collision #" + i);
          System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
          System.out.println(triangle.get1() + " " + triangle.get2() + " " + triangle.get3());
          
          System.out.println("Ray Origin " + ray.origin);
          System.out.println("Cam Location " + app.getCamera().getLocation());
          
         // System.out.println("Collision " + new Vector3f(pt.x * dist, pt.y * dist, pt.z));
          
        }
        // 5. Use the results (we mark the hit object)
        if (results.size() > 0){
          // The closest collision point is what was truly hit:
          CollisionResult closest = results.getClosestCollision();
          //mark.setLocalTranslation(closest.getContactPoint());
          // Let's interact - we mark the hit with a red dot.
         // rootNode.attachChild(mark);
        } else {
        // No hits? Then remove the red mark.
        //  rootNode.detachChild(mark);
        }
      }
    }
  };
    
//    private final AnalogListener dreamCastAnalogListener = new AnalogListener() {
//    @Override
//    public void onAnalog(String name, float intensity, float tpf) {
//        
//       
//        
//       if (name.equals("pick target")) {
//        // Reset results list.
//        //CollisionResults results = new CollisionResults();
//        // Convert screen click to 3d position
//        Vector2f click2d =  app.getInputManager().getCursorPosition();
//        
//        Vector2f viewPort2d = new Vector2f(((click2d.x / screenDimensions.x) - 0.5f) * 2 , ((click2d.y/ screenDimensions.y) - 0.5f) * 2);
//      //  Vector3f worldCoordinates = app.getCamera().getWorldCoordinates(new Vector2f(viewPort2d.x, viewPort2d.y), 0f).clone();// .getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).normalizeLocal().clone();
//       
//        //Vector3f start = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f);
//        
//
//// Vector3f dir = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0.1f);
//        
//
//        Vector3f dir = app.getCamera().getDirection();
//        Vector3f loc = app.getCamera().getLocation();
//        //Vector3f dir = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x,click2d.y), 0.1f).subtractLocal(click3d).normalizeLocal();
//        Vector3f starthyp = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x , click2d.y), 0).clone();
// 
//        
//        Vector3f endhyp = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1).subtractLocal(starthyp).normalizeLocal().clone();
//        
//        Ray ray = new Ray(starthyp, dir);
//         
//        if (viewPort2d.x < 0){
//        
//             viewPort2d.x--;
//            viewPort2d.x *= 1.5f;
//           
//        }
//        if (viewPort2d.y < 0){
//        
//            
//            viewPort2d.y *= 2f;
//            viewPort2d.y--;
//
//        }
//        //Vector3f distanceFromCam = 
//        //aPoint = cam bPoint = starthyp cPoint = endhyp
//        
//        //float hypDistance = starthyp.distance(endhyp);
//        
////        Vector3f c = new Vector3f(endhyp);
////        Vector3f a = new Vector3f(starthyp.x + endhyp.x, starthyp.y + endhyp.y, starthyp.z);
////        Vector3f b = new Vector3f(starthyp.x, starthyp.y, starthyp.z + endhyp.z);
////        
////        Vector3f aNorm = new Vector3f(a.normalize());
////        Vector3f cNorm = new Vector3f(c.normalize());
//
//        
//
//
////        Vector3f c2 = new Vector3f(c.mult(c));
////        Vector3f b2 = new Vector3f(b.mult(b));
////        Vector3f a2 = new Vector3f(c2.subtract(b2));
////        
////        Vector3f aRoot = new Vector3f(FastMath.sqrt(a2.x), FastMath.sqrt(a2.y), FastMath.sqrt(a2.z));
//        
//        //a2 + b2 = c2
//        // a2 = c2 - b2
//        //a = root(c2 - b2)
//        
////        Vector3f aSquared = new Vector3f();
////          
////        Vector3f bSquared = new Vector3f();
////        Vector3f cSquared = new Vector3f();
//        
//       // Vector3f startPosition = starthyp.subtract(endhyp);
//       // Vector3f endPosition = new Vector3f();
//       // Vector3f screenCoords = app.getCamera().getScreenCoordinates(starthyp);
//        //System.out.println("hypDistance " + hypDistance);
//      
//        float perpX = 0;
//        float perpY = 0;
//        float perpZ = 0;
//        
////        if (dir.x >= 0){
////         //perpX = loc.x;
////         //perpY = loc.y + viewPort2d.y;
////         perpZ = loc.z - viewPort2d.x;
////        } else {
////        
////         //perpX = loc.x;
////         //perpY = loc.y + viewPort2d.y;
////         perpZ = loc.z + viewPort2d.x;
////        }
////        
////        if (dir.y >= 0){
////        
////         perpX = loc.x + viewPort2d.x;
////         //perpY += loc.y;
////         perpZ = loc.z + viewPort2d.y;
////        } else {
////        
////         perpX = loc.x + viewPort2d.x;
////         //perpY += loc.y;
////         perpZ = loc. z - viewPort2d.y;
////        }
////        
////        if (dir.z >= 0){
////        
////           perpX = loc.x - viewPort2d.x;
////        //perpY += loc.y + viewPort2d.y;
////        // perpZ += loc.z;
////        } else {
////        
////             perpX = loc.x + viewPort2d.x;
////        // perpY += loc.y + viewPort2d.y;
////         //perpZ += loc.z;
////        }
//        
//        perpX = ((loc.x + viewPort2d.x) * -dir.z) + (viewPort2d.x * dir.y);
//        perpY =  (loc.y + viewPort2d.y) * ((dir.x )) + (loc.y + viewPort2d.y) * ((dir.z));
//        perpZ = ((loc.z + viewPort2d.x) * -dir.x) + ((loc.z + viewPort2d.y) * dir.y);
//
//// if (dir.x >= 0){
////         perpX = (loc.x * ((dir.z) )) + ((loc.x + viewPort2d.x) * dir.y) + ((loc.x + viewPort2d.x) * dir.x);
////         perpY = ((loc.y + viewPort2d.y) * dir.z) + (loc.y * dir.x) + ((loc.y + viewPort2d.y) * dir.y);
////         perpZ = (((loc.z + viewPort2d.x) * dir.z) + ((loc.z + viewPort2d.y) * dir.x) + ((loc.z) * dir.y));
//
////        
////        if (dir.z >= 0){
////        
////           perpX = ((loc.x + viewPort2d.x) * dir.z);
////         perpY = ((loc.y + viewPort2d.y) * dir.z);
////         perpZ = ((loc.z) * dir.z);
////        } else {
////        
////             perpX = loc.x + viewPort2d.x;
////         perpY = loc.y + viewPort2d.y;
////         perpZ = loc.z;
////        }
//        Vector3f perpendicular = new Vector3f(perpX, -perpY, perpZ);//null; //new Vector3f( + (viewPort2d.x * dir.z), viewPort);
//        //Vector3f startPoint = new Vector3f(app.getCamera().getLocation().add(new Vector3f(viewPort2d.x + app.getCamera().getDirection().x, viewPort2d.y + app.getCamera().getDirection().y, app.getCamera().getDirection().z)));
//        //System.out.println(app.getCamera().getFrustumLeft());
//       //System.out.println("startPoint " + startPoint);
//       //System.out.println(app.getCamera().getUp());
//        System.out.println("viewport2d " + viewPort2d);
//        System.out.println("click2d " + click2d);
////        System.out.println("starthyp " + starthyp);
//          System.out.println("perpendicular " + perpendicular);
//        
//        System.out.println("endhyp " + endhyp);
//        
//        System.out.println("CamAngle " + app.getCamera().getDirection());
//        
//        System.out.println("CamLocation " + app.getCamera().getLocation());
//        //System.out.println("aRoot " + aRoot);
//        //Vector3f startPoint = new Vector3f(endhyp.add(starthyp.mult(5)));// new Vector3f(endhyp.x + starthyp.x, endhyp.y + starthyp.y, starthyp.z);
//        //Vector3f endPoint = new Vector3f();//new Vector3f(endhyp.add(startPoint).mult( 1f));
//        //Vector3f dir = new Vector3f();
//        if (clickOn){
//         GameShopDreamCast gsdc = new GameShopDreamCast(app, new GameShopLine(ray.origin,  (ray.direction) ));//perpendicular.subtract(new Vector3f(viewPort2d.x, viewPort2d.y, 1f).normalize())));
//         
//         app.getStateManager().getState(GameShopExecutorPool.class).addGameShopRunnables(0, new GameShopRunnable[] { gsdc });//.addGameShopRunnables(0, new GameShopRunnable[] { new GameShopDreamCast(app, new GameShopLine(click3d, dir))});
//         app.getStateManager().getState(GameShopDreamCastCloud.class).addGameShopDreamCasts(0, new GameShopDreamCast[]{ gsdc });
//      
//         clickOn = false;
//        }
//      } // else if ...
//    }
//  };

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
