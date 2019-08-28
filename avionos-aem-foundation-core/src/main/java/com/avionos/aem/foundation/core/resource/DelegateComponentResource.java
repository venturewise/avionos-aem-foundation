package com.avionos.aem.foundation.core.resource;

import com.avionos.aem.foundation.api.link.Link;
import com.avionos.aem.foundation.api.link.builders.LinkBuilder;
import com.avionos.aem.foundation.api.page.FoundationPage;
import com.avionos.aem.foundation.api.resource.ComponentResource;
import com.day.cq.tagging.Tag;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

/**
 * Extendable class that delegates to a <code>ComponentResource</code> instance.  Use when instantiating component
 * resource instances directly (i.e. not in the context of a JSP or HTL request).
 */
@JsonAutoDetect(fieldVisibility = NONE, getterVisibility = NONE, isGetterVisibility = NONE)
public abstract class DelegateComponentResource implements ComponentResource {

    private final ComponentResource componentResource;

    public DelegateComponentResource(final ComponentResource componentResource) {
        this.componentResource = componentResource;
    }

    @Override
    public final ValueMap asMap() {
        return componentResource.asMap();
    }

    @Override
    public final Optional<ComponentResource> findAncestor(final Predicate<ComponentResource> predicate) {
        return componentResource.findAncestor(predicate);
    }

    @Override
    public Optional<ComponentResource> findAncestor(final Predicate<ComponentResource> predicate,
        final boolean excludeCurrentResource) {
        return componentResource.findAncestor(predicate, excludeCurrentResource);
    }

    @Override
    public final Optional<ComponentResource> findAncestorWithProperty(final String propertyName) {
        return componentResource.findAncestorWithProperty(propertyName);
    }

    @Override
    public Optional<ComponentResource> findAncestorWithProperty(final String propertyName,
        final boolean excludeCurrentResource) {
        return componentResource.findAncestorWithProperty(propertyName, excludeCurrentResource);
    }

    @Override
    public final <T> Optional<ComponentResource> findAncestorWithPropertyValue(final String propertyName,
        final T propertyValue) {
        return componentResource.findAncestorWithPropertyValue(propertyName, propertyValue);
    }

    @Override
    public <V> Optional<ComponentResource> findAncestorWithPropertyValue(final String propertyName,
        final V propertyValue,
        final boolean excludeCurrentResource) {
        return componentResource.findAncestorWithPropertyValue(propertyName, propertyValue, excludeCurrentResource);
    }

    @Override
    public final List<ComponentResource> findDescendants(final Predicate<ComponentResource> predicate) {
        return componentResource.findDescendants(predicate);
    }

    @Override
    public final <T> T get(final String propertyName, final T defaultValue) {
        return componentResource.get(propertyName, defaultValue);
    }

    @Override
    public final <T> Optional<T> get(final String propertyName, final Class<T> type) {
        return componentResource.get(propertyName, type);
    }

    @Override
    public final Optional<String> getAsHref(final String propertyName) {
        return componentResource.getAsHref(propertyName);
    }

    @Override
    public final Optional<String> getAsHref(final String propertyName, final boolean strict) {
        return componentResource.getAsHref(propertyName, strict);
    }

    @Override
    public final Optional<String> getAsHref(final String propertyName, final boolean strict, final boolean mapped) {
        return componentResource.getAsHref(propertyName, strict, mapped);
    }

    @Override
    public final Optional<String> getAsHrefInherited(final String propertyName) {
        return componentResource.getAsHrefInherited(propertyName);
    }

    @Override
    public final Optional<String> getAsHrefInherited(final String propertyName, final boolean strict) {
        return componentResource.getAsHrefInherited(propertyName, strict);
    }

    @Override
    public final Optional<String> getAsHrefInherited(final String propertyName, final boolean strict,
        final boolean mapped) {
        return componentResource.getAsHrefInherited(propertyName, strict, mapped);
    }

    @Override
    public final Optional<Link> getAsLink(final String propertyName) {
        return componentResource.getAsLink(propertyName);
    }

    @Override
    public final Optional<Link> getAsLink(final String propertyName, final boolean strict) {
        return componentResource.getAsLink(propertyName, strict);
    }

    @Override
    public final Optional<Link> getAsLink(final String propertyName, final boolean strict, final boolean mapped) {
        return componentResource.getAsLink(propertyName, strict, mapped);
    }

    @Override
    public final Optional<Link> getAsLinkInherited(final String propertyName) {
        return componentResource.getAsLinkInherited(propertyName);
    }

    @Override
    public final Optional<Link> getAsLinkInherited(final String propertyName, final boolean strict) {
        return componentResource.getAsLinkInherited(propertyName, strict);
    }

    @Override
    public final Optional<Link> getAsLinkInherited(final String propertyName, final boolean strict,
        final boolean mapped) {
        return componentResource.getAsLinkInherited(propertyName, strict, mapped);
    }

    @Override
    public final <T> List<T> getAsList(final String propertyName, final Class<T> type) {
        return componentResource.getAsList(propertyName, type);
    }

