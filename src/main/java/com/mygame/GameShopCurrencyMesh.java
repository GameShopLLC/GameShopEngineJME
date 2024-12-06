/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import com.mygame.GameShopATMS;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer;
import com.jme3.util.BufferUtils;

import java.util.Arrays;

/**
 *
 * @author lyndenjayevans
 */
public class GameShopCurrencyMesh {
    
    int skip;
    SimpleApplication app;
    GameShopCurrencyLine[] currencyLines;
    GameShopATMS atms;
    Node node;
    
    public float width;
    public float height;
    
    GameShopCurrencyLine[] vInfinitesimals;
    
    public Vector4f[] textureSlices;
    
    public Vector3f[] vertices;
    public Vector2f[] texCoord;
    
    public Short[] indices;

    public Mesh m;

    public Geometry geom;
    
    //There should be sets of 4 CurrencyLines.... example 4 8 12 etc...
    public GameShopCurrencyMesh(SimpleApplication app, GameShopCurrencyLine[] currencyLines, GameShopATMS atms, Node node){
    
        assert(currencyLines.length % 4 == 0);
        //if (currencyLines.length % 4 == 0){
        
            skip = currencyLines.length / 4;
        //}  
        
        this.app = app;
        this.currencyLines = currencyLines;
        this.atms = atms;
        this.node = node;
        
        this.m = new Mesh();
        
        makeVerticalLines();
        
        setVerticesVertical();
        setIndicesVertical();
        setTexCoordsVertical();
        
        setBuffers();
        updateGeometry();
    }
    
    public void setVerticesVertical(){
     int totalVertices = 0;
          
          for (GameShopCurrencyLine gscl: vInfinitesimals){

             // System.out.println(totalVertices);
              totalVertices += gscl.infinitesimals.length;
          }
          
          this.vertices = new Vector3f[totalVertices];
          
          int i = 0;
          for (GameShopCurrencyLine gscl: vInfinitesimals){
           
              for (Vector3f v: gscl.infinitesimals){
              
                  this.vertices[i] = new Vector3f(v);
                  i++;
              }
          }
          
         // System.out.println(vertices.length);
         // System.out.println(Arrays.asList(vertices));
    }
    
    public void setIndicesVertical(){
       int totalIndices = 0;
 
        for (GameShopCurrencyLine gscl : vInfinitesimals){
        
            for (Vector3f v: gscl.infinitesimals){
 
            
                totalIndices += 6;
              
            }
        }

        totalIndices += this.vertices.length/4;
        
       // System.out.println("totalIndices: " + totalIndices);
        indices = new Short[totalIndices];
        
        int i = 0;
        int line = 0;
        int l = 0;
        for (int index = 0; index < indices.length; index += 6){
        
            if (l > 0 && l % (vInfinitesimals[line].numPoints) == 0) {
 
           
            indices[index] =  0;
            indices[index + 1] = 0;
            indices[index + 2] = 0;
            indices[index + 3] = 0;
            indices[index + 4] = 0;
            indices[index + 5] = 0;
            l = 0;
            continue;
            } else {
                if ((int)(i + vInfinitesimals[line].numPoints + 2) < (int)(vertices.length)) {
                    indices[index] = (short) (i + vInfinitesimals[line].numPoints + 1);
                    indices[index + 1] = (short) i;
                    indices[index + 2] = (short) (i + 1);
                    indices[index + 3] = (short) (i + 1);
                    indices[index + 4] = (short) (i + vInfinitesimals[line].numPoints + 2);
                    indices[index + 5] = (short) (i + vInfinitesimals[line].numPoints + 1);
                } else {
                    indices[index] =  0;
                    indices[index + 1] = 0;
                    indices[index + 2] = 0;
                    indices[index + 3] = 0;
                    indices[index + 4] = 0;
                    indices[index + 5] = 0;
                }
            
            
            
            //i++;
            }
            
            i++;
            l++;
           
            
        if (i % (vInfinitesimals[line].infinitesimals.length * vInfinitesimals[line].infinitesimals.length) == 0) {

            line++;
        }
        }
   
          System.out.println(indices.length);
          System.out.println(Arrays.asList(indices));
        
    }
    
