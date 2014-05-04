/*
 * Copyright 2014 NickZorb.
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

package gr.hua.utils.range.discrete;

import gr.hua.utils.range.Range;
import gr.hua.utils.range.RangeIterator;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author NickZorb
 */
public class DiscreteRangeTest {
    
    public DiscreteRangeTest() {
    }

    /**
     * Test of discretize method, of class DiscreteRange.
     */
    @Test
    public void testDiscretize() {
        System.out.println("discretize");
//        DiscreteRange.discretize(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of combine method, of class DiscreteRange.
     */
    @Test
    public void testCombine() {
        System.out.println("combine");
        DiscreteRange.combine(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseRange method, of class DiscreteRange.
     */
    @Test
    public void testParseRange() {
        System.out.println("parseRange");
        String s = "";
        DiscreteRange<String> expResult = null;
        DiscreteRange<String> result = DiscreteRange.parseRange(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class DiscreteRange.
     */
    @Test
    public void testRemove_GenericType() {
        System.out.println("remove");
        Object item = null;
        DiscreteRange instance = new DiscreteRangeImpl();
        instance.remove(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of append method, of class DiscreteRange.
     */
    @Test
    public void testAppend_GenericType() {
        System.out.println("append");
        Object item = null;
        DiscreteRange instance = new DiscreteRangeImpl();
        instance.append(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class DiscreteRange.
     */
    @Test
    public void testRemove_Collection() {
        System.out.println("remove");
        DiscreteRange instance = new DiscreteRangeImpl();
//        instance.remove(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class DiscreteRange.
     */
    @Test
    public void testRemove_DiscreteRange() {
        System.out.println("remove");
        DiscreteRange instance = new DiscreteRangeImpl();
//        instance.remove(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of append method, of class DiscreteRange.
     */
    @Test
    public void testAppend_Collection() {
        System.out.println("append");
        DiscreteRange instance = new DiscreteRangeImpl();
//        instance.append(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of append method, of class DiscreteRange.
     */
    @Test
    public void testAppend_DiscreteRange() {
        System.out.println("append");
        DiscreteRange instance = new DiscreteRangeImpl();
//        instance.append(null);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of itemSet method, of class DiscreteRange.
     */
    @Test
    public void testItemSet() {
        System.out.println("itemSet");
        DiscreteRange instance = new DiscreteRangeImpl();
        Set expResult = null;
        Set result = instance.itemSet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of split method, of class DiscreteRange.
     */
    @Test
    public void testSplit() {
        System.out.println("split");
        Object sub = null;
//        DiscreteRange instance = new DiscreteRangeImpl();
//        List<DiscreteRange<T>> expResult = null;
//        List<DiscreteRange<T>> result = instance.split(sub);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DiscreteRange.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DiscreteRange instance = new DiscreteRangeImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DiscreteRangeImpl<String> implements DiscreteRange<String> {

        @Override
        public void remove(String item) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void append(String item) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Set<String> itemSet() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<DiscreteRange<String>> split(String sub) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean empty() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean contains(String item) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public RangeIterator<String> iterate() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Range<String> clone() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int estimateSize() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
    
}
