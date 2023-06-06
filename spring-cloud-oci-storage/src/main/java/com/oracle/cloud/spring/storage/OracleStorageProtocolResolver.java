/*
 ** Copyright (c) 2023, Oracle and/or its affiliates.
 ** Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl/
 */

package com.oracle.cloud.spring.storage;

import com.oracle.bmc.objectstorage.ObjectStorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;

public class OracleStorageProtocolResolver implements ProtocolResolver, ResourceLoaderAware, BeanFactoryPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(OracleStorageProtocolResolver.class);

    @Nullable
    private ObjectStorageClient osClient;

    @Nullable
    private BeanFactory beanFactory;

    @Nullable
    private StorageOutputStreamProvider storageOutputStreamProvider;

    @Override
    public Resource resolve(String location, ResourceLoader resourceLoader) {
        ObjectStorageClient osClient = getS3Client();
        if (osClient == null) {
            LOGGER.warn("Could not resolve ObjectStorageClient. Resource {} could not be resolved", location);
            return null;
        }

        StorageOutputStreamProvider storageOutputStreamProvider = getStorageOutputStreamProvider();

        return OracleStorageResource.create(location, osClient, storageOutputStreamProvider);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        if (DefaultResourceLoader.class.isAssignableFrom(resourceLoader.getClass())) {
            ((DefaultResourceLoader) resourceLoader).addProtocolResolver(this);
        } else {
            LOGGER.warn("The provided delegate resource loader is not an implementation "
                    + "of DefaultResourceLoader. Custom Protocol using ocs:// prefix will not be enabled.");
        }
    }

    @Nullable
    private ObjectStorageClient getS3Client() {
        if (osClient != null) {
            return osClient;
        }

        if (beanFactory != null) {
            ObjectStorageClient osClient = beanFactory.getBean(ObjectStorageClient.class);
            this.osClient = osClient;
            return osClient;
        }

        return null;
    }

    @Nullable
    private StorageOutputStreamProvider getStorageOutputStreamProvider() {
        if (storageOutputStreamProvider != null) {
            return storageOutputStreamProvider;
        }

        if (beanFactory != null) {
            StorageOutputStreamProvider storageOutputStreamProvider = beanFactory.getBean(StorageOutputStreamProvider.class);
            this.storageOutputStreamProvider = storageOutputStreamProvider;
            return storageOutputStreamProvider;
        }

        return null;
    }
}
