package com.company.checkcompositionoffragments.core

import com.company.checkcompositionoffragments.CheckCompositionOfFragmentsTestContainer
import com.company.checkcompositionoffragments.builder.PersistenceObjectGraphBuilder
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.Metadata
import org.junit.ClassRule
import spock.lang.Shared
import spock.lang.Specification

class CheckCompositionOfFragmentsIntegrationSpecification extends Specification {

    @ClassRule
    @Shared
    CheckCompositionOfFragmentsTestContainer container = CheckCompositionOfFragmentsTestContainer.Common.INSTANCE

    PersistenceObjectGraphBuilder builder
    Metadata metadata
    DataManager dataManager

    void setup() {
        metadata = AppBeans.get(Metadata)
        dataManager = AppBeans.get(DataManager)

        builder = PersistenceObjectGraphBuilder.newInstance(metadata, dataManager)
        builder.classLoader = this.class.classLoader
        builder.classNameResolver = ["com.company.checkcompositionoffragments.entity",
                                     "com.haulmont.cuba.core.entity",
                                     "com.haulmont.cuba.security.entity"]
    }
}
