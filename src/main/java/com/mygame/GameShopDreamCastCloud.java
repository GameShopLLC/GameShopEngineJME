///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mygame;
//
//import com.jme3.app.Application;
//import com.jme3.app.SimpleApplication;
//import com.jme3.app.state.BaseAppState;
//import java.util.ArrayList;
//import java.util.Arrays;
//
///**
// *
// * @author lynden
// */
//public class GameShopDreamCastCloud extends BaseAppState {
//    
//    SimpleApplication app;
//    GameShopDreamCast[] gsDreamCasts;
//    //GameShopUtility<
//    public GameShopDreamCastCloud(SimpleApplication app){
//    
//        this.app = app;
//        gsDreamCasts = new GameShopDreamCast[1];
//    }
//
//    public void addGameShopDreamCasts(int index, GameShopDreamCast[] gsDreamCasts){
//    
//       // public void addDrawCalls(int index, String[] drawCalls){
//
//        boolean start = this.gsDreamCasts[0] != null;
//
//        ArrayList<GameShopDreamCast> newDCs = new ArrayList<>(Arrays.asList(gsDreamCasts));
//
//        ArrayList<GameShopDreamCast> oldDCs = null;
//
//        if (start) {
//            oldDCs = new ArrayList<>(Arrays.asList(this.gsDreamCasts));
//        }
//
//        ArrayList<GameShopDreamCast> lowerDCs = new ArrayList<>();
//
//        ArrayList<GameShopDreamCast> higherDCs = new ArrayList<>();
//
//        if (start) {
//            for (int i = 0; i < index; i++) {
//                lowerDCs.add(oldDCs.get(i));
//            }
//
//            for (int i = index; i < oldDCs.size(); i++) {
//                higherDCs.add(oldDCs.get(i));
//            }
//            this.gsDreamCasts = new GameShopDreamCast[(oldDCs.size()) + (newDCs.size())];
//
//        } else {
//            this.gsDreamCasts = new GameShopDreamCast[newDCs.size()];
//        }
//
//
//
//        int i = 0;
//        if (start) {
//            for (GameShopDreamCast dc : lowerDCs) {
//
//
//                this.gsDreamCasts[i] = dc;
//                i++;
//            }
//        }
////        System.out.println(Arrays.toString(oldDrawCalls.toArray()));
//  //      System.out.println(Arrays.toString(newDrawCalls.toArray()));
//
//        for (GameShopDreamCast dc: newDCs){
//
//            this.gsDreamCasts[i] = dc;
//            i++;
//        }
//
//        if (start) {
//            for (GameShopDreamCast dc : higherDCs) {
//
//                this.gsDreamCasts[i] = dc;
//                i++;
//            }
//        }
//        //newDrawCalls.addAll(index, drawCalls);
//        //        String[] temp = new String[this.drawCalls.length];
////
////        int i = 0;
////        for (String dc: this.drawCalls){
////
////            temp[i] = dc;
////            i++;
////        }
////
////        this.drawCalls = new String[(this.drawCalls.length) + drawCalls.length];
////
////        System.out.println("dc " + this.drawCalls.length);
//////        i = 0;
//////        int j = 0;
////
////        for (i = 0; i < index; i++){
////
////            this.drawCalls[i] = temp[i];
////        }
////
////        for (i = index; i < index + drawCalls.length; i++){
////
////            this.drawCalls[i] = drawCalls[i - index];
////        }
////
////        for (i = index + drawCalls.length; i < this.drawCalls.length; i++){
////
////            this.drawCalls[i] = temp[i - (drawCalls.length)];
////        }
//
//   // }
//    }
//    @Override
//    protected void initialize(Application aplctn) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    protected void cleanup(Application aplctn) {
//        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    protected void onEnable() {
//       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    protected void onDisable() {
//       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//}
