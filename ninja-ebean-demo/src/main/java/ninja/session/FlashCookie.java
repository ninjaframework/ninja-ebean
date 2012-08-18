/**
 * Copyright (C) 2012 the original author or authors.
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

package ninja.session;

import ninja.Context;

import com.google.inject.ImplementedBy;

/**
 * Flash scope:
 * A client side cookie that can be used to transfer information from one request to another.
 * 
 * Stuff in a flash cookie gets deleted after the next request.
 * 
 * Please note also that flash cookies are not signed.
 */
@ImplementedBy(FlashCookieImpl.class)
public interface FlashCookie {

	public void init(Context context);

	public void save(Context context);

	public void put(String key, String value);

	public void put(String key, Object value);

	public void now(String key, String value);

	public void error(String value, Object... args);

	public void success(String value, Object... args);

	public void discard(String key);

	public void discard();

	public void keep(String key);

	public void keep();

	public String get(String key);

	public boolean remove(String key);

	public void clearCurrentFlashCookieData();

	public boolean contains(String key);

	public String toString();
}
