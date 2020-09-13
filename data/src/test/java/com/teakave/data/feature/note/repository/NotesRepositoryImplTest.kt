package com.teakave.data.feature.note.repository

import com.teakave.data.feature.note.source.NotesSource
import com.teakave.domain.ErrorResult
import com.teakave.domain.Result
import com.teakave.domain.feature.note.model.NoteData
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import java.util.*

class NotesRepositoryImplTest : BehaviorSpec({

    Given("Notes repo with mocked local source") {
        val source = mockk<NotesSource>()
        val repo = NotesRepositoryImpl(source)
        When("Remove note returns Error") {
            coEvery {
                source.removeNote(any())
            } returns Result.Error(ErrorResult("Something went wrong"))
            Then("Repo func call should return Result.Error") {
                repo.removeNote(5) shouldBe instanceOf(Result.Error::class)
                coVerify(exactly = 1) { source.removeNote(any()) }
            }
        }
        When("Remove note returns Success") {
            coEvery {
                source.removeNote(any())
            } returns Result.Success(Unit)
            Then("Repo func call should return Result.Success") {
                repo.removeNote(5) shouldBe instanceOf(Result.Success::class)
                coVerify(exactly = 2) { source.removeNote(any()) }
            }
        }
        When("Can not save a new note") {
            coEvery {
                source.saveNote(any())
            } returns Result.Error(ErrorResult("Something went wrong"))
            Then("Repo func call should return Result.Error") {
                repo.saveNote(
                    NoteData(
                        null,
                        "title",
                        "content",
                        Date(),
                        Date()
                    )
                ) shouldBe instanceOf(Result.Error::class)
                coVerify(exactly = 1) { source.saveNote(any()) }
            }
        }
        When("Can not update a note") {
            coEvery {
                source.saveNote(any())
            } returns Result.Error(ErrorResult("Something went wrong"))
            Then("Repo func call should return Result.Error") {
                repo.saveNote(NoteData(12, "title", "content", Date(), Date())) shouldBe instanceOf(
                    Result.Error::class
                )
                coVerify(exactly = 2) { source.saveNote(any()) }
            }
        }
        When("Note is saved") {
            coEvery {
                source.saveNote(any())
            } returns Result.Success(Unit)
            Then("Repo func call should return Result.Success") {
                repo.saveNote(
                    NoteData(
                        null,
                        "title",
                        "content",
                        Date(),
                        Date()
                    )
                ) shouldBe instanceOf(Result.Success::class)
                coVerify(exactly = 3) { source.saveNote(any()) }
            }
        }
        When("Note is updated") {
            coEvery {
                source.saveNote(any())
            } returns Result.Success(Unit)
            Then("Repo func call should return Result.Success") {
                repo.saveNote(
                    NoteData(
                        12,
                        "title",
                        null,
                        Date(),
                        Date()
                    )
                ) shouldBe instanceOf(Result.Success::class)
                coVerify(exactly = 4) { source.saveNote(any()) }
            }
        }
        When("Observe notes") {
            And("Local source is returning Result.Error") {
                coEvery {
                    source.observeAllNotes()
                } returns flowOf(Result.Error(ErrorResult(message = null)))
                Then("Repo should return Result.Error") {
                    val result = repo.observeAllNotes()
                    result.collect {
                        it shouldBe instanceOf(Result.Error::class)
                    }
                }
            }
            And("Local source is returning Result.Success with an emptyList") {
                coEvery {
                    source.observeAllNotes()
                } returns flowOf(Result.Success(emptyList()))
                Then("Repo should return Result.Success") {
                    val result = repo.observeAllNotes()
                    result.collect {
                        it shouldBe instanceOf(Result.Success::class)
                        it.getOrNull() shouldBe emptyList()
                    }
                }
            }

            And("Local source is returning Result.Success with a note") {
                coEvery {
                    source.observeAllNotes()
                } returns flowOf(
                    Result.Success(
                        listOf(
                            NoteData(
                                noteId = 1,
                                title = "title",
                                content = "content",
                                createdDate = Date(),
                                lastUpdateDate = Date()
                            )
                        )
                    )
                )
                Then("Repo should return Result.Success") {
                    val result = repo.observeAllNotes()
                    result.collect {
                        it shouldBe instanceOf(Result.Success::class)
                        it.getOrNull()?.size shouldBe 1
                    }
                }
            }
        }
    }

})