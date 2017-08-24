package com.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by frank_zhao on 2017/8/23.
 *
 * @apiNote Jackson相关操作
 */
public class JsonUtil {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();


    /**
     * @param o
     * @return
     * @throws JsonProcessingException
     * @apiNote 对象转json
     */
    public static String objectToJson(Object o) throws JsonProcessingException {
        if (o == null) {
            return null;
        }
        return JSON_MAPPER.writeValueAsString(o);
    }


    /**
     * @param json
     * @param className
     * @param <T>
     * @return
     * @throws IOException
     * @apiNote json转对象
     */
    public static <T> T jsonToObject(String json, Class<T> className) throws IOException {
        if (json == null || json.length() < 1) {
            return null;
        }
        return JSON_MAPPER.readValue(json, className);
    }


    /**
     * @param content
     * @param className
     * @param <T>
     * @return
     * @throws IOException
     * @apiNote byte[]转对象
     */
    public static <T> T utf8BytesToBean(byte[] content, Class<T> className) throws IOException {
        if (null == content) {
            return null;
        }
        return JSON_MAPPER.readValue(new String(content, "utf-8"), className);
    }

    /**
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     * @throws IOException
     * @apiNote json转集合类型
     */
    public static <T> T jsonToObject(String json, TypeReference<T> typeReference) throws IOException {
        if (json == null || json.length() < 1) {
            return null;
        }
        return (T) JSON_MAPPER.readValue(json, typeReference);
    }

    /**
     * @param json
     * @param node
     * @return
     * @throws IOException
     * @apiNote 获取json中注定的字段值
     */
    public static String filter(String json, String node) throws IOException {
        JsonNode jsonNode = JSON_MAPPER.readTree(json);
        return jsonNode.get(node).toString();
    }
}
