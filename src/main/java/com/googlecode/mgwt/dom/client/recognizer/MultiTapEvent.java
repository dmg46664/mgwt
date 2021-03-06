/*
 * Copyright 2012 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.mgwt.dom.client.recognizer;

import com.googlecode.mgwt.collection.shared.LightArray;
import com.googlecode.mgwt.dom.client.event.touch.Touch;

public class MultiTapEvent extends GestureEvent {

	private final int numberOfFingers;
	private final LightArray<LightArray<Touch>> touchStarts;
	private final int numberOfTaps;

	public MultiTapEvent(int numberOfFingers, int numberOfTaps, LightArray<LightArray<Touch>> touchStarts) {
		this.numberOfFingers = numberOfFingers;
		this.numberOfTaps = numberOfTaps;
		this.touchStarts = touchStarts;
	}

	public int getNumberOfFinders() {
		return numberOfFingers;
	}

	public LightArray<LightArray<Touch>> getTouchStarts() {
		return touchStarts;
	}

	public int getNumberOfTabs() {
		return numberOfTaps;
	}

}
