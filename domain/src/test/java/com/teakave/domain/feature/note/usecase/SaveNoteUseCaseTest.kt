package com.teakave.domain.feature.note.usecase

import com.teakave.domain.ErrorResult
import com.teakave.domain.Result
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.domain.feature.note.repository.NotesRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import java.util.Date

class SaveNoteUseCaseTest : BehaviorSpec({

    Given("Save note UC with mocked repo") {
        val noteRepo = mockk<NotesRepository>()
        val useCase = SaveNoteUseCase(noteRepo)
        When("Note content and title is null") {
            val invalidNote = NoteData(null, null, null, Date(), Date())
            Then("UC should return Result.Error and repo.saveNote is not called") {
                useCase(invalidNote) shouldBe instanceOf(Result.Error::class)
                coVerify(exactly = 0) {
                    noteRepo.saveNote(any())
                }
            }
        }
        When("Note content and title is empty") {
            val invalidNote = NoteData(null, "", "", Date(), Date())
            Then("UC should return Result.Error and repo.saveNote is not called") {
                useCase(invalidNote) shouldBe instanceOf(Result.Error::class)
                coVerify(exactly = 0) {
                    noteRepo.saveNote(any())
                }
            }
        }
        When("Edited note content and title is empty") {
            // non null noteId means that note was already saved before
            val invalidNote = NoteData(12, null, null, Date(), Date())
            Then("UC should return Result.Error and repo.saveNote is not called") {
                useCase(invalidNote) shouldBe instanceOf(Result.Error::class)
                coVerify(exactly = 0) {
                    noteRepo.saveNote(any())
                }
            }
        }
        When("Can not save given note to DB") {
            coEvery {
                noteRepo.saveNote(any())
            } returns Result.Error(ErrorResult("Something went wrong"), Unit)
            Then("UC should return Result.Error") {
                useCase(NoteData(null, "title", "content", Date(), Date())) shouldBe instanceOf(
                    Result.Error::class
                )
                coVerify(exactly = 1) {
                    noteRepo.saveNote(any())
                }
            }
        }
        When("It is possible to save a new note with only a title") {
            coEvery {
                noteRepo.saveNote(any())
            } returns Result.Success(Unit)
            Then("UC should return Result.Success") {
                useCase(NoteData(null, "title", "", Date(), Date())) shouldBe Result.Success(Unit)
                // already called once
                coVerify(exactly = 2) {
                    noteRepo.saveNote(any())
                }
            }
        }
        When("It is possible to save a new note with content only") {
            coEvery {
                noteRepo.saveNote(any())
            } returns Result.Success(Unit)
            Then("UC should return Result.Success") {
                useCase(NoteData(null, null, "content", Date(), Date())) shouldBe Result.Success(
                    Unit
                )
                // already called twice
                coVerify(exactly = 3) {
                    noteRepo.saveNote(any())
                }
            }
        }
        When("It is possible to save a new note with title and content") {
            coEvery {
                noteRepo.saveNote(any())
            } returns Result.Success(Unit)
            Then("UC should return Result.Success") {
                useCase(NoteData(null, "title", "content", Date(), Date())) shouldBe Result.Success(
                    Unit
                )
                // already called three times
                coVerify(exactly = 4) {
                    noteRepo.saveNote(any())
                }
            }
        }
        When("It is possible to update a note") {
            coEvery {
                noteRepo.saveNote(any())
            } returns Result.Success(Unit)
            Then("UC should return Result.Success") {
                useCase(NoteData(10, "title", "", Date(), Date())) shouldBe Result.Success(Unit)
                // already called four times
                coVerify(exactly = 5) {
                    noteRepo.saveNote(any())
                }
            }
        }
    }

})
