package jungwoo.project_mid_20212092.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jungwoo.project_mid_20212092.dto.FeedDTO;
import jungwoo.project_mid_20212092.service.FeedService;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet(name = "FeedReadController", value = "/Feed/read")
@Log4j2
public class FeedReadController extends HttpServlet {

    private FeedService feedService = FeedService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long feedId = Long.parseLong(req.getParameter("feedId"));

            FeedDTO feedDTO = feedService.getFeed(feedId);

            //모델 담기
            req.setAttribute("dto", feedDTO);

            req.getRequestDispatcher("/WEB-INF/Feed/read.jsp").forward(req, resp);

        }catch(Exception e){
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }
}