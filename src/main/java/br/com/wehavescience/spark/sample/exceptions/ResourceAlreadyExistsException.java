package br.com.wehavescience.spark.sample.exceptions;

/**
 * @author Gabriel Francisco <gabfssilva@gmail.com>
 */
public class ResourceAlreadyExistsException extends RestfulException {

    public ResourceAlreadyExistsException() {
        super(409, "ID already exists, if you want to update an existing resource, use the PUT method instead of POST");
    }
}
