package com.zhul.cloud.common.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

/**
 * Created by yanglikai on 2019/5/23.
 */
public class MyNumberDeserializers extends NumberDeserializers {
  public MyNumberDeserializers() {
  }

  public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
    private static final long serialVersionUID = 1L;
    static final NumberDeserializers.BooleanDeserializer primitiveInstance;
    static final NumberDeserializers.BooleanDeserializer wrapperInstance;

    public BooleanDeserializer(Class<Boolean> cls, Boolean nvl) {
      super(cls, nvl, null);
    }

    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
      JsonToken token = jsonParser.getCurrentToken();
      if (token == JsonToken.VALUE_TRUE) {
        return Boolean.TRUE;
      } else if (token == JsonToken.VALUE_FALSE) {
        return Boolean.FALSE;
      } else if (token == JsonToken.VALUE_NUMBER_INT) {
        return
            jsonParser.getNumberType() == JsonParser.NumberType.INT
                ? (jsonParser.getIntValue() == 0 ? Boolean.FALSE : Boolean.TRUE)
                : Boolean.valueOf(this._parseBooleanFromInt(jsonParser, deserializationContext));
      } else if (token == JsonToken.VALUE_NULL) {
        return (Boolean) this.getNullValue(deserializationContext);
      } else if (token == JsonToken.VALUE_STRING) {
        String text = jsonParser.getText().trim();
        if (!"true".equals(text) && !"True".equals(text)) {
          if (!"false".equals(text) && !"False".equals(text)) {
            if (text.length() == 0) {
              return (Boolean) this.getEmptyValue(deserializationContext);
            } else if (this._hasTextualNull(text)) {
              return (Boolean) this.getNullValue(deserializationContext);
            } else {
              try {
                Integer val = Integer.valueOf(Integer.parseInt(text));
                return Boolean.valueOf(val.intValue() > 0);
              } catch (Exception var6) {
                throw deserializationContext.weirdStringException(
                    text,
                    this._valueClass, "only \"true\" or \"false\" recognized");
              }
            }
          } else {
            return Boolean.FALSE;
          }
        } else {
          return Boolean.TRUE;
        }
      } else if (token == JsonToken.START_ARRAY
          && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
        jsonParser.nextToken();
        Boolean parsed = this._parseBooleanPrimitive(jsonParser, deserializationContext);
        token = jsonParser.nextToken();
        if (token != JsonToken.END_ARRAY) {
          throw deserializationContext.wrongTokenException(
              jsonParser,
              JsonToken.END_ARRAY,
              "Attempted to unwrap single value array for single 'Boolean' value but there was more than a single value in the array");
        } else {
          return parsed;
        }
      } else {
        throw deserializationContext.mappingException(
            "Can not deserialize instance of %s out of %s",
            this._valueClass.getName(),
            token);
      }
    }

    @Override
    public Boolean deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer)
        throws IOException, JsonProcessingException {
      return this._parseBooleanPrimitive(jp, ctxt);
    }

    static {
      primitiveInstance = new NumberDeserializers.BooleanDeserializer(Boolean.class, Boolean.FALSE);
      wrapperInstance = new NumberDeserializers.BooleanDeserializer(Boolean.TYPE, (Boolean) null);
    }
  }
}
