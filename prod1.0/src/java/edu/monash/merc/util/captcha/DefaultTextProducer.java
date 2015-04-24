/*
 * Copyright (c) 2011-2012, Monash e-Research Centre
 * (Monash University, Australia)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 	* Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 	* Redistributions in binary form must reproduce the above copyright
 * 	  notice, this list of conditions and the following disclaimer in the
 * 	  documentation and/or other materials provided with the distribution.
 * 	* Neither the name of the Monash University nor the names of its
 * 	  contributors may be used to endorse or promote products derived from
 * 	  this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.monash.merc.util.captcha;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * DefaultTextProducer class
 *
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 */
public class DefaultTextProducer implements TextProducer {

    private static final Random _gen = new SecureRandom();
    private static final int DEFAULT_LENGTH = 6;
    private static final char[] DEFAULT_CHARS = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 'w',
            'x', 'y', 'z', '2', '3', '4', '5', '6', '7', '8', '9',};

    private final int _length;
    private final char[] _srcChars;

    public DefaultTextProducer() {
        this(DEFAULT_LENGTH, DEFAULT_CHARS);
    }

    public DefaultTextProducer(int length) {
        this(length, DEFAULT_CHARS);
    }

    public DefaultTextProducer(int length, char[] srcChars) {
        _length = length;
        _srcChars = srcChars != null ? Arrays.copyOf(srcChars, srcChars.length) : DEFAULT_CHARS;
    }

    public String getText() {
        int car = _srcChars.length - 1;

        String capText = "";
        for (int i = 0; i < _length; i++) {
            capText += _srcChars[_gen.nextInt(car) + 1];
        }

        return capText;
    }
}