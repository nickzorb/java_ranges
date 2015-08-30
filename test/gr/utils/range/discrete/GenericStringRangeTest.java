/*
 * Copyright 2015 Nikolaos Zormpas <nickzorb@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gr.utils.range.discrete;

import gr.hua.utils.range.RangeFactory;
import gr.hua.utils.range.RangeIterator;
import java.text.ParseException;
import static jdk.nashorn.internal.objects.Global.instance;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikolaos Zormpas <nickzorb@gmail.com>
 */
public class GenericStringRangeTest {
    
    public GenericStringRangeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * Test of min method, of class GenericStringRange.
     */
    @Test
    public void testMin() throws ParseException {
        System.out.println("min");
        String s = "{\"test1\",\"test2\",\"test3\"}";
        GenericStringRange instance = (GenericStringRange) RangeFactory.parseString(s);
        String expResult = "test1";
        String result = instance.min();
        assertEquals(expResult, result);
    }

    /**
     * Test of max method, of class GenericStringRange.
     */
    @Test
    public void testMax() throws ParseException {
        System.out.println("max");
        String s = "{\"test1\",\"test2\",\"test3\"}";
        GenericStringRange instance = (GenericStringRange) RangeFactory.parseString(s);
        String expResult = "test3";
        String result = instance.max();
        assertEquals(expResult, result);
    }

    /**
     * Test of step method, of class GenericStringRange.
     */
    @Test
    public void testStep() throws ParseException {
        System.out.println("step");
        String s = "{\"test1\",\"test2\",\"test3\"}";
        GenericStringRange instance = (GenericStringRange) RangeFactory.parseString(s);
        String expResult = null;
        String result = instance.step();
        assertEquals(expResult, result);
    }

    /**
     * Test of maxSpeed method, of class GenericStringRange.
     */
    @Test
    public void testMaxSpeed() throws ParseException {
        System.out.println("maxSpeed");
        String s = "{\"test1\",\"test2\",\"test3\"}";
        GenericStringRange instance = (GenericStringRange) RangeFactory.parseString(s);
        Long expResult = 1L;
        Long result = instance.maxSpeed();
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class GenericStringRange.
     */
    @Test
    public void testSize() throws ParseException {
        System.out.println("size");
        String s = "{\"test1\",\"test2\",\"test3\"}";
        GenericStringRange instance = (GenericStringRange) RangeFactory.parseString(s);
        Long expResult = 3L;
        Long result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of empty method, of class GenericStringRange.
     */
    @Test
    public void testEmpty() throws ParseException {
        System.out.println("empty");
        String s = "{\"test1\",\"test2\",\"test3\"}";
        GenericStringRange instance = (GenericStringRange) RangeFactory.parseString(s);
        boolean expResult = false;
        boolean result = instance.empty();
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class GenericStringRange.
     */
    @Test
    public void testContains() throws ParseException {
        System.out.println("contains");
        String s = "{\"test1\",\"test2\",\"test3\"}";
        GenericStringRange instance = (GenericStringRange) RangeFactory.parseString(s);
        boolean expResult = true;
        boolean result = instance.contains("test1");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.contains("test15");
        assertEquals(expResult, result);
    }

    /**
     * Test of iterate method, of class GenericStringRange.
     */
    @Test
    public void testIterate() throws ParseException {
        System.out.println("iterate");
        String[] items = new String[] { "test1", "test2", "test3" };
        String s = "{\"test1\",\"test2\",\"test3\"}";
        GenericStringRange instance = (GenericStringRange) RangeFactory.parseString(s);
        RangeIterator<String> iter = instance.iterate();
        for (int i = 0; i < 3; i++) {
            assertEquals(iter.next(), items[i]);
        }
    }

    /**
     * Test of clone method, of class GenericStringRange.
     */
    @Test
    public void testClone() throws ParseException {
        System.out.println("clone");
        String s = "{\"test1\",\"test2\",\"test3\"}";
        GenericStringRange instance = (GenericStringRange) RangeFactory.parseString(s);
        GenericStringRange result = (GenericStringRange) instance.clone();
        assertEquals(instance.toString(), result.toString());
    }

    /**
     * Test of toString method, of class GenericStringRange.
     */
    @Test
    public void testToString() throws ParseException {
        System.out.println("toString");
        String s = "{\"test1\",\"test2\",\"test3\"}";
        GenericStringRange instance = (GenericStringRange) RangeFactory.parseString(s);
        String result = instance.toString();
        assertEquals(s, result);
    }
    
}
