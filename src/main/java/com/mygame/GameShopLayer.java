package com.mygame;


import com.mygame.GameShopCurrencyLine;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

import java.util.ArrayList;
import java.util.Arrays;

public class GameShopLayer {

    public byte[][] layer;

    public short width;
    public short height;
    //512 x 512 [recommended]
    public GameShopLayer(short width, short height){

        layer = new byte[height][width * 4];

        this.width = width;
        this.height = height;

    }

    public void drawSquare(short pointX, short pointY, short currency, ColorRGBA color){
        short startX = -1;
        short startY = -1;
        short endX = -1;
        short endY = -1;
        //start with pointX and pointY, subtract radius to get the startPoint
        //compare width and height bounds to edge
        //check if width and height is in radius bounds


        if (pointX - currency <= 0){
            startX = 0;
        } else {
            startX = (short) ((pointX - currency) + 1);
        }

        if (pointY - currency <= 0){
            startY = 0;
        } else {
            startY = (short) ((pointY - currency) + 1);
        }

        if (pointX + currency >= width){
            endX = width;
        } else {
            endX = (short) ((pointX + currency) - 1);
        }

        if (pointY + currency >= height){
            endY = height;
        } else {
            endY = (short) ((pointY + currency) - 1);
        }

//        System.out.println("StartX " + startX);
////
//        System.out.println("StartY " + startY);
//
//        System.out.println("EndX " + endX);
//
//        System.out.println("EndY " + endY);
        for (short y = startY; y < endY; y++){

            for (short x = startX; x < endX; x++) {
                layer[y][x * 4] = (byte) (color.getColorArray()[0] * 255);
                layer[y][(x * 4) + 1]  = (byte) (color.getColorArray()[1] * 255);
                layer[y][(x * 4) + 2] = (byte) (color.getColorArray()[2] * 255);
                layer[y][(x * 4) + 3]  = (byte) (color.getColorArray()[3] * 255);
//
            }
        }
    }

    public byte[] outputLayer(){

        byte[] output = new byte[width * height * 4];
        System.out.println(output.length);
        int i = 0;
        for (short y = 0; y < height; y++){
            for (short x = 0; x < width * 4; x +=4){

                try {
                output[i] = layer[y][x];
                output[i + 1] = layer[y][x + 1];
                output[i + 2] = layer[y][x + 2];
                output[i + 3] = layer[y][x + 3];

                i += 4;
                } catch (ArrayIndexOutOfBoundsException e) {
                
                    System.out.println(i);
                    e.printStackTrace();
                    System.exit(0);
                }
            }

        }

        //System.out.println(Arrays.toString(output));
        return output;
    }

    public void drawCurrencyLine(GameShopCurrencyLine cl, short radius, ColorRGBA color){

        //int i = 0;
        for (int i = 0; i < cl.infinitesimals.length - 1; i++){

            //if (i < cl.infinitesimals.length - 1) {
            drawLine(new Vector2f(cl.infinitesimals[i].x,cl.infinitesimals[i].y), new Vector2f(cl.infinitesimals[i + 1].x,cl.infinitesimals[i + 1].y), radius, color);
            // i++;
            //}


        }

    }
    public void drawLine(Vector2f pointA, Vector2f pointB, short radius, ColorRGBA color){

//       float dist = pointA.distance(pointB);
//
//        int i = (int) (dist/radius);
//
        float x =  pointA.x;
        float y =  pointA.y;
//
//        Vector2f distance = pointB.subtract(pointA);
//

        float distX =  (pointB.x - pointA.x);
        float distY =  (pointB.y - pointA.y);

        float lastX = x;
        float lastY = y;
        while (true){

            if ((distX > 0 && x > pointB.x) || (distY > 0 && y > pointB.y)) {
                break;
            }


            if ((distX < 0 && x < pointB.x) || (distY < 0 && y < pointB.y)) {
                break;
            }

            if ((distX > 0 && x > pointB.x) || (distY < 0 && y < pointB.y)) {
                break;
            }


            if ((distX < 0 && x < pointB.x) || (distY > 0 && y > pointB.y)) {
                break;
            }
//
            drawCircle((short)x, (short)y, radius, color);
            if (distX >= 1) {
                //System.out.println("x+ " + x);
                float addX = (FastMath.sqrt(FastMath.sqr(radius) - FastMath.sqr(radius/distX)))/4;

                if (Float.isNaN(x)){
                    continue;
                }
                x += addX;
            }

            else if (distX <= -1) {
              //  System.out.println("x- " + x);
                float addX = (FastMath.sqrt(FastMath.sqr(radius) - FastMath.sqr(FastMath.abs(radius/distX))))/4;
                if (Float.isNaN(x)){
                    continue;
                }
                x -= addX;

            }
            if (distY >= 1) {
                //System.out.println("y+ " + y);
                float addY = (FastMath.sqrt(FastMath.sqr(radius) - FastMath.sqr(radius/distY)))/4;
                if (Float.isNaN(y)){
                    continue;
                }
                y += addY;
            }

            else if (distY <= -1) {
               // System.out.println("y- " + y);
                float addY = (FastMath.sqrt(FastMath.sqr(radius) - FastMath.sqr(FastMath.abs(radius/distY))))/4;
                if (Float.isNaN(y)){
                    continue;
                }
                y -= addY;

            }


        }

//        while (x < pointB.x) {
//            drawCircle((short) (x), (short) (y), radius, color);
//            x += (short) (radius/distance.x);
//            y += (short) (radius/distance.y);
//        }
//        for (int y = (int) pointA.y; y < pointB.y; y += radius/2){
//            for (short x = (short) pointA.x; x < pointB.x; x += radius/2){
//
//                drawCircle((short) (x), (short) (y), radius, color);
//
//
//            }
//        }
//        for (int j = 0; j < i; j += radius/2){
//            drawCircle((short) (pointA.x + j), (short) (pointA.y + j), radius, color);
//        }

    }

