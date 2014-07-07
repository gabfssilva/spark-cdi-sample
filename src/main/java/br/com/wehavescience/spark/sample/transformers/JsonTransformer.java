package br.com.wehavescience.spark.sample.transformers;

import spark.ResponseTransformer;

import static br.com.wehavescience.spark.sample.utils.JsonUtils.asJson;

/**
 * @author Gabriel Francisco <gabfssilva@gmail.com>
 */
public class JsonTransformer implements ResponseTransformer {
    @Override
    public String render(Object model) throws Exception {
        return asJson(model);
    }
}
