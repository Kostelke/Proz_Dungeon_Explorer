package pw.proz;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Character {

    private String name;
    private String URL;
    private String URL2;

    private int counter; // counter to how many times one can do various actions in turn. default 2
    private int AC;
    private int hp;
    private int move;
    private int str;

    private int dex;
    private int exp;
    private int urlType;

    private int con; //four characteristics - str for melee damage, wis for magic , dex for ranged and con asa pseudo health thing TODO implement saves
    private int level; // starting at 1. determines HP and primary stat
    private int posX;
    private int posY; // Used as a way to control where each actor is
    private String type; // either creature or Object. Maybe change to boolean? might be easier
    private ArrayList<Skill> Equipment = new ArrayList<Skill>();

    //todo delete all Image type things make tests
    //TODO delete this boolean stuff
    public Character(int x, int y, int uType, int lvl, String nam) {

        urlType=uType;
        name = nam;
        level = lvl;
        counter = 2;
        move = 6;
        con = (int) (Math.random() * (4 - 1) + 1) + (int) (Math.random() * (4 - 1) + 1) + (int) (Math.random() * (4 - 1) + 1);
        str = (int) (Math.random() * (4 - 1) + 1) + (int) (Math.random() * (4 - 1) + 1) + (int) (Math.random() * (4 - 1) + 1);
        dex = (int) (Math.random() * (4 - 1) + 1) + (int) (Math.random() * (4 - 1) + 1) + (int) (Math.random() * (4 - 1) + 1);
        hp = 8 + (level - 1) * ThreadLocalRandom.current().nextInt(1, 6) + level * con / 3;
        AC = 10 + dex / 3 + level;
        posX = x;
        posY = y;
        exp = 0;
        Equipment.add(new Skill(level));
        type = "Creature";
    }


    public void takeDamage(int damage) {
        hp = hp - damage;
    }


    public String attack(Character target, Skill action) {
        String g;
        if (counter > 0) {
            if ((target.getposX()-posX) * (target.getposX()-posX) + (target.getposY()-posY) * (target.getposY()-posY) <= action.getRange() * action.getRange()){
                int toHit;
            toHit = (int) (Math.random() * (20 - 1) + 1) + str / 3 + action.getRarity();
            if (toHit >= target.getAC()) {
                target.takeDamage(action.getDamage());
            } else {
                counter--;
                g = "\t Target missed!!";
                return g;
            }
            counter--;

            if (action.getDamage() > target.getHp()) {
                g = "\t Target was slain!!";
                exp = exp + target.getlevel();

                if (exp >= 3) {
                    level = level + 1;
                    hp = 8 + (level - 1) * (int) (Math.random() * (6 - 1) + 1) + level * con / 3;
                    exp = exp - 3;
                    AC = 10 + dex / 3 + level;
                    if (Equipment.size() < 6) {
                        Equipment.add(new Skill(level + 1));
                    }
                }
                return g;
            } else {
                g = "\t Target was succesfully hit!";
                return g;
            }
        }else{
                return "Target out of range";
            }
        }
        g = "Not enough actions - end your turn!";
        return g;
    }
    public String attack_test(Character target, int damage) {
        String g;
        if (counter > 0) {
            if ((target.getposX()-posX) * (target.getposX()-posX) + (target.getposY()-posY) * (target.getposY()-posY) <=50){
                int toHit;
                toHit = (int) (Math.random() * (20 - 1) + 1) + str / 3 ;
                if (toHit >= target.getAC()) {
                    target.takeDamage(damage);
                } else {
                    counter--;
                    g = "\t Target missed!!";
                    return g;
                }
                counter--;

                if (damage > target.getHp()) {
                    g = "\t Target was slain!!";
                    exp = exp + target.getlevel();

                    if (exp >= 3) {
                        level = level + 1;
                        hp = 8 + (level - 1) * (int) (Math.random() * (6 - 1) + 1) + level * con / 3;
                        exp = exp - 3;
                        AC = 10 + dex / 3 + level;
                        if (Equipment.size() < 6) {
                            Equipment.add(new Skill(level + 1));
                        }
                    }
                    return g;
                } else {
                    g = "\t Target was succesfully hit!";
                    return g;
                }
            }else{
                return "Target out of range";
            }
        }
        g = "Not enough actions - end your turn!";
        return g;
    }


    public String move(int x, int y) {  //Use to return information about action to event log in game!
        if (counter > 0) {
            if ((x * x + y * y) <= 36) {
                posX = posX + x;
                posY = posY + y;
                counter--;
                return "Character moved";
            } else {
                return "Failed to move";
            }
        } else {
            return "Not enough actions - end turn!";
        }


    }

    public Skill getSkill(int n) {
        return Equipment.get(n);
    }
    public ArrayList<Skill> getEquipment(){
        return Equipment;
    }


    public int getAC() {
        return AC;
    }

    ;

    public void setAC(int n) {
        AC = n;
    }

    ;

    public void resetCounter() {
        counter = 2;
    }

    ;

    public void setCounter(int n) {
        counter = n;
    }

    public String DisplayInfo(){
        String s;
        String b;
        b = "\n\tSTR " + str + "\n\tDEX " + dex + "\n\tCON " + con ;
        s = "\tTYPE: " + type + "\n\tNAME: " + name + "\n\tHP: " + hp + "\n\tAC:" + AC + "\n\tLEVEL: " + level;
        s = s + b;
        return s;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int n) {
        hp = n;
    }

    public int getlevel() {
        return level;
    }

    public int getposX() {
        return posX;
    }

    public int getposY() {
        return posY;
    }

    public int getcounter() {
        return counter;
    }

    public int getmove() {
        return move;
    }

    public String getURL() {
        return URL;
    }

    public String getURL2() {
        return URL2;
    }
    public String getName(){
        return name;
    }
    public int getType(){
        return urlType;
    }

}