    //0x0 center
    public void drawCircle(short pointX, short pointY, short radius, ColorRGBA color){

//        layer[((width - pointX) * (height - pointY) * 4)] = color.asBytesRGBA()[0];
//        layer[((width - pointX) * (height - pointY) * 4) + 1] = color.asBytesRGBA()[1];
//        layer[((width - pointX) * (height - pointY) * 4) + 2] = color.asBytesRGBA()[2];
//        layer[((width - pointX) * (height - pointY) * 4) + 3] = color.asBytesRGBA()[3];

        //System.out.println(ColorRGBA.White.getColorArray()[0]);
        short startX = -1;
        short startY = -1;
        short endX = -1;
        short endY = -1;
        //start with pointX and pointY, subtract radius to get the startPoint
        //compare width and height bounds to edge
        //check if width and height is in radius bounds


        if (pointX - radius <= 0){
            startX = 0;
        } else {
            startX = (short) ((pointX - radius) + 1);
        }

        if (pointY - radius <= 0){
            startY = 0;
        } else {
            startY = (short) ((pointY - radius) + 1);
        }

        if (pointX + radius >= width){
            endX = (short) (width - 1);
        } else {
            endX = (short) ((pointX + radius) - 1);
        }

        if (pointY + radius >= height){
            endY = (short) (height - 1);
        } else {
            endY = (short) ((pointY + radius) - 1);
        }

//        System.out.println("StartX " + startX);
////
//        System.out.println("StartY " + startY);
//
//        System.out.println("EndX " + endX);
//
//        System.out.println("EndY " + endY);
        int i = 0;
        for (short y = startY; y <= endY; y++){

            for (short x = startX; x <= endX; x++){

//                if (x <= FastMath.sqrt(FastMath.sqr(radius) - FastMath.sqr(y - pointY)) + pointX &&
//                        y <= FastMath.sqrt(FastMath.sqr(radius) - FastMath.sqr(x - pointX)) + pointY) {

                //System.out.println("FastMath " + ((FastMath.sqr(FastMath.abs(((float) pointX) - ((float) x/4))) + FastMath.sqr(FastMath.abs(pointY - y)))));
                // if ((FastMath.sqr(FastMath.abs((((float) x/4) - (float) pointX))) + FastMath.sqr(FastMath.abs(y - pointY))) < FastMath.sqr(radius) )

//                System.out.println("INTRIANGLE " + FastMath.pointInsideTriangle(new Vector2f(FastMath.sqr(pointX), FastMath.sqr(pointY)), new Vector2f(FastMath.sqr(pointX + radius), FastMath.sqr(pointY)), new Vector2f(FastMath.sqr(pointX), FastMath.sqr(pointY + radius)), new Vector2f(FastMath.sqr(x), FastMath.sqr(y))) );
//               if(FastMath.pointInsideTriangle(new Vector2f(FastMath.sqr(pointX), FastMath.sqr(pointY)), new Vector2f(FastMath.sqr(pointX + radius), FastMath.sqr(pointY)), new Vector2f(FastMath.sqr(pointX), FastMath.sqr(pointY + radius)), new Vector2f(x, y)) == 1)
//
                //int i = (pointX - x / 4) * (pointX - x / 4);
//                System.out.println ("FastMath " +(float) ((pointX - x ) * (pointX - x )) + ((pointY - y) * (pointY - y)));
//               System.out.println ("Radius" + (radius * radius));

                if ( ((pointX - x ) * (pointX - x)) + ((pointY - y) * (pointY - y)) < (radius * radius))

                {
                    //if (FastMath.sqrt(FastMath.sqr((((float) x /4) + pointX * 4)) + FastMath.sqr((y + pointY))) < radius){
                    layer[y][x * 4] = (byte) (color.getColorArray()[0] * 255); //127
                    layer[y][(x * 4) + 1] = (byte) (color.getColorArray()[1] * 255);
                    layer[y][(x * 4) + 2] = (byte) (color.getColorArray()[2] * 255);
                    layer[y][(x * 4) + 3] = (byte) (color.getColorArray()[3] * 255);
                    //System.out.println("IN");

                }
            }
        }

//        for (short h = 0; h < height; h++) {
//
//            for (short w = 0; w < width * 4; w += 4) {
//
//
//                if (startX == -1){
//
//                    startX = 0;
//                }
//
//
//            }
//        }




    }


}
