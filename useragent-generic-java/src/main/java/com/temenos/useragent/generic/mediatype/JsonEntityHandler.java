package com.temenos.useragent.generic.mediatype;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import static com.temenos.useragent.generic.mediatype.JsonUtil.extractLinks;
import static com.temenos.useragent.generic.mediatype.JsonUtil.navigateJsonObjectforPropertyPath;
import static com.temenos.useragent.generic.mediatype.PropertyNameUtil.extractIndex;
import static com.temenos.useragent.generic.mediatype.PropertyNameUtil.extractPropertyName;
import static com.temenos.useragent.generic.mediatype.PropertyNameUtil.flattenPropertyName;
import static com.temenos.useragent.generic.mediatype.PropertyNameUtil.isPropertyNameWithIndex;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.temenos.useragent.generic.Link;
import com.temenos.useragent.generic.internal.EntityHandler;
import com.temenos.useragent.generic.internal.Payload;


public class JsonEntityHandler implements EntityHandler {

    JSONObject jsonObject = new JSONObject();

    @Override
    public String getId() {
        return "";
    }

    @Override
    public List<Link> getLinks() {
        return extractLinks(jsonObject);
    }

    @Override
    public String getValue(String fqPropertyName) {
        List<String> pathParts = Arrays.asList(flattenPropertyName(fqPropertyName));
        Optional<JSONObject> parent = navigateJsonObjectforPropertyPath(Optional.ofNullable(jsonObject),
                pathParts.subList(0, pathParts.size() - 1), fqPropertyName, false);
        String lastPathPart = pathParts.get(pathParts.size() - 1);
        if (parent.isPresent()) {
            if (isPropertyNameWithIndex(lastPathPart)) {
                JSONArray jsonArr = parent.get().optJSONArray(extractPropertyName(lastPathPart));
                if (jsonArr != null) {
                    return jsonArr.opt(extractIndex(lastPathPart)) != null
                            ? String.valueOf(jsonArr.opt(extractIndex(lastPathPart))) : null;
                }
            } else {
                return parent.get().opt(lastPathPart) != null ? String.valueOf(parent.get().opt(lastPathPart)) : null;
            }
        }
        return null;
    }

    @Override
    public <T> void setValue(String fqPropertyName, T value) {
        List<String> pathParts = Arrays.asList(flattenPropertyName(fqPropertyName));
        Optional<JSONObject> parent = navigateJsonObjectforPropertyPath(Optional.ofNullable(jsonObject),
                pathParts.subList(0, pathParts.size() - 1), fqPropertyName, true);
        if (parent.isPresent()) {
            parent.get().put(pathParts.get(pathParts.size() - 1), value);
        }
    }

    @Override
    public int getCount(String fqPropertyName) {
        List<String> pathParts = Arrays.asList(flattenPropertyName(fqPropertyName));
        Optional<JSONObject> parent = navigateJsonObjectforPropertyPath(Optional.ofNullable(jsonObject),
                pathParts.subList(0, pathParts.size() - 1), fqPropertyName, false);
        String lastPathPart = pathParts.get(pathParts.size() - 1);
        if (parent.isPresent() && parent.get().optJSONArray(lastPathPart) != null) {
            return parent.get().optJSONArray(lastPathPart).length();
        }
        return 0;
    }

    @Override
    public void remove(String fqPropertyName) {
        List<String> pathParts = Arrays.asList(flattenPropertyName(fqPropertyName));
        Optional<JSONObject> parent = navigateJsonObjectforPropertyPath(Optional.ofNullable(jsonObject),
                pathParts.subList(0, pathParts.size() - 1), fqPropertyName, false);
        String lastPathPart = pathParts.get(pathParts.size() - 1);
        if (parent.isPresent()) {
            if (isPropertyNameWithIndex(lastPathPart)) {
                JSONArray jsonArr = parent.get().optJSONArray(extractPropertyName(lastPathPart));
                if (jsonArr != null) {
                    jsonArr.remove(extractIndex(lastPathPart));
                }
            } else {
                parent.get().remove(lastPathPart);
            }
        }
    }

    @Override
    public void setContent(InputStream stream) {
        if (stream == null) {
            throw new IllegalArgumentException("Input stream is null");
        }
        String content = null;
        try {
            content = IOUtils.toString(stream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        jsonObject = new JSONObject(content);
    }

    @Override
    public InputStream getContent() {
        return IOUtils.toInputStream(jsonObject.toString(4));
    }

    @Override
    public Payload embedded() {
        return null;
    }

}
