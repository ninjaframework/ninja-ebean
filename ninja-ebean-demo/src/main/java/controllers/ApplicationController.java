package controllers;

import java.util.List;
import java.util.Map;

import models.GuestBookEntry;
import ninja.Result;
import ninja.Results;
import ninja.i18n.Lang;
import ninja.params.Param;

import org.slf4j.Logger;

import com.avaje.ebean.EbeanServer;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ApplicationController {

    private Logger logger;

    private Lang lang;

    private EbeanServer ebeanServer;

    @Inject
    public ApplicationController(Lang lang,
                                 Logger logger,
                                 EbeanServer ebeanServer) {
        this.lang = lang;
        this.logger = logger;
        this.ebeanServer = ebeanServer;

    }

    public Result index() {

        // Get all guestbookentries now:
        List<GuestBookEntry> guestBookEntries = ebeanServer.find(
                GuestBookEntry.class).findList();
        
        Map<String, Object> toRender = Maps.newHashMap();
        toRender.put("guestBookEntries", guestBookEntries);

        // Default rendering is simple by convention
        // This renders the page in views/ApplicationController/index.ftl.html
        return Results.html().render(toRender);

    }

    public Result post(@Param("email") String email,
                       @Param("content") String content) {

        GuestBookEntry guestbookEntry = new GuestBookEntry(email, content);
        ebeanServer.save(guestbookEntry);
        
        // ... and redirect to main page
        return Results.redirect("/");

    }
}
