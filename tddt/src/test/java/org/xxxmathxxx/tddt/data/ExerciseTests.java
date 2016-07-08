package org.xxxmathxxx.tddt.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExerciseTests {
    @Test public void constructorTest() {
    	Exercise temp=new Exercise("thisIsAName", 1241451l, "thisIsADescription", null, null, null, null);
        
       // assertEquals(temp.name, "thisIsAName");
        //assertEquals(temp.id, "thisIsAnID");
        //assertEquals(temp.description, "thisIsADescription");
        
    }
}
