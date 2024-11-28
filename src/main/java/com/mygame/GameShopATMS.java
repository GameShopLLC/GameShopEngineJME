/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

//import androidx.annotation.NonNull;
import com.mygame.GameShopCurrencyLine;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.texture.Image;
import com.jme3.texture.Texture2D;
import com.jme3.texture.image.ColorSpace;
import com.jme3.util.BufferUtils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author chrx
 */
public class GameShopATMS {
    
    
    public GameShopLayer layer;

    public String[] drawCalls;
    public Vector4f[] textureSamples;

    public GameShopATMS(GameShopLayer layer, Vector4f[] textureSamples){

//        frames = new GameShopLayer[layers.length];
//        frames = layers;
        //this.layer = new GameShopLayer(layer.width, layer.height);
        this.layer = layer;
        this.textureSamples = new Vector4f[textureSamples.length];
        this.textureSamples = textureSamples;

        this.drawCalls = new String[1];
    }


    public void clear(){

        layer.drawCircle((short) ( layer.width/2), (short) (layer.height/2), (short) (layer.width), ColorRGBA.fromRGBA255(0,0,0,0));

    }

    public void addDrawCalls(int index, String[] drawCalls){

        boolean start = this.drawCalls[0] != null;

        ArrayList<String> newDrawCalls = new ArrayList<>(Arrays.asList(drawCalls));

        ArrayList<String> oldDrawCalls = null;

        if (start) {
            oldDrawCalls = new ArrayList<>(Arrays.asList(this.drawCalls));
        }

        ArrayList<String> lowerDrawCalls = new ArrayList<>();

        ArrayList<String> higherDrawCalls = new ArrayList<>();

        if (start) {
            for (int i = 0; i < index; i++) {
                lowerDrawCalls.add(oldDrawCalls.get(i));
            }

            for (int i = index; i < oldDrawCalls.size(); i++) {
                higherDrawCalls.add(oldDrawCalls.get(i));
            }
            this.drawCalls = new String[(oldDrawCalls.size()) + (newDrawCalls.size())];

        } else {
            this.drawCalls = new String[newDrawCalls.size()];
        }



        int i = 0;
        if (start) {
            for (String dc : lowerDrawCalls) {


                this.drawCalls[i] = dc;
                i++;
            }
        }
//        System.out.println(Arrays.toString(oldDrawCalls.toArray()));
  //      System.out.println(Arrays.toString(newDrawCalls.toArray()));

        for (String dc: newDrawCalls){

            this.drawCalls[i] = dc;
            i++;
        }

        if (start) {
            for (String dc : higherDrawCalls) {

                this.drawCalls[i] = dc;
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

    }

    public void process(){

        //ArrayList <String> commands = new ArrayList<>();

        clear();

        for (String dc: this.drawCalls){


            ArrayList<String> values = new ArrayList<>(Arrays.asList(dc.split(" ")));


            System.out.println(Arrays.toString(values.toArray()));

            if (dc.contains("drawCircle")) {

                this.layer.drawCircle(Short.parseShort(values.get(1)), Short.parseShort(values.get(2)), Short.parseShort(values.get(3)), ColorRGBA.fromRGBA255(Short.parseShort(values.get(5)), Short.parseShort(values.get(6)), Short.parseShort(values.get(7)), Short.parseShort(values.get(8))));

            } else if (dc.contains("drawSquare")) {

                this.layer.drawSquare(Short.parseShort(values.get(1)), Short.parseShort(values.get(2)), Short.parseShort(values.get(3)), ColorRGBA.fromRGBA255(Short.parseShort(values.get(5)), Short.parseShort(values.get(6)), Short.parseShort(values.get(7)), Short.parseShort(values.get(8))));


            } else if (dc.contains("drawLine")){

                this.layer.drawLine(new Vector2f(Short.parseShort(values.get(2)), Short.parseShort(values.get(3))), new Vector2f(Short.parseShort(values.get(5)), Short.parseShort(values.get(6))), Short.parseShort(values.get(8)), ColorRGBA.fromRGBA255(Short.parseShort(values.get(10)), Short.parseShort(values.get(11)), Short.parseShort(values.get(12)), Short.parseShort(values.get(13))));


            } else if (dc.contains("drawCurrencyLine")){

                String[] amountVectors = dc.split("Vector3");
                amountVectors = new String[amountVectors.length - 1];

                Vector3f[] points = new Vector3f[amountVectors.length];
//                GameShopCurrencyLine cl = new Ga
                int j = 3;
                for (int i = 0; i < amountVectors.length; i++){

                    points[i] = new Vector3f(Short.parseShort(values.get(j)), Short.parseShort(values.get(j + 1)), Short.parseShort(values.get(j+2)));

                    j+=4;
                }

                this.layer.drawCurrencyLine(new GameShopCurrencyLine(points, (byte) points.length), Short.parseShort(values.get(values.size() - 6)),ColorRGBA.fromRGBA255(Short.parseShort(values.get(values.size() - 4)), Short.parseShort(values.get(values.size() - 3)), Short.parseShort(values.get(values.size() - 2)), Short.parseShort(values.get(values.size() - 1))));


            }
        }
    }
    public Texture2D makeTexture(){
        //16384
        //8192
        //4096
        //2048
        //1024
        //512
//        GameShopLayer layer= new GameShopLayer((short) 1024, (short) 1024);
//        layer.drawCircle((short) 512, (short) 512, (short) 512, ColorRGBA.fromRGBA255(255,215,175,255));
//
//        //DRILL COLOR CODE

//        ATMS atms = new ATMS((byte) 1, layer);
        //atmsFront.frames[0] = layerFront;
        ByteBuffer data = BufferUtils.createByteBuffer(this.layer.outputLayer());
        // ByteBuffer data = BufferUtils.createByteBuffer((byte)0,(byte)127,(byte)0,(byte)62);
        Image image = new Image(Image.Format.RGBA8 , this.layer.width, this.layer.height, data, ColorSpace.Linear);
        return new Texture2D(image);
    }

   // @NonNull
    @Override
    public String toString() {
        return "GameShopATMS{" +
               // "layer=" + layer +
                ", drawCalls=" + Arrays.toString(drawCalls) +
                ", textureSamples=" + Arrays.toString(textureSamples) +
                '}';
    }

    //    public void changeColor (ColorRGBA colorFrom, ColorRGBA colorTo){
//
//        for (GameShopLayer frame: frames){
//            for (short y = 0; y < frame.height; y++){
//
//                for (short x = 0; x < frame.width; x++){
//
//                    if (frame.layer[y][x * 4] == (byte) (colorFrom.getColorArray()[0] * 255) &&
//                            frame.layer[y][(x * 4) + 1] == (byte) (colorFrom.getColorArray()[1] * 255) &&
//                            frame.layer[y][(x * 4) + 2] == (byte) (colorFrom.getColorArray()[2] * 255) &&
//                            frame.layer[y][(x * 4) + 3] == (byte) (colorFrom.getColorArray()[3] * 255)
//                    ){
//
//                        frame.layer[y][x * 4] = (byte) (colorTo.getColorArray()[0] * 255);
//                                frame.layer[y][(x * 4) + 1] = (byte) (colorTo.getColorArray()[1] * 255);
//                                frame.layer[y][(x * 4) + 2] = (byte) (colorTo.getColorArray()[2] * 255);
//                                frame.layer[y][(x * 4) + 3] = (byte) (colorTo.getColorArray()[3] * 255);
//                    }
//                }
//            }
//        }
//    }
}
