package com.poiasd.currencyapidbdemo.data.db

import com.poiasd.currencyapidbdemo.data.model.Currency
import com.poiasd.currencyapidbdemo.util.EntityMapper
import javax.inject.Inject

/**
 * Use to convert between the database entity [CurrencyDbEntity] and the domain model [Currency].
 */
class CurrencyDbEntityMapper @Inject constructor() : EntityMapper<CurrencyDbEntity, Currency> {
    override fun mapFromEntity(entity: CurrencyDbEntity): Currency {
        return Currency(
            alpha3Code = entity.alpha3Code,
            nameInRussian = entity.nameInRussian,
            denomination = entity.denomination,
            valueInRub = entity.valueInRub
        )
    }

    override fun mapToEntity(domainModel: Currency): CurrencyDbEntity {
        return CurrencyDbEntity(
            alpha3Code = domainModel.alpha3Code,
            nameInRussian = domainModel.nameInRussian,
            denomination = domainModel.denomination,
            valueInRub = domainModel.valueInRub
        )
    }

    fun mapFromEntityList(entities: List<CurrencyDbEntity>): List<Currency> {
        return entities.map { mapFromEntity(it) }
    }

    fun mapToEntityList(entities: List<Currency>): List<CurrencyDbEntity> {
        return entities.map { mapToEntity(it) }
    }
}
