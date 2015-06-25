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

import gr.hua.utils.range.numeric.NumericRange;
import java.text.ParseException;

/**
 *
 * @author Nikolaos Zormpas
 */
public class RangeFactory {

    public static Range parseString(String s) throws ParseException {
        s = s.trim();
        switch (s.charAt(0)) {
            case '[':
            case '(':
                return NumericRange.parseRange(s);
            case '{':
                char c = s.charAt(1);
                if (c != '/') {
                    return NumericRange.basicRange((long) s.split("\\\"\\s*,\\s*\\\"").length);
                } else {
                    return NumericRange.parseRange(s);
                }
            default:
                throw new IllegalArgumentException();
        }
    }
}
