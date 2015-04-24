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

package edu.monash.merc.dto.tl;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: simonyu
 * Date: 15/11/12
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TLTypeEvLevelFilter implements Serializable {
    private String dataType;

    private boolean typeEvLevel1;

    private boolean typeEvLevel2;

    private boolean typeEvLevel3;

    private boolean typeEvLevel4;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public boolean isTypeEvLevel1() {
        return typeEvLevel1;
    }

    public void setTypeEvLevel1(boolean typeEvLevel1) {
        this.typeEvLevel1 = typeEvLevel1;
    }

    public boolean isTypeEvLevel2() {
        return typeEvLevel2;
    }

    public void setTypeEvLevel2(boolean typeEvLevel2) {
        this.typeEvLevel2 = typeEvLevel2;
    }

    public boolean isTypeEvLevel3() {
        return typeEvLevel3;
    }

    public void setTypeEvLevel3(boolean typeEvLevel3) {
        this.typeEvLevel3 = typeEvLevel3;
    }

    public boolean isTypeEvLevel4() {
        return typeEvLevel4;
    }

    public void setTypeEvLevel4(boolean typeEvLevel4) {
        this.typeEvLevel4 = typeEvLevel4;
    }
}
