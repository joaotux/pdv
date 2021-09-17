package net.originmobi.pdv.utilitarios;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
