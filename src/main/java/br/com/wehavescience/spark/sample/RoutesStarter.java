package br.com.wehavescience.spark.sample;

import br.com.wehavescience.spark.sample.exceptions.ResourceAlreadyExistsException;
import br.com.wehavescience.spark.sample.exceptions.ResourceNotFoundException;
import br.com.wehavescience.spark.sample.exceptions.RestfulException;
import br.com.wehavescience.spark.sample.model.Customer;
import br.com.wehavescience.spark.sample.services.CustomerService;
import br.com.wehavescience.spark.sample.transformers.JsonTransformer;
import org.jboss.weld.environment.se.events.ContainerInitialized;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import static br.com.wehavescience.spark.sample.utils.JsonUtils.asObject;
import static java.lang.Integer.parseInt;
import static spark.Spark.*;

/**
 * @author Gabriel Francisco <gabfssilva@gmail.com>
 */
public class RoutesStarter {
    private static final String CONTENT_TYPE = "application/json";

    @Inject
    private CustomerService service;

    public void main(@Observes ContainerInitialized event) {
        setPort(9090);

        //listing all
        get("/customers", CONTENT_TYPE, (request, response) -> service.findAll(), new JsonTransformer());

        //getting a specific customer
        get("/customers/:id", CONTENT_TYPE, (request, response) -> {
            Customer customer = service.findById(parseInt(request.params(":id")));

            if (customer == null) {
                throw new ResourceNotFoundException();
            }

            return customer;
        }, new JsonTransformer());

        //adding a customer to the list
        post("/customers/:id", CONTENT_TYPE, (request, response) -> {
            int id = parseInt(request.params(":id"));
            if (service.findById(id) != null) {
                throw new ResourceAlreadyExistsException();
            }

            Customer customer = asObject(request.body(), Customer.class);
            customer.setId(id);
            service.save(customer);
            return customer;
        }, new JsonTransformer());

        //updating a customer of the list
        put("/customers/:id", CONTENT_TYPE, (request, response) -> {
            Customer customer = service.findById(parseInt(request.params(":id")));

            if (customer == null) {
                new ResourceNotFoundException();
            }

            customer = asObject(request.body(), Customer.class);
            customer.setId(parseInt(request.params(":id")));
            service.save(customer);
            return customer;
        }, new JsonTransformer());

        //deleting a customer from the list
        delete("/customers/:id", CONTENT_TYPE, (request, response) -> {
            Customer customer = service.findById(parseInt(request.params(":id")));

            if (customer == null) {
                new ResourceNotFoundException();
            }
            return service.remove(customer);
        }, new JsonTransformer());

        //exception handling
        exception(RestfulException.class, (e, request, response) -> {
            RestfulException restfulException = (RestfulException) e;
            response.status(restfulException.getStatusCode());
            response.body(restfulException.getMessage());
        });
    }
}
