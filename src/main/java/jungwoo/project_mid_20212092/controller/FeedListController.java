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
import java.util.List;

@Log4j2
@WebServlet(name = "FeedListController", value = "/Feed/list")
public class FeedListController extends HttpServlet {

    private final FeedService feedService = FeedService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("todo list..................");

        try {
            // FeedService 인스턴스를 사용하여 listAll() 호출
            List<FeedDTO> dtoList = feedService.listAll();
            req.setAttribute("FeedList", dtoList);
            req.getRequestDispatcher("/WEB-INF/Feed/list.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error while listing feeds: " + e.getMessage(), e);
            throw new ServletException("list error", e);
        }
    }
}