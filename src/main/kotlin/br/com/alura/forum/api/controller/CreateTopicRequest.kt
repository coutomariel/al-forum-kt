package br.com.alura.forum.api.controller

import br.com.alura.forum.api.service.TopicService
import br.com.alura.forum.domain.Course
import br.com.alura.forum.domain.Topic
import br.com.alura.forum.domain.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateTopicRequest(
    @field:NotBlank @field:Size(max = 30) val title: String,
    @field:NotBlank val message: String,
    @field:NotNull val idAuthor: Long,
    @field:NotNull val idCourse: Long
) {
    fun toModel(course: Course, author: User): Topic{
        return Topic(
            id = getNextId(),
            title = title,
            message = message,
            course = course,
            author = author
        )
    }

    private fun getNextId() = TopicService.nextId++
}
