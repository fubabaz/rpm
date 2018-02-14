/**
  * Copyright 2017 FuBaBaz Team.
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

package org.fubabaz.rpm.ui.bind;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author ejpark
 *
 */
public abstract class ModelPropertyChangeBinder implements PropertyChangeListener {

	protected final PropertyChangeSupport propertyChangeSupport;

	protected ModelPropertyChangeBinder(Object modelObject){
		this.propertyChangeSupport = new PropertyChangeSupport(modelObject);
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.propertyChangeSupport.firePropertyChange(evt);
	}
}