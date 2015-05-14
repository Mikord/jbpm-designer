package org.jbpm.designer.web.server;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jbpm.designer.bpmn2.validation.BPMN2SyntaxChecker;
import org.jbpm.designer.util.Utils;
import org.jbpm.designer.web.profile.IDiagramProfile;
import org.jbpm.designer.web.profile.IDiagramProfileService;
import org.jbpm.designer.web.profile.impl.ProfileServiceImpl;


/** 
 * 
 * Check syntax of a BPMN2 process.
 * 
 * @author Tihomir Surdilovic
 */
public class SyntaxCheckerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private static IDiagramProfileService _profileService = ProfileServiceImpl.getInstance();

	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		String json = req.getParameter("data");
        String profileName = req.getParameter("profile");
        String preprocessingData = req.getParameter("pp");
        String uuid = Utils.getUUID(req);
        IDiagramProfile profile = _profileService.findProfile(req, profileName);

        BPMN2SyntaxChecker checker = new BPMN2SyntaxChecker(json, preprocessingData, profile, uuid);
		checker.checkSyntax();
		resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        if(checker.errorsFound()) {
			resp.getWriter().write(checker.getErrorsAsJson().toString());
		}
	}
}
