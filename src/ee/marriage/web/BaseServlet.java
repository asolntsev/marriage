package ee.marriage.web;

import ee.marriage.model.MarriagesRepository;
import ee.marriage.model.PersonRepository;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_OK;

abstract class BaseServlet extends HttpServlet {
  @Inject
  protected PersonRepository persons;

  @Inject
  protected MarriagesRepository marriagesRegistry;

  protected void render(String template, HttpServletResponse response, Object... parameters) throws IOException, ServletException {
    Configuration cfg = new Configuration();
    cfg.setDirectoryForTemplateLoading(new File(getServletContext().getRealPath("/")));
    cfg.setObjectWrapper(new DefaultObjectWrapper());

    response.setContentType("text/html; charset=utf-8");
    response.setStatus(SC_OK);

    Writer out = new OutputStreamWriter(response.getOutputStream());
    try {
      cfg.getTemplate(template).process(toMap(parameters), out);
      out.flush();
    } catch (TemplateException e) {
      throw new ServletException(e);
    } finally {
      out.close();
    }
  }

  private Map toMap(Object[] parameters) {
    Map<String, Object> data = new HashMap<String, Object>(parameters.length / 2);
    for (int i = 0; i < parameters.length; i += 2) {
      data.put((String) parameters[i], parameters[i + 1]);
    }
    return data;
  }
}
