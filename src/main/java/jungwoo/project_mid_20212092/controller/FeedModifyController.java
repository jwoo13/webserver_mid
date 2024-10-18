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

@WebServlet(name = "todoModifyController", value = "/Feed/modify")
@Log4j2
public class FeedModifyController  extends HttpServlet {

    private FeedService feedService = FeedService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long feedId = Long.parseLong(req.getParameter("feedId"));
            FeedDTO feedDTO = feedService.getFeed(feedId);
            //모델 담기
            req.setAttribute("dto", feedDTO);
            req.getRequestDispatcher("/WEB-INF/Feed/modify.jsp").forward(req, resp);

        }catch(Exception e){
            log.error(e.getMessage());
            throw new ServletException("modify get... error");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        FeedDTO feedDTO = FeedDTO.builder()
                .feedId(Long.parseLong(req.getParameter("feedId")))
                .content(req.getParameter("content"))
                .modifiedDate(LocalDateTime.now())
                .build();


        log.info("/Feed/modify POST...");
        log.info(feedDTO);
        try {
            feedService.modifyFeed(feedDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/Feed/list");

    }


}