package org.example.restdocsenumtest.country

import org.example.restdocsenumtest.docs.Country
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/countries")
class CountryRestController {
    @GetMapping
    fun getCountry(@RequestParam country: Country): String {
        return country.description
    }
}