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

package ninja.template;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;

import ninja.Context;
import ninja.Result;
import ninja.i18n.Lang;
import ninja.utils.ResponseStreams;

import com.google.common.collect.Maps;
import com.google.inject.Inject;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class TemplateEngineFreemarker implements TemplateEngine {

	private String FILE_SUFFIX = ".ftl.html";

	private Configuration cfg;

	private final Lang lang;
	
	private final TemplateEngineHelper templateEngineHelper;

	private final Logger logger;

	@Inject
	TemplateEngineFreemarker(Lang lang, Logger logger, TemplateEngineHelper templateEngineHelper, TemplateEngineManager templateEngineManager) {
		this.lang = lang;
		this.logger = logger;
		this.templateEngineHelper = templateEngineHelper;
		cfg = new Configuration();
		cfg.setClassForTemplateLoading(this.getClass(), "/");
		
		//we are going to enable html escaping by default using this template loader:
		cfg.setTemplateLoader(new TemplateEngineFreemarkerEscapedLoader(cfg.getTemplateLoader()));
	}
    


	@Override
	public void invoke(Context context, Result result) {

		Object object = result.getRenderable();
		
		ResponseStreams responseStreams = context.finalizeHeaders(result);
		
		Map map;
		//if the object is null we simply render an empty map...
		if (object == null) {
		    map = Maps.newHashMap();
		} else if (!(object instanceof Map)) {
			throw new RuntimeException(
					"Freemarker Templating engine can only render Map of Strings...");

		} else {
			map = (Map) object;
		}		
		
		// provide all i18n templates to freemarker engine:
		Locale locale = context.getHttpServletRequest().getLocale();		
		Map<Object, Object> i18nMap = lang.getAll(locale);		
		map.putAll(i18nMap);

		String templateName = templateEngineHelper.getTemplateForResult(context.getRoute(),
                result, FILE_SUFFIX);

		// Specify the data source where the template files come from.
		// Here I set a file directory for it:
		try {

			Template freemarkerTemplate = cfg.getTemplate(templateName);

			// convert tuples:

			freemarkerTemplate.process(map, responseStreams.getWriter());

			responseStreams.getWriter().flush();			
			responseStreams.getWriter().close();

		} catch (Exception e) {
			logger.error("Error processing Freemarker Template " + templateName, e);
		} 

	}

    @Override
    public String getContentType() {
        return "text/html";
    }

	@Override
	public String getSuffixOfTemplatingEngine() {
		return FILE_SUFFIX;
	}
}
