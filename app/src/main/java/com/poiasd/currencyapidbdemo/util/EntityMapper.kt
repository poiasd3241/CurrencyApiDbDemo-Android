package com.poiasd.currencyapidbdemo.util

/**
 * Use to convert between a specific entity and its domain model.
 */
interface EntityMapper<Entity, DomainModel> {

    /**
     * Convert from the specific entity to its domain model.
     */
    fun mapFromEntity(entity: Entity): DomainModel

    /**
     * Convert to the specific entity from its domain model.
     */
    fun mapToEntity(domainModel: DomainModel): Entity
}
