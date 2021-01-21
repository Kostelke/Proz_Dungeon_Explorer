package pw.proz;

import junit.framework.TestCase;

public class ModelOrganizerTest extends TestCase {
    ModelOrganizer model = new ModelOrganizer();
    Character testReturn = new Character(0,0,0,0,"NULL");
    Character testPointer1 = new Character(0,0,1,0,"NULL");
    Character testPointer2 = new Character(0,0,2,0,"NULL");



    public void testAddCharacter(){
        model.addCharacter(0,0,0,1,"NULL");
        assertEquals(1, model.getCharacterLoader().size());
        assertEquals(0,model.getCharacterLoader().get(0).getposX());
        assertEquals(0,model.getCharacterLoader().get(0).getposY());
        model.getCharacterLoader().remove(0);
    }

    public void testReturnFromCoordinates(){
        model.getCharacterLoader().add(0,testReturn);
        assertSame(testReturn, model.returnFromCordinates(0,0));
        model.getCharacterLoader().remove(0);
    }
    public void testNextTurn(){
        testPointer1.setCounter(0);
        model.getCharacterLoader().add(0,testPointer1);
        model.getCharacterLoader().add(1,testPointer2);
        model.setPointer(testPointer1);
        model.nextTurn();
        assertEquals(2, testPointer1.getcounter());
        assertSame(testPointer2, model.getPointer());


    }



}