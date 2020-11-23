package com.shinkson47.datacwk.main.frontend;

import com.shinkson47.datacwk.lib.collection.pool.StudentPool;
import com.shinkson47.datacwk.lib.student.Student;
import com.shinkson47.datacwk.main.GlobalData;
import com.shinkson47.opex.frontend.fxml.FXMLController;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * <h1>Utility methods for the JFX GUI tool.</h1>
 * <br>
 * <p>
 *
 * </p>
 *
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 20/11/2020</a>
 * @version 1
 * @since v1
 */
public class Controller extends FXMLController {

    public static final String GUI_FXML = "../../../datacwk/main/frontend/gui.fxml";
    public static final String NULL_P = "P#######";
    public TextField txtSearchSelectP;
    public Label lblSelectedP;
    public Label lblSelectedMentor;
    public Label lblSelectedMenteeCount;
    public Label lblSelectedIsMentor;
    public Label lblSelectedisMentee;
    public ListView<String> lstSelectedMentees;
    public CheckBox chkAllowNotif;
    public TextField txtInstantiate;
    public AnchorPane paneSelectedStudent;
    public AnchorPane panelGlobalTree;
    public AnchorPane paneGlobalList;
    public TextField txtRemoveMentee;
    public TextField txtUpdateMentee;
    public TextField txtUpdateMentor;
    public TextField txtDetMentor;
    public TextField txtDetMentee;
    public TextField txtRelMentee;
    public TextField txtRelMentor;
    public CheckBox chkAllowFlash;
    public TextField txtTransferMentee;
    private Student selectedStudent = null;
    //#region FXML injections
    public TreeView<String> PoolTree;
    public ListView<String> PoolList;

    //#endregion

    public Controller(){
        this(GUI_FXML);
    }

    public Controller(String _FXML) {
        super(_FXML);
    }

    //#region Global Render
    /**
     * <h2>Clears then re-renders Global Tree and List view</h2>
     * such that they show the global pool in it's current state.
     */
    public void reRenderGlobal() {
        RenderGlobalTree();
        RenderGlobalList();
        animateUpdate(panelGlobalTree);
        animateUpdate(paneGlobalList);
    }

    private void RenderGlobalList() {
        RenderStudentList(PoolList, StudentPool.Global);

    }

    private <T> void RenderStudentList(ListView<String> listView, ArrayList<Student> students){
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Student s : students)
            items.add(s.getpNumber());

