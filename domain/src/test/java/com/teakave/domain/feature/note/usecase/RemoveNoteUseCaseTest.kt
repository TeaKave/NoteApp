package com.teakave.domain.feature.note.usecase

import com.teakave.domain.ErrorResult
import com.teakave.domain.Result
import com.teakave.domain.feature.note.repository.NotesRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class RemoveNoteUseCaseTest : BehaviorSpec({

    Given("Remove UC with mocked repo") {
        val repo = mockk<NotesRepository>()
        val useCase = RemoveNoteUseCase(repo)
        When("Given noteId is negative") {
            coEvery {
                repo.removeNote(any())
            } returns Result.Error(ErrorResult("Something went wrong"))
            Then("UC should return Result.Error") {
                useCase(-1) shouldBe instanceOf(Result.Error::class)
                coVerify(exactly = 0) {
                    repo.removeNote(any())
                }
            }
        }
        When("Can not remove given note by noteId") {
            coEvery {
                repo.removeNote(any())
            } returns Result.Error(ErrorResult("Something went wrong"))
            Then("UC should return Result.Error") {
                useCase(1) shouldBe instanceOf(Result.Error::class)
                coVerify(exactly = 1) {
                    repo.removeNote(any())
                }
            }
        }
        When("Note is removed by given noteId") {
            coEvery {
                repo.removeNote(any())
            } returns Result.Success(Unit)
            Then("UC should return Result.Success") {
                useCase(3) shouldBe Result.Success(Unit)
                coVerify(exactly = 2) {
                    repo.removeNote(any())
                }
            }
        }
    }

})
