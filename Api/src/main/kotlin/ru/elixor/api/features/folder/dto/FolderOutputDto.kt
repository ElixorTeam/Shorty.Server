package ru.elixor.api.features.folder.dto

import ru.elixor.api.entities.folder.FolderEntity
import java.util.*

class FolderOutputDto (
    val uid: UUID,
    val title: String,
)

fun FolderEntity.toDto() = FolderOutputDto(
    uid = uid!!,
    title = title
)