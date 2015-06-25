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
package gr.hua.utils.range;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikolaos Zormpas
 */
public class RangeFactoryTest {

    public RangeFactoryTest() {
    }

    /**
     * Test of parseString method, of class RangeFactory.
     * @throws java.lang.Exception
     */
    @Test
    public void testParseString() throws Exception {
        System.out.println("parseString");
        String s[] = {
            "[0/15]/1/3",
            "[0/15]/1/3",
            "(15.5/25.5]/0.35/15",
            "(15.5/25.5]/0.35/15",
            "{/15}/5/35",
            "[0/14]/5/35",
            "{\"test1\" , \"test2\", \"test3\"}",
            "[0/2]/1/1"};
        for (int i = 0; i < s.length; i += 2) {
            assertEquals(s[i + 1], RangeFactory.parseString(s[i]).toString());
        }
    }
}