    public void setTexCoordsVertical(){


          this.texCoord = new Vector2f[this.vertices.length];

        if (currencyLines.length / 4 != atms.textureSamples.length){

            System.out.println("You Need 4 CurrencyLines for every 1 Texture Sample");
            for (Vector2f tc: this.texCoord){

                tc = new Vector2f();
            }
            return;
        }

        int maxLines = vInfinitesimals.length;
        int lines = 0;
        
        System.out.println("currencyLines " + currencyLines.length);
        System.out.println("vInfinitesimals " + vInfinitesimals.length);


        int i = 0;
        float x = 0;
        float y = 0;
        int slice = 0;

        for (int v = 0; v < texCoord.length; v++){

            if (v == 0){
                x = atms.textureSamples[slice].x;
                y  = atms.textureSamples[slice].z;
            }
            if (slice == atms.textureSamples.length){

                break;
            }

            texCoord[v] = new Vector2f((atms.textureSamples[slice].x + (x)) , (atms.textureSamples[slice].z + (y)));

            y += (((float) 1 /(((float) vInfinitesimals.length /atms.textureSamples.length))));

            if (y > atms.textureSamples[slice].w){
                y  = atms.textureSamples[slice].z;
                x += (((float) 1 /(((float) currencyLines.length /atms.textureSamples.length))));
            }




            if (x > atms.textureSamples[slice].y) {

                x = atms.textureSamples[slice].x;
                slice++;
            }


/*
            if (i > vInfinitesimals[lines].numPoints) {

                i = 0;
                y += .5f;
            }
            texCoord[v] = new Vector2f((((float)y)), ((float)i/(float)vInfinitesimals[lines].numPoints));

           // texCoord[v] = new Vector2f();

           // texCoord[v] = new Vector2f();

            i++;
            */
        }

//        for (int v = 0; v < texCoord.length; v++){
//
//             if (i > vInfinitesimals[lines].numPoints) {
//
//                i = 0;
//                y += .5f;
//            }
//            texCoord[v] = new Vector2f((float)(((float)y)), ((float)i/(float)vInfinitesimals[lines].numPoints));
//            i++;
//
//
//
//        }
        
          System.out.println(texCoord.length);
          System.out.println(Arrays.asList(texCoord));
        
    }
     
    
    public void setBuffers(){
    
        System.out.println("indices.length " + indices.length);
        short[] indexes = new short[indices.length];

for (int j = 0; j < indices.length; j++) {
    indexes[j] = indices[j]; // Autoboxing
}
         // Setting buffers
        m.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(this.vertices));
        m.setBuffer(VertexBuffer.Type.TexCoord, 2, BufferUtils.createFloatBuffer(this.texCoord));
        m.setBuffer(VertexBuffer.Type.Index, 1, BufferUtils.createShortBuffer(indexes));

