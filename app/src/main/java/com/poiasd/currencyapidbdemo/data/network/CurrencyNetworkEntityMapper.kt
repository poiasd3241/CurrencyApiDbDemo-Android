package com.poiasd.currencyapidbdemo.data.network

import com.poiasd.currencyapidbdemo.data.model.Currency
import com.poiasd.currencyapidbdemo.data.network.response.CurrencyNetworkEntity
import com.poiasd.currencyapidbdemo.util.EntityMapper
import javax.inject.Inject

/**
 * Use to convert between the network entity [CurrencyNetworkEntity] and the domain model [Currency].
 */
class CurrencyNetworkEntityMapper @Inject constructor() :
    EntityMapper<CurrencyNetworkEntity, Currency> {

    override fun mapFromEntity(entity: CurrencyNetworkEntity): Currency {
        return Currency(
            alpha3Code = entity.alpha3Code,
            nameInRussian = entity.nameInRussian,
            denomination = entity.denomination,
            valueInRub = entity.valueInRub
        )
    }

    override fun mapToEntity(domainModel: Currency): CurrencyNetworkEntity {
        return CurrencyNetworkEntity(
            alpha3Code = domainModel.alpha3Code,
            nameInRussian = domainModel.nameInRussian,
            denomination = domainModel.denomination,
            valueInRub = domainModel.valueInRub
        )
    }
}
