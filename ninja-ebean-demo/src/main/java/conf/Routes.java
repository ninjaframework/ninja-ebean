package conf;

import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import controllers.ApplicationController;

public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {

        router.GET().route("/").with(ApplicationController.class, "index");

        router.POST().route("/post").with(ApplicationController.class, "post");

        router.GET().route("/assets/.*").with(AssetsController.class, "serve");

    }

}
