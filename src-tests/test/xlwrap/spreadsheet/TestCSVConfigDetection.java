/**
 * Copyright 2009 Andreas Langegger, andreas@langegger.at, Austria
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package test.xlwrap.spreadsheet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import at.jku.xlwrap.spreadsheet.csv.CSVParseConfig;

/**
 * Tests for {@link CSVParseConfig#autoDetectConfig(String)}.
 */
public class TestCSVConfigDetection {

    @Test
    public void detectTabQuoted() {
        CSVParseConfig cfg = CSVParseConfig.autoDetectConfig("\"a\"\t\"b\"\t\"c\"");
        assertEquals("\t", cfg.getColSeperator());
        assertEquals("\"", cfg.getValDelimiter());
    }

    @Test
    public void detectCommaUnquoted() {
        CSVParseConfig cfg = CSVParseConfig.autoDetectConfig("a,b,c");
        assertEquals(",", cfg.getColSeperator());
        assertEquals("", cfg.getValDelimiter());
    }

    @Test
    public void detectCommaQuoted() {
        CSVParseConfig cfg = CSVParseConfig.autoDetectConfig("\"a\",\"b\",\"c\"");
        assertEquals(",", cfg.getColSeperator());
        assertEquals("\"", cfg.getValDelimiter());
    }

    @Test
    public void detectSemicolonSingleQuotes() {
        CSVParseConfig cfg = CSVParseConfig.autoDetectConfig("'a';'b';'c'");
        assertEquals(";", cfg.getColSeperator());
        assertEquals("'", cfg.getValDelimiter());
    }
}
