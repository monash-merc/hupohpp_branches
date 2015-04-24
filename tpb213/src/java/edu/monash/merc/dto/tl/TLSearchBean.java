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
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: simonyu
 * Date: 12/11/12
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class TLSearchBean implements Serializable {

    private String selectedChromType;

    private boolean nxDbSelected;

    private boolean gpmDbSelected;

    private boolean hpaDbSelected;

    private boolean paDbSelected;

    private long selectedVersion;

    private List<TLTypeEvLevelFilter> tlTypeEvLevelFilters;

    public String getSelectedChromType() {
        return selectedChromType;
    }

    public void setSelectedChromType(String selectedChromType) {
        this.selectedChromType = selectedChromType;
    }

    public boolean isNxDbSelected() {
        return nxDbSelected;
    }

    public void setNxDbSelected(boolean nxDbSelected) {
        this.nxDbSelected = nxDbSelected;
    }

    public boolean isGpmDbSelected() {
        return gpmDbSelected;
    }

    public void setGpmDbSelected(boolean gpmDbSelected) {
        this.gpmDbSelected = gpmDbSelected;
    }

    public boolean isHpaDbSelected() {
        return hpaDbSelected;
    }

    public void setHpaDbSelected(boolean hpaDbSelected) {
        this.hpaDbSelected = hpaDbSelected;
    }

    public boolean isPaDbSelected() {
        return paDbSelected;
    }

    public void setPaDbSelected(boolean paDbSelected) {
        this.paDbSelected = paDbSelected;
    }

    public long getSelectedVersion() {
        return selectedVersion;
    }

    public void setSelectedVersion(long selectedVersion) {
        this.selectedVersion = selectedVersion;
    }

    public List<TLTypeEvLevelFilter> getTlTypeEvLevelFilters() {
        return tlTypeEvLevelFilters;
    }

    public void setTlTypeEvLevelFilters(List<TLTypeEvLevelFilter> tlTypeEvLevelFilters) {
        this.tlTypeEvLevelFilters = tlTypeEvLevelFilters;
    }
}
