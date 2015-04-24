/*
 * Copyright (c) 2011-2013, Monash e-Research Centre
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
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
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
 *
 * You should have received a copy of the GNU Affero General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package edu.monash.merc.struts2.validator;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PasswordIntegrityValidator class provides the name validation
 *
 * @author Simon Yu
 *         <p/>
 *         Email: xiaoming.yu@monash.edu
 * @version 1.0
 * @since 1.0
 *        <p/>
 *        Date: 30/12/11 12:01 PM
 */
public class PasswordIntegrityValidator extends FieldValidatorSupport {

    static Pattern digitPattern = Pattern.compile("[0-9]");

    static Pattern letterPattern = Pattern.compile("[a-zA-Z]");

    static Pattern specialCharsDefaultPattern = Pattern.compile("!@#$");

    private String specialCharacters;

    public String getSpecialCharacters() {
        return specialCharacters;
    }

    public void setSpecialCharacters(String specialCharacters) {
        this.specialCharacters = specialCharacters;
    }

    public void validate(Object object) throws ValidationException {
        String fieldName = getFieldName();

        Object value = this.getFieldValue(fieldName, object);
        if (!(value instanceof String)) {
            addFieldError(fieldName, object);

        } else {
            String fieldValue = (String) value;

            fieldValue = fieldValue.trim();

            if (fieldValue.length() == 0) {
                addFieldError(fieldName, object);
            } else {
                Matcher digitMatcher = digitPattern.matcher(fieldValue);
                Matcher letterMatcher = letterPattern.matcher(fieldValue);
                Matcher specialCharacterMatcher = null;

                if (getSpecialCharacters() != null) {
                    Pattern speciallPattern = Pattern.compile("[" + getSpecialCharacters() + "]");
                    specialCharacterMatcher = speciallPattern.matcher(fieldValue);
                } else {
                    specialCharacterMatcher = null;
                }

                if (!digitMatcher.find()) {
                    addFieldError(fieldName, object);
                } else if (!letterMatcher.find()) {
                    addFieldError(fieldName, object);
                } else if (specialCharacterMatcher != null && !specialCharacterMatcher.find()) {
                    addFieldError(fieldName, object);
                }
            }
        }
    }
}
