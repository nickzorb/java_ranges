/*
 * Copyright 2014 Nick Zorbas.
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

import gr.hua.utils.range.RangeIterator;
import gr.hua.utils.range.numeric.GenericRealRange;
import gr.hua.utils.range.numeric.NumericRange;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

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
        GenericRealRange range = (GenericRealRange) NumericRange.generateRange(1.0, 2.5, 0.25, NumericRange.CLOSED_OPEN);
        GenericDiscreteRange expResult = new GenericDiscreteRange();
        RangeIterator t = range.iterate();
        while (t.hasNext()) {
            expResult.append(t.next());
        }
        GenericDiscreteRange result = new GenericDiscreteRange();
        DiscreteRange.discretize(range, result);
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of combine method, of class DiscreteRange.
     */
    @Test
    public void testCombine() {
        GenericDiscreteRange<String> expResult = new GenericDiscreteRange();
        expResult.append("apples", "oranges", "pears", "tomatoes");
        GenericDiscreteRange<String> firstPart = new GenericDiscreteRange();
        firstPart.append("apples", "oranges");
        GenericDiscreteRange<String> secondPart = new GenericDiscreteRange();
        secondPart.append("pears", "tomatoes");
        GenericDiscreteRange<String> result = new GenericDiscreteRange();
        DiscreteRange.combine(result, firstPart, secondPart);
        assertEquals(expResult.toString(), result.toString());
    }
}