        RenderList(listView, items);
    }

    private <T> void RenderList(ListView<T> list, ObservableList<T> olist){
        list.setItems(olist);
    }

    private void RenderGlobalTree() {
        ArrayList<Student> rootElements = StudentPool.Global.findAllRoot();
        TreeItem<String> TreeRoot = new TreeItem<>("GLOBAL MENTOR HIERARCHY");
        for (Student s : rootElements)
            TreeRoot.getChildren().add(constructStudentTree(s));

        PoolTree.setRoot(TreeRoot);
        expandTreeView(TreeRoot);
    }

    public TreeItem<String> constructStudentTree(Student Root){
        TreeItem<String> currentLevel = new TreeItem<>(Root.getpNumber());
        for (Student s : Root.getMentees())
            currentLevel.getChildren().add(
                    constructStudentTree(s)
            );
        return currentLevel;
    }

    private void expandTreeView(TreeItem<?> item){
        if(item != null && !item.isLeaf()){
            item.setExpanded(true);
            for(TreeItem<?> child:item.getChildren()){
                expandTreeView(child);
            }
        }
    }

    public void showAlert(String message){
        showAlert(Alert.AlertType.INFORMATION, message);
    }

    public void showAlert(Alert.AlertType type, String message){
        if (!chkAllowNotif.isSelected() && type == Alert.AlertType.CONFIRMATION) return; // User has requested not to display confirmations, return if alert is a confirmation.

        Alert alert = new Alert(type, message);
        alert.showAndWait();
    }
    //#endregion

    //#region Selected Student

    private boolean requireSelection() {
        if (selectedStudent == null)
            showAlert(Alert.AlertType.ERROR, "No Student is selected!");

        return selectedStudent != null;
    }

    private void clearSelectedStudent() {
        selectStudent(null);
    }

    public void selectStudent(String P){
        selectedStudent = GUISearch(P);
        animateUpdate(paneSelectedStudent);
        lblSelectedP.setText((selectedStudent != null) ? selectedStudent.getpNumber() : NULL_P);
        lblSelectedMentor.setText(selectedStudent != null ? (selectedStudent.hasMentor()) ? selectedStudent.getMentor().getpNumber() : NULL_P : NULL_P );
        lblSelectedisMentee.setText(selectedStudent == null ? "false" : String.valueOf(selectedStudent.isMentee()));
        lblSelectedIsMentor.setText(selectedStudent == null ? "false" : String.valueOf(selectedStudent.isMentor()));
        if (selectedStudent == null) {
            lstSelectedMentees.getItems().clear();
            return;
        }
        RenderStudentList(lstSelectedMentees, selectedStudent.getMentees());
        showAlert(Alert.AlertType.CONFIRMATION, "Selected " + P);
    }

    private Student GUISearch(String p) {
        if (p == null) return null;
        GUIIsValidP(p); // Redundant in terms of checking, but vital for user feedback.
        Student result = StudentPool.Global.findByP(p);

        if (result == null)
            showAlert(Alert.AlertType.ERROR, p + " does not exist in the global pool!");

        return result;
    }

    private boolean GUIIsValidP(String p) {
        boolean result = Student.isValidPNumber(p);
        if (!result)
            showAlert(Alert.AlertType.ERROR, p + " is not in the valid P Number format!");

        return result;
    }

    private void animateUpdate(AnchorPane pane){
        if (!chkAllowFlash.isSelected()) return;

        final Animation animation = new Transition() {                                                                  // Define animation to play.
            // Local Final, only created the first time this is called.
            // Local, not class level - only way to get access to super.anchorPane.
            // Simplified constructor
            {
                setCycleDuration(Duration.millis(500));                                                                 // 0.5 sec
                setInterpolator(Interpolator.EASE_OUT);                                                                 // Animation type. Sets how the below method is called, starting at max and reducing to null over the time specified above.
            }

            @Override
            protected void interpolate(double frac) {
                Color vColor = new Color(GlobalData.col, GlobalData.col, GlobalData.col, frac);                              // Color to use, with opacity based on current point in animation.
                pane.setBackground(new Background(new BackgroundFill(vColor, CornerRadii.EMPTY, Insets.EMPTY)));  // Set background of order item pane with a new background containing the generated color, since the colour of an existing background cannot be modified.
            }
        };

        animation.play();                                                                                               // Start animation; let FXML handle.
    }

    //#endregion

    //#region operations
    public void opSearchAndSelect(ActionEvent actionEvent) {
        selectStudent(txtSearchSelectP.getText());
    }


    public void opInstantiate(ActionEvent actionEvent) {
        String P = txtInstantiate.getText();
        if (GUIIsValidP(P))
            new Student(P);
        reRenderGlobal();
        showAlert(Alert.AlertType.CONFIRMATION, "Created a student with the ID of " + P);
    }

    public void opUnenrole(ActionEvent actionEvent) {
        selectedStudent.delete();
        clearSelectedStudent();
        reRenderGlobal();
    }

    public void resetData(ActionEvent actionEvent) {
        resetData();
        reRenderGlobal();
    }

    public void opDefineRel(ActionEvent actionEvent) {
        StudentPool.setRelationship(
                GUISearch(txtRelMentor.getText()),
                GUISearch(txtRelMentee.getText())
        );
        reRenderGlobal();
    }

    public void opDetMentee(ActionEvent actionEvent) {
        Student s = GUISearch(txtDetMentee.getText());
        if (s == null) return;
        reRenderGlobal();
        showAlert(s.getpNumber() + "#isMentee = " + s.isMentee());
    }

    public void opDetMentor(ActionEvent actionEvent) {
        Student s = GUISearch(txtDetMentor.getText());
        if (s == null) return;
        reRenderGlobal();
        showAlert(s.getpNumber() + "#isMentor = " + s.isMentor());
    }

    public void opUpdateMentor(ActionEvent actionEvent) {
        if (!requireSelection()) return;

        StudentPool.setRelationship(GUISearch(txtUpdateMentor.getText()), selectedStudent);
        reRenderGlobal();
    }

    public void opUpdateMentee(ActionEvent actionEvent) {
        if (!requireSelection()) return;
        StudentPool.setRelationship(selectedStudent, GUISearch(txtUpdateMentee.getText()));
        reRenderGlobal();
    }

    public void opRemoveMentee(ActionEvent actionEvent) {
        if (!requireSelection()) return;

        Student s = GUISearch(txtRemoveMentee.getText());
        if (s == null) return;

        s.clearMentor();
        reRenderGlobal();
        showAlert(Alert.AlertType.CONFIRMATION, s.getpNumber() + " is no longer a mentee of " + selectedStudent.getpNumber() + "!");
    }

    public void opClearMentor(ActionEvent actionEvent) {
        if (!requireSelection()) return;
        selectedStudent.clearMentor();
        reRenderGlobal();
        showAlert(Alert.AlertType.CONFIRMATION, selectedStudent + " no longer has a mentor!");
    }

    public void opTransferMentees(ActionEvent actionEvent) {
        if (!requireSelection()) return;
        selectedStudent.transferMenteesTo(GUISearch(txtTransferMentee.getText()));
        reRenderGlobal();
    }
    //#endregion





    //#region static
    /**
     * <h2>Resets the database to represent Table 1 from the spec.</h2>
     * Clears all existing data, re-instantiates default students, and reassignes default relationships.
     */
    public static void resetData() {
        formatData();
        StudentPool.createManyStudents(GlobalData.SPEC_TABLE_ONE);
        GlobalData.CreateDefaultRelationships();
    }

    /**
     * <h2>Removes all students from the global pool.</h2>
     */
    public static void formatData() {
        StudentPool.Global.clear();
    }

    public void formatData(ActionEvent actionEvent) {
        formatData();
        reRenderGlobal();
    }

    public void treeSelect(MouseEvent mouseEvent) {
        TreeItem<String> selectedTreeItem = PoolTree.getSelectionModel().getSelectedItem();
        if (selectedTreeItem == null) return;
        selectStudent(selectedTreeItem.getValue());
    }

    public void poolSelect(MouseEvent mouseEvent) {
        selectStudent(PoolList.getSelectionModel().getSelectedItem());
    }

    public void selectedSelect(MouseEvent mouseEvent) {
        selectStudent(lstSelectedMentees.getSelectionModel().getSelectedItem());
    }




    //#endregion
}
