package com.teakave.domain.usecases

import com.teakave.domain.Result

abstract class UseCaseResultNoParams<out T : Any> : UseCase<Result<T>, Unit>() {

    suspend operator fun invoke() = super.invoke(Unit)

}
