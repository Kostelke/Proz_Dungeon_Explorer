package pw.proz;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class AssetViewer {

    private List<Label> characterModels = new ArrayList<Label>();
    private List<Button> buttonSkillList = new ArrayList<Button>(); // to ease access to skill buttons on bottom menu
    private List<Label> iniative = new ArrayList<Label>();


    public AssetViewer(ModelOrganizer backObjects) {
        for (int i = 0; i < backObjects.getCharacterLoader().size(); i++) {
            characterModels.add(new Label());
            characterModels.get(i).setPrefHeight(40);
            characterModels.get(i).setPrefWidth(40);
            if (backObjects.getCharacterLoader().get(i).getType() == 0) {// goblin
                characterModels.get(i).setStyle("-fx-background-image: url('goblin.png')");
            } else if (backObjects.getCharacterLoader().get(i).getType() == 1) { // demon
                characterModels.get(i).setStyle("-fx-background-image: url('demon.png')");

            } else if (backObjects.getCharacterLoader().get(i).getType() == 2) { // beholder
                characterModels.get(i).setStyle("-fx-background-image: url('beholder.png')");

            } else if (backObjects.getCharacterLoader().get(i).getType() == 3) { // knight
                characterModels.get(i).setStyle("-fx-background-image: url('knight.png')");

            } else if (backObjects.getCharacterLoader().get(i).getType() == 4) { // mage
                characterModels.get(i).setStyle("-fx-background-image: url('mage.png')");
            }
            /// Load url into Character Labels - worry about adding them later

        }
        //Buttons must be added later.
        // Through Method
        //the same goes for Iniative as buttons are already in place
    }

    public void loadCharacterModels(GridPane stage, ModelOrganizer backObjects) {
        for (int i = 0; i < characterModels.size(); i++) {
            stage.add(characterModels.get(i), 1, 1);
            characterModels.get(i).setTranslateY(backObjects.getCharacterLoader().get(i).getposY() * 40);
            characterModels.get(i).setTranslateX(backObjects.getCharacterLoader().get(i).getposX() * 40);
        }
    }


    public void linkInitiative(Label one, Label two, Label three, Label four, Label five, Label six, Label seven) {
        iniative.add(one);
        iniative.add(two);
        iniative.add(three);
        iniative.add(four);
        iniative.add(five);
        iniative.add(six);
        iniative.add(seven);
        //just link so its easier to manipulate Labels
    }

    public void loadInitiative(ModelOrganizer backObjects) {
        int m = backObjects.getCharacterLoader().indexOf(backObjects.getPointer()); // so we know where in initiative we are
        for (int n = 0; n < iniative.size(); n++) {
            if (backObjects.getCharacterLoader().get(m % backObjects.getCharacterLoader().size()).getType() == 0) {
                iniative.get(n).setStyle("-fx-background-image: url('goblinBIG.png')");
            } else if (backObjects.getCharacterLoader().get(m % backObjects.getCharacterLoader().size()).getType() == 1) {
                iniative.get(n).setStyle("-fx-background-image: url('demonBIG.png')");
            } else if (backObjects.getCharacterLoader().get(m % backObjects.getCharacterLoader().size()).getType() == 2) {
                iniative.get(n).setStyle("-fx-background-image: url('beholderBIG.png')");
            } else if (backObjects.getCharacterLoader().get(m % backObjects.getCharacterLoader().size()).getType() == 3) {
                iniative.get(n).setStyle("-fx-background-image: url('knightBIG.png')");
            } else if (backObjects.getCharacterLoader().get(m % backObjects.getCharacterLoader().size()).getType() == 4) {
                iniative.get(n).setStyle("-fx-background-image: url('mageBIG.png')");
            }
            m++;

        }

    }

    public void linkSkillButton(Button one, Button two, Button three, Button four, Button five, Button six) {
        buttonSkillList.add(one);
        buttonSkillList.add(two);
        buttonSkillList.add(three);
        buttonSkillList.add(four);
        buttonSkillList.add(five);
        buttonSkillList.add(six);
    }
     // the same as link iniative

    public void loadSkills(ModelOrganizer backObject) {
        for (int i = 0; i < backObject.getPointer().getEquipment().size(); i++) {
            buttonSkillList.get(i).setStyle("-fx-background-image: url('" + backObject.getPointer().getEquipment().get(i).getSource() + ".png')"); // whole command to set image
            buttonSkillList.get(i).setDisable(false);
        }
    }
    //Load from current pointer



    public void resetSkills() {
        for (int i = 0; i < buttonSkillList.size(); i++) {
            buttonSkillList.get(i).setDisable(true);
            buttonSkillList.get(i).setStyle("-fx-background-image: url('button_skill.jpg')");
        }
    }
    //just to reset buttons after end turn
    public void movePointerModel(ModelOrganizer backObjects){
        int n = backObjects.getCharacterLoader().indexOf(backObjects.getPointer()); // return base pointer index
        characterModels.get(n).setTranslateY(backObjects.getPointer().getposY()*40);
        characterModels.get(n).setTranslateX(backObjects.getPointer().getposX()*40);

    }
    public List<Button> getbuttonSkillList(){
        return buttonSkillList;
    }
    public void killModel(int n){
      characterModels.get(n).setStyle("-fx-background-image: url('blood-puddle-png-8.png')");
        characterModels.remove(n);
    }
    public void updateModels(ModelOrganizer backObjects){
        for (int i = 0; i < backObjects.getCharacterLoader().size(); i++) {
            if (backObjects.getCharacterLoader().get(i).getType() == 0) {// goblin
                characterModels.get(i).setStyle("-fx-background-image: url('goblin.png')");
            } else if (backObjects.getCharacterLoader().get(i).getType() == 1) { // demon
                characterModels.get(i).setStyle("-fx-background-image: url('demon.png')");

            } else if (backObjects.getCharacterLoader().get(i).getType() == 2) { // beholder
                characterModels.get(i).setStyle("-fx-background-image: url('beholder.png')");

            } else if (backObjects.getCharacterLoader().get(i).getType() == 3) { // knight
                characterModels.get(i).setStyle("-fx-background-image: url('knight.png')");

            } else if (backObjects.getCharacterLoader().get(i).getType() == 4) { // mage
                characterModels.get(i).setStyle("-fx-background-image: url('mage.png')");
            }

        }

    }
    public void markPointer(ModelOrganizer backObjects){
        int i= backObjects.getCharacterLoader().indexOf(backObjects.getPointer());
        if (backObjects.getCharacterLoader().get(i).getType() == 0) {// goblin
            characterModels.get(i).setStyle("-fx-border-color: green; -fx-border-width: 3; -fx-background-image: url('goblin.png'); ");
        } else if (backObjects.getCharacterLoader().get(i).getType() == 1) { // demon
            characterModels.get(i).setStyle("-fx-border-color: green; -fx-border-width: 3; -fx-background-image: url('demon.png')");

        } else if (backObjects.getCharacterLoader().get(i).getType() == 2) { // beholder
            characterModels.get(i).setStyle("-fx-border-color: green; -fx-border-width: 3; -fx-background-image: url('beholder.png')");

        } else if (backObjects.getCharacterLoader().get(i).getType() == 3) { // knight
            characterModels.get(i).setStyle("-fx-border-color: green; -fx-border-width: 3; -fx-background-image: url('knight.png')");

        } else if (backObjects.getCharacterLoader().get(i).getType() == 4) { // mage
            characterModels.get(i).setStyle("-fx-border-color: green; -fx-border-width: 3; -fx-background-image: url('mage.png')");
        }

    }

}


