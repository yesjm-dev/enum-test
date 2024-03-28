package org.example.restdocsenumtest.docs

enum class Country(
    val description: String
) : DocsEnumType {
    KOREA("한국"),
    JAPAN("일본"),
    TAIWAN("대만"),
    HONG_KONG("홍콩"),
    MACAU("마카오"),
    LAOS("라오스"),
    UNITED_KINGDOM("영국"),
    FRANCE("프랑스"),
    SWITZERLAND("스위스"),
    SPAIN("스페인"),
    CZECH("체코"),
    GERMANY("독일"),
    ;

    override fun description() = description
}