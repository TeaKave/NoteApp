package com.teakave.domain.usecases

abstract class UseCaseNoParams<out T> : UseCase<T, Unit>() {

    suspend operator fun invoke(): T = super.invoke(Unit)
}
