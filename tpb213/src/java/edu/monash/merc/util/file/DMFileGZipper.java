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

package edu.monash.merc.util.file;

import edu.monash.merc.exception.DMFileException;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * @author Simon Yu
 * @version 1.0
 * @email Xiaoming.Yu@monash.edu
 *
 * Date: 27/02/12
 * Time: 4:30 PM
 */
public class DMFileGZipper {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    private static int BUFFER_SIZE = 1240*15;

    public void unzipFile(String zipFileName, String destFileName) {

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(zipFileName));
            unzipFile(fileInputStream, destFileName);
        } catch (IOException e) {
            throw new DMFileException(e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception fex) {
                    logger.warn(fex.getMessage());
                }
            }
        }
    }

    public void unzipFile(InputStream gzipFileInputStream, String destFileName) {
        GZIPInputStream gzipInputStream = null;
        OutputStream out = null;
        try {
            gzipInputStream = new GZIPInputStream(gzipFileInputStream);
            out = new FileOutputStream(destFileName);
            byte[] buf = new byte[BUFFER_SIZE];
            //size can be changed according to programmer 's need.
            int len;
            while ((len = gzipInputStream.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            throw new DMFileException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (gzipInputStream != null) {
                    gzipInputStream.close();
                }
                if (gzipFileInputStream != null) {
                    gzipFileInputStream.close();
                }
            } catch (Exception fex) {
                logger.warn(fex.getMessage());
            }
        }
    }
}
