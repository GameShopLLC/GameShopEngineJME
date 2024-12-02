package com.mygame;

//import com.gameshopcorp.gameshopengine.ui.GameShopFont;
import com.jme3.app.SimpleApplication;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture2D;
import com.jme3.texture.image.ColorSpace;
import com.jme3.util.BufferUtils;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import java.nio.ByteBuffer;
import java.util.logging.Logger;


//import com.simsilica.lemur.Button;
//import com.simsilica.lemur.Command;
//import com.simsilica.lemur.Container;
//import com.simsilica.lemur.GuiGlobals;
//import com.simsilica.lemur.Label;
//import com.simsilica.lemur.style.BaseStyles;


/**
 * Created by potterec on 3/17/2016.
 */
public class Main extends SimpleApplication {

    GameShopExecutorPool gsp;
    
    public static void main(String[] args) {
        Main app = new Main();
        AppSettings settings = new AppSettings(true);
       // settings.setFullscreen(true);
        
       //settings.setResolution(1920, 1080);
     GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
  DisplayMode[] modes = device.getDisplayModes();
  int i=0; // note: there are usually several, let's pick the first
  settings.setResolution(modes[i].getWidth(),modes[i].getHeight());
  settings.setFrequency(modes[i].getRefreshRate());
  settings.setBitsPerPixel(modes[i].getBitDepth());
  settings.setFullscreen(device.isFullScreenSupported());

  //app.setSettings(settings);
       app.setSettings(settings);
       app.start();
        
    }

