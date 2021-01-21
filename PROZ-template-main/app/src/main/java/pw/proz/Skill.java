package pw.proz;


import javafx.scene.control.Label;

import java.util.concurrent.ThreadLocalRandom;

public class Skill {
    private int damage;
    private int price;
    private String type;
    private int source;
    private int rarity;
    private int range;

    public Skill(int rare) {

        rarity = rare;
        damage = rare * 3 + rare * (int) (Math.random() * (8 - 1) + 1);
        ;
        price = rare * 1000 + (int) (Math.random() * rare * (100 - 1) + 1);
        type = "Skill";
        range = 5 * (int) (Math.random() * (8 - 1) + 1);
        source = (int) (Math.random() * (14 - 1) + 1);

    }

    public String DisplayInfo() {
        return "\t" + "TYPE: " + type + "\n" + "\t" + "DAMAGE: " + damage + "\n" + "\t" + "RARITY: " + rarity + "\n" + "\t" + "RANGE: " + range;
    }

    public int getDamage() {
        return damage;
    }

    public int getSource() {
        return source;
    }


    public int getRarity() {
        return rarity;
    }


    public int getRange() {
        return range;
    }

}