        m.updateBound();
    }
    
    public void updateGeometry(){
    
         //*****RenderState*****

       // texture.setWrap(Texture.WrapMode.Repeat);
        // *************************************************************************
        // First mesh uses one solid color
        // *************************************************************************

        // Creating a geometry, and apply a single color material to it
        this.geom = new Geometry("OurMesh " + node.getName() , m);


        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);

        //mat.getAdditionalRenderState().setBlendEquationAlpha(RenderState.BlendEquationAlpha.Max);
        // mat.setColor("Color", ColorRGBA.fromRGBA255(255,255,255,255));
        mat.setTexture("ColorMap", atms.makeTexture());
        geom.setMaterial(mat);
        geom.setQueueBucket(RenderQueue.Bucket.Transparent);
        //geom.getMesh().scaleTextureCoordinates(new Vector2f(2, 2));

        // Attaching our geometry to the root node.
        //app.getRootNode().attachChild(geom);
        node.attachChild(geom);
    }


    
    public void makeVerticalLines(){
//     this.dim = 3;
//
        //width = (currencyLines[0].points[currencyLines[0].points.length - 1].x - currencyLines[0].points[0].x);
        //height = (currencyLines[currencyLines.length - 1].points[0].y - currencyLines[0].points[0].y);
       // this.vInfinitesimals = new GameShopPolyLine[((polyLines[0].numPoints - 2) * 2) + 2];//[polyLines[0].infinitesimals.length];
       
       int totalInfinitesimals = 0;
       //System.out.println("cl inf " + currencyLines[0].infinitesimals.length);
       for (int lines = 0; lines < currencyLines.length; lines += 4){

           totalInfinitesimals += currencyLines[lines].infinitesimals.length;

       }

//        int lines = 0;
//        while (lines < currencyLines.length){
//            totalInfinitesimals += currencyLines[lines].infinitesimals.length;
//
//
//        }
       
       
       this.vInfinitesimals = new GameShopCurrencyLine[totalInfinitesimals];//new GameShopCurrencyLine[(currencyLines[0].infinitesimals.length)];//[polyLines[0].infinitesimals.length];
 
       // System.out.println("vInfinitesimals " + this.vInfinitesimals.length);
        
        int j = 0;
        int i = 0;
        int total = 0;
        for (GameShopCurrencyLine vi: this.vInfinitesimals){

//            if (total == this.vInfinitesimals.length - 1){
//
//                break;
//            }
             if (i == currencyLines[j].infinitesimals.length){
            
                i = 0;
                j+=4;
            }
            // System.out.println();
//            System.out.println(i);
//            System.out.println(j);
//            System.out.println(total);

            this.vInfinitesimals[total] = new GameShopCurrencyLine(new Vector3f[]{currencyLines[j].infinitesimals[i], currencyLines[j + 1].infinitesimals[i], currencyLines[j + 2].infinitesimals[i], currencyLines[j + 3].infinitesimals[i]}, (byte) currencyLines[j].infinitesimals.length);
        
            i++;
           total++;
        }
        //System.out.println(total);
        
       // System.out.println(Arrays.asList(this.vInfinitesimals[0].infinitesimals));
        //System.out.println(j);
        
        //int j = 0;
        //for (int i = 0; i < this.vInfinitesimals.length;  i++){

 
//            this.vInfinitesimals[i] = new GameShopCurrencyLine(new Vector3f[]{currencyLines[j].infinitesimals[i], currencyLines[j + 1].infinitesimals[i], currencyLines[j + 2].infinitesimals[i], currencyLines[j + 3].infinitesimals[i]}, (byte) currencyLines[j].infinitesimals.length);
//          
//            if (i > 0 && i % 4 == 0){
//            j += 4;
//            }
      //  }
    }
    
//      public Texture2D makeTexture(String side){
//          //16384
//          //8192
//          //4096
//          //2048
//          //1024
//          //512
//        GameShopLayer layer= new GameShopLayer((short) 1024, (short) 1024);
//        layer.drawCircle((short) 512, (short) 512, (short) 512, ColorRGBA.fromRGBA255(255,215,175,255));
//
//        //DRILL COLOR CODE
//        // layer.drawCircle((short) 63, (short) 63, (short) 128, ColorRGBA.fromRGBA255(0,0,0,255));
//
//        ATMS atms = new ATMS((byte) 1, layer);
//        //atmsFront.frames[0] = layerFront;
//        ByteBuffer data = BufferUtils.createByteBuffer(atms.frames[0].outputLayer());
//        // ByteBuffer data = BufferUtils.createByteBuffer((byte)0,(byte)127,(byte)0,(byte)62);
//        Image image = new Image(Image.Format.RGBA8 , 1024, 1024, data, ColorSpace.Linear);
//        return new Texture2D(image);
//    }
}
