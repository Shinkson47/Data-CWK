package com.shinkson47.datacwk.main;

import com.shinkson47.datacwk.lib.collection.pool.StudentPool;
import com.shinkson47.datacwk.main.frontend.Controller;
import com.shinkson47.opex.frontend.fxml.FXMLMain;
import javafx.scene.control.Alert;

/**
 * <h1>Executable GUI utility that demonstrates the data structure, and it's operations</h1>
 * <br>
 * <blockquote>
 *
 *     IMPORTANT NOTICE FOR RUNTIME<br>
 *
 *
 *
 *     This utility requires JavaFX to execute. The university recommended JDK 'Liberica' contains JavaFX. <br>
 *
 *     lib/OPEX.jar must also be added to the class path. <br><br>
 *     The repo provided contains a project configured for OPEX, Liberica and JUnit 4 for both IntelliJ and Eclipse.
 *
 *
 *
 *
 *
 *  </blockquote>
 *  <p>
 *     A graphical utility that displays the current structure, with controls to permit manual
 *     manipulation of the data structure.
 *  </p>

 * <br>
 * <p>
 * Some utilities for displaying FXML ate from my own library, OPEX, are used for ease with handling the Java FX application,
 * and FXML. I declare the utilities used within my library are of my work only. <br>
 * See more about OPEX <a href="https://www.shinkson47.in/OPEX/Home.html">here</a>
 * </p>
 * <br>

 *
 *
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 20/11/2020</a>
 * @version 1
 * @since v1
 */
public class GUIDemo extends FXMLMain<Controller> {

    public static final Controller DUMMY = new Controller();

    public GUIDemo(){
        this(DUMMY);
    }

    private GUIDemo(Controller _dummyController) {
        super(_dummyController);
    }

    @Override
    protected void preLoad() {

    }

    /**
     * Operations to be performed after the FXML form has been opened.
     */
    @Override
    protected void postLoad() {
        controller.showAlert(Alert.AlertType.WARNING, "This utility contains flashing animations to indicate views that have been updated. Flashing can be disabled with the relevant checkbox found the top left. You can also disable the pop-up confirmations in the same place.");
        controller.resetData();
        controller.reRenderGlobal();
    }

    /**
     * this class is already bootable, but just to be sure. <br><br>
     *
     * For accessibility only, forwarded main override. I have no idea how well eclipse will handle the main that's
     * abstracted within the extension hierarchy.
     *
     *
     * @param args
     * @deprecated Class is already bootable via JavaFX Application within super.
     */
    @Deprecated
    public static void main(String[] args) {
        launch(args);
    }

}
