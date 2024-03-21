package ru.itech.exception

import org.springframework.http.HttpStatus

class CommunityNotFoundException(communityId: Int): BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = 404,
        description = "community not found with id=$communityId"
    )
) {
}