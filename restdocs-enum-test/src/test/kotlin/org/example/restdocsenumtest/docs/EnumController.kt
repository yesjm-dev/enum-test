package org.example.restdocsenumtest.docs

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class EnumController {
    @GetMapping("/enums")
    fun findEnums(): ApiResponseDto<EnumDocs> {
        return ApiResponseDto.of(
            EnumDocs(
                country = getDocs(Country.entries.toTypedArray()),
            ),
        )
    }

    private fun <E> getDocs(values: Array<E>): Map<String, String> where E : Enum<E>, E : DocsEnumType {
        return values.associate { it.name to it.description() }
    }
}

data class EnumDocs(
    var country: Map<String, String>,
)