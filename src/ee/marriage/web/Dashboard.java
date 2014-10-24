package ee.marriage.web;

import ee.marriage.model.Marriage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Dashboard extends BaseServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Marriage> marriages = marriagesRegistry.all();

    render("dashboard.ftl", response, "marriages", marriages);
  }
}