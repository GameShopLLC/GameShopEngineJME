/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygame;

/**
 *
 * @author lynden
 */

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ControlBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.TreeBox;
import de.lessvoid.nifty.controls.TreeItem;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.controls.listbox.builder.ListBoxBuilder;
import de.lessvoid.nifty.controls.treebox.builder.TreeBoxBuilder;
import de.lessvoid.nifty.render.NiftyImage;
import de.lessvoid.nifty.screen.DefaultScreenController;

public class GameShopUI extends BaseAppState {

    GameShopCurrencyMesh[] cm;
    
    public GameShopUI(GameShopCurrencyMesh[] cm){
    
        this.cm = cm;
        
    }
    @Override
    protected void initialize(Application app) {
        //It is technically safe to do all initialization and cleanup in the
        //onEnable()/onDisable() methods. Choosing to use initialize() and
        //cleanup() for this is a matter of performance specifics for the
        //implementor.
        //TODO: initialize your AppState, e.g. attach spatials to rootNode
    }

    @Override
    protected void cleanup(Application app) {
        //TODO: clean up what you initialized in the initialize method,
        //e.g. remove all spatials from rootNode
    }

    //onEnable()/onDisable() can be used for managing things that should
    //only exist while the state is enabled. Prime examples would be scene
    //graph attachment or input listener attachment.
    @Override
    protected void onEnable() {
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(
                getApplication().getAssetManager(),
                getApplication().getInputManager(),
                getApplication().getAudioRenderer(),
                getApplication().getGuiViewPort());

        Nifty nifty = niftyDisplay.getNifty();
        getApplication().getGuiViewPort().addProcessor(niftyDisplay);
        //((SimpleApplication) getApplication()).getFlyByCamera().setDragToRotate(true);

        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");

        // <screen>
        nifty.addScreen("GameShopUI_Screen", new ScreenBuilder("GameShopUI"){{
            controller(new GameShopUIScreenController(cm)); // Screen properties

            // <layer>
            layer(new LayerBuilder("UI_Layer") {{
                childLayoutVertical(); // layer properties, add more...

                // <panel>
                
                panel(new PanelBuilder("Panel_UI") {{
                    childLayoutVertical(); // panel properties, add more...
 
                      panel(new PanelBuilder("Panel_Top") {{
                    childLayoutHorizontal(); // panel properties, add more...
                    height("5%");
                        width("15%");
                    panel(new PanelBuilder("Panel_MenuBar") {{
                    childLayoutHorizontal(); // panel properties, add more...

                    
                    // GUI elements
                    control(new ButtonBuilder("Button_New", "New"){{
                        alignLeft();
                        //valignLeft();
//                        height("5%");
//                        width("15%");
                    }});
                     control(new ButtonBuilder("Button_Load", "Load"){{
                        alignLeft();
                        //valignLeft();
//                        height("5%");
//                        width("15%");
                    }});
                     control(new ButtonBuilder("Button_Save", "Save"){{
                        alignLeft();
                        //valignLeft();
//                        height("5%");
//                        width("15%");
                    }});
                     control(new ButtonBuilder("Button_Run", "Run"){{
                        alignLeft();
                        //valignLeft();
//                        height("5%");
//                        width("15%");
                    }});
                     control(new ButtonBuilder("Button_Compile", "Compile"){{
                        alignLeft();
                        //valignLeft();
//                        height("5%");
//                        width("15%");
                    }});
                      
                    //.. add more GUI elements here

                }});

                }});
                      
                        panel(new PanelBuilder("Panel_Mid") {{
                            childLayoutHorizontal(); // panel properties, add more...
 height("75%");
                        width("100%");
                            
                              
                panel(new PanelBuilder("Panel_CurrencyMesh") {{
                    childLayoutHorizontal(); // panel properties, add more...
                        height("100%");
                        width("20%");
                    
                    // GUI elements


                    // Using the builder pattern
control(new ListBoxBuilder("myListBox") {{
    height("100%");
                        width("100%");
  displayItems(4);
  selectionModeSingle();
  optionalHorizontalScrollbar();
  optionalVerticalScrollbar();
  width("*"); // standard nifty width attribute
}});
                    //.. add more GUI elements here
                   
                    
                    

                }});
                
                 panel(new PanelBuilder("Panel_Game") {{
                    childLayoutHorizontal(); // panel properties, add more...

                     height("100%");
                     width("60%");
                     
                   
                    //.. add more GUI elements here

                }});
                 
                
//                panel(new PanelBuilder("Panel_ATMS") {{
//                    childLayoutHorizontal(); // panel properties, add more...
//
//                     height("100%");
//                        width("20%");
//                    
//                    // GUI elements
//                    control(new ButtonBuilder("Button_ATMS", "ATMS Info Here"){{
//                        alignRight();
//                        //valignLeft();
//                        height("100%");
//                        width("100%");
//                    }});
//                    //.. add more GUI elements here
//
//                }});
                    

                }});
                        
//                          panel(new PanelBuilder("Panel_Bottom") {{
//                    childLayoutHorizontal(); // panel properties, add more...
//  height("20%");
//                     width("100%");
//                     
//              
//                panel(new PanelBuilder("Panel_Files") {{
//                    childLayoutHorizontal(); // panel properties, add more...
//
//                     height("100%");
//                     width("100%");
//                    
//                    // GUI elements
//                    control(new ButtonBuilder("Button_Files", "Files Info Here"){{
//                        alignLeft();
//                        //valignLeft();
//                        height("100%");
//                        width("100%");
//                    }});
//                   
//                    //.. add more GUI elements here
//
//                }});
//
//                }});
                    

                }});
               
                // </panel>
            }});
            // </layer>
        }}.build(nifty));
        // </screen>

        nifty.gotoScreen("GameShopUI_Screen"); // start the screen
    }

    @Override
    protected void onDisable() {
        //Called when the state was previously enabled but is now disabled
        //either because setEnabled(false) was called or the state is being
        //cleaned up.
    }

    @Override
    public void update(float tpf) {
        //TODO: implement behavior during runtime
    }

}
