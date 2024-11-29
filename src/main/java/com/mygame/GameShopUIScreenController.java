/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.util.List;

/**
 *
 * @author lynden
 */
public class GameShopUIScreenController implements ScreenController {
  
    Nifty nifty;
    Screen screen;
    GameShopCurrencyMesh[] cm;
  /**
   * Fill the listbox with items. In this case with Strings.
   */
  public void fillMyListBox() {
    ListBox listBox = screen.findNiftyControl("myListBox", ListBox.class);
//    listBox.addItem("a");
//    listBox.addItem("b");
//    listBox.addItem("c");

      for (GameShopCurrencyMesh cm: this.cm) {
      
          listBox.addItem("[CurrencyMesh]");
      }
  }

  public GameShopUIScreenController(GameShopCurrencyMesh[] cm){
  
      this.cm = cm;
  }
  /**
   * When the selection of the ListBox changes this method is called.
   */
  @NiftyEventSubscriber(id="myListBox")
  public void onMyListBoxSelectionChanged(final String id, final ListBoxSelectionChangedEvent<String> event) {
    List<String> selection = event.getSelection();
    for (String selectedItem : selection) {
      System.out.println("listbox selection [" + selectedItem + "]");
    }
  }

    @Override
    public void bind(Nifty nifty, Screen screen) {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    
       this.nifty = nifty;
       this.screen = screen;
    }

    @Override
    public void onStartScreen() {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
   
       fillMyListBox();
    }

    @Override
    public void onEndScreen() {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}