package org.example.restdocsenumtest.docs

interface EnumLinkGenerator {
    companion object {
        fun link(docUrl: DocUrl): String {
            return "link:common/${docUrl.pageId}.html[${docUrl.text} 코드,role=\"popup\"]"
        }
    }
}

enum class DocUrl(val pageId: String, val text: String) {
    COUNTRY("country", "내가 다녀왔던 나라"),
}