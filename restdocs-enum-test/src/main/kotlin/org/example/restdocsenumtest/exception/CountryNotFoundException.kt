package org.example.restdocsenumtest.exception

import org.springframework.http.HttpStatus

class CountryNotFoundException : ApplicationException(
    httpStatus = HttpStatus.NOT_FOUND,
    errorCode = ErrorCode.NOT_FOUND,
)