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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "FeedCreateController", value = "/Feed/create")
@Log4j2
public class FeedCreateController extends HttpServlet {

    private FeedService feedService = FeedService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("/feed/create GET .......");
        req.getRequestDispatcher("/WEB-INF/Feed/create.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FeedDTO feedDTO = FeedDTO.builder()
                .content(req.getParameter("content"))
                .modifiedDate(LocalDateTime.now())
                .build();

        log.info("/feed/create POST...");
        log.info(feedDTO);
        try {
            feedService.createFeed(feedDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/Feed/list");

    }
}


