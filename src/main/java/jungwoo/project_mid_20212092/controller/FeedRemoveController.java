package jungwoo.project_mid_20212092.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jungwoo.project_mid_20212092.service.FeedService;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet(name = "FeedRemoveController", value = "/Feed/remove")
@Log4j2
public class FeedRemoveController extends HttpServlet {

    private FeedService feedService = FeedService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long feedId = Long.parseLong(req.getParameter("feedId"));
        log.info("feedId: " + feedId);

        try{
            feedService.removeFeed(feedId);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
        resp.sendRedirect("/Feed/list");

    }
}