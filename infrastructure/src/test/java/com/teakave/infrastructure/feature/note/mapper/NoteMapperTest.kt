package com.teakave.infrastructure.feature.note.mapper

import com.teakave.domain.feature.note.model.NoteData
import com.teakave.infrastructure.feature.note.source.local.database.entity.NoteEntity
import com.teakave.infrastructure.feature.note.source.local.database.mapper.toData
import com.teakave.infrastructure.feature.note.source.local.database.mapper.toEntity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.instanceOf
import java.util.Date

class NoteMapperTest : BehaviorSpec({
    Given("Note mapper") {
        When("Mapping valid data from NoteData to NoteEntity") {
            Then("Mapper should return valid NoteEntity") {
                val entityResult = NoteData(1, "title", null, Date(), Date()).toEntity()
                entityResult shouldNotBe null
                entityResult as NoteEntity
                entityResult shouldBe instanceOf(NoteEntity::class)
                entityResult.content shouldBe null
                entityResult.title shouldBe "title"
                entityResult.noteId shouldBe 1
            }
        }
        When("NoteData is null") {
            val noteData: NoteData? = null
            Then("Mapper should return null") {
                noteData?.toEntity() shouldBe null
            }
        }
        When("noteId of noteData is null") {
            val noteData = NoteData(
                noteId = null,
                title = null,
                content = null,
                createdDate = Date(),
                lastUpdateDate = Date()
            )
            Then("Mapper should return null") {
                val result = noteData.toEntity()
                result shouldBe instanceOf(NoteEntity::class)
                result.content shouldBe null
                result.title shouldBe null
                result.createdDate shouldNotBe null
                result.lastUpdateDate shouldNotBe null
            }
        }
        When("Mapping valid data from NoteEntity to NoteData") {
            val content = "content string value1234567890"
            val noteEntity =
                NoteEntity(
                    title = "title string value",
                    content = content,
                    createdDate = Date(),
                    lastUpdateDate = Date()
                )
            Then("Mapper should return a valid NoteData object") {
                val result = noteEntity.toData()
                result shouldBe instanceOf(NoteData::class)
                result.noteId shouldBe null
                result.content shouldBe content
            }
        }
        When("NoteEntity is null") {
            val nullNoteEntity: NoteEntity? = null
            Then("Mapper should return null") {
                nullNoteEntity?.toData() shouldBe null
            }
        }
    }
})