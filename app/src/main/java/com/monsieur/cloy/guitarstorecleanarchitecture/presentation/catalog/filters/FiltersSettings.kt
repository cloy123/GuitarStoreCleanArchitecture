package com.monsieur.cloy.guitarstorecleanarchitecture.presentation.catalog.filters

class FiltersSettings(var sortBy: Sort,
                      var priceFrom: Int,
                      var priceTo: Int,
                      var listFirmsId: List<Int>,
                      var listTypesId: List<Int>) {
}

enum class Sort {
    ByPopularity,
    ByPriceDescending,
    ByPriceAscending,
    ByName
}