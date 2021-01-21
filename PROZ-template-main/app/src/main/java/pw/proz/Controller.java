package pw.proz;


import com.sun.javafx.tk.ImageLoader;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class Controller {
    private ModelOrganizer charQ;

    private AssetViewer imageLoader;

    @FXML
    private Label viewLog;
    @FXML
    private GridPane stage;
    @FXML
    private Button skillOne;
    @FXML
    private Button skillTwo;
    @FXML
    private Button skillThree;
    @FXML
    private Button skillFour;
    @FXML
    private Button skillFive;
    @FXML
    private Button skillSix;
    @FXML
    private Label curInfo;
    @FXML
    private Label int0;
    @FXML
    private Label int1;
    @FXML
    private Label int2;
    @FXML
    private Label int3;
    @FXML
    private Label int4;
    @FXML
    private Label int5;
    @FXML
    private Label int6;




    public void initialized(ModelOrganizer e) {

        charQ = e;
        imageLoader = new AssetViewer(charQ);

        imageLoader.linkInitiative(int0, int1, int2, int3, int4, int5, int6);
        imageLoader.linkSkillButton(skillOne, skillTwo, skillThree, skillFour, skillFive, skillSix);
        imageLoader.resetSkills();

        charQ.setPointer(charQ.getCharacterLoader().get(0));


        imageLoader.loadCharacterModels(stage, charQ);
        imageLoader.loadInitiative(charQ);
        imageLoader.loadSkills(charQ);



//        LoadSkills();
    }

    @FXML
    public void ChangeActiveSkill() {
        if (skillOne.isPressed()) {
            charQ.setActiveSkill(charQ.getPointer().getEquipment().get(0));

        }
        if (skillTwo.isPressed()) {
            charQ.setActiveSkill(charQ.getPointer().getEquipment().get(1));
        }
        if (skillThree.isPressed()) {
            charQ.setActiveSkill(charQ.getPointer().getEquipment().get(2));
        }
        if (skillFour.isPressed()) {
            charQ.setActiveSkill(charQ.getPointer().getEquipment().get(3));
        }
        if (skillFive.isPressed()) {
            charQ.setActiveSkill(charQ.getPointer().getEquipment().get(4));
        }
        if (skillSix.isPressed()) {
            charQ.setActiveSkill(charQ.getPointer().getEquipment().get(5));
        }
    }

    //Unnecessary Long but otherwise doesnt work -Corecctly show Skill info
    @FXML
    public void ShowSkillInfo1() {
        if (!imageLoader.getbuttonSkillList().get(0).isDisabled()) {
            viewLog.setText(charQ.getPointer().getEquipment().get(0).DisplayInfo());
        }
    }

    @FXML
    public void ShowSkillInfo2() {
        if (!imageLoader.getbuttonSkillList().get(1).isDisabled()) {
            viewLog.setText(charQ.getPointer().getEquipment().get(1).DisplayInfo());
        }
    }

    @FXML
    public void ShowSkillInfo3() {
        if (!imageLoader.getbuttonSkillList().get(2).isDisabled()) {
            viewLog.setText(charQ.getPointer().getEquipment().get(2).DisplayInfo());
        }
    }

    @FXML
    public void ShowSkillInfo4() {
        if (!imageLoader.getbuttonSkillList().get(3).isDisabled()) {
            viewLog.setText(charQ.getPointer().getEquipment().get(3).DisplayInfo());
        }
    }

    @FXML
    public void ShowSkillInfo5() {
        if (!imageLoader.getbuttonSkillList().get(4).isDisabled()) {
            viewLog.setText(charQ.getPointer().getEquipment().get(4).DisplayInfo());
        }
    }

    @FXML
    public void ShowSkillInfo6() {
        if (!imageLoader.getbuttonSkillList().get(5).isDisabled()) {
            viewLog.setText(charQ.getPointer().getEquipment().get(5).DisplayInfo());
        }
    }


    @FXML
    public void HandleMouseEnter() {
        viewLog.setText("Im a viewLog");
    }


    public void ShowCordinates(MouseEvent event) { // shows coordinates and info on other characters

        int mouseX = (int) event.getX() / 40;
        int mouseY = (int) event.getY() / 40;


        if (charQ.returnFromCordinates(mouseX, mouseY).getName() != "NULL") {
            viewLog.setText(charQ.returnFromCordinates(mouseX, mouseY).DisplayInfo());
            curInfo.setText(charQ.getPointer().DisplayInfo());
        } else {
            viewLog.setText("");
            curInfo.setText(charQ.getPointer().DisplayInfo());
        }

    }


    @FXML
    public void TakeAction(MouseEvent event) {
        /* When Action is declared by clicking on the map two things can happen
        1. No enemies on square clicked - if ing range of movement , move there.
        2. If square is occupied you do attack
        Action ecenomy is taken care in Character class both in move and attack method

        */
        int mouseX = (int) (event.getX() / 40);
        int mouseY = (int) (event.getY() / 40);

        int mX = (int) event.getX() / 40 - charQ.getPointer().getposX();
        int mY = (int) event.getY() / 40 - charQ.getPointer().getposY();  //variables for moving


        int CharIndex = charQ.getCharacterLoader().indexOf(charQ.getPointer()); // to avoid attacking yourself
        int m = CharIndex + 1;

        boolean isOccupied = false; // Is square you clicked on occupied - if yes go to attack. if not move there. Check if its in range!!
        for (int i = 0; i < charQ.getCharacterLoader().size() - 1; i++) {
            if ((charQ.getCharacterLoader().get(m % charQ.getCharacterLoader().size()).getposY() == mouseY) && (charQ.getCharacterLoader().get(m % charQ.getCharacterLoader().size()).getposX() == mouseX)) { // iterate through list, avoiding yourself
                isOccupied = true;

            }
            m++;
        }
        if (isOccupied) {

            viewLog.setText(charQ.getPointer().attack(charQ.returnFromCordinates(mouseX, mouseY), charQ.getActiveSkill()));

            if (charQ.returnFromCordinates(mouseX, mouseY).getHp() <= 0) {

                imageLoader.killModel(charQ.getCharacterLoader().indexOf(charQ.returnFromCordinates(mouseX, mouseY)));
                charQ.getCharacterLoader().remove(charQ.getCharacterLoader().indexOf(charQ.returnFromCordinates(mouseX, mouseY)));
                // change model texture to blood splatter and remove from active model list -> its linked to gridpane so it wont disappear
                // then remove it from Model mechanics

            }
        }
        else {
            viewLog.setText(charQ.moveCharacter(mX, mY));
            imageLoader.movePointerModel(charQ);
            //Move character adn then read it current position and change model accordingly
        }

    }


    @FXML
    public void NextTurn() {
        charQ.nextTurn();
        imageLoader.updateModels(charQ);
        imageLoader.resetSkills();
        imageLoader.loadSkills(charQ);
        imageLoader.loadInitiative(charQ);
        imageLoader.markPointer(charQ);
    }
    // Change current pointer ie active character to new one, giving previous one 2 action points
    // then update models -> in this case just delete green border
    // turn off all butons and reset texture then load new ones from current pointer
    // update Iniative and then add greeen border to pointer model



}

