/*
 * Copyright 2015 Nikolaos Zormpas.
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
package gr.hua.utils.range.numeric;

import gr.hua.utils.range.RangeFactory;
import java.text.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikolaos Zormpas
 */
public class GenericNumericRangeTest {

    /**
     * Test of clone method, of class GenericNumericRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testClone() throws ParseException {
        System.out.println("clone");
        String s = "[0/15]/1/3";
        GenericNumericRange instance = (GenericNumericRange) RangeFactory.parseString(s);
        GenericNumericRange result = instance.clone();
        assertEquals(instance, result);
    }

    /**
     * Test of maxSpeed method, of class GenericNumericRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testMaxSpeed() throws ParseException {
        System.out.println("maxSpeed");
        String s = "[0/15]/1/3";
        GenericNumericRange instance = (GenericNumericRange) RangeFactory.parseString(s);
        Long expResult = 3L;
        Long result = instance.maxSpeed();
        assertEquals(expResult, result);
    }

    /**
     * Test of min method, of class GenericNumericRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testMin() throws ParseException {
        System.out.println("min");
        String s = "[0/15]/1/3";
        GenericNumericRange instance = (GenericNumericRange) RangeFactory.parseString(s);
        Number expResult = 0L;
        Number result = instance.min();
        assertEquals(expResult, result);
    }

    /**
     * Test of max method, of class GenericNumericRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testMax() throws ParseException {
        System.out.println("max");
        String s = "[0/15]/1/3";
        GenericNumericRange instance = (GenericNumericRange) RangeFactory.parseString(s);
        Number expResult = 15L;
        Number result = instance.max();
        assertEquals(expResult, result);
    }

    /**
     * Test of step method, of class GenericNumericRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testStep() throws ParseException {
        System.out.println("step");
        String s = "[0/15]/2/3";
        GenericNumericRange instance = (GenericNumericRange) RangeFactory.parseString(s);
        Number expResult = 2L;
        Number result = instance.step();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class GenericNumericRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testEquals() throws ParseException {
        System.out.println("equals");
        String s = "[0/15]/2/3";
        GenericNumericRange instance = (GenericNumericRange) RangeFactory.parseString(s);
        Object other = RangeFactory.parseString(s);
        boolean expResult = true;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
    }
}
