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
package at.jku.xlwrap.map.transf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import at.jku.xlwrap.common.XLWrapException;
import at.jku.xlwrap.exec.ExecutionContext;
import at.jku.xlwrap.exec.TransformationStage;
import at.jku.xlwrap.map.range.NameChanger;
import at.jku.xlwrap.map.range.Range;
import at.jku.xlwrap.vocab.XLWrap;

/**
 * @author andyl
 *
 */
public class ScriptRepeat extends TransformationBase {
	private final String initCode;
	private final String nextCode;
	private final String repeatCondition;
	
	/**
	 * @param initCode
	 * @param nextCode
	 * @param repeatCondition
	 * @param restriction
	 * @param skipCondition
	 * @param breakCondition
	 * @throws XLWrapException
	 */
	public ScriptRepeat(String initCode, String nextCode, String repeatCondition, String restriction, String skipCondition, String breakCondition) throws XLWrapException {
		super(restriction, skipCondition, breakCondition);
		this.initCode = initCode;
		this.nextCode = nextCode;
		this.repeatCondition = repeatCondition;
	}

	/**
	 * @return the initCode
	 */
	public String getInitCode() {
		return initCode;
	}
	
	/**
	 * @return the nextCode
	 */
	public String getNextCode() {
		return nextCode;
	}
	
	/**
	 * @return the repeatCondition
	 */
	public String getRepeatCondition() {
		return repeatCondition;
	}
	
	/* (non-Javadoc)
	 * @see at.jku.xlwrap.map.transf.TransformationBase#getArgsAsString()
	 */
	@Override
	public String getArgsAsString() {
		return "Script repeat using init code: { " + initCode + " }, next code: { " + nextCode + " }, repeat cond: { " + repeatCondition + " }";
	}
	
	@Override
	public TransformationStage getExecutor(ExecutionContext context) {
		return new TransformationStage(context) {
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("JavaScript");

			@Override
			public void init() throws XLWrapException {
		        try {
		        	engine.eval(initCode);
		        } catch (Exception e) {
		        	throw new XLWrapException("Error in JavaScript code for " + XLWrap.initScript + ".", e);
		        }
			}
			
			@Override
			public boolean hasMoreTransformations() throws XLWrapException {
				try {
					Object result = engine.eval(repeatCondition);					
					if (!(result instanceof Boolean))
						throw new XLWrapException("JavaScript code for " + XLWrap.repeatCondition+ " must return boolean value: " + repeatCondition + "!");
					else
						return (Boolean) result;
				} catch (Exception e) {
					throw new XLWrapException("Error in JavaScript code for " + XLWrap.repeatCondition + ".", e);
				}
			}
			
			@Override
			public Range transform(Range range, Range restriction) throws XLWrapException {
				try {
					engine.eval(nextCode);
					range.changeFileName(new NameChanger(engine), restriction, context);
					return range;
				} catch (Exception e) {
					throw new XLWrapException("Error in JavaScript code for " + XLWrap.nextScript + ".", e);
				}
			}
			
			@Override
			public String getThisStatus() {
				StringBuffer sb = new StringBuffer();
				Bindings b = engine.getBindings(ScriptContext.ENGINE_SCOPE);
				for (String name : b.keySet())
					sb.append(name + " = " + b.get(name) + "; ");
				return "FileShift: variables: " + sb.toString();
			}

		};
	}

}
