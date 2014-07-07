package br.com.wehavescience.spark.sample.exceptions;

/**
 * @author Gabriel Francisco <gabfssilva@gmail.com>
 */
public class ResourceNotFoundException extends RestfulException {
    public ResourceNotFoundException() {
        super(404, "Not found");
    }
}
