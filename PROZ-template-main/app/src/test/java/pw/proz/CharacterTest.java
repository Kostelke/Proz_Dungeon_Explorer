package pw.proz;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CharacterTest extends TestCase {
    private Character test;
    private Character test2;
    private Character test_attack;
    private Character test_attack2;
    private Character test_target0;
    private Character test_target1;
    private Character test_target2;
    private Character test_target3;

    private int a;


    @Test
    public void testtakeDamage() {
        //given
        test = new Character(1, 1, 0, 29, "Sułtan kosmitów");
        a = test.getHp();

        //when
        test.takeDamage(5);

        //then
        assertTrue(a == (test.getHp() + 5));

    }

    @Test
    public void testattack() {
        //given
        String message1 ="\t Target was slain!!" ;
        String message2 = "\t Target was succesfully hit!";
        String message3 = "Not enough actions - end your turn!";
        String message4 = "\t Target missed!!" ;
        test_target0 = new Character(0, 0, 0, 0, "ZERO_HP");        // test if kills
        test_target1 = new Character(0, 0, 0, 1, "HUGE_HP");        // test if damages and see if correctly
        test_target2 = new Character(0, 0, 0, 1, "ZERO_HP");        // test when no actions
        test_target3 = new Character(0, 0, 0, 2, "ZERO_HP");        //test when missed

        test_attack = new Character(0, 0, 0, 5, "ddd");
        test_attack2 = new Character(0, 0, 0, 5, "ddd");
        test_attack.setCounter(20);
        test_target0.setHp(1);
        test_target0.setAC(0);
        test_target1.setAC(0);
        test_target1.setHp(40);
        test_attack2.setCounter(0);
        test_target3.setAC(60);
        a = test_target1.getHp();

        //when
        test_attack.attack_test(test_target1, 11);

        //then
        assertTrue(test_attack.attack_test(test_target0, 5)==message1);
        assertTrue(a == (test_target1.getHp() + 11));
        assertSame(message2, test_attack.attack_test(test_target1, 1));

        assertTrue(test_attack2.attack_test(test_target2, 5)==message3);
        assertTrue(test_attack.attack_test(test_target3, 5)==message4);




    }

    @Test
    public void testmove() {
        //given
        test2 = new Character(0, 0, 2, 2, "Zorro");

        //when
        test2.move(1, 1);

        // then

        assertTrue(test2.getposX() == 1);
        assertTrue(test2.getposY() == 1);
    }





}