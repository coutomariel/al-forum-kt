package br.com.alura.forum.api.controller

import br.com.alura.forum.domain.StatusTopic
import br.com.alura.forum.domain.Topic
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UpdateTopicRequest(
    @field:NotBlank @field:Size(max = 30) val title: String,
    @field:NotBlank val message: String,
    @field:NotNull val status: StatusTopic,
) {
    fun toModel(topic: Topic): Topic {
        return Topic(
            id = topic.id,
            title = title,
            message = message,
            createdAt = topic.createdAt,
            course = topic.course,
            author = topic.author,
            status = status,
            answers = topic.answers
        )
    }
}

