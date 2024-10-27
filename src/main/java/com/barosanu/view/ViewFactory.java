package com.barosanu.view;

import com.barosanu.EmailManager;
import com.barosanu.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ViewFactory {

    private EmailManager emailManager;
    private ArrayList<Stage> activeStage;
    private boolean mainViewInitialized = false;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
        activeStage = new ArrayList<Stage>();
    }

   public boolean isMainViewInitialized() {
        return mainViewInitialized;
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    //View options handling:
    private ColorTheme colorTheme = ColorTheme.DEFAULT;
    private FontSize fontSize = FontSize.MEDIUM;


    public void showLoginWindow(){
        System.out.println("show login window called ");

        BaseController controller = new LoginWindowController(emailManager,this, "/view/LoginWindow.fxml");
        initializeStage(controller);
    }

    public void showMainWindow() {
        System.out.println("main window called ");

        BaseController controller = new MainWindowController(emailManager,this, "/view/MainWindow.fxml");
        initializeStage(controller);
        mainViewInitialized = true;
    }
    public void showEmailDetailsWindow(){
        BaseController controller = new EmailDetailsController(emailManager, this, "/view/EmailDetailsWindow.fxml");
        initializeStage(controller);
    }

    public void showOptionsWindow(){
        System.out.println("options window called ");

        BaseController controller = new OptionsWindowController(emailManager,this, "/view/OptionsWindow.fxml");
        initializeStage(controller);
    }

    public void showComposeMessageWindow(){
        System.out.println("compose message window called ");

        BaseController controller = new ComposeMessageController(emailManager,this, "/view/ComposeMessageWindow.fxml");
        initializeStage(controller);
    }

    private void initializeStage(BaseController controller){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        activeStage.add(stage);

    }

    public void closeStage(Stage stageToClose){
        stageToClose.close();
        activeStage.remove(stageToClose);
    }


    public void updateStyles() {
    for (Stage stage: activeStage) {
        Scene scene = stage.getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(ColorTheme.getCssPath(colorTheme)).toExternalForm());
        scene.getStylesheets().add(getClass().getResource(FontSize.getCssPath(fontSize)).toExternalForm());

    }
    }
}
