package org.iaroslaveremeev.servlets;

import org.iaroslaveremeev.model.TV;
import org.iaroslaveremeev.repository.TvRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * 4. Сделать сервлет TVServlet который обрабатывает /tv с методами post, который добавляет
 * новый телевизор в репозиторий , get который получает телевизор по id или список телевизоров
 */
@WebServlet("/tv")
public class TVServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        TvRepository tvRepository = new TvRepository();
        if(id != null){
            try {
                resp.getWriter().println(tvRepository.getTvById(Integer.parseInt(id)));
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                resp.getWriter().println("Incorrect id!");
            }
        }
        else {
            resp.getWriter().println(tvRepository.getTvs());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String color = req.getParameter("color");
        String timeExpectancy = req.getParameter("timeExpectancy");
        String price = req.getParameter("price");
        if(brand != null && model != null && color != null && timeExpectancy != null && price != null) {
            try {
                TV newTv = new TV(brand, model, color, Integer.parseInt(timeExpectancy), Double.parseDouble(price));
                TvRepository tvRepository = new TvRepository();
                tvRepository.addTv(newTv);
                resp.getWriter().println(tvRepository.getTvs());
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                resp.getWriter().println("Incorrect parameters format!");
            }
        }
        else{
            resp.setStatus(400);
            resp.getWriter().println("Incorrect parameters!");
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        TvRepository tvRepository = new TvRepository();
        if(id != null){
            try {
                tvRepository.deleteById(Integer.parseInt(id));
                resp.getWriter().println("Repository after deleting:");
                resp.getWriter().println(tvRepository.getTvs());
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                resp.getWriter().println("Incorrect id!");
            }
        }
        else {
            resp.getWriter().println("No TV with this ID found. Nothing will be deleted");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String id = req.getParameter("id");
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String color = req.getParameter("color");
        String timeExpectancy = req.getParameter("timeExpectancy");
        String price = req.getParameter("price");
        if(id != null && brand != null && model != null && color != null && timeExpectancy != null && price != null) {
            try {
                TV newTv = new TV(Integer.parseInt(id), brand, model, color, Integer.parseInt(timeExpectancy), Double.parseDouble(price));
                TvRepository tvRepository = new TvRepository();
                tvRepository.substitute(newTv);
                resp.getWriter().println("Repository after substitution:");
                resp.getWriter().println(tvRepository.getTvs());
            } catch (NumberFormatException e) {
                resp.setStatus(400);
                resp.getWriter().println("Incorrect parameters format!");
            }
        }
        else{
            resp.setStatus(400);
            resp.getWriter().println("Incorrect parameters!");
        }
    }
}
