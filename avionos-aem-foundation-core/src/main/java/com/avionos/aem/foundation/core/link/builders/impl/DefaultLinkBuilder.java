package com.avionos.aem.foundation.core.link.builders.impl;

import com.avionos.aem.foundation.api.link.Link;
import com.avionos.aem.foundation.api.link.builders.LinkBuilder;
import com.avionos.aem.foundation.api.link.enums.LinkTarget;
import com.avionos.aem.foundation.core.constants.PathConstants;
import com.avionos.aem.foundation.core.link.impl.DefaultLink;
import com.avionos.aem.foundation.core.utils.PathUtils;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.SetMultimap;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public final class DefaultLinkBuilder implements LinkBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultLinkBuilder.class);

    private SetMultimap<String, String> parameters = LinkedHashMultimap.create();

    private final String path;

    private final ResourceResolver resourceResolver;

    private Map<String, String> properties = new HashMap<>();

    private List<String> selectors = new ArrayList<>();

    private List<Link> children = new ArrayList<>();

    // initialized with default values

    private boolean external;

    private boolean active = false;

    private String extension = null;

    private String host = null;

    private String scheme = null;

    private boolean opaque = false;

    private int port = 0;

    private boolean secure = false;

    private String suffix = "";

    private String target = LinkTarget.SELF.getTarget();

    private String title = "";

    public DefaultLinkBuilder(final String path, final ResourceResolver resourceResolver) {
        this.path = path;
        this.resourceResolver = resourceResolver;

        external = PathUtils.isExternal(path);
    }

    @Override
    public Link build() {
        final StringBuilder builder = new StringBuilder().append(buildHost());

        final StringBuilder mappable = new StringBuilder()
            .append(path)
            .append(buildSelectors());

        final String extension;

        if (external) {
            extension = "";
        } else {
            if (path.contains(PathConstants.SELECTOR)) {
                extension = path.substring(path.indexOf(PathConstants.SELECTOR) + 1);
            } else {
                extension = this.extension == null ? PathConstants.EXTENSION_HTML : this.extension;

                if (StringUtils.isNotEmpty(extension)) {
                    mappable.append('.').append(extension);
                }
            }
        }

        if (resourceResolver != null) {
            builder.append(resourceResolver.map(mappable.toString()));
        } else {
            builder.append(mappable.toString());
        }

        builder.append(suffix);

        final String queryString = buildQueryString();

        builder.append(queryString);

        final String href = builder.toString();

        final Link link = new DefaultLink(path, extension, suffix, href, selectors, queryString, external, target,
            title, properties, active, children);

        LOG.debug("returning link : {}", link);

        return link;
    }

    @Override
    public LinkBuilder addChild(final Link child) {
        children.add(checkNotNull(child));

        return this;
    }

    @Override
    public LinkBuilder addParameter(final String name, final String value) {
        parameters.put(checkNotNull(name), checkNotNull(value));

        return this;
    }

    @Override
    public LinkBuilder addParameters(final Map<String, String> parameters) {
        this.parameters.putAll(Multimaps.forMap(checkNotNull(parameters)));

        return this;
    }

    @Override
    public LinkBuilder addParameters(final SetMultimap<String, String> parameters) {
        this.parameters.putAll(checkNotNull(parameters));

        return this;
    }

    @Override
    public LinkBuilder addProperties(final Map<String, String> properties) {
        this.properties.putAll(checkNotNull(properties));

        return this;
    }

    @Override
    public LinkBuilder addProperty(final String name, final String value) {
        properties.put(checkNotNull(name), checkNotNull(value));

        return this;
    }

    @Override
    public LinkBuilder addSelector(final String selector) {
        selectors.add(checkNotNull(selector));

        return this;
    }

    @Override
    public LinkBuilder addSelectors(final List<String> selectors) {
        this.selectors.addAll(checkNotNull(selectors));

        return this;
    }

    @Override
    public LinkBuilder setActive(final boolean active) {
        this.active = active;

        return this;
    }

    @Override
    public LinkBuilder setExtension(final String extension) {
        this.extension = extension;

        return this;
    }

    @Override
    public LinkBuilder setExternal(final boolean external) {
        this.external = external;

        return this;
    }

    @Override
    public LinkBuilder setHost(final String host) {
        this.host = host;

        return this;
    }

    @Override
    public LinkBuilder setScheme(final String scheme) {
        this.scheme = scheme;

        return this;
    }

    @Override
    public LinkBuilder setOpaque(final boolean opaque) {
        this.opaque = opaque;

        return this;
    }

    @Override
    public LinkBuilder setPort(final int port) {
        this.port = port;

        return this;
    }

    @Override
    public LinkBuilder setSecure(final boolean secure) {
        this.secure = secure;

        return this;
    }

    @Override
    public LinkBuilder setSuffix(final String suffix) {
        this.suffix = suffix;

        return this;
    }

    @Override
    public LinkBuilder setTarget(final String target) {
        this.target = target;

        return this;
    }

    @Override
    public LinkBuilder setTitle(final String title) {
        this.title = title;

        return this;
    }

    // internals

    private String buildHost() {
        final StringBuilder builder = new StringBuilder();

        if (external) {
            // ex: www.avionos.com
            if (scheme != null && !path.startsWith(scheme)) {
                builder.append(scheme).append(":");

                if (!opaque) {
                    builder.append("//");
                }
            }
        } else if (host != null) {
            if (scheme != null) {
                builder.append(scheme);
            } else {
                builder.append(secure ? "https" : "http");
            }

            builder.append(":");

            if (!opaque) {
                builder.append("//");
            }

            builder.append(host);

            if (port > 0) {
                builder.append(':');
                builder.append(port);
            }
        }

        return builder.toString();
    }

    private String buildQueryString() {
        final StringBuilder builder = new StringBuilder();

        if (!parameters.isEmpty()) {
            builder.append('?');

            parameters.keySet().forEach(name -> {
                parameters.get(name).forEach(value -> {
                    try {
                        builder.append(URLEncoder.encode(name, StandardCharsets.UTF_8.name()));
                        builder.append('=');
                        builder.append(URLEncoder.encode(value, StandardCharsets.UTF_8.name()));
                    } catch (UnsupportedEncodingException uee) {
                        LOG.error("invalid encoding for parameter :" + name + "=" + value, uee);
                    }

                    builder.append('&');
                });
            });

            builder.deleteCharAt(builder.length() - 1);
        }

        return builder.toString();
    }

    private String buildSelectors() {
        final StringBuilder builder = new StringBuilder();

        if (!external) {
            selectors.forEach(selector -> {
                builder.append('.');
                builder.append(selector);
            });
        }

        return builder.toString();
    }
}
