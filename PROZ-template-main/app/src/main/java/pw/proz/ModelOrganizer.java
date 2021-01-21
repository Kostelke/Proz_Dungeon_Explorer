package pw.proz;

import java.util.ArrayList;

public class ModelOrganizer {

    private ArrayList<Character> characterLoader;
    private Character Nullreturner; // Return empty character when no info is found. Char name would be null to easily check in Controller
    private Character pointer;
    private Skill activeSkill;

    ModelOrganizer() {
        characterLoader = new ArrayList<Character>();
        Nullreturner = new Character(0, 0, 0, 0, "NULL" );
        pointer = Nullreturner;
    }


    public void addCharacter(int utype, int posX, int posY, int level, String name) {
        characterLoader.add(new Character(posX, posY, utype, level, name));

    }

    public Character returnFromCordinates(int x, int y) {

        if (characterLoader.size() != 0) {
            for (int n = 0; n < characterLoader.size(); n++) {
                if (characterLoader.get(n).getposX() == x && characterLoader.get(n).getposY() == y) {

                    return characterLoader.get(n);
                }
            }


        }
        return Nullreturner;
    }

    public String moveCharacter(int x, int y) {
        return pointer.move(x, y);
    }

    public void setPointer(Character toPointer) {
        pointer = toPointer;
        setActiveSkill(toPointer.getSkill(0)); // TODO make getSKiil() in Character
    }

    public void attack(Character target) {
        pointer.attack(target, activeSkill);
    }

    public void setActiveSkill(Skill active) {
        activeSkill = active;
    }

    public String getCharUrl(int n) {
        return characterLoader.get(n).getURL();
    }

    public String getBigCharUrl(int n) {
        return characterLoader.get(n).getURL2();
    }

    public int getSkillUrl(Skill skill) {
        return skill.getSource();
    }

    public void nextTurn() {
        pointer.resetCounter();
        setPointer(characterLoader.get((characterLoader.indexOf(pointer) + 1) % characterLoader.size()));
    }
    public ArrayList<Character> getCharacterLoader(){
        return characterLoader;
    }
    public Character getPointer(){
        return pointer;
    }
    public String getName(Character e){
        return e.getName();
    }
    public Skill getActiveSkill(){
        return  activeSkill;
    }


}
