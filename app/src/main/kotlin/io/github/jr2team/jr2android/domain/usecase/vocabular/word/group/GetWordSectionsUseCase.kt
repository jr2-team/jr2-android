package io.github.jr2team.jr2android.domain.usecase.vocabular.word.group

import io.github.jr2team.jr2android.data.repository.GroupDbRepository
import io.github.jr2team.jr2android.data.repository.SectionDbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetWordSectionsUseCase(
    private val sectionRepository: SectionDbRepository = SectionDbRepository(),
    private val groupRepository: GroupDbRepository = GroupDbRepository()
) {
    suspend fun execute() = withContext(Dispatchers.IO) {
        val sections = sectionRepository.getSectionsWithGroups()
        sections.forEach { section ->
            section.groups.forEach { wordGroup ->
                wordGroup.itemsCount = groupRepository.getItemsInGroup(wordGroup.id)
            }
        }
        return@withContext sections
    }
}