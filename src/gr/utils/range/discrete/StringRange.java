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

import gr.hua.utils.range.Range;
import java.text.ParseException;

/**
 *
 * @author Nikolaos Zormpas <nickzorb@gmail.com>
 */
public interface StringRange extends Range<String> {

    static StringRange parseRange(String s) throws ParseException {
        String[] tokens = s.split("\\\"\\s*,\\s*\\\"");
        tokens[0] = tokens[0].substring(2);
        String last = tokens[tokens.length - 1];
        tokens[tokens.length - 1] = last.substring(0, last.length() - 2);
        return new GenericStringRange(tokens);
    }

    @Override
    String toString();
}
