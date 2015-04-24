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

package edu.monash.merc.system.remote;

import edu.monash.merc.exception.DMFTPException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

/**
 * FTPFileGetter class which downloads the file from a ftp site
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 27/02/12 12:24 PM
 */
public class FTPFileGetter extends FTPClient {

    /**
     * A convenience method for connecting and logging in
     *
     * @param host
     * @param userName
     * @param password
     * @return
     */
    public boolean connectAndLogin(String host, String userName, String password) {
        boolean success = false;
        try {
            connect(host);
            int reply = getReplyCode();
            if (FTPReply.isPositiveCompletion(reply)) {
                success = login(userName, password);
            }
            if (!success) {
                disconnect();
            }
        } catch (Exception ex) {
            throw new DMFTPException(ex);
        }
        return success;
    }

    /**
     * Change the current working directory of the FTP session.
     *
     * @param changedDirectory The new current working directory.
     * @return True if successfully completed, false if not.
     */
    public boolean changeToWorkingDirectory(String changedDirectory) {
        boolean changed = false;
        try {
            changed = changeWorkingDirectory(changedDirectory);
        } catch (Exception ex) {
            throw new DMFTPException(ex);
        }
        return changed;
    }

    /**
     * Turn passive transfer mode on or off. If Passive mode is active, a
     * PASV command to be issued and interpreted before data transfers;
     * otherwise, a PORT command will be used for data transfers. If you're
     * unsure which one to use, you probably want Passive mode to be on.
     *
     * @param setPassive
     */
    public void setPassiveMode(boolean setPassive) {
        if (setPassive)
            enterLocalPassiveMode();
        else
            enterLocalActiveMode();
    }

    /**
     * Use ASCII mode for file transfers
     *
     * @return a boolean value that indicates the setting for ascii mode is successful or not.
     */
    public boolean ascii() {
        boolean setOk = false;
        try {
            setOk = setFileType(FTP.ASCII_FILE_TYPE);
        } catch (Exception ex) {
            throw new DMFTPException(ex);
        }
        return setOk;
    }

    /**
     * Use Binary mode for file transfers
     *
     * @return a boolean value that indicates the setting for binary mode is successful or not.
     */
    public boolean binary() {
        boolean setOk = false;
        try {
            setOk = setFileType(FTP.BINARY_FILE_TYPE);
        } catch (Exception ex) {
            throw new DMFTPException(ex);
        }
        return setOk;
    }

    /**
     * Download a file from the server, and save it to the specified local file
     *
     * @param serverFile The name of the remote file.
     * @param localFile  The local file which will be saved in local directory.
     * @return True if successfully completed, false if not.
     */
    public boolean downloadFile(String serverFile, String localFile) {
        boolean completed = false;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(localFile);
            completed = retrieveFile(serverFile, out);
        } catch (Exception ex) {
            throw new DMFTPException(ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception foutEx) {
                    //ignore whatever
                }
            }
        }
        return completed;
    }

    /**
     * Retrieves a named file from the server and writes it to the given OutputStream
     *
     * @param remoteFile
     * @param localOutPutStream
     * @return True if successfully completed, false if not.
     */
    public boolean downloadFile(String remoteFile, OutputStream localOutPutStream) {
        boolean completed = false;
        try {
            completed = retrieveFile(remoteFile, localOutPutStream);
        } catch (Exception ex) {
            throw new DMFTPException(ex);
        }
        return completed;
    }

    /**
     * Returns an InputStream from which a named file from the server can be read
     *
     * @param remoteFile The name of the remote file.
     * @return An InputStream from which the remote file can be read.
     *         If the data connection cannot be opened (e.g., the file does not exist),
     *         null is returned (in which case you may check the reply code to determine the exact reason for failure).
     */
    public InputStream downloadFileStream(String remoteFile) {
        try {
            return retrieveFileStream(remoteFile);
        } catch (Exception ex) {
            throw new DMFTPException(ex);
        }
    }

    /**
     * Issue the FTP MDTM command (not supported by all servers to retrieve the last modification time of a file.
     * The modification string should be in the ISO 3077 form "YYYYMMDDhhmmss(.xxx)?".
     * The timestamp represented should also be in GMT, but not all FTP servers honour this.
     *
     * @param remoteFile The file path to query.
     * @return A string representing the last file modification time in YYYYMMDDhhmmss format.
     */
    public String getLastModifiedTime(String remoteFile) {
        try {
            String replyCode = getModificationTime(remoteFile);
            String tempTime = StringUtils.substringAfter(replyCode, String.valueOf(FTPReply.FILE_STATUS));
            return StringUtils.trim(tempTime);
        } catch (Exception ex) {
            throw new DMFTPException(ex);
        }
    }

    /**
     * Get the list of files in the current directory as a Vector of Strings, exclusive sub-directories
     *
     * @return a Vecgtor of Strings which contains file names
     */
    public Vector<String> listFileNames() {
        Vector<String> v = new Vector();
        try {
            FTPFile[] files = listFiles();
            for (int i = 0; i < files.length; i++) {
                if (!files[i].isDirectory()) {
                    v.addElement(files[i].getName());
                }
            }
        } catch (Exception ex) {
            throw new DMFTPException(ex);
        }
        return v;
    }

    /**
     * Get the list of sub-directories in the current directory as a Vector of Strings, exclusive files
     *
     * @return a List of sub-directories
     */
    public Vector<String> listSubdirNames() {
        Vector<String> v = new Vector();
        try {
            FTPFile[] files = listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    v.addElement(files[i].getName());
                }
            }
        } catch (Exception ex) {
            throw new DMFTPException(ex);
        }
        return v;
    }
}
