package com.viewResolver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.view.AbstractView;
public class AjaxView extends AbstractView {


    @Override
    protected void renderMergedOutputModel(	Map map, 
    										HttpServletRequest request,
    										HttpServletResponse response) throws Exception {
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonString = mapper.writeValueAsString(map);
        response.setContentType( "text/plain; charset=UTF-8" );
        response.getOutputStream().write( jsonString.getBytes() );
    }
}