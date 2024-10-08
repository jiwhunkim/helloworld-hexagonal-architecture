package com.helloworld.config

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.stereotype.Component

@Component
class LazyBeanFactorPostProcessor : BeanFactoryPostProcessor {
    val logger = KotlinLogging.logger {}

    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        for (name in beanFactory.beanDefinitionNames) {
            if (beanFactory.getBeanDefinition(name).isLazyInit) {
                logger.info { "name : $name / lazy-init : ${beanFactory.getBeanDefinition(name).isLazyInit}" }
            }
        }
    }
}
