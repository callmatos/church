package org.church.ws.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.church.core.dao.GenericDaoObject;
import org.church.ws.event.ResourceCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
class ResourceCreatedDiscoverabilityListener implements ApplicationListener<ResourceCreatedEvent<GenericDaoObject>> {

    @Override
    public void onApplicationEvent(final ResourceCreatedEvent<GenericDaoObject> resourceCreatedEvent) {


        final HttpServletResponse response = resourceCreatedEvent.getResponse();
        
        final Integer idOfNewResource = resourceCreatedEvent.getObject().getId();

        addLinkHeaderOnResourceCreation(response, idOfNewResource);
    }

    void addLinkHeaderOnResourceCreation(final HttpServletResponse response, final Integer idOfNewResource) {
        // final String requestUrl = request.getRequestURL().toString();
        // final URI uri = new UriTemplate("{requestUrl}/{idOfNewResource}").expand(requestUrl, idOfNewResource);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idOfNewResource}").buildAndExpand(idOfNewResource).toUri();
        response.setHeader(HttpHeaders.LOCATION, uri.toASCIIString());
    }

}