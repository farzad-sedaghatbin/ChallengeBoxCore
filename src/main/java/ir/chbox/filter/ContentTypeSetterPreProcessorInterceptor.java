package ir.chbox.filter;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

@Provider
@ServerInterceptor
public class ContentTypeSetterPreProcessorInterceptor implements
        PreProcessInterceptor {

    public ServerResponse preProcess(HttpRequest request,
                                     ResourceMethod method) throws Failure, WebApplicationException {
        request.setAttribute(InputPart.DEFAULT_CONTENT_TYPE_PROPERTY,
                "txt/html; charset=UTF-8");
        return null;
    }

}