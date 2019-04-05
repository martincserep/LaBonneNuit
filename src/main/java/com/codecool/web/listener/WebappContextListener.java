package com.codecool.web.listener;

import com.codecool.web.service.GreetingService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public final class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets deployed.");

        GreetingService gs = new GreetingService();
        sce.getServletContext().setAttribute(GreetingService.class.getName(), gs);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets undeployed.");
    }
}
