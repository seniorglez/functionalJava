/*Copyright (c) 2020 Diego Dominguez Gonzalez
 *
 *Permission is hereby granted, free of charge, to any person obtaining a copy
 *of this software and associated documentation files (the "Software"), to deal
 *in the Software without restriction, including without limitation the rights
 *to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *copies of the Software, and to permit persons to whom the Software is
 *furnished to do so, subject to the following conditions:
 *
 *The above copyright notice and this permission notice shall be included in all
 *copies or substantial portions of the Software.
 *
 *THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *SOFTWARE.
 */

package com.seniorglez.functionalJava.functors;


import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class ResultTest {

    @Test
    public void testResultFailure() {
        Result result = Result.get( ()-> {
            try {
                return new Result.Success(Integer.valueOf("Hi m8s"));
            } catch (NumberFormatException e) {
                return new Result.Failure("That's not an int");
            }
        });
       boolean a = result instanceof Result.Failure;
       assertTrue(a);
    }

    @Test
    public void testResultSuccess() {
        Result result = Result.get( ()-> {
            try {
                return new Result.Success(Integer.valueOf("1000"));
            } catch (NumberFormatException e) {
                return new Result.Failure("That's not an int");
            }
        });
        boolean a = result instanceof Result.Success;
        Integer integer = (Integer) ((Result.Success)result).getValue();
        int value = integer.intValue();
        assertTrue(a && value == 1000);
    }
}