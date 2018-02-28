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

package org.fubabaz.rpm.ui.util;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author ejpark
 *
 */
public class ResourceUtil {

	private static Map<ImagePath,Image> IMAGE_MAP = new HashMap<ImagePath,Image>();
	private static Map<RGB, Color> COLOR_MAP = new HashMap<RGB, Color>();

	private static Bundle bundle = FrameworkUtil.getBundle(ResourceUtil.class);
	private static ResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources());
	
	public static Image getImage(ImagePath path) {
		Image image = IMAGE_MAP.get(path);
		if (image == null) {
	        URL url = FileLocator.find(bundle, new Path(path.value()), null);
	        image= resourceManager.createImage(ImageDescriptor.createFromURL(url));
	        IMAGE_MAP.put(path, image);
		}
		return image;
	}

	public static Color getColor(RGB rgb) {
		Color color = COLOR_MAP.get(rgb);
		if (color == null) {
			color = resourceManager.createColor(rgb);
			COLOR_MAP.put(rgb, color);
		}
		return color;
	}

	public static Color getColor(int r, int g, int b) {
		return getColor(new RGB(r, g, b));
	}

	public static Color getColor(int systemColorID) {
		Display display = Display.getCurrent();
		return display.getSystemColor(systemColorID);
	}
}
