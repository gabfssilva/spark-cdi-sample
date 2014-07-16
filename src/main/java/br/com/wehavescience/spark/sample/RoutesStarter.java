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

    @Inject
    private JsonTransformer jsonTransformer;

    public void main(@Observes ContainerInitialized event) {
        setPort(9090);

        //listing all
        get("/customers", (request, response) -> service.findAll(), jsonTransformer);

        //getting a specific customer
        get("/customers/:id", (request, response) -> {
            Customer customer = service.findById(parseInt(request.params(":id")));

            if (customer == null) {
                throw new ResourceNotFoundException();
            }

            return customer;
        }, jsonTransformer);

        //adding a customer to the list
        post("/customers/:id", (request, response) -> {
            int id = parseInt(request.params(":id"));
            if (service.findById(id) != null) {
                throw new ResourceAlreadyExistsException();
            }

            Customer customer = asObject(request.body(), Customer.class);
            customer.setId(id);
            service.save(customer);
            return customer;
        }, jsonTransformer);

        //updating a customer of the list
        put("/customers/:id", (request, response) -> {
            Customer customer = service.findById(parseInt(request.params(":id")));

            if (customer == null) {
                new ResourceNotFoundException();
            }

            customer = asObject(request.body(), Customer.class);
            customer.setId(parseInt(request.params(":id")));
            service.save(customer);
            return customer;
        }, jsonTransformer);

        //deleting a customer from the list
        delete("/customers/:id", (request, response) -> {
            Customer customer = service.findById(parseInt(request.params(":id")));

            if (customer == null) {
                new ResourceNotFoundException();
            }

            return service.remove(customer);
        }, jsonTransformer);


        after((request, response) -> {
            response.type(CONTENT_TYPE);
        });

        //exception handling
        exception(RestfulException.class, (e, request, response) -> {
            RestfulException restfulException = (RestfulException) e;
            response.status(restfulException.getStatusCode());
            response.body(restfulException.getMessage());
        });
    }
}
