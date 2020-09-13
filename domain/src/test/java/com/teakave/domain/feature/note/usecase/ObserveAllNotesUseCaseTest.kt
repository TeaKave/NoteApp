package com.teakave.domain.feature.note.usecase

import com.teakave.domain.ErrorResult
import com.teakave.domain.Result
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.domain.feature.note.repository.NotesRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import java.util.Date

class ObserveAllNotesUseCaseTest : BehaviorSpec({

    Given("Observer all notes UC with mocked repo") {
        val repo = mockk<NotesRepository>()
        val useCase = ObserveAllNotesUseCase(repo)
        When("Can not load all notes") {
            coEvery {
                repo.observeAllNotes()
            } returns flowOf(Result.Error(ErrorResult("Something went wrong")))
            Then("UC should return Result.Error") {
                val result = useCase()
                result.collect {
                    it shouldBe instanceOf(Result.Error::class)
                }
                coVerify(exactly = 1) {
                    repo.observeAllNotes()
                }
            }
        }
        When("Loaded notes but with emptyList") {
            coEvery {
                repo.observeAllNotes()
            } returns flowOf(Result.Success(emptyList()))
            Then("UC should return Result.Success with an empty list") {
                val result = useCase()
                result.collect {
                    it shouldBe instanceOf(Result.Success::class)
                    it.getOrNull() shouldBe emptyList()
                }
                coVerify(exactly = 2) {
                    repo.observeAllNotes()
                }
            }
        }
        When("Loaded 2 notes") {
            val listOfNotes = listOf(
                NoteData(0, "title", null, Date(), Date()),
                NoteData(5, "title", "content", Date(), Date())
            )
            coEvery {
                repo.observeAllNotes()
            } returns flowOf(Result.Success(listOfNotes))
            Then("UC should return Result.Success with two notes") {
                val result = useCase()
                result.collect {
                    it shouldBe instanceOf(Result.Success::class)
                    it.getOrNull() shouldContainExactly listOfNotes
                    it.getOrNull()?.get(0)?.content shouldBe null
                    it.getOrNull()?.get(1)?.content shouldBe "content"
                    coVerify(exactly = 3) {
                        repo.observeAllNotes()
                    }
                }
            }
        }
    }

})