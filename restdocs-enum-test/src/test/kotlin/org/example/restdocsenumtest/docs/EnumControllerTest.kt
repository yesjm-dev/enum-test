package org.example.restdocsenumtest.docs

import com.fasterxml.jackson.core.type.TypeReference
import org.example.restdocsenumtest.BaseRestDocsTest
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.beneathPath
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.snippet.Attributes.attributes
import org.springframework.restdocs.snippet.Attributes.key
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.reflect.full.memberProperties

class EnumControllerTest: BaseRestDocsTest() {
    @Test
    fun enums() {
        val result =
            mockMvc.perform(
                RestDocumentationRequestBuilders.get("/test/enums")
                    .contentType(MediaType.APPLICATION_JSON),
            )
        val mvcResult = result.andReturn()
        val enumDocs: EnumDocs = getData(mvcResult)
        val snippets: MutableList<CustomResponseFieldsSnippet> = mutableListOf()
        EnumDocs::class.memberProperties.forEach {
            val name = it.name
            val value = it.call(enumDocs) as Map<String, String>

            snippets.add(
                customResponseFields(
                    name = name,
                    value = value,
                ),
            )
        }
        result.andExpect(status().isOk())
            .andDo(
                document(
                    "enum",
                    *snippets.toTypedArray(),
                ),
            )
    }

    fun customResponseFields(
        name: String,
        value: Map<String, String>,
    ): CustomResponseFieldsSnippet {
        return CustomResponseFieldsSnippet(
            "custom-response",
            beneathPath("data.$name").withSubsectionId(name),
            enumConvertFieldDescriptor((value)).asList(),
            attributes(key("title").value(name)),
            true,
        )
    }

    private fun enumConvertFieldDescriptor(enumValues: Map<String, String>): Array<FieldDescriptor> {
        return enumValues.map { (key, value) ->
            fieldWithPath(key).description(value)
        }.toTypedArray()
    }

    private fun getData(result: MvcResult): EnumDocs {
        val apiResponseDto: ApiResponseDto<EnumDocs> =
            objectMapper
                .readValue(
                    result.response.contentAsByteArray,
                    object : TypeReference<ApiResponseDto<EnumDocs>>() {},
                )
        return apiResponseDto.data
    }
}