    @Override
    public final <T> List<T> getAsListInherited(final String propertyName, final Class<T> type) {
        return componentResource.getAsListInherited(propertyName, type);
    }

    @Override
    public final Optional<FoundationPage> getAsPage(final String propertyName) {
        return componentResource.getAsPage(propertyName);
    }

    @Override
    public final <AdapterType> Optional<AdapterType> getAsType(final String propertyName,
        final Class<AdapterType> type) {
        return componentResource.getAsType(propertyName, type);
    }

    @Override
    public <AdapterType> List<AdapterType> getAsTypeList(final String propertyName, final Class<AdapterType> type) {
        return componentResource.getAsTypeList(propertyName, type);
    }

    @Override
    public final Optional<String> getImageReference(final boolean isSelf) {
        return componentResource.getImageReference(isSelf);
    }

    @Override
    public final Optional<FoundationPage> getAsPageInherited(final String propertyName) {
        return componentResource.getAsPageInherited(propertyName);
    }

    @Override
    public final <AdapterType> Optional<AdapterType> getAsTypeInherited(final String propertyName,
        final Class<AdapterType> type) {
        return componentResource.getAsTypeInherited(propertyName, type);
    }

    @Override
    public final Optional<String> getImageReferenceInherited(final boolean isSelf) {
        return componentResource.getImageReferenceInherited(isSelf);
    }

    @Override
    public final Optional<ComponentResource> getComponentResource(final String relativePath) {
        return componentResource.getComponentResource(relativePath);
    }

    @Override
    public final List<ComponentResource> getComponentResources() {
        return componentResource.getComponentResources();
    }

    @Override
    public final List<ComponentResource> getComponentResources(final Predicate<ComponentResource> predicate) {
        return componentResource.getComponentResources(predicate);
    }

    @Override
    public final List<ComponentResource> getComponentResources(final String relativePath) {
        return componentResource.getComponentResources(relativePath);
    }

    @Override
    public final List<ComponentResource> getComponentResources(final String relativePath, final String resourceType) {
        return componentResource.getComponentResources(relativePath, resourceType);
    }

    @Override
    public final List<ComponentResource> getComponentResources(final String relativePath,
        final Predicate<ComponentResource> predicate) {
        return componentResource.getComponentResources(relativePath, predicate);
    }

    @Override
    public final String getHref() {
        return componentResource.getHref();
    }

    @Override
    public final String getHref(final boolean mapped) {
        return componentResource.getHref(mapped);
    }

    @Override
    public final String getId() {
        return componentResource.getId();
    }

    @Override
    public final Optional<String> getImageReference() {
        return componentResource.getImageReference();
    }

    @Override
    public final Optional<String> getImageReference(final String name) {
        return componentResource.getImageReference(name);
    }

    @Override
    public final Optional<String> getImageReferenceInherited() {
        return componentResource.getImageReferenceInherited();
    }

    @Override
    public final Optional<String> getImageReferenceInherited(final String name) {
        return componentResource.getImageReferenceInherited(name);
    }

    @Override
    public final Optional<String> getImageRendition(final String renditionName) {
        return componentResource.getImageRendition(renditionName);
    }

    @Override
    public final Optional<String> getImageRendition(final String name, final String renditionName) {
        return componentResource.getImageRendition(name, renditionName);
    }

    @Override
    public List<Tag> getTags(final String propertyName) {
        return componentResource.getTags(propertyName);
    }

    @Override
    public final int getIndex() {
        return componentResource.getIndex();
    }

    @Override
    public final int getIndex(final String resourceType) {
        return componentResource.getIndex(resourceType);
    }

    @Override
    public final <T> T getInherited(final String propertyName, final T defaultValue) {
        return componentResource.getInherited(propertyName, defaultValue);
    }

    @Override
    public final <T> Optional<T> getInherited(final String propertyName, final Class<T> type) {
        return componentResource.getInherited(propertyName, type);
    }

    @Override
    public final Link getLink() {
        return componentResource.getLink();
    }

    @Override
    public final Link getLink(final boolean mapped) {
        return componentResource.getLink(mapped);
    }

    @Override
    public final LinkBuilder getLinkBuilder() {
        return componentResource.getLinkBuilder();
    }

    @Override
    public final LinkBuilder getLinkBuilder(final boolean mapped) {
        return componentResource.getLinkBuilder(mapped);
    }

    @Override
    public final Optional<ComponentResource> getComponentResourceInherited(final String relativePath) {
        return componentResource.getComponentResourceInherited(relativePath);
    }

    @Override
    public final List<ComponentResource> getComponentResourcesInherited(final String relativePath) {
        return componentResource.getComponentResourcesInherited(relativePath);
    }

    @Override
    public final Optional<ComponentResource> getParent() {
        return componentResource.getParent();
    }

    @Override
    public final String getPath() {
        return componentResource.getPath();
    }

    @Override
    public final Resource getResource() {
        return componentResource.getResource();
    }

    @Override
    public final boolean isHasImage() {
        return componentResource.isHasImage();
    }

    @Override
    public final boolean isHasImage(final String name) {
        return componentResource.isHasImage(name);
    }
}
