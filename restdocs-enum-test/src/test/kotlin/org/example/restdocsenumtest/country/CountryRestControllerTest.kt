package org.example.restdocsenumtest.country

import org.example.restdocsenumtest.BaseRestDocsTest
import org.example.restdocsenumtest.docs.Country
import org.example.restdocsenumtest.docs.DocUrl
import org.example.restdocsenumtest.docs.EnumLinkGenerator
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest
import org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.queryParameters
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CountryRestControllerTest : BaseRestDocsTest() {
    @Test
    fun `getCountry 컨트롤러 테스트`() {
        mockMvc.perform(
            get("/countries")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("country", Country.KOREA.name)
        )
            .andExpect(status().isOk)
            .andDo(
                document(
                    "getCustomers",
                    preprocessRequest(Preprocessors.prettyPrint()),
                    preprocessResponse(Preprocessors.prettyPrint()),
                    queryParameters(
                        parameterWithName("country").description(EnumLinkGenerator.link(DocUrl.COUNTRY)),
                    ),
                ),
            )
    }
}