package controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import ninja.NinjaTest;

import org.junit.Test;

import com.google.common.collect.Maps;

public class ApplicationControllerTest extends NinjaTest {

    
    @Test
    public void testUploadingWorks() {
        
        String result = ninjaTestBrowser.makeRequest(getServerAddress());
        assertFalse(result.contains("myEmail me.com"));
        assertFalse(result.contains("my funky content"));
        
        Map<String, String> parameters = Maps.newHashMap();
        parameters.put("email", "myEmail me.com");
        parameters.put("content", "my funky content");
        
        ninjaTestBrowser.makePostRequestWithFormParameters(getServerAddress() + "post", null, parameters);
        
        result = ninjaTestBrowser.makeRequest(getServerAddress());
       

        assertTrue(result.contains("myEmail me.com"));
        assertTrue(result.contains("my funky content"));
        
        
        
    }
}
