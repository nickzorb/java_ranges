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

package gr.hua.utils.range;

import gr.hua.utils.range.discrete.GenericDiscreteRange;
import gr.hua.utils.range.numeric.GenericRealRange;
import gr.hua.utils.range.numeric.NumericRange;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author NickZorb
 */
public class RangeTest {
    
    public RangeTest() {
    }

    /**
     * Test of parseString method, of class Range.
     */
    @Test
    public void testParseString() throws Exception {
        String s = "{\"apples\", \"oranges\", \"tomatoes\"}";
        Range expResult = new GenericDiscreteRange();
        ((GenericDiscreteRange<String>) expResult).append("apples", "oranges", "tomatoes");
        Range result = Range.parseString(s);
        assertEquals(expResult.toString(), result.toString());
        s = "[0,0 - 16.5) : 0.1";
        expResult = NumericRange.generateRange(0, 16.5, 0.1, NumericRange.CLOSED_OPEN);
        result = Range.parseString(s);
        assertEquals(expResult.toString(), result.toString());
    }
    
}
