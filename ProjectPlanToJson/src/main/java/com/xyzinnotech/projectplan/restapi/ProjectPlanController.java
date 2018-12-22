package com.xyzinnotech.projectplan.restapi;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.xyzinnotech.projectplan.service.ProjectReader;


@Path("/projectPlan")
public class ProjectPlanController {
	@POST
	@Path("/mppToJson")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response getMsg(@FormDataParam("file")InputStream in,@FormDataParam("file") FormDataContentDisposition fileDetail) {
 		ProjectReader projectReader = new ProjectReader();
 		String output = projectReader.mppToJson(in);
 		return Response.status(200).entity(output).build();
	}
}