    Node baseNode;
   // CameraNode cameraNode;
    @Override
    public void simpleInitApp() {

        
        

        gsp = new GameShopExecutorPool(this);
        //gsp.initialize(this);
        gsp.addGameShopRunnables(0, new GameShopRunnable[] { new GameShopDreamCast(this, new GameShopLine(new Vector3f(), new Vector3f()))});
        
        inputManager.addMapping("pick target", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
 
        //GameShopDreamCast gsDreamCast = new GameShopDreamCast(this);
        
        GameShopInputInterface gsii = new GameShopInputInterface(this);
        
        this.flyCam.setDragToRotate(true);
        InputManager inputManager = this.getInputManager();
        inputManager.setCursorVisible(true);
//        Box b = new Box(1, 1, 1);
//        Geometry geom = new Geometry("Box", b);
//
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", ColorRGBA.Blue);
//        geom.setMaterial(mat);
//
//        rootNode.attachChild(geom);

//
        //System.out.println("Max Texture size: " + GL11.glGetInteger(GL11.GL_MAX_TEXTURE_SIZE));

        
        this.viewPort.setBackgroundColor(ColorRGBA.White);

        


        //this.cameraNode = new CameraNode("CameraNode", cam);
        int n = 2;
        //0 456 912
        //1 460 924
        //2 504 956
        GameShopCurrencyMesh[] cms = new GameShopCurrencyMesh[n];
        baseNode = new Node();
       // baseNode.setLocalTranslation(new Vector3f(cam.getLocation().getX(), cam.getLocation().getY(), cam.getLocation().getZ()));
        //baseNode.rotate(0, 90, 0);
        //baseNode.setLocalTranslation(cam.getLocation());
        //baseNode.setLocalTranslation(getRootNode().getLocalTranslation());
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++){

            GameShopATMS gameShopATMS = new GameShopATMS( new GameShopLayer((short)256,(short)128), new Vector4f[]{new Vector4f(0,.5f,0,1), new Vector4f(.5f,1f,0,1)});
            gameShopATMS.clear();
            //gameShopATMS.addDrawCalls(0, new String[]{"Hi", "I", "Am", "LyndenJayEvans", "Welcome"});
            //gameShopATMS.addDrawCalls(3, new String[]{ "Not", "The", "Person", "Named"});
           // gameShopATMS.addDrawCalls(0, new String[] {"drawSquare 50 50 50 Color 255 0 0 255", "drawCircle 150 150 100 Color 0 0 255 255", "drawLine Vector2 0 0 Vector2 100 100 Radius 15 Color 0 0 0 255",
           // "drawCurrencyLine CurrencyLine Vector3 0 0 0 Vector3 10 20 0 Vector3 20 30 0 Vector3 30 40 0 NumPoints 4 Radius 10 Color 255 255 255 255"});
           gameShopATMS.addDrawCalls(0, new String[]{"drawCircle 96 78 50 Color 0 0 0 255","drawCircle 150 78 50 Color 0 0 0 255", "drawCircle 128 48 55 Color 0 0 0 255", "drawCircle 108 68 25 Color 255 255 255 255", "drawCircle 148 68 25 Color 255 255 255 255"});
           gameShopATMS.process();
            System.out.println(gameShopATMS.toString());
            //gameShopATMS.layer.drawCircle((short) 128, (short) 63, (short) 63, ColorRGBA.fromRGBA255(255,215,175,255));
           // gameShopATMS.layer.drawCircle((short) 190, (short) 63, (short) 32, ColorRGBA.fromRGBA255(255,215,175,255));


            byte depth = 2;
            GameShopCurrencyLine[] cl = new GameShopCurrencyLine[4];
            nodes[i] = new Node("" + i);
            float z = -i - 3;
            cl = new GameShopCurrencyLine[]{

                    new GameShopCurrencyLine(new Vector3f[]{new Vector3f(0,0,z), new Vector3f(.33f, 0, z), new Vector3f(.66f,0,z), new Vector3f(1, 0, z)}, depth),
                    new GameShopCurrencyLine(new Vector3f[]{new Vector3f(0,.33f,z), new Vector3f(.33f, .33f, z), new Vector3f(.66f,.33f,z), new Vector3f(1, .33f, z)}, depth),
                    new GameShopCurrencyLine(new Vector3f[]{new Vector3f(0,.66f,z), new Vector3f(.33f, .66f, z), new Vector3f(.66f,.66f,z), new Vector3f(1, .66f, z)}, depth),
                    new GameShopCurrencyLine(new Vector3f[]{new Vector3f(0,1,z), new Vector3f(.33f, 1, z), new Vector3f(.66f,1,z), new Vector3f(1, 1, z)}, depth),

                    new GameShopCurrencyLine(new Vector3f[]{new Vector3f(1,0,z), new Vector3f(1.33f, 0, z), new Vector3f(1.66f,0,z), new Vector3f(2, 0, z)}, depth),
                    new GameShopCurrencyLine(new Vector3f[]{new Vector3f(1,.33f,z), new Vector3f(1.33f, .33f, z), new Vector3f(1.66f,.33f,z), new Vector3f(2, .33f, z)}, depth),
                    new GameShopCurrencyLine(new Vector3f[]{new Vector3f(1,.66f,z), new Vector3f(1.33f, .66f, z), new Vector3f(1.66f,.66f,z), new Vector3f(2, .66f, z)}, depth),
                    new GameShopCurrencyLine(new Vector3f[]{new Vector3f(1,1,z), new Vector3f(1.33f, 1, z), new Vector3f(1.66f,1,z), new Vector3f(2, 1, z)}, depth)


            };

            cms[i] = new GameShopCurrencyMesh(this, cl, gameShopATMS,nodes[i]);
            //this.getRootNode().attachChild(nodes[i]);


            baseNode.attachChild(nodes[i]);

//            GameShopFont gameShopFont = new GameShopFont(gameShopATMS, "");
//            gameShopFont.drawCharacter('A', 1);
//            gameShopATMS.process();
//            cms[i].updateGeometry();
            System.out.println(gameShopATMS.toString());
            //SimpleMesh sm = new SimpleMesh(this, new Vector3f[]{}, new Vector2f[]{}, makeTexture(), this.getRootNode());
        }

        this.stateManager.attach(new GameShopUI(cms));
        this.stateManager.attach(gsp);
        //cameraNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        //cameraNode.setLocalTranslation(getRootNode().getLocalTranslation());
        getRootNode().attachChild(baseNode);
       // baseNode.attachChild(cameraNode);
       //cameraNode.setLocalTranslation(new Vector3f(0, 0, 1));
        //setting the camNode to look at the teaNode
       // cameraNode.lookAt(baseNode.getLocalTranslation(), Vector3f.UNIT_Y);

        //flyCam.setEnabled(false);

    //is.getRootNode().attachChild(baseNode);
 

        long maxMemory = Runtime.getRuntime().maxMemory();
        long allocatedMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();

        System.out.println("max: " + maxMemory);
        System.out.println("allocated: " + allocatedMemory);
        System.out.println("free: " + freeMemory);

        System.out.println("Cam " + cam.getRotation());
        System.out.println("BaseNode " + baseNode.getLocalRotation());
        //44098840
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code

        this.getStateManager().getState(GameShopExecutorPool.class).update(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

   
}
